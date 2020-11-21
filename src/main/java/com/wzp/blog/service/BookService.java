package com.wzp.blog.service;

import com.wzp.blog.entity.NoteResult;

import java.util.List;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/12 17:53
 * @description
 */
public interface BookService {

    /*根据userId查询所有笔记本*/
    NoteResult findBooksByUserId(String userId);
    /*添加笔记本操作*/
    NoteResult addNotebook(String cn_user_id,String cn_notebook_name);

}
