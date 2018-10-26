package com.junge.demo.http.minghu;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.junge.demo.http.ExecutorServiceUtil;
import com.skylink.framework.result.CommonResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntInfoDataTest {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private String url = "http://tvpn.myimpos.com/gateway/zdbopen";
	//private String url = "http://192.168.10.126:9101/";
	
	private String corpId = "mingh.gyl.inner";
			
	private String corpkey = "";
	
	private RequestParam getRequestParam(String data) {
		RequestParam param = new RequestParam();
		param.setCorpId(corpId);
		param.setNoncestr(String.valueOf(System.currentTimeMillis()));
		param.setSign("33333333333333333333");
		param.setData(data);
		return param;
	}

	@Test
	public void addEntInfoData() {
		
		// 新增企业资料
		//addTestData("entinfo", "/ent/infosync");
		// 新增客户资料
		//addTestData("custinfo", "/ent/custsync");
		// 新增商品资料
		addTestData("goodsinfo", "/goods/sync");
		// 新增销售
		//addTestData("saleorders", "/sale/add");
		// 新增库存盘点
		//addTestData("clertpd", "/clerk/pd/update");
		// 新增销售上报
		//addTestData("clertsale", "/clerk/sale/add");
	}
	
	private void addTestData(String fileName, String urlPre) {
		try {
			File outputFile = new File("D:\\minghu\\" + fileName + ".txt");
			List<String> dataList = FileUtils.readLines(outputFile, "UTF-8");
			for(String data : dataList) {
				ExecutorServiceUtil.submit(new Runnable() {

					@Override
					public void run() {
						try {
							System.out.println(JSONObject.toJSON(restTemplate.postForEntity(url + urlPre, getRequestParam(data), CommonResult.class)));
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			
			TimeUnit.MINUTES.sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
