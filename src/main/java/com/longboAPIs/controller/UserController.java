package com.longboAPIs.controller;

import com.longboAPIs.entity.User;
import com.longboAPIs.service.UserAuthoritiesService;
import com.longboAPIs.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/userManagement")
public class UserController {

    @Autowired
    UserManagementService userManagementService;
    @Autowired
    UserAuthoritiesService userAuthoritiesService;

    @GetMapping("/user")
    public String getUser(Model model) {
        model.addAttribute("modifyUser", new User());  //修改用户实体
        model.addAttribute("addUser",new User());  //新增用户实体
        return "userManagement/user";
    }

    @GetMapping("/user/getAll")
    @ResponseBody
    public String getAllUsers(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,@RequestParam("sort") String sort){
        return userManagementService.getAllUsers(pageNumber,pageSize,sort).toString();
    }

    @PostMapping("user/add")
    public String addUser(@ModelAttribute User addUser){
        userManagementService.addUser(addUser);
        return "redirect:/userManagement/user";
    }


    @PostMapping("user/deleteUserByid")
    public String deleteUserByid(@RequestParam("id") int id){
        userManagementService.deleteUserByid(id);
        return "redirect:/userManagement/user";
    }

    @PostMapping("user/modify")
    public String modifyUser(@ModelAttribute User modifyUser){
        userManagementService.modifyUser(modifyUser);
        userAuthoritiesService.modifyAuthorities(modifyUser);
        return "redirect:/userManagement/user";
    }

}
