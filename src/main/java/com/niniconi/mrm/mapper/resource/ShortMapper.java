package com.niniconi.mrm.mapper.resource;

import com.niniconi.mrm.entity.resource.ShortEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShortMapper{
    /**
     * get the record count
     * @return int of count
     */
    int count();

    void createShort(@Param("rid") String rid);
    ShortEntity getShort(@Param("rid") String rid);
    List<ShortEntity> getAllShort(int limit, int offset);

    void delete(@Param("rid")String rid);
}
