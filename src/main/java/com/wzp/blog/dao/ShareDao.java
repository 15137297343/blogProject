package com.wzp.blog.dao;

import com.wzp.blog.entity.Share;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/18 17:58
 * @description
 */
public interface ShareDao {
    @Insert("insert into cn_share(cn_share_id,cn_share_title,cn_share_body,cn_note_id) values(#{cn_share_id},#{cn_share_title},#{cn_share_body},#{cn_note_id})")
    Integer insertShare(Share share);

    /*搜索分享笔记查询（模糊查询）*/
    @Select("select * from cn_share where cn_share_title like #{keyword} limit #{begin},5")
    List<Share> findShareNoteByLike(Map<String,Object> params);

    /*查询分享笔记信息*/
    @Select("select * from cn_share where cn_share_id=#{shareId}")
    Share findShareNoteByShareId(String shareId);

}
