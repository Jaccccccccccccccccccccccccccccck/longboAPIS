package com.longboAPIs.controller;

import com.longboAPIs.entity.User;
import com.longboAPIs.service.UserAuthoritiesService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/userAuthoritiesManagement")
public class UserAuthoritiesController {

    @Autowired
    UserAuthoritiesService userAuthoritiesService;


    @PostMapping("/getAuthoritiesByUsername")
    @ResponseBody
    public String getApiListPage(@RequestParam("username") String username) {
//        System.out.print(userAuthoritiesService.getAuthoritiesByUsername(username));
        return JSONArray.fromObject(userAuthoritiesService.getAuthoritiesByUsername(username)).toString();
    }


}
