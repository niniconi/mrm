package com.niniconi.mrm.mapper.config;

import org.apache.ibatis.annotations.Param;

public interface UserConfig {
    void setProperty(@Param("uid") int uid,@Param("key") String key,@Param("value") String value);
}
