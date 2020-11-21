package com.wzp.blog.controller;

import com.wzp.blog.dao.NoteDao;
import com.wzp.blog.entity.Note;
import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.service.NoteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/13 23:06
 * @description
 */
@RestController
public class LoadNoteController {
    @Resource
    private NoteService noteService;
    /*加载笔记信息*/
    @RequestMapping("note/noteInfo.do")
    public NoteResult execute(String noteId){
        NoteResult note = noteService.findNoteByNoteId(noteId);
        return note;
    }

}
