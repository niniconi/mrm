package com.niniconi.mrm.mapper.resource;

import com.niniconi.mrm.entity.resource.IllustrationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IllustrationMapper{
    /**
     * get the record count
     * @return int of count
     */
    int count();

    void createIllustration(@Param("rid")String rid);

    IllustrationEntity getIllustration(@Param("rid")String rid);

    List<IllustrationEntity> getAllIllustration(int limit , int offset);
    void delete(@Param("rid")String rid);
}
