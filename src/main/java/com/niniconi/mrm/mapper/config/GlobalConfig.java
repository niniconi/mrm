package com.niniconi.mrm.mapper.config;

import org.apache.ibatis.annotations.Param;

public interface GlobalConfig {

    /**
     * set the property
     * @param key key
     * @param value value
     */
    void setProperty(@Param("key") String key,@Param("value") String value);

    /**
     * add the property
     * @param key key
     * @param value value
     */
    void addProperty(@Param("key") String key,@Param("value") String value);
}
