package com.huahua.sms;

import com.huahua.sms.HttpAliyun.AliyunUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: admin
 * @Date: 2019/4/18 15:46
 * @Description:
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @Autowired
    private AliyunUtils aliyunUtils;

    @Value("${HOST}")
    private String host;
    @Value("${PATH}")
    private String path;
    @Value("${METHOD}")
    private String method;
    @Value("${APPCODE}")
    private String appcode;
    @Value("${TYPEID}")
    private String tplId;
    @RabbitHandler
    public void send(Map<String,String> map){
        aliyunUtils.sendSms(map.get("mobile"),map.get("code"),appcode,host,path,method,tplId);
        System.out.println(map.get("mobile"));
        System.out.println(map.get("code"));
    }
}
