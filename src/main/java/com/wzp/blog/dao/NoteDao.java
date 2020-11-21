package com.wzp.blog.dao;

import com.wzp.blog.entity.Note;
import com.wzp.blog.entity.Share;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/13 21:25
 * @description
 */
public interface NoteDao {
    /*查询笔记列表*/
    @Select("select cn_note_title,cn_note_id,cn_note_type_id from cn_note where cn_notebook_id=#{bookId} and cn_note_status_id='1'")
    List<Note> findNoteByBookId(String bookId);
    /*查询笔记信息*/
    @Select("select * from cn_note where cn_note_id=#{noteId}")
    Note findNoteByNoteId(String noteId);
    /*修改笔记信息*/
    @Update("<script>update cn_note\n" +
            "        <set>\n" +
            "            <if test=\"cn_note_type_id != null\">\n" +
            "                cn_note_type_id = #{cn_note_type_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_status_id != null\">\n" +
            "                cn_note_status_id = #{cn_note_status_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_notebook_id != null\">\n" +
            "                cn_notebook_id = #{cn_notebook_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_title != null\">\n" +
            "                cn_note_title = #{cn_note_title},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_body != null\">\n" +
            "                cn_note_body = #{cn_note_body},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_last_modify_time != null\">\n" +
            "                cn_note_last_modify_time = #{cn_note_last_modify_time},\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where cn_note_id = #{cn_note_id}</script>")
    Integer updateNote(Note note);
    /*添加笔记*/
    @Insert("insert into cn_note(cn_note_id,cn_notebook_id,cn_user_id,cn_note_status_id,cn_note_type_id,cn_note_title,cn_note_body,cn_note_create_time,cn_note_last_modify_time) values(#{cn_note_id},#{cn_notebook_id},#{cn_user_id},'1','1',#{cn_note_title},'',#{cn_note_create_time},#{cn_note_last_modify_time})")
    Integer insertNote(Note note);
    /*删除笔记实际上是修改笔记状态,所以共用一个update方法*/






}
