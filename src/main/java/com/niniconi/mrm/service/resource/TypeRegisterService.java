package com.niniconi.mrm.service.resource;

import com.niniconi.mrm.entity.enumeration.MessageStatus;
import com.niniconi.plugin.api.res.FieldEntity;
import com.niniconi.mrm.entity.MessageEntity;
import com.niniconi.mrm.mapper.DatabaseUtilMapper;
import com.niniconi.mrm.mapper.resource.ResTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class TypeRegisterService {
    @Resource
    ResTypeMapper resType;
    @Resource
    DatabaseUtilMapper databaseUtil;
    public MessageEntity register(String label, List<FieldEntity> list){
        try {
            resType.add(label.toUpperCase());
            databaseUtil.createResourceTable(label.toLowerCase(),list);
        }catch (Exception e){
            e.printStackTrace();
            return new MessageEntity(MessageStatus.FAILED,"failed to register Type");
        }
        log.info("register a new type:"+label);
        return new MessageEntity(MessageStatus.SUCCESS,"success to register type");
    }
}
