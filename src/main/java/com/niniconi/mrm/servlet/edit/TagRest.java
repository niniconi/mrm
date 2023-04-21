package com.niniconi.mrm.servlet.edit;

import com.niniconi.mrm.service.resource.TagService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/tag")
public class TagRest {

    @Resource
    TagService tagService;

    @GetMapping("/addTag")
    public boolean addTag(@RequestParam(name = "rid")String rid, @RequestParam(name = "tag") String tagStr) {
        return tagService.createTag(rid, tagService.stringToTag(tagStr));
    }

    @GetMapping("/updateTag/{tagId}")
    public boolean updateTag(@PathVariable String tagId,@RequestParam(name = "tagType") String tagType,@RequestParam(name = "tag") String tag){
        return tagService.updateTag(tagId,tagType,tag);
    }

    @GetMapping("/deleteTag/{tagId}")
    public void deleteTag(@PathVariable String tagId){
        tagService.delete(tagId);
    }
}
