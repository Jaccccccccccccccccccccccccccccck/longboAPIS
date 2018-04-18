package com.longboAPIs.controller;

import com.longboAPIs.service.UserAuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
