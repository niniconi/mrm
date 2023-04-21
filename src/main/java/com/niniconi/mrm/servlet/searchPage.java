package com.niniconi.mrm.servlet;

import com.niniconi.mrm.entity.resource.ResourceEntity;
import com.niniconi.mrm.service.resource.InquireService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Slf4j
public class searchPage {
    @Resource
    public InquireService inquireService;

    /**
     * 搜索
     * @param key 搜索的规则
     *            key：资源的名字
     * @param page
     * @return
     */
    @GetMapping("/search")
    public String search(@RequestParam(name = "key") String key, @RequestParam(name = "page",required = false,defaultValue = "0") int page, Model model){
        List<ResourceEntity> res = inquireService.nameSearch(key,page,20);
        model.addAttribute("resources",res);
        return "search.html";
    }
}
