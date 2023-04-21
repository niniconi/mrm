package com.niniconi.mrm.plugin;

import com.niniconi.mrm.entity.MessageEntity;
import com.niniconi.mrm.entity.PluginEntity;
import com.niniconi.mrm.entity.enumeration.MessageStatus;
import com.niniconi.mrm.mapper.PluginMapper;
import com.niniconi.mrm.mapper.config.Config;
import com.niniconi.mrm.plugin.api.MrmApi;
import com.niniconi.mrm.service.files.FileService;
import com.niniconi.plugin.api.Plugin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Service
@Slf4j
public class PluginService {

    @Resource
    public PluginMapper plugin;
    @Resource
    public FileService file;
    @Resource
    public Config config;

    private Properties getProperties(JarFile jarFile) throws IOException {
        JarEntry entry = jarFile.getJarEntry("config.properties");
        InputStream inputStream = jarFile.getInputStream(entry);
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    private BufferedImage getLogo(JarFile jarFile) throws IOException {
        InputStream inputStream = jarFile.getInputStream(jarFile.getJarEntry("logo.png"));
        return ImageIO.read(inputStream);
    }

    private void load(String fid) throws ClassNotFoundException, IOException {
        String jarPath = file.getPath(fid);
        ClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file:"+jarPath)});
        JarFile jarFile = new JarFile(jarPath);

        Properties properties = getProperties(jarFile);

        String pluginPackageName = properties.getProperty("plugin-package-name");
        String pluginName = properties.getProperty("plugin-name");
        String pluginDescription = properties.getProperty("plugin-description");
        String pluginVersion = properties.getProperty("plugin-version");
        if(plugin.exist(pluginPackageName) == 0) {
            BufferedImage logo = getLogo(jarFile);//TODO add database
            plugin.addPlugin(pluginPackageName, fid , pluginName);
        }
        Class<?> classObj = classLoader.loadClass(pluginPackageName);
        try {
            Plugin plugin = (Plugin) classObj.newInstance();
            plugin.init(new MrmApi(config));
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        log.info("load plugin["+pluginPackageName+"] success");
    }

    public MessageEntity installPlugin(MultipartFile jar){
        String fid = file.addFile(".plugin",jar);
        try {
            load(fid);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            file.deleteFile(fid);
            log.error("plugin install failed");
            return new MessageEntity(MessageStatus.FAILED,"install plugin failed");
        }
        log.info("plugin install success");
        return new MessageEntity(MessageStatus.SUCCESS,"install plugin success");
    }

    public void loadInstalledPlugins(){
        List<PluginEntity> plugins = plugin.getAllPlugin();
        for (PluginEntity plugin:plugins) {
            try {
                load(plugin.getJarPath());
            } catch (ClassNotFoundException | IOException e) {
                log.error("load plugin "+plugin.getClassName()+"failed");
                e.printStackTrace();
                continue;
            }
            log.info("load plugin"+plugin.getClassName()+" success");
        }
    }

    public List<PluginEntity> installedPlugins(){
        return plugin.getAllPlugin();
    }

}
