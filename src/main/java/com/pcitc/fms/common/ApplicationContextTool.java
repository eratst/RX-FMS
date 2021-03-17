package com.pcitc.fms.common;

import org.springframework.context.ApplicationContext;

public class ApplicationContextTool {

	private static ApplicationContext context;

	public synchronized static Object getBean(String beanName) {

		return context.getBean(beanName);

	}

	public static void setContext(ApplicationContext context) {
		ApplicationContextTool.context = context;
	}
}
