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
 * 
 * @author "liuxj"
 *
 */
@RestController
@RequestMapping("/test2/")
public class HttpTestController2 {

	
	@Autowired
	private RestTemplate restTemplate;
	
	private String url = "http://192.168.20.177:8091/zdbpssvender-mobile/general/query/logininfo?accessToken=72baea89f3ce41e7bc62d8e1a3b44653&devid=865166010252172&sessionId=6E9559C9B7F6943A7C8FEC7D43763229";
	
	private String url1 = "http://192.168.20.177:8091/zdbpssvender-mobile/general/query/logininfo?accessToken=72baea89f3ce41e7bc62d8e1a3b44653&devid=865166010252172&sessionId=6E9559C9B7F6943A7C8FEC7D43763229";
	
	/**
	 * 同步所有数据
	 * @param param
	 * @return
	 * @author xcq
	 * @editTime 2018年5月15日
	 */
	@RequestMapping(value = "/begin", method = RequestMethod.GET)
	public String synchronizedAllData() {
		
		for (int i=0; i<20000; i++) {
			
			/*try {
				TimeUnit.MILLISECONDS.sleep((int)(10 * RandomUtils.nextDouble()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			
			ExecutorServiceUtil.submit(new Runnable() {
				
				@Override
				public void run() {
					long btime = System.currentTimeMillis();
					System.out.println(JSONObject.toJSON(restTemplate.postForEntity(url, null, CommonResult.class)) + ",time:" + (System.currentTimeMillis() - btime));
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
		int count = 100;
		
		CountDownLatch latch = new CountDownLatch(count);
		
		for (int i=0; i<count; i++) {
			latch.countDown();
			ExecutorServiceUtil.submit(new Runnable() {
				
				@Override
				public void run() {
					try {
						latch.await();
						long btime = System.currentTimeMillis();
						System.out.println(JSONObject.toJSON(restTemplate.postForEntity(url, null, CommonResult.class)) + ",time:" + (System.currentTimeMillis() - btime));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			});
			
		}
		
		return "success";
	}
	
}
