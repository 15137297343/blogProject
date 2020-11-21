package com.wzp.blog.entity;

import lombok.Data;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/11 19:16
 * @description
 */
@Data
public class NoteResult {
    private Integer status;
    private String msg;
    private Object data;
}
