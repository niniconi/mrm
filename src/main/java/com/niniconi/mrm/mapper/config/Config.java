package com.niniconi.mrm.mapper.config;

import com.niniconi.mrm.entity.Property;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Config {
    String getProperty(@Param("uid") int uid,@Param("key") String key);

    List<Property> list(@Param("uid") int uid, @Param("offset") int offset, @Param("limit") int limit);
}
