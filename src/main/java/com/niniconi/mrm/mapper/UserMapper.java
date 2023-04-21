package com.niniconi.mrm.mapper;

import com.niniconi.mrm.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * get the record count
     * @return int of count
     */
    int count();

    int existsByUid(@Param("uid")String uid);
    int existsByName(@Param("username")String username);

    /**
     * createAccount
     * @param username 长度 255
     * @param password 长度 255
     * @param permission 范围 1 Byte
     */
    void createAccount(@Param("username") String username,@Param("password") String password,@Param("permission") int permission);

    void deleteAccountByUsername(@Param("username") String username);
    void deleteAccountByUID(@Param("uid") long id);

    /**
     * 用于登陆,同时返回账户权限
     * @param username username
     * @param password password
     * @return the permission of this account
     */
    char accountVerify(@Param("username") String username,@Param("password") String password);
    char getUserPermission(@Param("uid") long id);
    List<UserEntity> listUsers(@Param("limit") int limit, @Param("offset") int offset);
    UserEntity selectUser(@Param("uid")String uid);
    List<UserEntity> selectUsersByPermission(@Param("limit") int limit, @Param("offset") int offset, @Param("permission") int permission);

    /**
     * 修改权限
     * @param uid user ID
     * @param permission 要改为的权限
     */
    void modifyPermission(@Param("uid")long uid,@Param("permission") int permission);
}
