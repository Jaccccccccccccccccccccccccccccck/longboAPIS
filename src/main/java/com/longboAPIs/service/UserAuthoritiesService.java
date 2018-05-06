package com.longboAPIs.service;

import com.longboAPIs.entity.TableAuthority;
import com.longboAPIs.entity.User;

import java.util.HashMap;
import java.util.List;

public interface UserAuthoritiesService {
    List<TableAuthority> getAllTableAuthorities();
    List<TableAuthority> getTableAuthoritiesDetailByUsername(String username);
    List<HashMap<String,Integer>> getAuthoritiesByUsername(String username);
    void modifyAuthorities(User user);
}
