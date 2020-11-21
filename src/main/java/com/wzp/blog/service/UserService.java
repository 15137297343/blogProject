package com.wzp.blog.service;

import com.wzp.blog.entity.NoteResult;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/11 19:06
 * @description
 */
public interface UserService {
    /*登录操作*/
    NoteResult login(String name,String password);
    /*添加用户操作*/
    NoteResult register(String name,String password,String nick);


}
