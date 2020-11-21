package com.wzp.blog.util;

import com.alibaba.druid.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/11 20:32
 * @description
 * 主键的生成方式和明文的加密方式
 */
public class NoteUtil {
    /*生成主键UUID*/
    public static String creatId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-","");
    }
    /* 使用MD5*/
    public static String md5(String src) throws NoSuchAlgorithmException {
        /*将字符串信息采用md5处理*/
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(src.getBytes());
        /*将MD5处理结果利用Base64转成字符串*/
        String s = Base64.byteArrayToBase64(digest);
        return s;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(creatId());
        System.out.println(md5("123456"));
    }
}
