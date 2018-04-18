package com.longboAPIs.controller;

import com.github.pagehelper.PageInfo;
import com.longboAPIs.service.DBContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class DBContentController {
    @Autowired
    private DBContentService dbContentService;

    @GetMapping("jlzx/{tableName}/{pageNum}/{pageSize}")
    public PageInfo<HashMap> listAll(@PathVariable("tableName") String tableName, @PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return dbContentService.selectByPage(tableName, pageNum,pageSize);
    }
}
