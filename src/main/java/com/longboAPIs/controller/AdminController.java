package com.longboAPIs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
    @GetMapping({"/login","/"})
    public String getLoginPage() {
        return "admin/login";
    }

    @GetMapping("/admin/main")
    public String getMainPage(){
        return "admin/main";
    }
}
