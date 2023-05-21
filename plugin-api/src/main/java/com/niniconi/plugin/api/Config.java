package com.niniconi.plugin.api;

/**
 * 负责插件的配置管理
 */
public interface Config {
    String getGlobalValue(String key);
    String getUserValue(int uid,String key);
    /**
    * 添加专属于插件的配置项目
    * @param key 键
    * @param value 配置的值
    */
    void addProperty(String key,String value);
}
