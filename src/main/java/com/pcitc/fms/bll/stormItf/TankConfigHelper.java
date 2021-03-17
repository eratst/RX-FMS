/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TankConfigHelper
 * Date:18-3-5 下午5:02
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.stormItf;

import com.google.gson.Gson;
import com.pcitc.fms.common.ApplicationContextTool;
import com.pcitc.fms.common.JsonTool;
import com.pcitc.fms.service.stormModel.TankConfigVO;
import com.pcitc.fms.service.stormModel.TankDeltCnfg;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pcitc.fms.exception.BusinessException;


/**
 * 罐配置信息的数据库查询类
 *
 * @author haiwen.wang
 */
public class TankConfigHelper {
	private static Logger log = LoggerFactory.getLogger(TankConfigHelper.class);
	/**
	 * Instantiates a new Tank config helper.
	 */
	public TankConfigHelper() {
		
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
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return con;
//		return ConnectionFactory.getHikariConnection(dbParam);
	}

	/**
	 * 查询默认配置
	 *
	 * @return string
	 * @throws BusinessException the business exception
	 * @throws SQLException the sql exception
	 */
	public String queryTankDefaultConfig() throws BusinessException, SQLException {
		Map<String, TankDeltCnfg> tankMap = new HashMap<String, TankDeltCnfg>();
		Gson gson = new Gson();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DELT_CNFG_ID, VAL, NAME, TYPE, DES FROM IP_DELT_CNFG_T where TYPE like '%TankCalc' or TYPE is null "
				+ "union "
				+ "SELECT DELT_CNFG_ID, VAL, NAME, TYPE, DES FROM "
				+ "(SELECT "
				+ "-1 AS DELT_CNFG_ID,"
				+ "'H' AS VAL,"
				+ "'KZGS' AS NAME,"
				+ "'TNK_FORM' AS TYPE,"
				+ "'罐检尺' AS DES "
				+ "FROM DUAL "
				+ "union "
				+ "SELECT "
				+ "-2 AS DELT_CNFG_ID,"
				+ "'P20' AS VAL,"
				+ "'KZGS' AS NAME,"
				+ "'TNK_FORM' AS TYPE,"
				+ "'标准密度' AS DES "
				+ "FROM DUAL"
				+ " union "
				+ "SELECT "
				+ "-3 AS DELT_CNFG_ID,'"
				+ "P' AS VAL,"
				+ "'KZGS' AS NAME,"
				+ "'TNK_FORM' AS TYPE,"
				+ "'油品在真空中的密度换算到空气中密度' AS DES"
				+ " FROM DUAL"
				+ " union"
				+ " SELECT "
				+ "-4 AS DELT_CNFG_ID,"
				+ "'T' AS VAL,"
				+ "'KZGS' AS NAME,"
				+ "'TNK_FORM' AS TYPE,"
				+ "'油品温度' AS DES"
				+ " FROM DUAL) T "
				+ "where T.TYPE like '%TankCalc'");
		
		// 执行
		Connection con = null;
		Statement pst = null;
		ResultSet ret = null;

		try {
			con = getConnection();
			if (con == null) {
				throw new BusinessException("", "", "TankDeltCnfg(con = null)!");
			}
			pst = con.createStatement();
			ret = pst.executeQuery(sql.toString());

			while (ret.next()) {
				TankDeltCnfg tankDeltCnfg = new TankDeltCnfg();
				tankDeltCnfg.setDelt_cnfg_id(ret.getBigDecimal("DELT_CNFG_ID").toString());
				tankDeltCnfg.setStrValue(ret.getString("VAL"));
				tankDeltCnfg.setStrName(ret.getString("NAME"));
				tankDeltCnfg.setStrType(ret.getString("TYPE"));
				tankDeltCnfg.setStrDescribe(ret.getString("DES"));

				tankMap.put(tankDeltCnfg.getStrName(), tankDeltCnfg);
			}
			return gson.toJson(tankMap);
		} catch (SQLException e) {
			throw new BusinessException("", "", "TankDeltCnfg is fail!"+e);
		} finally {
			try {
				if (ret != null) {
					ret.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}finally {
				try {
					if (pst != null) {
            pst.close();
          }
				} catch (SQLException e) {
					log.error(e.getMessage());
				}finally {
					if (con != null) {
						con.close();
					}
				}

			}
		}
	}

