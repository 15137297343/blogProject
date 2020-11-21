package testcase;

import com.wzp.blog.controller.BooksController;
import com.wzp.blog.controller.SearchShareNoteController;
import com.wzp.blog.controller.ShareNoteInfoController;
import com.wzp.blog.dao.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wang
 * @version 1.0
 * @date 2020/11/11 18:59
 * @description
 */
public class TestCase {
    ApplicationContext ac=null;
    @Before
    public void test(){
      ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    @Test
    public void test1(){
        UserDao userDao = ac.getBean("userDao", UserDao.class);
        System.out.println(userDao.findByName("test1"));
    }
    @Test
    public void test2(){
        BooksController findBooksByUserIdController = ac.getBean("booksController", BooksController.class);
        System.out.println(findBooksByUserIdController.execute("333c6d0b-e4a2-4596-9902-a5d98c2f665a"));
    }

    @Test
    public void test3(){
        SearchShareNoteController searchShareNoteController = ac.getBean("searchShareNoteController", SearchShareNoteController.class);
        System.out.println(searchShareNoteController.execute("如果", 1));
    }
    @Test
    public void test4(){
        ShareNoteInfoController bean = ac.getBean("shareNoteInfoController", ShareNoteInfoController.class);
        System.out.println(bean.execute("69997f54ca174285b4a1543cb5cd5f87"));
    }
}
