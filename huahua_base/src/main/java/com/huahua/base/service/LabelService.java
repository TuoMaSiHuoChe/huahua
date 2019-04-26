package com.huahua.base.service;

import com.huahua.base.dao.LabelDao;
import com.huahua.base.pojo.Label;
import huahua.until.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: admin
 * @Date: 2019/4/10 23:00
 * @Description:
 */
@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     *
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据ID查询标签
     *
     * @return
     */
    public Label findById(String id) {

        return labelDao.findOneById(id);
    }

    /**
     * 增加标签
     *
     * @param label
     */
    public void add(Label label) {
        //设置ID
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    /**
     * 修改标签
     *
     * @param label
     */
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * 删除标签
     *
     * @param id
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    public List<Label> findSearch(Map searchMap) {
        Specification specification = createSpecification(searchMap);
        return labelDao.findAll(specification);
    }
    /**
     * 方法的重载根据条件进行分页查询
     *
     * @param searchMap
     * @return
     */
    public Page findSearch(Map searchMap, int page, int size) {
        Specification specification = createSpecification(searchMap);
        PageRequest pageRequest=PageRequest.of(page-1,size);
        return labelDao.findAll(specification,pageRequest);
    }

    private Specification createSpecification(Map searchMap) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (null!=searchMap.get("id")&&"".equals(searchMap.get("id"))) {
                predicates.add(cb.like(root.get("labelname").as(String.class), "%" + searchMap.get("labelname") + "%"));
            }
            if (!StringUtils.isEmpty((String)searchMap.get("state"))) {
                predicates.add(cb.equal(root.get("state").as(String.class), searchMap.get("state")));
            }
            if (!StringUtils.isEmpty((String)searchMap.get("recommend"))) {
                predicates.add(cb.equal(root.get("recommend").as(String.class), searchMap.get("recommend")));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public List<Label> toplist(String s) {
        return labelDao.findAllByRecommendOrderByFansDesc(s);
    }

    public List<Label> list(String s) {
        return labelDao.findAllByStateOrderByFansDesc(s);
    }
}