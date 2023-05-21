package com.niniconi.mrm.mapper.resource;

import org.apache.ibatis.annotations.Param;

public interface ResTypeMapper {
    void add(@Param("type") String type);
    void del(@Param("type") String type);
}
