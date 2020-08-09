package com.hef.dao;

import com.hef.beans.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.util.FileCopyUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


/**
 * @author lifei
 * @since 2020/8/8
 */
@ContextConfiguration(locations = {"classpath:spring-db.xml"})
public class PostDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    private PostDao postDao;

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Test
    @Rollback(value = false)
    public void addPostTest(){
        Post post = new Post();
        post.setForumId(1);
        post.setUserId(2);
        post.setTopicId(3);
        post.setPostText("继承AbstractTransactionalTestNGSpringContextTests类，" +
                "test方法中的测试数据不会真的提交数据库，他将在test方法执行完毕后进行回滚");
        String attachStr = "I have a dream";
        ClassPathResource classPathResource = new ClassPathResource("photo/temp.png");
        byte[] bytes = new byte[0];
        try {
            bytes = FileCopyUtils.copyToByteArray(classPathResource.getFile());
            post.setPostAttach(bytes);
            postDao.addPost(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以块数据方式读取LOB数据
     */
    @Test
    public void getAttachsTest(){
        List<Post> postList = postDao.getAttachs(2);
        Assert.assertNotNull(postList);
    }

    /**
     * 以流数据方式读取LOB数据
     * @throws FileNotFoundException
     */
    @Test
    public void getAttachsByStreamTest() throws FileNotFoundException {
        OutputStream outputStream = new FileOutputStream("out/temp.png");
        postDao.getAttachsByStream(2, outputStream);
    }
}
