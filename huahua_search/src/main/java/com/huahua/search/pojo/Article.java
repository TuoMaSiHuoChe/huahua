package com.huahua.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author: admin
 * @Date: 2019/4/16 16:47
 * @Description:
 */
@Data
@Document(indexName="huahua",type="article")
public class Article implements Serializable {
    @Id
    /**
     * ID
     */
    private String id;
    /**
     * //标题
     */
    @Field(index= true,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String title;

    @Field(index= true,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String content;

    /**
     * //审核状态
     */
    private String state;

}
