package com.huahua.article.dao;

import com.huahua.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author: admin
 * @Date: 2019/4/18 14:10
 * @Description:
 */
public interface CommentDao extends MongoRepository<Comment,String> {


    public List<Comment> findByArticleid(String articleid);
}
