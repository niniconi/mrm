package com.niniconi.mrm.plugin;

import com.niniconi.mrm.entity.MessageEntity;
import com.niniconi.mrm.entity.PluginEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PluginRest {

    @Resource
    public PluginService plugin;

    @PostMapping("/plugin/install")
    public MessageEntity install(@RequestParam(name = "jar") MultipartFile jarFile){
        return plugin.installPlugin(jarFile);
    }

    @GetMapping("/plugin/installed")
    public List<PluginEntity> installed(){
        return plugin.installedPlugins();
    }
}
