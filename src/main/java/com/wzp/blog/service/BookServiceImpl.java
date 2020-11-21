package com.wzp.blog.service;

import com.wzp.blog.dao.BookDao;
import com.wzp.blog.entity.Book;
import com.wzp.blog.entity.NoteResult;
import com.wzp.blog.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/12 17:55
 * @description
 */
@Service
public class BookServiceImpl implements BookService{
    @Resource
    private BookDao bookDao;
    /*通过userID查询个人笔记本*/
    @Override
    public NoteResult findBooksByUserId(String userId) {
        NoteResult result = new NoteResult();
        if (userId==null){
            result.setStatus(1);
            result.setMsg("用户未登录");
            return result;
        }
        List<Book> books = bookDao.findByUserId(userId);
        result.setStatus(0);
        result.setMsg("查询成功！");
        result.setData(books);
        return result;
    }
/*添加笔记本操作*/
    @Override
    public NoteResult addNotebook(String cn_user_id, String cn_notebook_name)
    {
        NoteResult noteResult = new NoteResult();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Book book = Book.builder().cn_notebook_id(NoteUtil.creatId()).cn_user_id(cn_user_id).cn_notebook_type_id("5").cn_notebook_name(cn_notebook_name).cn_notebook_createtime(timestamp).build();
        Integer i = bookDao.insertBook(book);
        if (i==1){
            noteResult.setStatus(0);
            noteResult.setData(book);
            noteResult.setMsg("添加笔记本成功！");
        }else{
            noteResult.setStatus(1);
            noteResult.setMsg("添加笔记本失败！");
        }
        return noteResult;
    }
}
