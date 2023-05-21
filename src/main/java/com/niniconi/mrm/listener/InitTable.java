package com.niniconi.mrm.listener;

import com.niniconi.mrm.plugin.PluginService;
import com.niniconi.mrm.service.DatabaseUtilService;
import com.niniconi.mrm.service.UserService;
import com.niniconi.mrm.service.resource.TypeRegisterService;
import com.niniconi.plugin.api.res.FieldEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
@Order(0)
public class InitTable implements ApplicationListener<ApplicationReadyEvent> {
    @Resource
    public DatabaseUtilService databaseUtilService;
    @Resource
    public UserService userService;
    @Resource
    public PluginService pluginService;

    @Resource
    TypeRegisterService typeRegisterService;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        //create table if it's exists.
        if(!databaseUtilService.existsTable("resource")){
            databaseUtilService.createTableResource();
            log.info("create table resource");
        }
        if(!databaseUtilService.existsTable("tag")){
            databaseUtilService.createTableTag();
            log.info("create table tag");
        }
        if(!databaseUtilService.existsTable("users")){
            databaseUtilService.createTableUsers();
            log.info("create table users");
        }
        if(!databaseUtilService.existsTable("global_config")){
            databaseUtilService.createTableGlobalConfig();
            databaseUtilService.setGlobalConfig();
            log.info("create table global config");
        }
        if(!databaseUtilService.existsTable("user_config")){
            databaseUtilService.createTableUserConfig();
            log.info("create table user config");
        }
        if(!databaseUtilService.existsTable("illustration_res")){
            databaseUtilService.createTableIllustration();
            log.info("create table illustration_res");
        }
        if(!databaseUtilService.existsTable("comics_res")){
            databaseUtilService.createTableComics();
            log.info("create table comics_res");
        }
        if(!databaseUtilService.existsTable("cartoon_res")){
            databaseUtilService.createTableCartoon();
            log.info("create table cartoon_res");
        }
        if(!databaseUtilService.existsTable("books_res")){
            databaseUtilService.createTableBooks();
            log.info("create table books_res");
        }
        if(!databaseUtilService.existsTable("music_res")){
            databaseUtilService.createTableMusic();
            log.info("create table music_res");
        }
        if(!databaseUtilService.existsTable("short_res")){
            databaseUtilService.createTableShort();
            log.info("create table short_res");
        }
        if(!databaseUtilService.existsTable("plugins")){
            databaseUtilService.createTablePlugins();
            pluginService.init();
            log.info("create table plugins");
        }
        if(!databaseUtilService.existsTable("res_types")){
            databaseUtilService.initResourceTypeList();
            log.info("create table res_types");
        }
        //load plugins
        pluginService.loadInstalledPlugins();

        //TODO
        //create an account root
        //userService.register("root","");
    }
}
