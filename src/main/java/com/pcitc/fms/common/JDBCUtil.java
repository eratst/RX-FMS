package com.pcitc.fms.common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;

public class JDBCUtil {

	@SuppressWarnings("unused")
	private static ApplicationContext context;
	private static DataSource dataSource;
	private static Connection con;

	public static synchronized Connection getConnection() {
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void setContext(ApplicationContext context) {
		JDBCUtil.context = context;
		dataSource = (DataSource) context.getBean("dataSource");
	}
	
	public static synchronized DataSource getDataSource(){
		return dataSource;
	}
	
	public static synchronized ApplicationContext getContext(){
		return context;
	}
}
