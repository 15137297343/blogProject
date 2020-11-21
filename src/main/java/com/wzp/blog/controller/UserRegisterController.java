package com.wzp.blog.controller;

import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/12 15:26
 * @description
 */
@RestController
public class UserRegisterController {
    @Resource
    private UserService userService;
    /*用户注册*/
    @RequestMapping("user/register.do")
    public NoteResult register(String name,String password,String nick){
        NoteResult result = userService.register(name, password, nick);
        return result;
    }
}
