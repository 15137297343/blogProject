package com.wzp.blog.dao;

import com.wzp.blog.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/11 18:59
 * @description
 */
public interface UserDao {
    @Select("select * from cn_user where cn_user_name=#{name}")
    User findByName(String name);
    @Insert("insert into cn_user values(#{cn_user_id},#{cn_user_name},#{cn_user_password},#{cn_user_token},#{cn_user_nick})")
    void addUser(User user);
}
