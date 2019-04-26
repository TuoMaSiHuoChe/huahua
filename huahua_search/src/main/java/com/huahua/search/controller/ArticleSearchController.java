package com.huahua.search.controller;

import com.huahua.search.pojo.Article;
import com.huahua.search.service.ArticleSearchService;
import huahua.common.PageResult;
import huahua.common.Result;
import huahua.common.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author: admin
 * @Date: 2019/4/16 18:55
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {
    @Autowired
    private ArticleSearchService articleSearchService;


    @RequestMapping(method= RequestMethod.POST)
    public Result save(@RequestBody Article article){
        articleSearchService.add(article);
        return new Result(true, StatusCode.OK, "操作成功");
    }
    @RequestMapping(value="/search/{keywords}/{page}/{size}",method=
            RequestMethod.GET)
    public Result findByTitleLike(@PathVariable String keywords, @PathVariable int page, @PathVariable int size){
        Page<Article> articlePage=articleSearchService.findByTitleLike(keywords,page,size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Article>(articlePage.getTotalElements(),articlePage.getContent()));
    }
}
