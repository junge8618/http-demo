/**
 * 
 */
package com.junge.demo.http;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author "liuxj"
 *
 */
public class FileUtil {

	private static Object fileContent = null;
	
	private static String fileName = "E:\\request.json";
	
	private FileUtil() {
		
	}
	
	public static Object getContent() {
		if (null == fileContent) {
			synchronized (FileUtil.class) {
				if (null == fileContent) {
					try {
						fileContent = JSONObject.parse(FileUtils.readFileToString(new File(fileName), Charset.forName("UTF-8")));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return fileContent;
	}
	
}
