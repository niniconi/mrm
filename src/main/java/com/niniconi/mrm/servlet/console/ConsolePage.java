package com.niniconi.mrm.servlet.console;

import com.niniconi.mrm.entity.Property;
import com.niniconi.mrm.mapper.config.Config;
import com.niniconi.mrm.mapper.config.GlobalConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/console")
public class ConsolePage {
    @Resource
    public GlobalConfig globalConfig;
    @Resource
    public Config config;
    @GetMapping("")
    public String console(){
       return "admin/console.html" ;
    }
    @GetMapping("/terminal")
    public String terminal(){
        return "admin/terminal.html";
    }

    @GetMapping("/plugin")
    public String plugin(){
        return "admin/plugin.html";
    }

    @GetMapping("/config")
    public String config(Model model,@RequestParam(name = "page",required = false,defaultValue = "0") int page,@RequestParam(name = "limit",required = false,defaultValue = "20")int limit){
        List<Property> configs = config.list(-1,page*limit,limit);
        System.out.println(configs);
        model.addAttribute("configs",configs);
        return "admin/config.html";
    }

    @GetMapping("/config/{uid}")
    public String config(Model model,@PathVariable int uid,@RequestParam(name = "page",required = false,defaultValue = "0") int page,@RequestParam(name = "limit",required = false,defaultValue = "20")int limit){
        List<Property> configs = config.list(uid,page*limit,limit);
        model.addAttribute("configs",configs);
        return "admin/config.html";
    }
}