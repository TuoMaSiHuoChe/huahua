package com.huahua.qa.dao;


import com.huahua.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

    /**
     * 查询最新问题
     * @param label
     * @param of
     * @return
     */
    @Query("select p from Problem p where id in( select problemid from Pl pl  where pl.lableid = ?1) order by replytime desc")
    Page<Problem> findNewListByLabelId(String label, Pageable of);


    /**
     * 根据标签ID查询热门问题列表
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p where id in( select problemid from Pl where labelid=?1 ) order by reply desc")
    public Page<Problem> findHotListByLabelId(String labelId, Pageable pageable);


    /**
     * 根据标签ID查询等待回答列表
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p where id in( select problemid from Pl where labelid=?1 ) and reply=0 order by createtime desc")
    public Page<Problem> findWaitListByLabelId(String labelId, Pageable pageable);
}
