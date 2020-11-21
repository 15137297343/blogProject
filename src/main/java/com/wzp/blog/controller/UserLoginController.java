package com.wzp.blog.controller;

import com.wzp.blog.dao.UserDao;
import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/12 9:26
 * @description
 */
@RestController
public class UserLoginController {
    @Resource
    private UserService userService;
    /*用户登录*/
    @RequestMapping("user/login.do")
    public NoteResult login(String name,String password){
        NoteResult result = userService.login(name, password);
        return result;

    }
}
