package com.niniconi.mrm.servlet.transfer;

import com.niniconi.mrm.service.files.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("error")
public class StaticTransferRest {
    @Resource
    public FileService fileService;
    @GetMapping("img/404.png")
    public void img404(HttpServletResponse response){
        fileService.readErrorImage(response,404);
    }
}
