package com.wzp.blog.controller;

import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/17 9:42
 * @description
 */
@RestController
public class AddNotebookController {
    @Resource
    private BookService bookService;
    @RequestMapping("book/addBook.do")
    public NoteResult execute(String cn_user_id, String cn_notebook_name){
        NoteResult noteResult = bookService.addNotebook(cn_user_id, cn_notebook_name);
        return noteResult;
    }
}
