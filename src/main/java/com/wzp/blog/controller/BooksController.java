package com.wzp.blog.controller;
import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/12 18:01
 * @description
 */
@RestController
public class BooksController {
    @Resource
    private BookService bookService;
    /*显示笔记本列表请求*/
    @RequestMapping("book/books.do")
    public NoteResult execute(String userId){
        NoteResult booksByUserId = bookService.findBooksByUserId(userId);
        return booksByUserId;
    }
}
