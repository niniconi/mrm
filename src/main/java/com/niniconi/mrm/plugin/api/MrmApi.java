package com.niniconi.mrm.plugin.api;

import com.niniconi.plugin.api.Config;
import com.niniconi.plugin.api.Mrm;
import com.niniconi.plugin.api.Resource;

public class MrmApi implements Mrm {
    private final com.niniconi.mrm.mapper.config.Config config;

    public MrmApi(com.niniconi.mrm.mapper.config.Config config) {
        this.config = config;
    }

    @Override
    public Config config() {
        return new ConfigApi(config);
    }

    @Override
    public Resource resource() {
        return new ResourceApi();
    }
}
