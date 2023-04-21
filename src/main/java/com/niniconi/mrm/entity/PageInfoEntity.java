package com.niniconi.mrm.entity;

import lombok.Data;

@Data
public class PageInfoEntity {
    long numberOfPage;
    long index;//this page

    public PageInfoEntity(long numberOfPage, long index) {
        this.numberOfPage = numberOfPage;
        this.index = index;
    }
}
