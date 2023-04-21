package com.niniconi.mrm.mapper;

import org.apache.ibatis.annotations.Param;

public interface DatabaseUtilMapper {
    /**
     * 判断表是否存在
     *
     * @param tableName 表名称
     * @return 结果
     * @author niniconi
     */
    Integer existsTable(@Param("tableName") String tableName);

    void createTableFiles();
    void createTableTag();
    void createTableUsers();
    void createTableGlobalConfig();
    void createTableUserConfig();

    /**
     * 建立资源表
     * illustration
     * comics
     * cartoon
     * books
     * music
     * short
     * @author niniconi
     */
    void createTableIllustration();
    void createTableComics();
    void createTableCartoon();
    void createTableBooks();
    void createTableMusic();
    void createTableShort();

    /**
     * the table of resource
     */
    void createTableResource();

    void createTablePlugins();
}
