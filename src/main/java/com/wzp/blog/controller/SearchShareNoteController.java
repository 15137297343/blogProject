package com.wzp.blog.controller;

import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.service.NoteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/20 8:46
 * @description
 */
@RestController
public class SearchShareNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("search/searchShareNote.do")
    public NoteResult execute(String keyword,Integer page){

       return noteService.searchShareNote(keyword,page);
   }
}
