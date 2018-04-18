package com.longboAPIs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.longboAPIs.dao.user.UserManagementDao;
import com.longboAPIs.entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserManagemetService")
public class UserManagementServiceImpl implements UserManagementService{

    @Autowired
    UserManagementDao userManagementDao;

    @Override
    public String getAllUsers(int currentPage, int pageSize , String sort ){
        return JSONObject.fromObject( "{\"total\":\""+userManagementDao.getAllUsersCount()+"\",\"rows\":"+JSONArray.fromObject(userManagementDao.getAllUsers(sort,currentPage,pageSize))+"}").toString();
    }

    @Override
    public void addUser(User user){
        userManagementDao.addUser(user);
    };

    @Override
    public void modifyUser(User user){
        userManagementDao.modifyUser(user);
    };

    @Override
    public String deleteUserByid(int id){
        userManagementDao.deleteUserByid(id);
        return "success";
    }
}
