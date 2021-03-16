/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: DesientyOfAtmosphereHepler
 * Date:18-3-5 下午4:24
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.stormItf;

import com.google.gson.Gson;
import com.pcitc.fms.common.ApplicationContextTool;
import com.pcitc.fms.service.stormModel.AirDen;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pcitc.fms.exception.BusinessException;


/**
 * The type Desienty of atmosphere hepler.
 */
public class DesientyOfAtmosphereHepler {
	private static Logger log = LoggerFactory.getLogger(DesientyOfAtmosphereHepler.class);
	/**
	 * Instantiates a new Desienty of atmosphere hepler.
	 */
	public DesientyOfAtmosphereHepler() {
		
	}

	/**
	 * Gets connection.
	 *
	 * @return the connection
	 */
	private Connection getConnection()  {
		DataSource dataSource = (DataSource) ApplicationContextTool.getBean("dataSource");
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return con;
//		return ConnectionFactory.getHikariConnection(dbParam);
	}


	/**
	 * Get air den val by condition string.
	 *
	 * @param dbValue the db value
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	public String GetAirDenValByCondition(String dbValue) throws BusinessException, SQLException {
		List<AirDen> list = new ArrayList<AirDen>();
		//执行
		Connection con = null;
		Statement pst = null;
		ResultSet ret = null;
		Gson gson = new Gson();
		try {
			con = getConnection();
			if(con == null){
				throw new BusinessException("","","queryConfig(con = null)!");
			}
			pst = con.createStatement();
			ret = pst.executeQuery("select AIR_FLOG_MOD_VAL from PM_DEN_MOD_VAL_T where DEN_FLR_LMT <= "+dbValue+
					" and DEN_UP_LMT >= "+dbValue+
					" or ((DEN_FLR_LMT is null or DEN_FLR_LMT = 0) and DEN_UP_LMT >= "+dbValue+
					") or (DEN_UP_LMT is null and DEN_FLR_LMT <= "+dbValue+")");

			while(ret.next()){
				AirDen ptf = new AirDen();
				Double FLOG_MOD_VAL = ret.getDouble("AIR_FLOG_MOD_VAL");
				//封装
				ptf.setFLOG_MOD_VAL(FLOG_MOD_VAL);
				list.add(ptf);
			}
			
		} catch (SQLException e) {
			throw new BusinessException("","","queryConfig is fail!"+e.toString());
		}finally{
			try {
				if(ret != null){
					ret.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}finally {
				try {
					if(pst != null){
            pst.close();
          }
				} catch (SQLException e) {
					log.error(e.getMessage());
				} finally {
					if(con != null){
						con.close();
					}
				}

			}
		}
		return gson.toJson(list);
	}

	/**
	 * Get air den co ef by condition string.
	 *
	 * @param dbValue the db value
	 * @return the string
	 * @throws BusinessException the business exception
	 */
	public String GetAirDenCoEFByCondition(String dbValue) throws BusinessException, SQLException {
		List<AirDen> list = new ArrayList<AirDen>();
		//执行
		Connection con = null;
		Statement pst = null;
		ResultSet ret = null;
		Gson gson = new Gson();
		try {
			con = getConnection();
			if(con == null){
				throw new BusinessException("","","queryConfig(con = null)!");
			}
			pst = con.createStatement();
			
			
		//	PM_DEN_MOD_COEF_T
			ret = pst.executeQuery("select FLOG_MOD_COEF from PM_DEN_MOD_COEF_T where DEN_FLR_LMT <= "+dbValue+
					"and DEN_UP_LMT >= "+dbValue+
					"union select FLOG_MOD_COEF from PM_DEN_MOD_COEF_T where DEN_FLR_LMT = (select min(DEN_FLR_LMT) from PM_DEN_MOD_COEF_T)and "+dbValue+
					" < (select min(DEN_FLR_LMT) from PM_DEN_MOD_COEF_T) union select FLOG_MOD_COEF from PM_DEN_MOD_COEF_T where DEN_UP_LMT = (select max(DEN_UP_LMT) from PM_DEN_MOD_COEF_T) and "+dbValue+
					" > (select max(DEN_UP_LMT) from PM_DEN_MOD_COEF_T)");

			while(ret.next()){
				AirDen ptf = new AirDen();
				Double MOD_COEF = ret.getDouble("FLOG_MOD_COEF");
				
				//封装
				
				ptf.setFLOG_MOD_COEF(MOD_COEF);
				
				list.add(ptf);
			}
			
		} catch (SQLException e) {
			throw new BusinessException("","","queryConfig is fail!"+e.toString());
		}finally{
			try {
				if(ret != null){
					ret.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}finally {
				try {
					if(pst != null){
            pst.close();
          }
				} catch (SQLException e) {
					log.error(e.getMessage());
				} finally {
					if(con != null){
						con.close();
					}
				}
			}
		}
		return gson.toJson(list);
	}
}
