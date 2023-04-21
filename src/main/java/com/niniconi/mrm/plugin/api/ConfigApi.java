package com.niniconi.mrm.plugin.api;

import com.niniconi.plugin.api.Config;

public class ConfigApi implements Config {
    private final com.niniconi.mrm.mapper.config.Config config;

    public ConfigApi(com.niniconi.mrm.mapper.config.Config config) {
        this.config = config;
    }

    @Override
    public String getGlobalValue(String key) {
        return config.getProperty(-1,key);
    }

    @Override
    public String getUserValue(int uid, String key) {
        return config.getProperty(uid,key);
    }

    //@Override
    public void addProperty(String key,String value){
        //TODO
    }
}
