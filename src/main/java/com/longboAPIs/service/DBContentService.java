package com.longboAPIs.service;

import com.github.pagehelper.PageInfo;
import java.util.HashMap;

public interface DBContentService {
    public PageInfo<HashMap> selectByPage( String tableName, int currentPage, int pageSize);
}
