package com.niniconi.mrm.servlet;

import com.niniconi.mrm.service.DatabaseUtilService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/save")
public class Database {
    @Resource
    public DatabaseUtilService databaseUtilService;
    @GetMapping("/database.json")
    public Object saveDatabase(HttpServletResponse response){
        response.setContentType("application/json");
        return databaseUtilService.saveDatabase();
    }
}
