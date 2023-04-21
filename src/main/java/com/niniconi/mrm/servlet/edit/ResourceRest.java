package com.niniconi.mrm.servlet.edit;

import com.niniconi.mrm.entity.MessageEntity;
import com.niniconi.mrm.service.resource.ResourceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/resource/")
public class ResourceRest {
    @Resource
    public ResourceService resourceService;
    @GetMapping("/delete/{rid}")
    public MessageEntity delete(@PathVariable String rid){
        return resourceService.deleteResource(rid);
    }
    @GetMapping("/rename/{rid}")
    public MessageEntity rename(@PathVariable String rid, @RequestParam(name = "name")String name){
        return resourceService.renameResource(rid,name);
    }
    @GetMapping("/editdescription/{rid}")
    public MessageEntity editDescription(@PathVariable String rid,@RequestParam(name = "description")String description){
        return resourceService.editResource(rid,description);
    }
}
