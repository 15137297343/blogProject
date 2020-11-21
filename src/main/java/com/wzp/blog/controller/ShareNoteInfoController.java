package com.wzp.blog.controller;

import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.service.NoteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/20 11:02
 * @description
 */
@RestController
public class ShareNoteInfoController {
    @Resource
    private NoteService noteService;
    @RequestMapping("note/loadShareNote.do")
    public NoteResult execute(String shareId){
        return noteService.findfindShareNoteByShareId(shareId);
    }
}
