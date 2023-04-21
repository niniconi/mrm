package com.niniconi.mrm.servlet.console;

import com.niniconi.mrm.entity.MessageEntity;
import com.niniconi.mrm.entity.enumeration.MessageStatus;
import com.niniconi.mrm.mapper.config.GlobalConfig;
import com.niniconi.mrm.mapper.config.UserConfig;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/console")
public class ConfigRest {
    @Resource
    public GlobalConfig globalConfig;
    @Resource
    public UserConfig userConfig;

    @PostMapping("/set/config/{uid}")
    public MessageEntity setConfig(@PathVariable int uid, @RequestParam(name = "key")String key,@RequestParam(name = "value")String value){
        try {
            userConfig.setProperty(uid,key,value);
        }catch (Exception e){
            return new MessageEntity(MessageStatus.FAILED,"set failed");
        }
        return new MessageEntity(MessageStatus.SUCCESS,"set success");
    }
    @PostMapping("/set/config")
    public MessageEntity setConfig(@RequestParam(name = "key")String key,@RequestParam(name = "value")String value){
        try {
            globalConfig.setProperty(key, value);
        }catch (Exception e){
            return new MessageEntity(MessageStatus.FAILED,"set failed");
        }
        return new MessageEntity(MessageStatus.SUCCESS,"set success");
    }
}
