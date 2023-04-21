package com.niniconi.mrm.servlet;

import com.niniconi.mrm.service.InfoService;
import com.niniconi.mrm.service.files.FileService;
import com.niniconi.mrm.service.resource.ResourceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/info")
public class Info {
    @Resource
    public FileService fileService;
    @Resource
    public ResourceService resourceService;
    @Resource
    public InfoService infoService;
    @GetMapping("/HdInfo")
    public Map<String, FileService.HdInfo> getHdInfo(){
        return fileService.getHdInfo();
    }
    @GetMapping("/ResourceCount")
    public long getResourceInfo(){
        return resourceService.getResourceCount();
    }
    @GetMapping("/ResourceSizeCount")
    public long getResourceSizeCount(){
        return resourceService.getResourceSizeCount();
    }
}
