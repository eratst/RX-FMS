//package com.pcitc.fms.bll.stormItf;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//import com.pcitc.fms.common.JsonTool;
//import com.pcitc.fms.dal.dao.stormDao.BaseDao;
//import com.pcitc.fms.service.stormModel.TankCalcPara;
//import com.pcitc.fms.service.stormModel.TankTagVO;
//
//import com.pcitc.fms.exception.BusinessException;
//
///**
// * 数据库操作类
// *
// * @author haiwen.wang
// */
//public class VCFHelper extends BaseDao {
//
//	/**
//	 * Instantiates a new Vcf helper.
//	 */
//	public VCFHelper() {
//		super();
//
//	}
//
//	/**
//	 * 查询体积温度系数(温度)
//	 *
//	 * @param para the para
//	 * @return yhcpxs by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getYHCPXSByCondition(TankCalcPara para) throws BusinessException{
//		String sql = "select DEN_TEMP_COFE,CUBA_TEMP_COFE from PM_LIQPROD_COEF_T where MTRL_CODE='" + para.getWlbm()
//				+ "'";
//		String[] filedNames = { "DEN_TEMP_COFE", "CUBA_TEMP_COFE" };
//		return getObject(sql, filedNames);
//	}
//
//	/**
//	 * 取密度介于密度上线和密度下限之间对应的体温系数
//	 *
//	 * @param para the para
//	 * @return tjwdxs by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getTJWDXSByCondition(TankCalcPara para) throws BusinessException  {
//		String sql = "select CUBA_TEMP_COFE from PM_CUBA_TEMP_COEF_T where DEN_FLR_LMT<= " + para.getDbDen()
//				+ " and DEN_UP_LMT>=" + para.getDbDen() + "" + "  union select CUBA_TEMP_COFE from PM_CUBA_TEMP_COEF_T "
//				+ "  where DEN_FLR_LMT=(select min(DEN_FLR_LMT) from PM_CUBA_TEMP_COEF_T ) " + "  and "
//				+ para.getDbDen() + "< (select min(DEN_FLR_LMT) from PM_CUBA_TEMP_COEF_T )"
//				+ "  union select CUBA_TEMP_COFE from PM_CUBA_TEMP_COEF_T "
//				+ "  where DEN_UP_LMT=(select max(DEN_UP_LMT) from PM_CUBA_TEMP_COEF_T ) " + "  and " + para.getDbDen()
//				+ "> (select max(DEN_UP_LMT) from PM_CUBA_TEMP_COEF_T )";
//		String[] filedNames = { "CUBA_TEMP_COFE" };
//		return getObject(sql, filedNames);
//	}
//
//	/**
//	 * 接近当前温度的小温度和大温度
//	 *
//	 * @param para the para
//	 * @return temp max min by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getTempMaxMinByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select max(TEMP) as TEMP from PM_VCF_T where  ARTC_ST= " + para.getIntType() + " and TEMP<="
//				+ para.getDbTemperatureOfOil() + " union all select min(TEMP) as TEMP FROM PM_VCF_T "
//				+ " WHERE  ARTC_ST = " + para.getIntType() + " and TEMP >= " + para.getDbTemperatureOfOil();
//		return getObject(sql, "TEMP");
//	}
//
//	/**
//	 * 求温度下接近当前密度最大密度和最小密度
//	 *
//	 * @param para the para
//	 * @return den max min by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getDenMaxMinByCondition(TankCalcPara para) throws BusinessException {
//		String sql = " select max(DEN) as DEN from PM_VCF_T where ARTC_ST=" + para.getIntType() + " and TEMP="
//				+ para.getDbTemperatureOfOil() + " and DEN<=" + para.getDbDen() + "  union all"
//				+ "  select min(DEN) as DEN from PM_VCF_T where ARTC_ST=" + para.getIntType() + " and TEMP="
//				+ para.getDbTemperatureOfOil() + " and DEN>=" + para.getDbDen();
//		return getObject(sql, "DEN");
//	}
//
//	/**
//	 * 求温度下最接近大密度的小密度
//	 *
//	 * @param para the para
//	 * @return den lower max by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getDenLowerMaxByCondition(TankCalcPara para) throws BusinessException, SQLException {
//		String sql = " select max(DEN) as DEN from PM_VCF_T where ARTC_ST=" + para.getIntType() + " and TEMP="
//				+ para.getDbTemperatureOfOil() + " and DEN<" + para.getDbDen();
//		return getObject(sql, "DEN");
//	}
//
//	/**
//	 * 求温度下最接近小密度的大密度
//	 *
//	 * @param para the para
//	 * @return den high min by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getDenHighMinByCondition(TankCalcPara para) throws BusinessException  {
//		String sql = "select min(DEN) as DEN from PM_VCF_T where ARTC_ST=" + para.getIntType() + " and TEMP="
//				+ para.getDbTemperatureOfOil() + " and DEN>" + para.getDbDen();
//		return getObject(sql, "DEN");
//	}
//
//	/**
//	 * 求温度和密度对应的vcf值
//	 *
//	 * @param para the para
//	 * @return vcf by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getVCFByCondition(TankCalcPara para) throws BusinessException {
//		String sql = " select VCF_VAL from PM_VCF_T where ARTC_ST=" + para.getIntType() + " and TEMP="
//				+ para.getDbTemperatureOfOil() + " and DEN = " + para.getDbDen();
//		return getObject(sql, "VCF_VAL");
//	}
//
//	/**
//	 * 求最接近大温度的小温度
//	 *
//	 * @param para the para
//	 * @return temp lower max by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getTempLowerMaxByCondition(TankCalcPara para) throws BusinessException {
//		String sql = " select max(TEMP) as TEMP from PM_VCF_T where  ARTC_ST=" + para.getIntType() + " and TEMP<"
//				+ para.getDbTemperatureOfOil();
//		return getObject(sql, "TEMP");
//	}
//
//	/**
//	 * 求最接近小温度的大温度
//	 *
//	 * @param para the para
//	 * @return temp high min by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getTempHighMinByCondition(TankCalcPara para) throws BusinessException {
//		String sql = " select min(TEMP) as TEMP from PM_VCF_T where  ARTC_ST=" + para.getIntType() + " and TEMP<"
//				+ para.getDbTemperatureOfOil();
//		return getObject(sql, "TEMP");
//	}
//
//	/**
//	 * 求物料和温度对应的VCF
//	 *
//	 * @param para the para
//	 * @return wlvcf by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getWLVCFByCondition(TankCalcPara para) throws BusinessException  {
//		String sql = " select VCF_VAL from PM_MTRL_TEMP_VCF_T where MTRL_CODE= " + para.getWlbm() + " and  TEMP= "
//				+ para.getDbTemperatureOfOil();
//		return getObject(sql, "VCF_VAL");
//	}
//
//	/**
//	 * 求接近当前温度的小温度和大温度(物料)
//	 *
//	 * @param para the para
//	 * @return wl temp max min by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getWLTempMaxMinByCondition(TankCalcPara para) throws BusinessException  {
//		String sql = " select MAX(TEMP) AS TEMP from PM_MTRL_TEMP_VCF_T " + " WHERE MTRL_CODE=" + para.getWlbm()
//				+ " AND TEMP <" + para.getDbTemperatureOfOil() + "" + " union all"
//				+ " select MIN(TEMP) AS TEMP from PM_MTRL_TEMP_VCF_T " + " WHERE MTRL_CODE= " + para.getWlbm()
//				+ " AND TEMP > " + para.getDbTemperatureOfOil();
//		return getObject(sql, "TEMP");
//	}
//
//	/**
//	 * 求最接近大温度的小温度(物料)
//	 *
//	 * @param para the para
//	 * @return wl temp lower max by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getWLTempLowerMaxByCondition(TankCalcPara para) throws BusinessException  {
//		String sql = " select MAX(TEMP) AS TEMP from pm_mtrl_temp_vcf_t T "
//				+ "WHERE T.MTRL_CODE= "+para.getWlbm()+" AND T.TEMP < "+para.getDbTemperatureOfOil();
//		return getObject(sql, "TEMP");
//	}
//
//	/**
//	 * 求最接近小温度的大温度(物料)
//	 *
//	 * @param para the para
//	 * @return wl temp high min by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getWLTempHighMinByCondition(TankCalcPara para) throws BusinessException  {
//		String sql = " select MIN(TEMP) AS TEMP from pm_mtrl_temp_vcf_t T "
//				+ "WHERE T.MTRL_CODE= "+para.getWlbm()+" AND T.TEMP > "+para.getDbTemperatureOfOil();
//		return getObject(sql, "TEMP");
//	}
//}