	/**
	 * 查询
	 *
	 * @param str the str
	 * @return string
	 * @throws BusinessException the business exception
	 * @throws SQLException the sql exception
	 */
	public String queryTankConfig(String str) throws BusinessException, SQLException {
		List<String> tankIDList = JsonTool.StringToStringtList(str);
		Map<String, TankConfigVO> tankConfigMap = new HashMap<String, TankConfigVO>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT TNK_CNFG_ID,TNK_CODE,"
				+ "(select NODE_NAME from PM_NODE_T where IP_TNK_CNFG_T.TNK_CODE = PM_NODE_T.Node_Id) as TankName,"
				+ " STD_DEN, VTF, MOD_CHK_FORM, TOTL_CHK_VPF, WTR_CHK_VPF, CUBA_TEMP_COEF, VCF,VCF_DEC_FRA_DGT,"
				+ " TNK_FORM,TNK_GAS_FORM, TNK_AIR_DEN, TNK_FORM_MODE, STD_CUBA_FORM  "
				+ " FROM IP_TNK_CNFG_T WHERE TNK_CODE in (");
		for (int i = 0; i < tankIDList.size(); i++) {
			if (i != tankIDList.size() - 1) {
				sql.append("'").append(tankIDList.get(i)).append("',");
			} else {
				sql.append("'").append(tankIDList.get(i)).append("')");
			}
		}
		// 执行
		Connection con = null;
		Statement pst = null;
		ResultSet ret = null;
		Gson gson = new Gson();
		try {
			con = getConnection();
			if (con == null) {
				throw new BusinessException("", "", "queryTankConfig(con = null)!");
			}
			pst = con.createStatement();
			ret = pst.executeQuery(sql.toString());

			while (ret.next()) {
				TankConfigVO tankConfigVO = new TankConfigVO();
				tankConfigVO.setConfigID(ret.getBigDecimal("TNK_CNFG_ID").toString());
				tankConfigVO.setTankID(ret.getBigDecimal("TNK_CODE").toString());
				tankConfigVO.setTankCode(ret.getBigDecimal("TNK_CODE").toString());
				tankConfigVO.setTankName(ret.getString("TankName"));
				tankConfigVO.setDenForm(ret.getString("STD_DEN"));
				tankConfigVO.setFormVTF(ret.getString("VTF"));
				tankConfigVO.setRevChkForm(ret.getString("MOD_CHK_FORM"));
				tankConfigVO.setFormVPF(ret.getString("TOTL_CHK_VPF"));
				tankConfigVO.setRevChkForm(ret.getString("WTR_CHK_VPF"));
				tankConfigVO.setFormYHCPXS(ret.getString("CUBA_TEMP_COEF"));
				tankConfigVO.setFormVCF(ret.getString("VCF"));
				tankConfigVO.setXswVCF(ret.getString("VCF_DEC_FRA_DGT"));
				tankConfigVO.setTankForm(ret.getString("TNK_FORM"));
				tankConfigVO.setTankGasForm(ret.getString("TNK_GAS_FORM"));
				tankConfigVO.setTankAirDen(ret.getString("TNK_AIR_DEN"));
				tankConfigVO.setTankFormMode(ret.getString("TNK_FORM_MODE"));
				tankConfigVO.setStdCubaForm(ret.getString("STD_CUBA_FORM"));				

				tankConfigMap.put(tankConfigVO.getTankID(), tankConfigVO);
			}
			return gson.toJson(tankConfigMap);
		} catch (SQLException e) {
			throw new BusinessException("", "", "queryTankConfig is fail!"+e);
		} finally {
			try {
				if (ret != null) {
					ret.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}finally {
				try {
					if (pst != null) {
            pst.close();
          }
				} catch (SQLException e) {
					log.error(e.getMessage());
				}finally {
					if (con != null) {
						con.close();
					}
				}

			}
		}
	}
}
