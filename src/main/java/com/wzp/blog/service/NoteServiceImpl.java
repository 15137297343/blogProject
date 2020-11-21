package com.wzp.blog.service;

import com.wzp.blog.dao.NoteDao;
import com.wzp.blog.dao.ShareDao;
import com.wzp.blog.entity.Note;
import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.entity.Share;
import com.wzp.blog.util.NoteUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/13 21:32
 * @description
 */
@Service
public class NoteServiceImpl implements NoteService {
    @Resource
    private ShareDao shareDao;
    @Resource
    private NoteDao noteDao;
    /*根据bookId查询笔记列表*/
    @Override
    public NoteResult findNoteByBookId(String bookId) {
        NoteResult noteResult=new NoteResult();
        List<Note> notes = noteDao.findNoteByBookId(bookId);
        noteResult.setStatus(0);
        noteResult.setMsg("查询笔记成功！");
        noteResult.setData(notes);
        return noteResult;
    }
    /*查询笔记信息*/
    @Override
    public NoteResult findNoteByNoteId(String noteId) {
        NoteResult noteResult=new NoteResult();
        Note note = noteDao.findNoteByNoteId(noteId);
        noteResult.setStatus(0);
        noteResult.setMsg("查询笔记信息成功！");
        noteResult.setData(note);
        return noteResult;
    }
     /*修改笔记信息*/
    @Override
    public NoteResult updateNote(String noteId, String noteTitle, String noteBody) {
        Note note = Note.builder().cn_note_id(noteId).cn_note_title(noteTitle).cn_note_body(noteBody).cn_note_last_modify_time(System.currentTimeMillis()).build();
        Integer i = noteDao.updateNote(note);
        NoteResult noteResult = new NoteResult();
        if(i==1){
           noteResult.setStatus(0);
           noteResult.setMsg("保存笔记成功");
        }else{
            noteResult.setStatus(1);
            noteResult.setMsg("保存笔记失败");
        }
        return noteResult;
    }
     /*添加笔记*/
    @Override
    public NoteResult insertNote(String userId, String bookId, String noteTitle) {
        NoteResult noteResult = new NoteResult();
        String cn_note_id=NoteUtil.creatId();
        Note note = Note.builder().cn_user_id(userId).cn_notebook_id(bookId).cn_note_title(noteTitle).cn_note_id(cn_note_id).cn_note_create_time(System.currentTimeMillis()).cn_note_last_modify_time(System.currentTimeMillis()).build();
        Integer i = noteDao.insertNote(note);
        if (i>0){
            noteResult.setStatus(0);
            noteResult.setMsg("添加笔记成功！");
            noteResult.setData(cn_note_id);
        }else{
        noteResult.setMsg("添加笔记失败！");
        noteResult.setStatus(1);
        }
        return noteResult;
    }
    /*删除笔记操作*/
    @Override
    public NoteResult deleteNote(String noteId) {
        NoteResult noteResult = new NoteResult();
        Note note = Note.builder().cn_note_id(noteId).cn_note_status_id("2").cn_note_last_modify_time(System.currentTimeMillis()).build();
        Integer i = noteDao.updateNote(note);
        if (i>0){
            noteResult.setStatus(0);
            noteResult.setMsg("删除笔记成功！");
        }else {
            noteResult.setStatus(1);
            noteResult.setMsg("删除笔记失败！");
        }
        return noteResult;
    }
    /*移动笔记操作*/
    @Override
    public NoteResult moveNote(String noteId, String bookId) {
        NoteResult noteResult = new NoteResult();
        Note note = Note.builder().cn_note_id(noteId).cn_notebook_id(bookId).build();
        Integer integer = noteDao.updateNote(note);
        if (integer>0){
           noteResult.setStatus(0);
           noteResult.setMsg("移动笔记成功");
        }else{
            noteResult.setStatus(1);
            noteResult.setMsg("移动笔记失败");
        }
        return noteResult;
    }
    /*分享笔记操作*/
    @Override
    public NoteResult shareNote(String noteId) {
        NoteResult noteResult = new NoteResult();
        //根据noteId查询笔记标题和笔记内容
        Note note = noteDao.findNoteByNoteId(noteId);
        if ("2".equals(note.getCn_note_type_id())){
            noteResult.setStatus(1);
            noteResult.setMsg("该笔记已经分享过了");
            return noteResult;
        }
        note.setCn_note_type_id("2");
       noteDao.updateNote(note);
        Share share = Share.builder().cn_share_id(NoteUtil.creatId()).cn_share_title(note.getCn_note_title()).cn_share_body(note.getCn_note_body()).cn_note_id(noteId).build();
        Integer integer = shareDao.insertShare(share);
        if (integer>0){
            noteResult.setStatus(0);
            noteResult.setMsg("分享笔记成功");
        }else{
            noteResult.setStatus(1);
            noteResult.setMsg("分享笔记失败");
        }
        return noteResult;
    }

    @Override
    public NoteResult searchShareNote(String keyword, Integer page) {
        NoteResult noteResult = new NoteResult();
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("keyword","%"+keyword+"%");
        params.put("begin",(page-1)*5);
        List<Share> noteByLike = shareDao.findShareNoteByLike(params);
        noteResult.setMsg("搜索分享笔记成功");
        noteResult.setStatus(0);
        noteResult.setData(noteByLike);
        return noteResult;
    }

    @Override
    public NoteResult findfindShareNoteByShareId(String shareId) {
        NoteResult noteResult = new NoteResult();
        Share share = shareDao.findShareNoteByShareId(shareId);
        noteResult.setData(share);
        noteResult.setStatus(0);
        noteResult.setMsg("查询分享笔记信息成功");
        return noteResult;
    }

}
