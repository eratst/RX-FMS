//package com.pcitc.fms.bll.stormItf;
//
////import com.pcitc.fms.dal.dao.stormDao.BaseDao;
//import com.pcitc.fms.exception.BusinessException;
//import com.pcitc.fms.service.stormModel.TankCalcPara;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
///**
// * The type Cuba helper.
// */
//public class CubaHelper extends BaseDao{
//	private static Logger log = LoggerFactory.getLogger(CubaHelper.class);
//
//	/**
//	 * Instantiates a new Cuba helper.
//	 */
//	public CubaHelper() {
//		
//	}
//
//	/**
//	 * 求卧罐罐容
//	 *
//	 * @param para the para
//	 * @return lie tank cuba by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getLieTankCubaByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select CUBA  from PM_LIE_CUBA_T where TANK_ID=" + para.getTankID() + " AND HGT="
//				+ para.getDbHeight();
//		return getObject(sql, "CUBA");
//	}
//
//	/**
//	 * 求接近当前高度的小高度和大高度
//	 *
//	 * @param para the para
//	 * @return max min lie tank cuba height by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getMaxMinLieTankCubaHeightByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select max(HGT) as HGT from PM_LIE_CUBA_T where TANK_ID=" + para.getTankID() + " AND HGT<="
//				+ para.getDbHeight() + "" + " union all " + " select min(HGT) as HGT from PM_LIE_CUBA_T where TANK_ID="
//				+ para.getTankID() + " AND HGT>=" + para.getDbHeight();
//		return getObject(sql, "HGT");
//	}
//
//	/**
//	 * 求接近大高度的小高度
//	 *
//	 * @param para the para
//	 * @return lower max lie tank cuba height by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getLowerMaxLieTankCubaHeightByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select max(HGT) as HGT from PM_LIE_CUBA_T where TANK_ID=" + para.getTankID() + " AND HGT<"
//				+ para.getDbHeight();
//		return getObject(sql, "HGT");
//	}
//
//	/**
//	 * 求接近小高度的大高度
//	 *
//	 * @param para the para
//	 * @return high min lie tank cuba height by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getHighMinLieTankCubaHeightByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select min(HGT) as HGT from PM_LIE_CUBA_T where TANK_ID=" + para.getTankID() + " AND HGT>"
//				+ para.getDbHeight();
//		return getObject(sql, "HGT");
//	}
//
//	/**
//	 * 取大于当前高度最小高度对应体积
//	 *
//	 * @param para the para
//	 * @return high curt heitht lie tank cuba by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getHighCurtHeithtLieTankCubaByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select CUBA  from PM_LIE_CUBA_T where TANK_ID=" + para.getTankID() + " AND HGT=(select min(HGT) "
//				+ "from PM_LIE_CUBA_T where TANK_ID=" + para.getTankID() + " and HGT>=" + para.getDbHeight() + ")";
//		return getObject(sql, "CUBA");
//	}
//
//	/**
//	 * Gets glb pres rev cofe by condition.
//	 *
//	 * @param para the para
//	 * @return glb pres rev cofe by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getGlbPresRevCofeByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "SELECT PRES_REV_COFE FROM PM_GLB_PRES_COEF_T" + " WHERE TANK_ID = " + para.getTankID()
//				+ " AND PRES_FLR_LMT < " + para.getDbStress() + " AND PRES_UP_LMT >= " + para.getDbStress();
//		String ret = null;
//		try {
//			ret = getObject(sql, "PRES_REV_COFE");
//		} catch (BusinessException e) {
//			log.error("fail",e);
//			throw  new BusinessException("","",e.getMessage());
//		}
//		return ret;
//	}
//
//	/**
//	 * 由公式获得罐容
//	 *
//	 * @param para the para
//	 * @return glb tank cuba by form by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getGlbTankCubaByFormByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select HGT,CUBA  from PM_GLB_CUBA_T where TANK_ID=" + para.getTankID();
//		String[] filedNames = { "HGT", "CUBA" };
//		return getObject(sql, filedNames);
//	}
//
//	/**
//	 * 求球罐罐容
//	 *
//	 * @param para the para
//	 * @return glb tank cuba by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getGlbTankCubaByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select CUBA  from PM_GLB_CUBA_T where TANK_ID=" + para.getTankID() + " AND HGT="
//				+ para.getDbHeight();
//		return getObject(sql, "CUBA");
//	}
//
//
//	/**
//	 * 求接近大高度的小高度
//	 *
//	 * @param para the para
//	 * @return lower max glb tank cuba height by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getLowerMaxGlbTankCubaHeightByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select max(HGT) as HGT from PM_GLB_CUBA_T where TANK_ID = " + para.getTankID() + " AND HGT< "
//				+ para.getDbHeight();
//		return getObject(sql, "HGT");
//	}
//
//	/**
//	 * 求接近小高度的大高度
//	 *
//	 * @param para the para
//	 * @return high min glb tank cuba height by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getHighMinGlbTankCubaHeightByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select min(HGT) as HGT from PM_GLB_CUBA_T where TANK_ID = " + para.getTankID() + " AND HGT > "
//				+ para.getDbHeight();
//		return getObject(sql, "HGT");
//	}
//
//
//	/**
//	 * 取大于当前高度最小高度对应体积qiu
//	 *
//	 * @param para the para
//	 * @return high curt height glb tank cuba by conditionqiu
//	 * @throws BusinessException the business exception
//	 */
//	public String getHighCurtHeightGlbTankCubaByConditionqiu(TankCalcPara para) throws BusinessException {
//		String sql = "select max(HGT) as HGT from PM_GLB_CUBA_T where TANK_ID=" + para.getTankID() + " AND HGT<="
//				+ para.getDbHeight() + " union all  select min(HGT) as HGT from PM_GLB_CUBA_T where TANK_ID="
//				+ para.getTankID() + " AND HGT>=" + para.getDbHeight() + "";
//		return getObject(sql, "HGT");
//	}
//
//	/**
//	 * 求接近当前高度的小高度和大高度
//	 *
//	 * @param para the para
//	 * @return max min glb tank cuba height by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getMaxMinGlbTankCubaHeightByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select max(HGT) as HGT from PM_GLB_CUBA_T where TANK_ID=" + para.getTankID() + " AND HGT<="
//				+ para.getDbHeight() + "" + " union all select min(HGT) as HGT from PM_GLB_CUBA_T " + " where TANK_ID= "
//				+ para.getTankID() + " AND HGT>= " + para.getDbHeight();
//		return getObject(sql, "HGT");
//	}
//
//	/**
//	 * 取大于当前高度最小高度对应体积qiu
//	 *
//	 * @param para the para
//	 * @return high curt height glb tank cuba by conditionqiu cuba
//	 * @throws BusinessException the business exception
//	 */
//	public String getHighCurtHeightGlbTankCubaByConditionqiuCUBA(TankCalcPara para) throws BusinessException {
//		String sql = "select max(CUBA) as CUBA from PM_GLB_CUBA_T where TANK_ID=" + para.getTankID() + " AND HGT<="
//				+ para.getDbHeight() + " union all  select min(CUBA) as CUBA from PM_GLB_CUBA_T where TANK_ID="
//				+ para.getTankID() + " AND HGT>=" + para.getDbHeight() + "";
//		return getObject(sql, "CUBA");
//	}
//
//	/**
//	 * 取压力区间内大于当前高度最小高度对应体积
//	 *
//	 * @param para the para
//	 * @return pre sec high curt height glb tank cuba by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getPreSecHighCurtHeightGlbTankCubaByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select CUBA from PM_GLB_CUBA_T" + " where HGT >= " + para.getDbHeight() + " and TANK_ID = "
//				+ para.getTankID() + " and ((PRES_FLR_LMT <= " + para.getDbStress() + " and PRES_UP_LMT > "
//				+ para.getDbStress() + ") " + " or (PRES_FLR_LMT is null and PRES_UP_LMT > " + para.getDbStress()
//				+ ") or" + " (PRES_FLR_LMT <= " + para.getDbStress() + " and PRES_UP_LMT = 0)) order by HGT";
//		// 修改by 王玖洲 and PRES_UP_LMT is null
//		return getObject(sql, "CUBA");
//	}
//
//	/**
//	 * 取压力0.5界值大于当前高度最小高度对应体积
//	 *
//	 * @param para the para
//	 * @return pre lmt high curt height glb tank cuba by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getPreLmtHighCurtHeightGlbTankCubaByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select CUBA" + " from PM_GLB_CUBA_T" + " where HGT =(select min(HGT) from PM_GLB_CUBA_T"
//				+ " where TANK_ID = " + para.getTankID() + " and ((PRES_UP_LMT = 0.5 and PRES_FLR_LMT is null and "
//				+ para.getDbStress() + " < 0.5) or" + " (PRES_FLR_LMT = 0.5 and PRES_UP_LMT is null and "
//				+ para.getDbStress() + " >= 0.5))" + " and HGT >= " + para.getDbHeight() + ")" + " and TANK_ID = "
//				+ para.getTankID() + " and ((PRES_UP_LMT = 0.5 and PRES_FLR_LMT is null and " + para.getDbStress()
//				+ " < 0.5) or" + " (PRES_FLR_LMT = 0.5 and PRES_UP_LMT is null and " + para.getDbStress() + " >= 0.5))";
//		return getObject(sql, "CUBA");
//	}
//
//	/**
//	 * 浮前由高度获得罐容
//	 *
//	 * @param para the para
//	 * @return fq std tank cuba
//	 * @throws BusinessException the business exception
//	 */
//	public String getFQStdTankCuba(TankCalcPara para) throws BusinessException {
//		String sql = "select DISP_SEQ_NBR  from  PM_FLT_PER_CUAB_T where TANK_ID=" + para.getTankID()
//				+ " and FLT_PER_HGT=" + para.getDbHeight();
//		return getObject(sql, "DISP_SEQ_NBR");
//	}
//
//	/**
//	 * 浮前求接近当前高度的小高度和大高度
//	 *
//	 * @param para the para
//	 * @return fq max min std tank cuba height
//	 * @throws BusinessException the business exception
//	 */
//	public String getFQMaxMinStdTankCubaHeight(TankCalcPara para) throws BusinessException {
//		String sql = "select max(FLT_PER_HGT) as FLT_PER_HGT from PM_FLT_PER_CUAB_T " + "where TANK_ID="
//				+ para.getTankID() + " and FLT_PER_HGT<=" + para.getDbHeight() + " union all"
//				+ " select min(FLT_PER_HGT) as FLT_PER_HGT from  PM_FLT_PER_CUAB_T " + " where TANK_ID="
//				+ para.getTankID() + " and FLT_PER_HGT>=" + para.getDbHeight();
//		return getObject(sql, "FLT_PER_HGT");
//	}
//
//	/**
//	 * 浮前求接近大高度的小高度
//	 *
//	 * @param para the para
//	 * @return fq lower max std tank cuba height
//	 * @throws BusinessException the business exception
//	 */
//	public String getFQLowerMaxStdTankCubaHeight(TankCalcPara para) throws BusinessException {
//		String sql = "select max(FLT_PER_HGT) as FLT_PER_HGT from pm_flt_per_cuab_t where TANK_ID=" + para.getTankID()
//				+ " and FLT_PER_HGT<" + para.getDbHeight();
//		return getObject(sql, "FLT_PER_HGT");
//	}
//
//	/**
//	 * 浮前求接近小高度的大高度
//	 *
//	 * @param para the para
//	 * @return fq high min std tank cuba height
//	 * @throws BusinessException the business exception
//	 */
//	public String getFQHighMinStdTankCubaHeight(TankCalcPara para) throws BusinessException {
//		String sql = "select min(FLT_PER_HGT) as FLT_PER_HGT from  pm_flt_per_cuab_t where TANK_ID=" + para.getTankID()
//				+ " and FLT_PER_HGT>" + para.getDbHeight();
//		return getObject(sql, "FLT_PER_HGT");
//	}
//
//
//	/**
//	 * 由高度获得罐容
//	 *
//	 * @param para the para
//	 * @return std tank cuba
//	 * @throws BusinessException the business exception
//	 */
//	public String getStdTankCuba(TankCalcPara para) throws BusinessException {
//		String sql = "select max(DM_CUBA) as DM_CUBA  from PM_STD_DM_CUAB_T where TANK_ID=" + para.getTankID()
//				+ " and HGT<=" + para.getDbHeight();
//		return getObject(sql, "DM_CUBA");
//	}
//
//	/**
//	 * 求接近当前高度的小高度和大高度
//	 *
//	 * @param para the para
//	 * @return max min std tank cuba height by condition
//	 * @throws BusinessException the business exception
//	 */
//	public String getMaxMinStdTankCubaHeightByCondition(TankCalcPara para) throws BusinessException {
//		String sql = "select max(HGT) AS HGT from PM_STD_DM_CUAB_T where TANK_ID=" + para.getTankID() + " and HGT<="
//				+ para.getDbHeight() + " union all" + " select min(HGT) AS HGT from PM_STD_DM_CUAB_T where TANK_ID="
//				+ para.getTankID() + " and HGT>=" + para.getDbHeight();
//		return getObject(sql, "HGT");
//	}
//
//	/**
//	 * 求接近当前高度的小罐容和大罐容
//	 *
//	 * @param para the para
//	 * @return max min std tank cuba height by conditionby
//	 * @throws BusinessException the business exception
//	 */
//	public String getMaxMinStdTankCubaHeightByConditionby(TankCalcPara para) throws BusinessException {
//		String sql = "select max(DM_CUBA) AS DM_CUBA from PM_STD_DM_CUAB_T where TANK_ID=" + para.getTankID()
//				+ " and HGT<=" + para.getDbHeight() + " union all"
//				+ " select min(DM_CUBA) AS DM_CUBA from PM_STD_DM_CUAB_T where TANK_ID=" + para.getTankID()
//				+ " and HGT>=" + para.getDbHeight();
//		return getObject(sql, "DM_CUBA");
//	}
//
//	/**
//	 * 求接近大高度的小高度
//	 *
//	 * @param para the para
//	 * @return lower max std tank cuba height
//	 * @throws BusinessException the business exception
//	 */
//	public String getLowerMaxStdTankCubaHeight(TankCalcPara para) throws BusinessException {
//		String sql = "select max(SEC_INT_PART)  AS HGT  from PM_STD_CMMM_CUAB_T " + "where TANK_ID = "
//				+ para.getTankID() + "  and SEC_ID = " + para.getZone_id() + "  and SEC_INT_PART < " + para.getMm();
//		return getObject(sql, "HGT");
//	}
//
//	/**
//	 * 求接近小高度的大高度
//	 *
//	 * @param para the para
//	 * @return high min std tank cuba height
//	 * @throws BusinessException the business exception
//	 */
//	public String getHighMinStdTankCubaHeight(TankCalcPara para) throws BusinessException {
//		String sql = "select min(SEC_INT_PART)  AS HGT  from PM_STD_CMMM_CUAB_T " + "where TANK_ID = "
//				+ para.getTankID() + "  and SEC_ID = " + para.getZone_id() + "  and SEC_INT_PART > " + para.getMm();
//		return getObject(sql, "HGT");
//	}
//
//	/**
//	 * 求区间id
//	 *
//	 * @param para the para
//	 * @return std tank cuba zone id
//	 * @throws BusinessException the business exception
//	 */
//	public String getStdTankCubaZoneId(TankCalcPara para) throws BusinessException {
//		String sql = "select SEC_ID from PM_STD_SEC_T where TANK_ID=" + para.getTankID() + " " + "and SEC_FLR_LMT<="
//				+ para.getDbHeight() + " and SEC_UP_LMT>=" + para.getDbHeight() + " order by SEC_FLR_LMT desc";
//		return getObject(sql, "SEC_ID");
//	}
//
//	/**
//	 * 求毫米罐容
//	 *
//	 * @param para the para
//	 * @return std tank cuba mm
//	 * @throws BusinessException the business exception
//	 */
//	public String getStdTankCubaMm(TankCalcPara para) throws BusinessException {
//		String sql = "select MM_TNKAREA_VAL from PM_STD_CMMM_CUAB_T " + "where TANK_ID=" + para.getTankID()
//				+ " and SEC_ID=" + para.getZone_id() + " and SEC_INT_PART=" + para.getMm();
//		return getObject(sql, "MM_TNKAREA_VAL");
//	}
//
//	/**
//	 * 求厘米罐容
//	 *
//	 * @param para the para
//	 * @return std tank cuba cm
//	 * @throws BusinessException the business exception
//	 */
//	public String getStdTankCubaCm(TankCalcPara para) throws BusinessException {
//		String sql = " select DM_TNKAREA_VAL from PM_STD_CMMM_CUAB_T " + "where TANK_ID=" + para.getTankID()
//				+ " and SEC_ID=" + para.getZone_id() + " and SEC_INT_PART=" + para.getCm();
//		return getObject(sql, "DM_TNKAREA_VAL");
//	}
//
//	/**
//	 * 求毫米罐容接近当前高度的小高度和大高度
//	 *
//	 * @param para the para
//	 * @return max min std tank cuba height mm
//	 * @throws BusinessException the business exception
//	 */
//	public String getMaxMinStdTankCubaHeightMm(TankCalcPara para) throws BusinessException {
//		String sql = " select max(SEC_INT_PART) AS HGT from PM_STD_CMMM_CUAB_T " + "where TANK_ID = " + para.getTankID()
//				+ "  and SEC_ID = " + para.getZone_id() + "  and SEC_INT_PART < " + para.getMm() + " union all"
//				+ " select min(SEC_INT_PART)  AS HGT  from PM_STD_CMMM_CUAB_T " + " where TANK_ID = " + para.getTankID()
//				+ "  and SEC_ID = " + para.getZone_id() + "  and SEC_INT_PART > " + para.getMm();
//		return getObject(sql, "HGT");
//	}
//
//	/**
//	 * 求毫米罐容接近大高度的小高度
//	 *
//	 * @param para the para
//	 * @return lower max std tank cuba height mm
//	 * @throws BusinessException the business exception
//	 */
//	public String getLowerMaxStdTankCubaHeightMm(TankCalcPara para) throws BusinessException {
//		String sql = " select max(HGT) AS HGT from PM_STD_DM_CUAB_T where TANK_ID=" + para.getTankID() + " and HGT<"
//				+ para.getDbHeight();
//		return getObject(sql, "HGT");
//	}
//
//	/**
//	 * 求毫米罐容接近小高度的大高度
//	 *
//	 * @param para the para
//	 * @return high min std tank cuba height mm
//	 * @throws BusinessException the business exception
//	 */
//	public String getHighMinStdTankCubaHeightMm(TankCalcPara para) throws BusinessException {
//		String sql = " select min(SEC_INT_PART)  AS HGT  from PM_STD_CMMM_CUAB_T" + " where TANK_ID = "
//				+ para.getTankID() + " and SEC_ID = " + para.getZone_id() + " and SEC_INT_PART > " + para.getMm();
//		return getObject(sql, "HGT");
//	}
//}
