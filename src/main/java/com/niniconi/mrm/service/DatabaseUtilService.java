package com.niniconi.mrm.service;

import com.niniconi.mrm.mapper.DatabaseUtilMapper;
import com.niniconi.mrm.mapper.FilesMapper;
import com.niniconi.mrm.mapper.config.Config;
import com.niniconi.mrm.mapper.config.GlobalConfig;
import com.niniconi.mrm.mapper.resource.ResTypeMapper;
import com.niniconi.mrm.mapper.resource.ResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DatabaseUtilService {

    @Resource
    public DatabaseUtilMapper databaseUtil;
    @Resource
    public Config config;
    @Resource
    public GlobalConfig globalConfig;
    @Resource
    public ResourceMapper res;
    @Resource
    public FilesMapper filesMapper;
    @Resource
    public ResTypeMapper resType;

    /**
     * 判断表是否存在
     *
     * @param tableName 表名
     * @return boolean 结果（true=存在，false=不存在）
     * @author niniconi
     */
    public Boolean existsTable(String tableName) {
        Integer tableNumber =  databaseUtil.existsTable(tableName);
        return tableNumber > 0;
    }


    public void createTableTag(){databaseUtil.createTableTag();}
    public void createTableUsers(){databaseUtil.createTableUsers();}
    public void createTableFiles(){databaseUtil.createTableFiles();}
    public void createTableGlobalConfig(){databaseUtil.createTableGlobalConfig();}
    public void createTableUserConfig(){databaseUtil.createTableUserConfig();}

    /**
     * 建立资源表
     * illustration
     * comics
     * cartoon
     * books
     * music
     * @author niniconi
     */
    public void createTableIllustration(){
        databaseUtil.createTableIllustration();
    }
    public void createTableComics(){
        databaseUtil.createTableComics();
    }
    public void createTableCartoon(){
        databaseUtil.createTableCartoon();
    }
    public void createTableBooks(){
        databaseUtil.createTableBooks();
    }
    public void createTableMusic(){
        databaseUtil.createTableMusic();
    }
    public void createTableShort(){
        databaseUtil.createTableShort();
    }

    /**
     * create table Resource
     */
    public void createTableResource() {
        if(!existsTable("files"))createTableFiles();
        databaseUtil.createTableResource();
    }

    public void createTablePlugins(){
        databaseUtil.createTablePlugins();
    }

    /**
     * delete all data table
     */
//    public void dropAllTable(){databaseUtil.dropAllTable();}

    /**
     * set default value for config
     */
    public void setGlobalConfig(){
        globalConfig.addProperty("content-path","/home/arch/test");
        File content = new File("/home/arch/test");
        if(!content.exists() || content.isFile()){
            content.mkdirs();
        }
        globalConfig.addProperty("default-cover",".data/default.jpg");
        globalConfig.addProperty("plugin-content",".plugin");
    }

    public Object saveDatabase(){
        Map<String,Object> ret = new HashMap<>();
        ret.put("resource",res.saveAllResource());
        ret.put("files",filesMapper.saveAllFiles());
        return ret;
    }

    public void initResourceTypeList(){
        databaseUtil.createResourceTypeList();
        resType.add("MUSIC");
        resType.add("COMICS");
        resType.add("CARTOON");
        resType.add("ILLUSTRATION");
        resType.add("BOOKS");
        resType.add("SHORT");
    }
}
