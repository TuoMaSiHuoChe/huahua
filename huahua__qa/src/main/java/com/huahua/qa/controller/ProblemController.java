package com.huahua.qa.controller;

import com.huahua.qa.client.LabelClient;
import com.huahua.qa.pojo.Problem;
import com.huahua.qa.service.ProblemService;
import huahua.common.PageResult;
import huahua.common.Result;
import huahua.common.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param problem
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Problem problem  ){
		Claims claims=(Claims)request.getAttribute("user_claims");
		if(claims==null){
			return new Result(false,StatusCode.ACCESSERROR,"无权访问");
		}
		problemService.add(problem);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param problem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Problem problem, @PathVariable String id ){
		problem.setId(id);
		problemService.update(problem);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		problemService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	/**
	 * 最新问答列表
	 * @param label
	 */
	@RequestMapping(value="/newlist/{label}/{page}/{size}",method= RequestMethod.GET)
	public Result newlist(@PathVariable String label,@PathVariable int page,@PathVariable int size){
		Page<Problem> pageList=problemService.findNewListByLabelId(label,page,size);
		PageResult<Problem> pageResult = new PageResult(pageList.getTotalElements(), pageList.getContent());
		return new Result(true, StatusCode.OK, "查询成功",pageResult);
	}
	/**
	 * 最热问答列表
	 * @param label
	 */
	@RequestMapping(value="/hotlist/{label}/{page}/{size}",method= RequestMethod.GET)
	public Result hotlist(@PathVariable String label,@PathVariable int page,@PathVariable int size){
		Page<Problem> pageList=problemService.findHotListByLabelId(label,page,size);
		PageResult<Problem> pageResult = new PageResult(pageList.getTotalElements(), pageList.getContent());
		return new Result(true, StatusCode.OK, "查询成功",pageResult);
	}
	/**
	 * 等待问答列表
	 * @param label
	 */
	@RequestMapping(value="/waitlist/{label}/{page}/{size}",method= RequestMethod.GET)
	public Result waitList(@PathVariable String label,@PathVariable int page,@PathVariable int size){
		Page<Problem> pageList=problemService.findWaitListByLabelId(label,page,size);
		PageResult<Problem> pageResult = new PageResult(pageList.getTotalElements(), pageList.getContent());
		return new Result(true, StatusCode.OK, "查询成功",pageResult);
	}

	@Autowired
	private LabelClient labelClient;


	@RequestMapping(value = "/label/{labelid}")
	public Result findLabelById(@PathVariable String labelid){
		Result result = labelClient.findById(labelid);
		return result;
	}
}
