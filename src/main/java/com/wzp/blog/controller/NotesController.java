package com.wzp.blog.controller;

import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/13 21:38
 * @description
 */
@RestController
public class NotesController {
    @Resource
    private NoteService noteService;
    /*根据笔记本Id查询笔记列表信息*/
    @RequestMapping("note/notes.do")
    public NoteResult execute(String bookId){
        NoteResult notes = noteService.findNoteByBookId(bookId);
        return notes;
    }
}
