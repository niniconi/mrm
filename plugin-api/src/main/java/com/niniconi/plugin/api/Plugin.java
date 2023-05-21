package com.niniconi.plugin.api;

/**
 * 用于描述插件的接口
 */
public interface Plugin {
    /**
     * 插件的初始化
     * @param mrm mrm的接口
     */
    void init(Mrm mrm);
}
