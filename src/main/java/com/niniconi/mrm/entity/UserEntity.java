package com.niniconi.mrm.entity;

import lombok.Data;

@Data
public class UserEntity {
    long uid;
    String username;
    String password;

    /**
     * 用户的权限，总共有八位
     * <p>
     *     第一位:是否可以修改设置
     * </p>
     */
    char permission;
}
