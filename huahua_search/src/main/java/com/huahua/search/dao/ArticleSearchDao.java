package com.huahua.search.dao;

import com.huahua.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: admin
 * @Date: 2019/4/16 18:53
 * @Description:
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {
    Page<Article> findAllByTitleLike(String title, Pageable pageable);
}
