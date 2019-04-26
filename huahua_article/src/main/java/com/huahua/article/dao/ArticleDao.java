package com.huahua.article.dao;

import com.huahua.article.pojo.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Modifying
    @Query("update Article set state='1' where id=?1")
    void examine(String id);

    @Modifying
    @Query("update Article a set thumbup=thumbup+1 where id=?1")
    int updateThumbup(String id);

    Article findOneById(String id);

    /**
     * 根据频道查询文章列表
     * @param channelid state isPublic
     * @return
     */
    List<Article> findAllByChannelidAndStateAndIspublicOrderByCreatetimeDesc(String channelid, String state, String isPublic, Pageable pageable);

    /**
     * 根据专栏查询文章列表
     * @param columnid state isPublic
     * @return
     */
    List<Article> findAllByColumnidAndStateAndIspublicOrderByCreatetimeDesc(String columnid,String state,String isPublic,Pageable pageable);

    /**
     * 查询头条文章
     * @param istop state isPublic
     * @return
     */
    List<Article> findAllByIstopAndStateAndIspublicOrderByCreatetimeDesc(String istop,String state,String isPublic);
}
