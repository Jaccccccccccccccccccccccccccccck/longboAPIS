package com.longboAPIs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.longboAPIs.entity.User;

import java.util.List;

public interface UserManagementService {
    String getAllUsers(int currentPage, int pageSize , String sort);
    void addUser(User user);
    void modifyUser(User user);
    String deleteUserByid(int id);

}
