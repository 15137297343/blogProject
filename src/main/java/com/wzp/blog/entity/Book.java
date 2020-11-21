package com.wzp.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/12 17:47
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private  String cn_notebook_id;
    private  String cn_user_id;
    private  String cn_notebook_type_id;
    private  String cn_notebook_name;
    private  String cn_notebook_desc;
    private Timestamp cn_notebook_createtime;
}
