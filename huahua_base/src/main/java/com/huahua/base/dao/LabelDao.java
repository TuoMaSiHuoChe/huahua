package com.huahua.base.dao;

import com.huahua.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author: admin
 * @Date: 2019/4/10 22:57
 * @Description:
 */
public interface LabelDao extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {


    List<Label> findAllByRecommendOrderByFansDesc(String s);

    List<Label> findAllByStateOrderByFansDesc(String s);

    Label findOneById(String id);
}
