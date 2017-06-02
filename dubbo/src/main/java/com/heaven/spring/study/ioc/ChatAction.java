package com.heaven.spring.study.ioc;

import com.fruit.erp.domain.ErpLoginReq;
import com.fruit.erp.domain.ErpLoginUserInfoDubbo;
import com.fruit.erp.service.dubbo.IErpLoginDubboService;
import com.fruit.erp.service.dubbo.po.DubboResult;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ChatAction {

    public void SayHello() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationConsumer.xml" });
		context.start();
		IErpLoginDubboService demoServer = (IErpLoginDubboService) context.getBean("erpLoginDubboService");
		ErpLoginReq req = new ErpLoginReq();
		DubboResult<ErpLoginUserInfoDubbo> result = demoServer.getLoginInfo(req);
		System.out.println(result.getData().getToken());
    }

}
