package com.huahua.search.service;

import com.huahua.search.dao.ArticleSearchDao;
import com.huahua.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author: admin
 * @Date: 2019/4/16 18:55
 * @Description:
 */
@Service

public class  ArticleSearchService {
    @Autowired
    private ArticleSearchDao articleSearchDao;
    /**
     * 增加文章
     * @param article
     */
    public void add(Article article){
        articleSearchDao.save(article);
    }

    public Page<Article> findByTitleLike(String keywords, int page, int size) {
        PageRequest of = PageRequest.of(page - 1, size);
        Page<Article> allByTitleLike = articleSearchDao.findAllByTitleLike("%" + keywords + "%", of);
        return allByTitleLike;
    }
}
