package com.huahua.article.service;

import com.huahua.article.dao.CommentDao;
import com.huahua.article.pojo.Comment;
import huahua.until.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: admin
 * @Date: 2019/4/18 14:11
 * @Description:
 */
@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private IdWorker idWorker;
    public void add(Comment comment){
        comment.set_id( idWorker.nextId()+"" );
        commentDao.save(comment);
    }

    public List<Comment> findByArticleid(String articleid){
        return commentDao.findByArticleid(articleid);
    }
}
