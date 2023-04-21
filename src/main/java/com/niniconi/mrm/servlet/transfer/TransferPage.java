package com.niniconi.mrm.servlet.transfer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransferPage {
    @GetMapping("/uploadPage")
    public String uploadPage(@RequestParam(name = "status",required = false,defaultValue = "0") int status, Model model){
        return "/upload.html";
    }
}
