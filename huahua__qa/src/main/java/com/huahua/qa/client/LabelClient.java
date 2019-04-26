package com.huahua.qa.client;

import huahua.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: admin
 * @Date: 2019/4/24 11:32
 * @Description:
 */
@FeignClient("huahua-base")
public interface LabelClient {


    @RequestMapping(value="/label/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id);
}
