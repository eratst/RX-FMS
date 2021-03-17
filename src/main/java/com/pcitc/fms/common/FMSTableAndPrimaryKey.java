package com.pcitc.fms.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FMSTableAndPrimaryKey {
	
	private static Map<String,String> maps  = new ConcurrentHashMap<String,String>();;
	
	public static String getPrimaryKeyByTableName(String tableName) {
		if (maps != null) {
			String primaryKey = maps.get(tableName);
			return primaryKey;
		}
		return null;
	}
	
    
    public static void setFMSTableAndPrimaryKey(){
    	String tableSchema = System.getenv("FMS_DATABASE_NAME");
    	Connection conn = null;
    	ResultSet resultSet = null;
    	Statement statement = null;
    	try {
    		String sql = "select TABLE_NAME,COLUMN_NAME from information_schema.KEY_COLUMN_USAGE where TABLE_SCHEMA='"+tableSchema+"' and CONSTRAINT_NAME='PRIMARY';";
    		conn = JDBCUtil.getConnection();
    		statement = conn.createStatement();
    		resultSet = statement.executeQuery(sql);
    		while (resultSet.next()) {
    			String tableName = resultSet.getString("TABLE_NAME");
    			String columnName = resultSet.getString("COLUMN_NAME");
    			maps.put(tableName.toUpperCase(), columnName.toUpperCase());
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeJDBCBasicInfo(conn,new Statement[]{statement},new ResultSet[]{resultSet});
		}
    }
    
    
    private static void closeJDBCBasicInfo(Connection conn,Statement[] statements,ResultSet[] resultSets){
    	
    	for (int i=0;i<resultSets.length;i++) {
    		if (resultSets[i]!=null) {
    			try {
    				resultSets[i].close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	for (int i=0;i<statements.length;i++) {
    		if (statements[i]!=null) {
    			try {
    				statements[i].close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }

}
