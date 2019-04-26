package com.huahua.sms;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * @author: admin
 * @Date: 2019/4/18 16:34
 * @Description:
 */
@Component
@RabbitListener(queues = "email")
public class EmailListener {
    @Autowired
    private JavaMailSender send;
    @RabbitHandler
    public void send(Map<String,String> map){
        System.out.println(map.get("mobile"));
        MimeMessage message = send.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            StringBuilder sb = new StringBuilder();
            sb.append("<h1>欢迎你注册了花花社区</h1>")
                    .append("<p style='color:#F00'>你的选择真的太棒了！</p>")
                    .append("<p style='text-align:left'> 您的验证码为"+map.get("code")+"</p>")
                   .append("<a href='http://127.0.0.1:9008/user/email/"+map.get("code")+"/"+map.get("mobile")+"' '>点击完成注册</a>");
            helper.setFrom("3211141074@qq.com");
            helper.setTo(map.get("mobile"));
            helper.setSubject("花花社区验证码");
            helper.setText(sb.toString(), true);
            send.send(message);
            System.out.println("发送成功~");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送失败~");
        }

    }

}
