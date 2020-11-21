package com.wzp.blog.controller;

import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.service.NoteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/18 14:41
 * @description
 */
@RestController
public class MoveNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("note/moveNote.do")
    public NoteResult execute(String noteId, String bookId){
        NoteResult noteResult = noteService.moveNote(noteId, bookId);
        return noteResult;
    }
}
