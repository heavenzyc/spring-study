package com.heaven.spring.study.ioc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fruit.push.api.common.PushDubboResult;
import com.fruit.push.api.sms.ISmsService;
import com.fruit.push.api.wx.IWechatNotificationService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heaven.zyc on 2017/7/2.
 */
public class WxTemplateAction {

    public static void wxTemplate() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationConsumer.xml" });
        context.start();
        IWechatNotificationService demoServer = (IWechatNotificationService) context.getBean("wechatNotificationService");


        String s = "{\n" +
                "        \"tips\":{\n" +
                "            \"value\":\"您好，您已成功发起一个团购\",\n" +
                "            \"color\":\"#173177\"\n" +
                "        },\n" +
                "        \"orderId\":{\n" +
                "            \"value\":\"1234567\"\n" +
                "        },\n" +
                "        \"price\":{\n" +
                "            \"value\":\"100.0\"\n" +
                "        }\n" +
                "    }";

        JSONObject jsonObject = JSONObject.parseObject(s);
        PushDubboResult res = demoServer.sendNotification("odqQ3t40Sn8m12d7684XYvFq9rF4", "nxzdhR0sZUqNRDScJpSGbg4fjTZhbez5Qk5Jts7mo5Y", "http://www.baidu.com ", jsonObject);
        System.out.println(JSON.toJSON(res));
    }

    public static void main(String[] args) {
        wxTemplate();
    }
}
