package com.huahua.split.dao;

import com.huahua.split.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: admin
 * @Date: 2019/4/12 20:04
 * @Description:
 */
public interface SpitDao extends MongoRepository<Spit, java.lang.String> {

    /**
     * 根据上级ID查询吐槽列表（分页）
     * @param parentid
     * @param pageable
     * @return
     */
    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
