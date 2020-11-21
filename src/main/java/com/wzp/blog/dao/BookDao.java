package com.wzp.blog.dao;

import com.wzp.blog.entity.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/12 17:50
 * @description
 */
public interface BookDao {
    /*查询book列表*/
    @Select("select * from cn_notebook where cn_user_id=#{userId}")
    List<Book> findByUserId(String userId);

    /*添加笔记本*/
    @Insert("insert into cn_notebook(cn_notebook_id,cn_user_id," +
            "cn_notebook_type_id,cn_notebook_name,cn_notebook_desc," +
            "cn_notebook_createtime) values(#{cn_notebook_id},#{cn_user_id}," +
            "#{cn_notebook_type_id},#{cn_notebook_name},#{cn_notebook_desc},#{cn_notebook_createtime})")
    Integer insertBook(Book book);
}
