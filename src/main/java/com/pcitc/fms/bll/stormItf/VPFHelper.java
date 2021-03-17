//package com.pcitc.fms.bll.stormItf;
//
//import com.pcitc.fms.dal.dao.stormDao.BaseDao;
//import com.pcitc.fms.exception.BusinessException;
//import com.pcitc.fms.service.stormModel.SystemParamVO;
//import com.pcitc.fms.service.stormModel.TankCalcPara;
//
//
//
//
///**
// * VPF的数据库操作类
// *
// * @author haiwen.wang
// */
//public class VPFHelper extends BaseDao{
//
//	/**
//	 * Instantiates a new Vpf helper.
//	 */
//	public VPFHelper() {
//		
//	}
//
//	/**
//	 * Instantiates a new Vpf helper.
//	 *
//	 * @param paramVO the param vo
//	 */
//	public VPFHelper(SystemParamVO paramVO) {
//		super();
//
//	}
//
//	/**
//	 * 求接近当前高度的小高度和大高度
//	 *
//	 * @param para the para
//	 * @return max min std pres height by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getMaxMinStdPresHeightByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select max((INT_PL  + FST_DEC_FRA/10)) as Height "
//				+ "from PM_STD_PRES_COEF_T where TANK_ID = "+para.getTankID()+" and (INT_PL  + FST_DEC_FRA/10) <= "+para.getDbHeight()
//				+ " union all "
//				+ " select min((INT_PL  + FST_DEC_FRA/10)) as Height "
//				+ "from   PM_STD_PRES_COEF_T where TANK_ID = "+para.getTankID()+" and (INT_PL  + FST_DEC_FRA/10) >="+para.getDbHeight();
//		return getObject(sql, "Height");
//	}
//
//	/**
//	 * 求接近大高度的小高度
//	 *
//	 * @param para the para
//	 * @return lower max std pres height condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getLowerMaxStdPresHeightCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select max( (INT_PL  + FST_DEC_FRA/10)) as Height "
//				+ "from   PM_STD_PRES_COEF_T "
//				+ "where TANK_ID = "+para.getTankID()+" and  (INT_PL  + FST_DEC_FRA/10) < "+para.getDbHeight();
//		return getObject(sql, "Height");
//	}
//
//	/**
//	 * 求接近小高度的大高度
//	 *
//	 * @param para the para
//	 * @return high min std pres height by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getHighMinStdPresHeightByCondition(TankCalcPara para) throws BusinessException  {
//		String sql = "select min( (INT_PL  + FST_DEC_FRA/10)) as Height "
//				+ "from PM_STD_PRES_COEF_T "
//				+ "where TANK_ID = "+para.getTankID()+" and  (INT_PL  + FST_DEC_FRA/10) > "+para.getDbHeight();
//		return getObject(sql, "Height");
//	}
//
//	/**
//	 * 立罐静压修正系数
//	 *
//	 * @param para the para
//	 * @return std pres rev cofe by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getStdPresRevCofeByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select PRES_REV_COFE from PM_STD_PRES_COEF_T "
//				+ "where TANK_ID = "+para.getTankID()+" and  (INT_PL  + FST_DEC_FRA/10) = "+para.getDbHeight();
//		return getObject(sql, "PRES_REV_COFE");
//	}
//}
