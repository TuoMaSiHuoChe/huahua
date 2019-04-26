package com.huahua.qa.dao;

import com.huahua.qa.pojo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ReplyDao extends JpaRepository<Reply,String>,JpaSpecificationExecutor<Reply>{
	/**
	 *
	 *@描述 根据id查询
	 *@参数 id
	 *@返回值  Reply
	 *@创建人 托马斯小火车
	 *@创建时间 2019/4/12
	 *@修改人和其它信息
	 *
	 */
	Reply findOneById(String id);
	/**
	 *
	 *@描述 根据问题id查询回答列表 并按创建时间排序
	 *@参数 problemid
	 *@返回值  List
	 *@创建人 托马斯小火车
	 *@创建时间 2019/4/12
	 *@修改人和其它信息
	 *
	 */
	List<Reply> findAllByProblemidOrderByCreatetimeDesc(String problemid);
}
