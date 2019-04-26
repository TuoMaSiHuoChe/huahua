package com.huahua.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.huahua.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{


    /**
     * 查询前4状态为2的
     * @param s 状态
     * @return List
     */
    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String s);

    /**
     * 查询状态不为0并以创建日期降序排序，查询前12条记录
     * @param s 状态
     * @return List
     */
    List<Recruit> findByStateNotOrderByCreatetimeDesc(String s);
}
