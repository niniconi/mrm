package com.niniconi.mrm.mapper.resource;

import com.niniconi.mrm.entity.resource.MusicEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MusicMapper{
    /**
     * get the record count
     * @return int of count
     */
    int count();

    void createMusic(@Param("rid")String rid,@Param("mv") String mv);
    MusicEntity getMusic(@Param("rid") String rid);

    List<MusicEntity> getAllMusic(int limit, int offset);

    List<MusicEntity> saveAllMusic();
    void delete(@Param("rid")String rid);
}
