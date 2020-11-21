package com.wzp.blog.controller;

import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.service.NoteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/16 18:25
 * @description
 */
@RestController
public class UpdateNoteInfoController {
    @Resource
    private NoteService noteService;
    @RequestMapping("note/updateNote.do")
    public NoteResult execute(String noteId, String noteTitle, String noteBody){
        NoteResult noteResult = noteService.updateNote(noteId, noteTitle, noteBody);
        return  noteResult;
    }

}
