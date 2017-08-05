package com.heaven.spring.study.ioc;

import com.alibaba.fastjson.JSONObject;
import com.fruit.push.api.common.PushClientConfig;
import com.fruit.push.api.common.PushDubboResult;
import com.fruit.push.api.gexin.IGexinPushService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heaven.zyc on 2017/7/2.
 */
public class PushAction {

    public void pushSend() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationConsumer.xml" });
        context.start();
        IGexinPushService demoServer = (IGexinPushService) context.getBean("gexinPushService");

        PushClientConfig config = new PushClientConfig();
        config.setAppKey("r1ZMs023Nn5iya7KIlgXe");
        config.setAppId("do75XyKRkqA4at6DHxDTh2");
        config.setMasterSecret("f2dHpKy8Jf9bHBvufaGYO6");

        List<String> list = new ArrayList<String>();
        list.add(String.valueOf(110));

        JSONObject message = new JSONObject();
        message.put("sourceId", 57);
        message.put("targetId", 3);
        message.put("msgType", "1");
        message.put("methodCode", "message.listMsg");
        List<Long> userList = new ArrayList<Long>();
        userList.add(Long.valueOf(110));
        message.put("userIds", userList);
        JSONObject params = new JSONObject();
        params.put("content", String.format("恭喜！！！你已升级至%s，立刻查看专享特权。", "银牌用户"));
        params.put("title", "会员升级");
        JSONObject extra = new JSONObject();
        extra.put("name", "会员升级");
        extra.put("type", "903");
        extra.put("storeId", "");
        extra.put("venderId", "");
        params.put("extras", extra);
        message.put("params", params);

        PushDubboResult result = demoServer.pushUserMessage(config ,list, message);
        System.out.println(JSONObject.toJSON(result));
    }

    public static void main(String[] args) throws InterruptedException {
        PushAction pushAction = new PushAction();
        pushAction.pushSend();
    }
}
