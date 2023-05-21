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

/**
 * 用于插件向服务器注册和管理资源
 */
@Service
@Slf4j
public class TypeRegisterService {
    @Resource
    ResTypeMapper resType;
    @Resource
    DatabaseUtilMapper databaseUtil;

    /**
     * 向服务器注册资源类型
     * 这个过程会生成一个用于存储资源信息的表,还会记录下这个类型名
     * 类型名不区分大小写
     * @param label 定义类型名称
     * @param list 注册时的字段
     * @return 结果的状态
     */
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
