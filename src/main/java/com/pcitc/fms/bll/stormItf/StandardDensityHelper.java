//package com.pcitc.fms.bll.stormItf;
//
//import com.pcitc.fms.dal.dao.stormDao.BaseDao;
//import com.pcitc.fms.exception.BusinessException;
//import com.pcitc.fms.service.stormModel.TankCalcPara;
//
///**
// * The type Standard density helper.
// */
//public class StandardDensityHelper extends BaseDao {
//
//
//	/**
//	 * Instantiates a new Standard density helper.
//	 *
//	 * @Title: getTempMaxMinByCondition 获取温度最大、最小值
//	 * @Description:
//	 */
//	public StandardDensityHelper() {
//		
//	}
//
//	/**
//	 * Gets temp max min by condition 1.
//	 *
//	 * @param tableName the table name
//	 * @param materiaCode the materia code
//	 * @param dbTemperatureOfOil the db temperature of oil
//	 * @return the temp max min by condition 1
//	 * @throws BusinessException the business exception
//	 */
//	public String getTempMaxMinByCondition1(String tableName, String materiaCode, String dbTemperatureOfOil)
//			throws BusinessException {
//		String sql1 = "select max(CUBA_TEMP_COFE) as CUBA_TEMP_COFE from " + tableName + " where MTRL_CODE = "
//				+ materiaCode + " and CUBA_TEMP_COFE <= " + dbTemperatureOfOil + " ";
//		String union = " union all ";
//		String sql2 = "select min(CUBA_TEMP_COFE) as CUBA_TEMP_COFE  from " + tableName + " where  MTRL_CODE = "
//				+ materiaCode + " and CUBA_TEMP_COFE >= " + dbTemperatureOfOil + " ";
//		String sql=sql1 + union + sql2;
//		return getObject(sql1 + union + sql2, "CUBA_TEMP_COFE");
//	}
//
//	/**
//	 * Gets temp lower max by condition.
//	 *
//	 * @param tableName the table name
//	 * @param materiaCode the materia code
//	 * @param dbTemperatureOfOil the db temperature of oil
//	 * @return temp lower max by condition
//	 * @throws BusinessException the business exception
//	 * @Title: getTempLowerMaxByCondition 根据最大值取最小值
//	 * @Description: TODO
//	 */
//	public String getTempLowerMaxByCondition(String tableName, String materiaCode, String dbTemperatureOfOil)
//			throws BusinessException {
//		String sql = "select max(CUBA_TEMP_COFE) as CUBA_TEMP_COFE from " + tableName + " where MTRL_CODE = "
//				+ materiaCode + "and CUBA_TEMP_COFE < " + dbTemperatureOfOil + " ";
//		return getObject(sql, "CUBA_TEMP_COFE");
//	}
//
//	/**
//	 * Gets temp high min by condition.
//	 *
//	 * @param tableName the table name
//	 * @param materiaCode the materia code
//	 * @param dbTemperatureOfOil the db temperature of oil
//	 * @return temp high min by condition
//	 * @throws BusinessException the business exception
//	 * @Title: getTempHighMinByCondition 根据最小值取最大值
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 */
//	public String getTempHighMinByCondition(String tableName, String materiaCode, String dbTemperatureOfOil)
//			throws BusinessException {
//
//		String sql = "select min(CUBA_TEMP_COFE) as CUBA_TEMP_COFE from " + tableName + " where  MTRL_CODE = "
//				+ materiaCode + " and CUBA_TEMP_COFE > " + dbTemperatureOfOil + " ";
//
//		return getObject(sql, "CUBA_TEMP_COFE");
//	}
//
//	/**
//	 * Gets den by temp by condition.
//	 *
//	 * @param tableName the table name
//	 * @param materiaCode the materia code
//	 * @param dbTemperatureOfOil the db temperature of oil
//	 * @return den by temp by condition
//	 * @throws BusinessException the business exception
//	 * @Title: getDenByTempByCondition 根据温度获取密度
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 */
//	public String getDenByTempByCondition(String tableName, String materiaCode, String dbTemperatureOfOil)
//			throws BusinessException {
//
//		String sql = "select DEN  from " + tableName + " where  MTRL_CODE= " + materiaCode + " and CUBA_TEMP_COFE = "
//				+ dbTemperatureOfOil + " ";
//
//		return getObject(sql, "DEN");
//	}
//
//	/**
//	 * Gets density by cfm temp.
//	 *
//	 * @param tableName the table name
//	 * @param materiaCode the materia code
//	 * @param dbTemp the db temp
//	 * @param dbConcentration the db concentration
//	 * @return the density by cfm temp
//	 * @throws BusinessException the business exception
//	 */
//	public String getDensityByCfmTemp(String tableName, String materiaCode, String dbTemp, String dbConcentration)
//			throws BusinessException {
//
//		String sql1 = "select max(CON) as CONT from " + tableName + " where  MTRL_CODE = " + materiaCode
//				+ " and CUBA_TEMP_COFE = " + dbTemp + " and CON <= " + dbConcentration + " ";
//		String union = " union all ";
//		String sql2 = "select min(CON) as CONT from " + tableName + " where  MTRL_CODE = " + materiaCode
//				+ " and CUBA_TEMP_COFE = " + dbTemp + " and CON >= " + dbConcentration + " ";
//
//		return getObject(sql1 + union + sql2, "CONT");
//	}
//
//	/**
//	 * Gets cont lower max by condition.
//	 *
//	 * @param tableName the table name
//	 * @param materiaCode the materia code
//	 * @param dbTemp the db temp
//	 * @param dbConcentration the db concentration
//	 * @return the cont lower max by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getContLowerMaxByCondition(String tableName, String materiaCode, String dbTemp,
//			String dbConcentration) throws BusinessException {
//		String sql = "select max(CON) as CONT from " + tableName + " where  MTRL_CODE= " + materiaCode
//				+ " and CUBA_TEMP_COFE= " + dbTemp + " and CON < " + dbConcentration + " ";
//		return getObject(sql, "CONT");
//	}
//
//	/**
//	 * Gets cont high min by condition.
//	 *
//	 * @param tableName the table name
//	 * @param materiaCode the materia code
//	 * @param dbTemp the db temp
//	 * @param dbConcentration the db concentration
//	 * @return the cont high min by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getContHighMinByCondition(String tableName, String materiaCode, String dbTemp,
//			String dbConcentration) throws BusinessException {
//		String sql = "select min(CON) as CONT from " + tableName + " where  MTRL_CODE= " + materiaCode
//				+ " and CUBA_TEMP_COFE= " + dbTemp + " and CON > " + dbConcentration + " ";
//		return getObject(sql, "CONT");
//	}
//
//	/**
//	 * Gets den by temp and cont by condition.
//	 *
//	 * @param tableName the table name
//	 * @param materiaCode the materia code
//	 * @param dbTemp the db temp
//	 * @param dbConcentration the db concentration
//	 * @return the den by temp and cont by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getDenByTempAndContByCondition(String tableName, String materiaCode, String dbTemp,
//			String dbConcentration) throws BusinessException {
//		String sql = "select DEN from " + tableName + " where  MTRL_CODE = " + materiaCode + " and CUBA_TEMP_COFE = "
//				+ dbTemp + " and CON = " + dbConcentration + " ";
//		return getObject(sql, "DEN");
//	}
//
//	/**
//	 * Gets yhcpxs den by condition.
//	 *
//	 * @param tableName the table name
//	 * @param materiaCode the materia code
//	 * @return the yhcpxs den by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getYHCPXSDenByCondition(String tableName, String materiaCode) throws BusinessException {
//		String sql = "select DEN from " + tableName + " where  MTRL_CODE= " + materiaCode;
//		return getObject(sql, "DEN");
//	}
//
//	/**
//	 * Gets std gas liquid den max min temp by condition.
//	 *
//	 * @param tableName the table name
//	 * @param dbTemperatureOfOil the db temperature of oil
//	 * @return the std gas liquid den max min temp by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getStdGasLiquidDenMaxMinTempByCondition(String tableName, String dbTemperatureOfOil)
//			throws BusinessException {
//		String sql1 = "select max(TEMP) TEMP from " + tableName + " where temp<= " + dbTemperatureOfOil + " ";
//		String union = " union all ";
//		String sql2 = "select min(TEMP) TEMP from " + tableName + " where temp>= " + dbTemperatureOfOil + " ";
//
//		return getObject(sql1 + union + sql2, "TEMP");
//	}
//
//	/**
//	 * Gets std gas liquid den lower max temp by condition.
//	 *
//	 * @param tableName the table name
//	 * @param dbTemperatureOfOil the db temperature of oil
//	 * @return the std gas liquid den lower max temp by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getStdGasLiquidDenLowerMaxTempByCondition(String tableName, String dbTemperatureOfOil)
//			throws BusinessException {
//		String sql = "select max(TEMP) TEMP from " + tableName + " where temp<" + dbTemperatureOfOil + " ";
//		return getObject(sql, "TEMP");
//	}
//
//	/**
//	 * Gets std gas liquid den high min temp by condition.
//	 *
//	 * @param tableName the table name
//	 * @param dbTemperatureOfOil the db temperature of oil
//	 * @return the std gas liquid den high min temp by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getStdGasLiquidDenHighMinTempByCondition(String tableName, String dbTemperatureOfOil)
//			throws BusinessException {
//		String sql = "select min(TEMP) TEMP from " + tableName + " where temp> " + dbTemperatureOfOil + " ";
//
//		return getObject(sql, "TEMP");
//	}
//
//	/**
//	 * Gets std gas liquid den by condition.
//	 *
//	 * @param tableName the table name
//	 * @param dbTemperatureOfOil the db temperature of oil
//	 * @return the std gas liquid den by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getStdGasLiquidDenByCondition(String tableName, String dbTemperatureOfOil)
//			throws BusinessException {
//		String sql = "select GAS_DEN,LIQUID_DEN FROM " + tableName + " WHERE TEMP= " + dbTemperatureOfOil + " ";
//
//		String[] names = { "GAS_DEN", "LIQUID_DEN" };
//
//		return getObject(sql, names);
//	}
//
//	/**
//	 * Gets temp max min by condition.
//	 *
//	 * @param tableName the table name
//	 * @param dbTemperatureOfOil the db temperature of oil
//	 * @param intType the int type
//	 * @return the temp max min by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getTempMaxMinByCondition(String tableName, String dbTemperatureOfOil, String intType)
//			throws BusinessException {
//
//		String sql1 = "select max(TEMP) as TEMP from " + tableName + " where ARTC_ST= " + intType + " and TEMP<= "
//				+ dbTemperatureOfOil + " ";
//		String union = " union all ";
//		String sql2 = "select min(TEMP) as TEMP from " + tableName + " where ARTC_ST= " + intType + " and TEMP>= "
//				+ dbTemperatureOfOil + " ";
//
//		return getObject(sql1 + union + sql2, "TEMP");
//	}
//
//	/**
//	 * Gets den max min by condition.
//	 *
//	 * @param tableName the table name
//	 * @param intStanDenType the int stan den type
//	 * @param dbCfmTemp the db cfm temp
//	 * @param dbDen the db den
//	 * @return the den max min by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getDenMaxMinByCondition(String tableName, String intStanDenType, String dbCfmTemp, String dbDen) throws BusinessException{
//		String sql1 = "select max(DEN) as DEN from " + tableName + " where ARTC_ST= " + intStanDenType + " and TEMP= "
//				+ dbCfmTemp + " and DEN<= " + dbDen + " ";
//		String union = " union all ";
//		String sql2 = "select min(DEN) as DEN from " + tableName + " where ARTC_ST= " + intStanDenType + " and TEMP= "
//				+ dbCfmTemp + " and DEN>= " + dbDen + " ";
//
//		return getObject(sql1 + union + sql2, "DEN");
//	}
//
//	/**
//	 * Gets den lower max by condition.
//	 *
//	 * @param tableName the table name
//	 * @param intStanDenType the int stan den type
//	 * @param dbCfmTemp the db cfm temp
//	 * @param dbDen the db den
//	 * @return the den lower max by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getDenLowerMaxByCondition(String tableName, String intStanDenType, String dbCfmTemp, String dbDen)
//			throws BusinessException {
//		String sql = "select max(DEN) as DEN from " + tableName + " where ARTC_ST= " + intStanDenType + " and TEMP= "
//				+ dbCfmTemp + " and DEN< " + dbDen + " ";
//		return getObject(sql, "DEN");
//	}
//
//	/**
//	 * Gets den high min by condition.
//	 *
//	 * @param tableName the table name
//	 * @param intStanDenType the int stan den type
//	 * @param dbCfmTemp the db cfm temp
//	 * @param dbDen the db den
//	 * @return the den high min by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getDenHighMinByCondition(String tableName, String intStanDenType, String dbCfmTemp, String dbDen)
//			throws BusinessException {
//		String sql = "select min(DEN) as DEN from " + tableName + " where ARTC_ST= " + intStanDenType + " and TEMP= "
//				+ dbCfmTemp + " and DEN> " + dbDen + " ";
//		return getObject(sql, "DEN");
//	}
//
//	/**
//	 * Gets stan den by condition.
//	 *
//	 * @param tableName the table name
//	 * @param intStanDenType the int stan den type
//	 * @param dbCfmTemp the db cfm temp
//	 * @param dbDen the db den
//	 * @return the stan den by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getStanDenByCondition(String tableName, String intStanDenType, String dbCfmTemp, String dbDen)
//			throws BusinessException {
//		String sql = "select STAN_DEN_VAL from " + tableName + " where ARTC_ST= " + intStanDenType + " and TEMP= "
//				+ dbCfmTemp + " and DEN= " + dbDen + "";
//
//		return getObject(sql, "STAN_DEN_VAL");
//	}
//
//	/**
//	 * 求接近当前液体密度的小液体问题和大液体密度
//	 *
//	 * @param para the para
//	 * @return liquid den max min by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getLiquidDenMaxMinByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select max(LIQUID_DEN) as LIQUID_DEN from PM_STAN_GAS_DEN_T where LIQUID_DEN<="
//				+ para.getLiquidDen() + " union all"
//				+ " select min(LIQUID_DEN) as LIQUID_DEN from PM_STAN_GAS_DEN_T where LIQUID_DEN>="
//				+ para.getLiquidDen();
//
//		return getObject(sql, "LIQUID_DEN");
//	}
//
//	/**
//	 * Gets gas den by condition.
//	 *
//	 * @param para the para
//	 * @return gas den by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getGasDenByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select GAS_DEN from PM_STAN_GAS_DEN_T where LIQUID_DEN="
//				+ para.getLiquidDen();
//
//		return getObject(sql, "GAS_DEN");
//	}
//
//}
//
//
