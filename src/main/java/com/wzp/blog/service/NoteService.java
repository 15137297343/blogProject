package com.wzp.blog.service;

import com.wzp.blog.entity.NoteResult;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/13 21:30
 * @description
 */
public interface NoteService {
    /*根据bookId查询笔记列表*/
    NoteResult findNoteByBookId(String bookId);
    /*根据noteId查询笔记信息*/
    NoteResult findNoteByNoteId(String noteId);
    /*修改笔记信息*/
    NoteResult updateNote(String noteId,String noteTitle,String noteBody);
    /*添加笔记*/
    NoteResult insertNote(String userId,String noteId,String noteTitle);
    /*删除笔记*/
    NoteResult deleteNote(String noteId);
    /*移动笔记操作，就是修改要移动笔记的所属笔记本id*/
    NoteResult moveNote(String noteId,String bookId);
    /*分享笔记操作*/
    NoteResult shareNote(String noteId);
    /*搜索分享笔记*/
    NoteResult searchShareNote(String keyword,Integer begin);
    /*查询分享笔记的信息*/
    NoteResult findfindShareNoteByShareId(String shareId);
}
