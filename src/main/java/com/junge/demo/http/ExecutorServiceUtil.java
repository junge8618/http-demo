package com.junge.demo.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceUtil {

	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10240);
	
	private static boolean isSyncCalResultDb = false;
	
	private ExecutorServiceUtil() {
	}
	
	public static boolean getIsSyncCalResultDb() {
		return ExecutorServiceUtil.isSyncCalResultDb;
	}
	
	public static void setSyncCalResultDb(String isSyncCalResultDb) {
		if (null == isSyncCalResultDb) {
			return ;
		}
		ExecutorServiceUtil.isSyncCalResultDb = Boolean.valueOf(isSyncCalResultDb);
	}
	
	public static void submit(Runnable task) {
		fixedThreadPool.submit(task);
	}
	
	public static void shutdown() {
		
		fixedThreadPool.shutdown();
	}
}
