package com.niniconi.mrm.mapper;

import com.niniconi.mrm.entity.FileEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilesMapper {

    /**
     * get the record count
     * @return int of count
     */
    int count();

    /**
     * 获取文件或路径的字符串
     * @param fid ID
     * @return path
     * @author niniconi
     */
    String getPath(@Param("fid") String fid);

    void deleteFile(@Param("fid") String fid);
    void addFile(@Param("fid") String fid,@Param("path") String path);

    List<FileEntity> saveAllFiles();
}
