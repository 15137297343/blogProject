package com.wzp.blog.controller;

import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.service.NoteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/17 21:04
 * @description
 */
@RestController
public class DeleteNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("note/deleteNote.do")
    public NoteResult execute(String noteId){
        NoteResult noteResult = noteService.deleteNote(noteId);
        return noteResult;
    }
}