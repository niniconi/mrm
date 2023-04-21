package com.niniconi.mrm.mapper;

import com.niniconi.mrm.entity.PluginEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PluginMapper {
    void addPlugin(@Param("className") String className,@Param("jarPath")String jarPath,@Param("name")String name);
    void deletePlugin(@Param("className")String className);
    List<PluginEntity> getAllPlugin();

    Integer exist(@Param("className")String className);
}
