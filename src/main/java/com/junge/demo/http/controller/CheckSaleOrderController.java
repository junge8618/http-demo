package com.junge.demo.http.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.junge.demo.http.FileUtil;
import com.skylink.framework.result.CommonResult;

@RestController
@RequestMapping("/checksaleorder/")
public class CheckSaleOrderController {

	@Autowired
	private RestTemplate restTemplate;
	
	private String url = "http://192.168.20.92:8064/zdbpssvender-pc/visit/order/check?devid=d41d8cd98f00b204e9800998ecf8427e&accessToken=b9c455cb28034bafb3ea2286497c15d1&sessionId=4B5CE477A2DDF61B22EAFC03DB86E3AA";
	
	/**
	 * 并发审核销售单
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String checkOrder() {
		int count = 100;
		CountDownLatch latch = new CountDownLatch(count);
		
		for (int i=0; i<count; i++) {
			
			latch.countDown();
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + " Thread.");
						latch.await();
						System.out.println(JSONObject.toJSON(restTemplate.postForEntity(url, FileUtil.getContent(), CommonResult.class)));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}).start();
		}
		
		return "success";
	}
}
