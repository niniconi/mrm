package com.niniconi.mrm.service;

import com.niniconi.mrm.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserService{
    @Resource
    public UserMapper userMapper;

    public int getUserCount(){
        return userMapper.count();
    }
    public boolean existsOfUid(String uid){
        return userMapper.existsByUid(uid) == 1;
    }
    public boolean existsOfName(String username){
        return userMapper.existsByName(username) == 1;
    }

    public void logIn(){
    }

    public void register(String username,String password,char permission) {
        userMapper.createAccount(username,password,permission);
    }

    public void signOut(){
    }

    // modifyPermission();
}
