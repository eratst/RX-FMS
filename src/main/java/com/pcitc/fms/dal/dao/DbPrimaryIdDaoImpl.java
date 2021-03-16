package com.pcitc.fms.dal.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.FMSTableAndPrimaryKey;

@Service
public class DbPrimaryIdDaoImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private DataSource dataSource;
	
	
	public List<BigDecimal> getSeqId(int count,String tablename){
		
		List<BigDecimal> result = new ArrayList<>();
		
		String primaryKey = FMSTableAndPrimaryKey.getPrimaryKeyByTableName(tablename);
		
		String sql="select max("+primaryKey+") as max_value from "+tablename+"";
		
		BigDecimal currentValue = getCurrentPrimaryValue(sql);
		
		if (currentValue == null) {
			currentValue = new BigDecimal("1");
		}
		
		for (int i=0;i<count;i++) {
			currentValue = currentValue.add(new BigDecimal("1"));
			result.add(currentValue);
		}
		
		return result;
		
	}

	private BigDecimal getCurrentPrimaryValue(String sql){
    	Connection conn = null;
    	ResultSet resultSet = null;
    	Statement statement = null;
    	try {
    		conn = dataSource.getConnection();
    		statement = conn.createStatement();
    		resultSet = statement.executeQuery(sql);
    		while (resultSet.next()) {
    			BigDecimal maxValue = resultSet.getBigDecimal("max_value");
    			return maxValue;
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeJDBCBasicInfo(conn,new Statement[]{statement},new ResultSet[]{resultSet});
		}
    	return null;
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
