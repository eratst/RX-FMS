package com.pcitc.fms.bll.stormItf;

import com.google.gson.Gson;
import com.pcitc.fms.common.ApplicationContextTool;
//import com.pcitc.fms.dal.dao.stormDao.BaseDao;
import com.pcitc.fms.service.stormModel.CalcParamVO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pcitc.fms.exception.BusinessException;


/**
 * 获取计算参数
 *
 * @author haiwen.wang
 */
public class CalcParamHelper {
	private static Logger log = LoggerFactory.getLogger(CalcParamHelper.class);
	/**
	 * Instantiates a new Calc param helper.
	 */
	public CalcParamHelper() {
		
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
	}

	/**
	 * 查询罐检尺记录
	 *
	 * @param tankid the tankid
	 * @return string
	 * @throws BusinessException the business exception
	 */
	public String queryParam(String tankid) throws BusinessException {
		CalcParamVO calcParamVO = new CalcParamVO();
		// TODO 缺了几个字段，并且字段需要对应检查
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder sql = new StringBuilder();
		sql.append(
				"select a.NODE_ID as nodeID, a.MTRL_ID as materialID, a.WATR_HGHT as dbHeightOfWater, a.PRES as dbStress,"
						+ " a.ACID_FCTR as dbConcentration, a.CHK_TIME as dtDate, a.OIL_DNST as dbInputDensity, "
						+ "a.ATM_TEMP as dbTemperatureOfGas, a.MNUL_CHK_VAL as dbTotalDimension, a.oil_temp as dbTemperatureOfOil,"
						+ " a.oil_dnst as dbDensity, a.WATER_RATE as dbPercentOfWater ");
		// 执行
		sql.append("from TM_TANK_CHK_T a where a.node_id='" + tankid + "' ");
		Connection con = null;
		Statement pst = null;
		ResultSet ret = null;
		Gson gson = new Gson();
		try {
			con = getConnection();
			if (con == null) {
				throw new BusinessException("","","queryParam(con = null)!");
			}
			pst = con.createStatement();
			ret = pst.executeQuery(sql.toString());
			while (ret.next()) {

				String tankID = ret.getBigDecimal("nodeID").toString();
				String materialID = ret.getBigDecimal("materialID").toString();
				Double dbHeightOfWater = ret.getBigDecimal("dbHeightOfWater") == null ? null
						: ret.getBigDecimal("dbHeightOfWater").doubleValue();
				Double dbStress = ret.getBigDecimal("dbStress") == null ? null
						: ret.getBigDecimal("dbStress").doubleValue();
				Double dbConcentration = ret.getBigDecimal("dbConcentration") == null ? null
						: ret.getBigDecimal("dbConcentration").doubleValue();
				Date dtDate = ret.getTimestamp("dtDate");
				Double dbInputDensity = ret.getBigDecimal("dbInputDensity") == null ? null
						: ret.getBigDecimal("dbInputDensity").doubleValue();
				Double dbTemperatureOfGas = ret.getBigDecimal("dbTemperatureOfGas") == null ? null
						: ret.getBigDecimal("dbTemperatureOfGas").doubleValue();
				Double dbTotalDimension = ret.getBigDecimal("dbTotalDimension") == null ? null
						: ret.getBigDecimal("dbTotalDimension").doubleValue();
				Double dbTemperatureOfOil = ret.getBigDecimal("dbTemperatureOfOil") == null ? null
						: ret.getBigDecimal("dbTemperatureOfOil").doubleValue();
				Double dbDensity = ret.getBigDecimal("dbDensity") == null ? null
						: ret.getBigDecimal("dbDensity").doubleValue();
				Double dbPercentOfWater = ret.getBigDecimal("dbPercentOfWater") == null ? null
						: ret.getBigDecimal("dbPercentOfWater").doubleValue();

				calcParamVO.setTankID(tankID);
				calcParamVO.setMaterialID(materialID);
				calcParamVO.setDbHeightOfWater(dbHeightOfWater);
				calcParamVO.setDbStress(dbStress);
				calcParamVO.setDbConcentration(dbConcentration);
				calcParamVO.setDtDate(dtDate);
				calcParamVO.setDbInputDensity(dbInputDensity);
				calcParamVO.setDbTemperatureOfGas(dbTemperatureOfGas);
				calcParamVO.setDbTotalDimension(dbTotalDimension);
				calcParamVO.setDbTemperatureOfOil(dbTemperatureOfOil);
				calcParamVO.setDbDensity(dbDensity);
				calcParamVO.setDbPercentOfWater(dbPercentOfWater);

			}
			return gson.toJson(calcParamVO);
		} catch (SQLException e) {
			throw new BusinessException("","","queryParam is fail!"+e.toString());
		} finally {
			try {
				try {
					if (ret != null) {
            ret.close();
          }
				} catch (SQLException e) {
					throw new BusinessException("","","queryParam close is fail!"+e.toString());
				} finally {
					try {
						if (pst != null) {
              pst.close();
            }
					} catch (SQLException e) {
						throw new BusinessException("","","queryParam close is fail!"+e.toString());
					} finally {
						if (con != null) {
							con.close();
						}
					}
				}
			} catch (SQLException e) {
				throw new BusinessException("","","queryParam close is fail!"+e.toString());
			}
		}
	}
}
