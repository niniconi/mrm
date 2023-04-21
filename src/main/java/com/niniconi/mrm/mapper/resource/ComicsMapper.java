package com.niniconi.mrm.mapper.resource;

import com.niniconi.mrm.entity.resource.ComicsEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComicsMapper{
    /**
     * get the record count
     * @return int of count
     */
    int count();

    void createComics(@Param("rid")String rid);

    ComicsEntity getComics(@Param("rid")String rid);

    List<ComicsEntity> getAllComics(int limit, int offset);
    void delete(@Param("rid")String rid);
}
