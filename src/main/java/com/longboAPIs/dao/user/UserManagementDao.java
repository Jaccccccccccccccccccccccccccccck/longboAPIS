package com.longboAPIs.dao.user;
import com.longboAPIs.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface UserManagementDao {

    @Select(value = "Select id,username,password,truename,company,department,position,telephone,emailaddress,enableduntil,qq,wechat,workaddress from users order by #{sort}")
    @ResultType(User.class)
    List<User> getAllUsers(@Param("sort")String sort, @Param("pageNumKey") int pageNum,
                           @Param("pageSizeKey") int pageSize);

    @Select(value = "Select count(*) from users")
    int getAllUsersCount();

    @Insert(value = "insert into users(username,password,truename,emailaddress,telephone,qq,wechat,workaddress,company,department,position,enableduntil) values (" +
            "#{username,jdbcType=VARCHAR}," +
            "#{password,jdbcType=VARCHAR}," +
            "#{truename,jdbcType=VARCHAR}," +
            "#{emailaddress,jdbcType=VARCHAR}," +
            "#{telephone,jdbcType=VARCHAR}," +
            "#{qq,jdbcType=VARCHAR}," +
            "#{wechat,jdbcType=VARCHAR}," +
            "#{workaddress,jdbcType=VARCHAR}," +
            "#{company,jdbcType=VARCHAR}," +
            "#{department,jdbcType=VARCHAR}," +
            "#{position,jdbcType=VARCHAR}," +
            "#{enableduntil}" +
            ")")
    void addUser(User user);

    @Update(value="update users set " +
            "username=#{username,jdbcType=VARCHAR},"+
            "password=#{password,jdbcType=VARCHAR},"+
            "truename=#{truename,jdbcType=VARCHAR},"+
            "emailaddress=#{emailaddress,jdbcType=VARCHAR},"+
            "telephone=#{telephone,jdbcType=VARCHAR},"+
            "qq=#{qq,jdbcType=VARCHAR},"+
            "wechat=#{wechat,jdbcType=VARCHAR},"+
            "workaddress=#{workaddress,jdbcType=VARCHAR},"+
            "company=#{company,jdbcType=VARCHAR},"+
            "department=#{department,jdbcType=VARCHAR},"+
            "position=#{position,jdbcType=VARCHAR},"+
            "enableduntil=#{enableduntil,jdbcType=VARCHAR}"+
            "where id = #{id}"
    )
    void modifyUser(User user);


    @Delete(value = "delete from users where id =#{id}")
    void deleteUserByid(int id);



}
