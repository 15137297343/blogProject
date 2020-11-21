package com.wzp.blog.entity;

import lombok.*;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/11 18:42
 * @description
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private  String cn_user_id;
    private  String cn_user_name;
    private  String cn_user_password;
    private  String cn_user_token;
    private  String cn_user_nick;
}
