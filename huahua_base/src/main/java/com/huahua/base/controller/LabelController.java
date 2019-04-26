package com.huahua.base.controller;

import com.huahua.base.pojo.Label;
import com.huahua.base.service.LabelService;
import huahua.common.PageResult;
import huahua.common.Result;
import huahua.common.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @Date: 2019/4/10 21:35
 * @Description:
 */
@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 查询全部列表
     *
     * @return Result
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    /**
     * 根据ID查询标签
     *
     * @param id
     * @return Result
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(id));
    }

    /**
     * 增加标签
     *
     * @param label
     * @return Result
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改标签
     *
     * @param label
     * @return Result
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Result updata(@RequestBody Label label, @PathVariable String id) {
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findSearch(searchMap));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
        Page pageList= labelService.findSearch(searchMap,page,size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult(pageList.getTotalElements(), pageList.getContent()));
    }
    /**
     * 推荐查询
     *
     * @return
     */
    @RequestMapping(value = "/toplist", method = RequestMethod.GET)
    public Result toplist() {
       List<Label> list= labelService.toplist("1");
        return new Result(true, StatusCode.OK, "查询成功", list);
    }
    /**
     * 推荐查询
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list() {
        List<Label> list= labelService.list("1");
        return new Result(true, StatusCode.OK, "查询成功", list);
    }
}
