package com.longboAPIs.controller;

import com.longboAPIs.service.UserAuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class InstructionController {

    @Autowired
    UserAuthoritiesService userAuthoritiesService;

    @GetMapping("/instruction/main")
    public String getInstructionPage() {
        return "instruction/main";
    }

    @GetMapping("/instruction/apiInfo")
    public String getApiInfoPage() {
        return "instruction/apiInfo";
    }

    @GetMapping("/instruction/apiList")
    public String getApiListPage(Model model) {
        model.addAttribute("table_authorities_List",userAuthoritiesService.getAllTableAuthorities());
        return "instruction/apiList";
    }

    @GetMapping("/instruction/apiInstruction")
    public String getApiInstruction() {
        return "instruction/apiInstruction";
    }

    @GetMapping("/instruction/howToUse")
    public String getApiUsage() {
        return "instruction/howToUse";
    }

    @GetMapping("/instruction/myAuthorities")
    public String getMyAuthorities(Model model,HttpSession session) {

        model.addAttribute("user_authorities_list",userAuthoritiesService.getTableAuthoritiesDetailByUsername(((UserDetails)((SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getPrincipal()).getUsername()));
        return "instruction/myAuthorities";
    }
}
