package com.wzp.blog.service;

import com.wzp.blog.dao.UserDao;
import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.entity.User;
import com.wzp.blog.util.NoteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/11 19:22
 * @description
 */
@Transactional
@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;
    @Override
    public NoteResult login(String name, String password) {
        NoteResult noteResult = new NoteResult();
        User user = userDao.findByName(name);

        if (user==null){
            noteResult.setStatus(1);
            noteResult.setMsg("用户名不存在！");
            return noteResult;
        }
        try {
            if(!user.getCn_user_password().equals(NoteUtil.md5(password))){
                noteResult.setStatus(2);
                noteResult.setMsg("密码不正确！");
                return noteResult;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        noteResult.setStatus(0);
        noteResult.setMsg("登录成功！");
        /*屏蔽密码*/
        user.setCn_user_password("");
        noteResult.setData(user);
        return noteResult;
    }

    @Override
    public NoteResult register(String name, String password, String nick) {
        NoteResult result = new NoteResult();
        User byName = userDao.findByName(name);
        if (byName!=null){
            result.setStatus(1);
            result.setMsg("用户名已存在！");
            return result;
        }
        if (byName==null){
            try {
                String md5pwd = NoteUtil.md5(password);
                User user = User.builder().cn_user_id(NoteUtil.creatId()).cn_user_name(name).cn_user_password(md5pwd).cn_user_nick(nick).cn_user_token(null).build();
                userDao.addUser(user);
                result.setStatus(0);
                result.setMsg("注册成功！");
                return result;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
