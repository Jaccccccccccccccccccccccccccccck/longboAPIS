package com.longboAPIs.service;

import com.longboAPIs.dao.user.UserAuthoritiesDao;
import com.longboAPIs.entity.TableAuthority;
import com.longboAPIs.entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("UserAuthoritiesService")
public class UserAuthoritiesServiceImpl implements UserAuthoritiesService {

    @Autowired
    UserAuthoritiesDao userAuthoritiesDao;

    @Override
    public List<TableAuthority> getAllTableAuthorities(){
        return userAuthoritiesDao.getAllTableAuthorities();
    }

    @Override
    public  List<TableAuthority> getTableAuthoritiesDetailByUsername(String username){
        return userAuthoritiesDao.getTableAuthoritiesDetailByUsername(username);
    }

    @Override
    public List<HashMap<String,Integer>> getAuthoritiesByUsername(String username){
        return userAuthoritiesDao.getAuthoritiesByUsername(username);
    };

    @Override
    public void modifyAuthorities(User user){
        JSONArray auths = JSONArray.fromObject(user.getAuthorities());
        for(int i=0;i<auths.size();i++){
            String authName  = auths.getJSONObject(i).get("authority").toString();
            int isAuth = (int)auths.getJSONObject(i).get("isAuth");
            if(0 == isAuth && userAuthoritiesDao.isAuthUser(user.getUsername(),authName)>=1){
                System.out.println("romove authority");
                userAuthoritiesDao.deleteAuthByUsernameAndAuth(user.getUsername(),auths.getJSONObject(i).get("authority").toString());
            }else if(1 == isAuth && userAuthoritiesDao.isAuthUser(user.getUsername(),authName)<1){
                userAuthoritiesDao.addAuthByUsernameAndAuth(user.getUsername(),auths.getJSONObject(i).get("authority").toString());
                System.out.println("add authority");
            }else {
            }
        }
    };

}
