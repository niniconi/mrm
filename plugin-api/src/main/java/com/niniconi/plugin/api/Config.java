package com.niniconi.plugin.api;

public interface Config {
    String getGlobalValue(String key);
    String getUserValue(int uid,String key);
    void addProperty(String key,String value);
}
