package com.longboAPIs.dao.user;

import com.longboAPIs.entity.TableAuthority;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface UserAuthoritiesDao {
    @Select(value = "Select table_name,table_authority,table_info from apis where enabled =1 order by table_name")
    @ResultType(TableAuthority.class)
    List<TableAuthority> getAllTableAuthorities();

    @Select(value = "select table_name,table_authority,table_info from apis,(select distinct authorities.authority from users,authorities where users.userName = authorities.username and users.userName = #{username}) auth where apis.table_authority=auth.authority order by table_authority")
    @ResultType(TableAuthority.class)
    List<TableAuthority> getTableAuthoritiesDetailByUsername(String username);

    @Select(value = "select table_authority as authority, case when authority is not null then 1 else 0 end as isAuth from apis left join (select distinct authorities.authority from users,authorities where users.userName = authorities.username and users.userName = #{username}) auth on apis.table_authority=auth.authority order by table_authority")
    List<HashMap<String,Integer>> getAuthoritiesByUsername(String username);

    @Select(value = "select count(1)  from users,authorities where users.userName = authorities.username and users.userName = #{username} and authorities.authority =#{auth_name}")
    int isAuthUser(@Param("username")String username, @Param("auth_name") String auth_name);

    @Delete(value = "delete from authorities where username= #{username} and authority =#{authority}")
    void deleteAuthByUsernameAndAuth(@Param("username")String username,@Param("authority")String authority);

    @Insert(value = "insert into authorities(username,authority) values(#{username} ,#{authority})")
    void addAuthByUsernameAndAuth(@Param("username")String username,@Param("authority")String authority);


}
