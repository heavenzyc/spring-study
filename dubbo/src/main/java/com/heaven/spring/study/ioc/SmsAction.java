package com.heaven.spring.study.ioc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fruit.push.api.common.PushClientConfig;
import com.fruit.push.api.common.PushDubboResult;
import com.fruit.push.api.gexin.IGexinPushService;
import com.fruit.push.api.sms.ISmsService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heaven.zyc on 2017/7/2.
 */
public class SmsAction {

    public static void smsSend() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationConsumer.xml" });
        context.start();
        ISmsService demoServer = (ISmsService) context.getBean("smsService");

        List<String> phoneList = new ArrayList<String>();
        phoneList.add("17760537165");
        String params = "{\"smsCode\": \"2575\"}";
        PushDubboResult result = demoServer.sendSmsMsg(phoneList, params, "SMS_80375031", "test.com");
        System.out.println(JSON.toJSON(result));
    }

    public static void main(String[] args) {
        smsSend();
    }
}
