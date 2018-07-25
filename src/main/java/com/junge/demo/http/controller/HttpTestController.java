package com.junge.demo.http.controller;


import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.junge.demo.http.ExecutorServiceUtil;
import com.junge.demo.http.FileUtil;
import com.skylink.framework.result.CommonResult;

/**
 * 数据同步
 * @author xcq
 * @version 2018年5月10日
 */
@RestController
@RequestMapping("/test/")
public class HttpTestController {

	
	@Autowired
	private RestTemplate restTemplate;
	
	private String url = "http://192.168.20.177:8094/zdbpssvender-pc/visit/order/create?devid=d41d8cd98f00b204e9800998ecf8427e&accessToken=d6603a406ae64565b5942de5cb94c032&sessionId=CC2C078AD658E50F83400B79A613050C&repeat_req_token=992122_eac2bc1fb8e64f49836821a8ad0670ea_1532082952947";
	
	private String url1 = "http://192.168.20.177:8094/zdbpssvender-pc/visit/order/create?devid=d41d8cd98f00b204e9800998ecf8427e&accessToken=d6603a406ae64565b5942de5cb94c032&sessionId=CC2C078AD658E50F83400B79A613050C&repeat_req_token=";
	
	/**
	 * 同步所有数据
	 * @param param
	 * @return
	 * @author xcq
	 * @editTime 2018年5月15日
	 */
	@RequestMapping(value = "/begin", method = RequestMethod.GET)
	public String synchronizedAllData() {
		
		for (int i=0; i<1000000; i++) {
			
			/*try {
				TimeUnit.MILLISECONDS.sleep((int)(10 * RandomUtils.nextDouble()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			
			ExecutorServiceUtil.submit(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(JSONObject.toJSON(restTemplate.postForEntity(url, FileUtil.getContent(), CommonResult.class)));
				}
			});
			
		}
		
		return "success";
	}
	
	/**
	 * 同步所有数据
	 * @param param
	 * @return
	 * @author xcq
	 * @editTime 2018年5月15日
	 */
	@RequestMapping(value = "/begin1", method = RequestMethod.GET)
	public String addSaleOrder() {
		int count = 500;
		
		CountDownLatch latch = new CountDownLatch(count);
		
		for (int i=0; i<count; i++) {
			final String token = UUID.randomUUID().toString();
			latch.countDown();
			ExecutorServiceUtil.submit(new Runnable() {
				
				@Override
				public void run() {
					try {
						latch.await();
						restTemplate.postForEntity(url1 + token, FileUtil.getContent(), CommonResult.class);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			});
			
		}
		
		return "success";
	}
	
}
