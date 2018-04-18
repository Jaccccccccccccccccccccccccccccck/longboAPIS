package com.longboAPIs.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longboAPIs.dao.dbContent.DBContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("DBContentService")
public class DBContentServiceImpl implements DBContentService {
    @Autowired
    private DBContentDao dbContentDao;

    public PageInfo<HashMap> selectByPage( String tableName, int currentPage, int pageSize){

            return new PageInfo<>(dbContentDao.getAll(tableName, currentPage, pageSize));
    }
}
