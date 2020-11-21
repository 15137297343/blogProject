package com.wzp.blog.controller;

import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.service.NoteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/18 18:11
 * @description
 */
@RestController
public class ShareNoteController {
    @Resource
    private NoteService noteService;

    @RequestMapping("note/shareNote.do")
    public NoteResult execute(String noteId){
        return noteService.shareNote(noteId);
    }
}
