package com.pcitc.fms.exception;

import com.pcitc.fms.common.CheckError;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;


/**
 * Title: BusinessExceptionMessage Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年7月3日
 */
public class BusinessExceptionMessage {
	private static Logger log = LoggerFactory.getLogger(BusinessExceptionMessage.class);
	/**
	 * Gets primarykey unique.
	 *
	 * @param e the e
	 * @return primarykey unique
	 * @Title: getPrimarykeyUnique
	 * @Description: TODO 新增时获取主键唯一信息
	 * @date 2017年7月3日
	 * @return: String
	 * @author zhenqiang.zhao
	 */
	public static String getPrimarykeyUnique(DataIntegrityViolationException e) {
		if(null == e.getCause()){
			throw e;
		}
		if(null == e.getCause().getCause()){
			throw e;
		}
		if (null != e.getCause().getCause().getMessage() && e.getCause().getCause().getMessage().length()>0) {
			String str =   e.getCause().getCause().getMessage();
			if(e.getCause().getCause().getMessage().indexOf("Duplicate") > -1){
				if(str.contains("key") && str.contains("entry") && str.contains("for")){
					str = StringUtils.substringAfter(str, "key") +":"+StringUtils.substringBetween(str, "entry","for")+CheckError.IS_EXIST;
				}
				return  str;
			}
			if(e.getCause().getCause().getMessage().indexOf("unique") > -1){
				if(str.contains("violated") && str.contains("unique")){
					str = StringUtils.substringBetween(str, "constraint","violated")+CheckError.IS_EXIST;
				}
				return  str;
			}
		 return  str;
		}
		 throw e;
	}

	/**
	 * Gets unique key message.
	 *
	 * @param e the e
	 * @return unique key message
	 * @Title: getUniqueKeyMess
	 * @Description: 唯一索引的冲突时提供的信息
	 * @date 2017年11月2日
	 * @return: String
	 * @author
	 */
	public static String getUniqueKeyMessage(DataIntegrityViolationException e) {
		if(null == e.getCause()){
			throw e;
		}
		if(null == e.getCause().getCause()){
			throw e;
		}
		if (null != e.getCause().getCause().getMessage() && e.getCause().getCause().getMessage().length()>0) {
			String str =   e.getCause().getCause().getMessage();
			if(str.contains("ORA-02290")&& str.contains("CKC_ENTR_TYPE_T_PM_ENT")) {
				str = CheckError.ENTRTYPEVALUE_ONLY;
				return str;
			}
			if(str.contains("ORA-02291")&& str.contains("FK_ORG_REF_ORGTYPE")) {
				str = CheckError.ORGREFORGTYPE;
				return str;
			}
			if(str.contains("ORA-02290")&& str.contains("CKC_EXPEND_FLAG_T_PM_BIZ")){
				str = CheckError.EXPENDFLAG;
				return str;
			}
			if(str.contains("ORA-00001")&& str.contains("AK_T_PM_BIZORG_MAIN")){
				str = CheckError.BIZORGCHECK;
				return str;
			}
			if(str.contains("ORA-02290")&& str.contains("CKC_BUSINESS_TYPE_T_PM_FAC")) {
				str = CheckError.BUSINESSTYPEONLY;
				return str;
			}
			if(str.contains("ORA-02290")&& str.contains("CKC_FCTR_BLOCK_T_PM_FAC")){
				str = CheckError.FCTRBLOCKONLY;
				return str;
			}
			if(str.contains("ORA-00001")&& str.contains("AK_PM_NODE_NAME")) {
				str = CheckError.NODENAME_EXIST;
				return str;
			}
			if(str.contains("ORA-00001")&& str.contains("AK_PM_NODE_ALIAS")) {
				str = CheckError.NODEALIAS_EXIST;
				return str;
			}
			if(str.contains("ORA-02291")&& str.contains("FK_PM_NODE_REF_NODETYPE")){
				str = CheckError.NODETYPE_IS_NOT_EXIST;
				return str;
			}
			if(e.getCause().getCause().getMessage().indexOf("Duplicate") > -1){
				if(str.contains("key") && str.contains("entry") && str.contains("for")){
					str = StringUtils.substringAfter(str, "key") +":"+StringUtils.substringBetween(str, "entry","for")+CheckError.IS_EXIST;
				}
				return  str;
			}
			if(e.getCause().getCause().getMessage().indexOf("ORA-00001") > -1){
				if(str.contains("ORA-00001")){
					//str = StringUtils.substringBetween(str, "constraint","violated")+CheckError.IS_EXIST;
					String[] split = str.split("_");
					int a = split.length;
					str = split[a-1].toLowerCase()+CheckError.IS_EXIST;
				}
				return  str;
			}
			return  str;
		}
		throw e;
	}

	/**
	 * Gets over length check.
	 *
	 * @param e the e
	 * @return over length check
	 * @throws Exception the exception
	 * @Title: getOverLengthCheck
	 * @Description: 超长校验 :eg  ERROR: ORA-12899: 列 "FMS"."ORGUNITVIEW"."CODE" 的值太大 (实际值: 37, 最大值: 36)
	 * @return: String
	 * @author zhenqiang.zhao
	 */
	public static String getOverLengthCheck(Exception e) throws BusinessException {
		if(null == e.getCause()){
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		if(null == e.getCause().getCause()){
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		if (null != e.getCause().getCause().getMessage() && e.getCause().getCause().getMessage().length()>0) {
			String str =   e.getCause().getCause().getMessage();
			return  str;
		}
		log.error("fail",e);
		throw  new BusinessException("","",e.getMessage());
	}

	/**
	 * Gets foreign check.
	 *
	 * @param name the name
	 * @param validateId the validate id
	 * @return foreign check
	 * @Title: getDataNotExist
	 * @Description: 获取数据不存在信息
	 * @date 2017年7月11日
	 * @return: String
	 * @author zhenqiang.zhao
	 */
	public static String getForeignCheck(String name, Integer validateId) {
		return name +":"+validateId+":"+CheckError.IS_FOREIGN_KEY;
	}

	/**
	 * Gets data not exist.
	 *
	 * @param name the name
	 * @param validateId the validate id
	 * @return the data not exist
	 */
	public static String getDataNotExist(String name, Integer validateId) {
		return name +":"+validateId+":"+CheckError.IS_NOT_EXIST;
	}

	/**
	 * Gets data not exist.
	 *
	 * @param name the name
	 * @param code the code
	 * @return the data not exist
	 */
	public static String getDataNotExist(String name, String code) {
		return name +":"+code+":"+CheckError.IS_NOT_EXIST;
	}

//	public static String getDataIsNull(Exception e) {
//	}

}
