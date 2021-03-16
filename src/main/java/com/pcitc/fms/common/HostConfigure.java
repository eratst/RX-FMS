package com.pcitc.fms.common;

import com.pcitc.fms.service.handler.FactoryHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HostConfigure {

	public static String AAAHOST;//AAA访问地址
	private static Logger log = LoggerFactory.getLogger(HostConfigure.class);
	static {
		InputStream inputStream = null;
		try {
			Properties properties = new Properties();
			String filepath = "hostconfig.properties";
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			 inputStream = classloader.getResourceAsStream(filepath);
			properties.load(inputStream);
			AAAHOST = properties.getProperty("AAAHOST").trim();
			inputStream.close();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}

		}
	}

}
