package com.wzp.blog.controller;

import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.service.NoteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/17 17:19
 * @description
 */
@RestController
public class AddNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("note/addNote.do")
    public NoteResult execute( String userId,String bookId, String noteTitle){
        NoteResult noteResult = noteService.insertNote(userId, bookId,noteTitle );
        return noteResult;
    }
}
