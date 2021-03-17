package com.pcitc.fms.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;

import com.pcitc.fms.bll.entity.Connections;
import com.pcitc.fms.bll.entity.OrgRelation;
import com.pcitc.fms.bll.entity.Relations;
import com.pcitc.fms.dal.dao.AdministrationDao;
import com.pcitc.fms.dal.dao.CommunityDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.LoadingDockDao;
import com.pcitc.fms.dal.dao.PipeNetworkDao;
import com.pcitc.fms.dal.dao.PlantDao;
import com.pcitc.fms.dal.dao.TankAreaDao;
import com.pcitc.fms.dal.dao.WarehouseDao;
import com.pcitc.fms.dal.pojo.Factory;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.TubulationModelStr;
import com.pcitc.imp.common.exception.BusiException;

import cc.aicode.e2e.ExcelHelper;

/**
 * 各种校验的工具类
 * 
 * @author haiwen.wang
 *
 */
/**
 * Title: CheckUtil
 * Description: TODO task mark zhenqiang.zhao
 * @author zhenqiang.zhao
 * @date 2017年7月6日
 * @version 1.0
 */
/**
 * Title: CheckUtil Description: TODO task mark zhenqiang.zhao
 * 
 * @author zhenqiang.zhao
 * @date 2017年7月20日
 * @version 1.0
 */

public class CheckUtil {

	@Autowired
	private FactoryDao fDao;
	@Autowired
	private AdministrationDao administrationDao;
	@Autowired
	private CommunityDao communityDao;
	@Autowired
	private WarehouseDao warehouseDao;
	@Autowired
	private TankAreaDao tankAreaDao;
	@Autowired
	private PipeNetworkDao pipeNetworkDao;
	@Autowired
	private PlantDao plantDao;
	@Autowired
	private LoadingDockDao loadingDockDao;

	// 匹配由数字、26个英文字母或者下划线组成的字符串
	public static final String regexForCode = "^[0-9a-zA-Z_]{1,}$";
	// 匹配由数字、26个英文字母或者汉字或者下划线组成的字符串，字符串前后允许有空格
	public static final String regexForString = "^\\s*^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$\\s*$";
	// 匹配由数字、点、26个英文字母或者下划线组成的字符串
	public static final String regexForKey = "^[0-9a-zA-Z_\\.\\:-]{1,}$";
	// 匹配汉字、数字、字母、下划线不能以下划线开头和结尾的字符串
	public static final String regexForName = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$";
	// 只允许正整数和零
	public static final String regexForSortNum = "^[0-9]*[1-9][0-9]*$";
	//只允许正整数
	public static final String regexForPositiveInteger ="^((-//d+)|(0+))$";
	// 只允许数字
	public static final String regexForNum = "[0-9]*";
	// 只允许数字
	public static final String regexForNonegative = "^\\s*\\d+(\\.{0,1}\\d+){0,1}\\s*$";
	// 只允许空格
	public static final String regexForBlank = "^\\s*$";
	// 验证是否为正整数、负整数、零
	// public static final String regexForInteger = "^-?[0-9]\\d*$";
	public static final String regexForInteger = "^[1-9]\\d*$";
	// 验证是否为浮点数
	public static final String regexForDouble = "^(-?\\d+)(\\.\\d+)?$";
	// code值的最大长度
	public static final int CODEMAXLENG = 36;
	// 创建者id的最大长度
	public static final int CRTIDMAXLENG = 20;
	// name值的最大长度
	public static final int NAMEMAXLENG = 50;
	// 七天的秒数
	public static final int SEVEN_DAYS = 7 * 24 * 60 * 60;
	// 工厂id序号
	public static final int FACTORYIDNUM = 3;
	// 区域类型序号
	public static final int AREATYPENUM = 4;
	// 区域id序号
	public static final int AREAIDNUM = 5;
	// 节点类型序号
	public static final int NODETYPENUM = 6;
	// 节点id序号
	public static final int NODEIDNUM = 7;
	private static Object factoryDao;

	// 校验长度
	//1-9
	public static Integer valueSize(String id) throws BusinessException {
		if (VerifyUtil.valueSize(id)) {
			return null;
		} else {
			throw new BusinessException("", "", id + ":"
					+ CheckError.VALUE_DICT_ERR);
		}
	}
	//1-6
	public static Integer valueSize1(String id) throws BusinessException {
		if (VerifyUtil.valueSize1(id)) {
			return null;
		} else {
			throw new BusinessException("", "", id + ":"
					+ CheckError.VALUE_DICT_ERR6);
		}
	}
	//1-29
	public static Integer valueSize2(String id) throws BusinessException {
		if (VerifyUtil.valueSize2(id)) {
			return null;
		} else {
			throw new BusinessException("", "", id + ":"
					+ CheckError.VALUE_DICT_ERR7);
		}
	}

	// 匹配空格
	public static boolean validateBlank(String resource) {
        return resource.matches(regexForBlank);
    }
	/**
	 * 校验code
	 * @return
	 */
	private String checkField(String field,String name) throws BusinessException{
	    if(field != null && !"".equals(field)){
	        if (!CheckUtil.checkStringMatchers(field).find()) {
	        throw new BusinessException(CheckError.LOADINGDOCK_CHECK, "", name+"只能由数字字母下划线组成");
	        }
	        if (CheckUtil.checkCodeIsNull(field)) {
	            throw new BusinessException(CheckError.LOADINGDOCK_CHECK, "", name+"不能为空,长度不能大于50");
	        }
	    }  
	        return field.trim();
	}
	
	public static boolean validateSortNum(String resource) {
		return resource.trim().matches("^([1-9]\\d*|[0]{1,1})$");
	}
	public static boolean validateTop(String resource) {
		return resource.trim().matches("^[1-9]+\\d*$");
	} 
	public static boolean validateSkip(String resource) {
		return resource.trim().matches("^([1-9]\\d*|[0]{1,1})$");
	} 
/*
 * 匹配数字1~9
 */
	public static boolean validateNum(String resource) {
		return resource.trim().matches("^([1-9])$");
	}
	/*
	 * 可以匹配的字符串：前后含有空格，字符串由数字、26个英文字母或者下划线组成
	 */
	public static Matcher checkStringMatchers(String field) {
		Matcher matcher = Pattern.compile(regexForName).matcher(field);
		return matcher;
	}

	/*
	 * 匹配非负数
	 */
	public static Matcher checkNumIsNonNnegative(String field) {
		Matcher matcher = Pattern.compile(regexForNonegative).matcher(field);
		return matcher;
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkStringIsNull(String field) {
		if (field == null || field.trim().length() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkCrtMntNameIsNull(String field) {
		if (field == null || field.trim().length() <= 0
				|| field.trim().length() > 16) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkCodeIsNull(String field) {
		if (field == null || field.trim().length() <= 0
				|| field.trim().length() > 49 || "".equals(field)) {
			return true;
		}
		return false;
	}

	public static boolean checkStringIsNotNull(String field, Integer length) {
		if (field == null || field.trim().length() <= 0
				|| field.trim().length() > length || "".equals(field)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkDataTypeIsNull(String field) {
		if (field == null || field.trim().length() <= 0
				|| field.trim().length() > 25) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkNameIsNull(String field) {
		if (field == null || field.trim().length() <= 0
				|| field.trim().length() > 49) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkNameIsNull(String field, int length) {
		if (field == null || field.trim().length() <= 0
				|| field.trim().length() > length) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkSexIsNull(String field) {
		if (field == null || field.trim().length() <= 0
				|| field.trim().length() > 2) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkEmailLength(String field) {
		if (field.trim().length() <= 0 || field.trim().length() > 50) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkPassWordLength(String field) {
		if (field.trim().length() <= 0 || field.trim().length() > 50) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkUserClassIsNull(String field) {
		if (field == null || field.trim().length() <= 0
				|| field.trim().length() > 2) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	public static boolean checkStringIsNotNull(String field) {
		if (field != null && field.trim().length() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param value
	 * @return
	 * @throws BusinessException
	 */
	public static boolean checkDigit(String value) {
		try {
			int value1 = Integer.parseInt(value);
			if (value1 < 0) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否是大于1的整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		/*
		 * Pattern pattern = Pattern.compile("[0-9]*"); return
		 * pattern.matcher(str).matches();
		 */
		return str.matches(regexForNum);
	}

	/**
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static BusinessException checkTimeStamp(String timeStamp,
			String stamp) {
		SimpleDateFormat format = new SimpleDateFormat(stamp);
		BusinessException busException = null;
		if (null == timeStamp) {
			return busException;
		} else {
			try {
				format.setLenient(false);
				// format.parseObject(timeStamp.toString());
				format.parse(timeStamp.toString().trim());
			} catch (ParseException e) {
				busException = new BusinessException("", "",
						CheckError.DATE_ERROR + ":" + timeStamp);
			}
		}
		return busException;
	}

	public static BusinessException checkTimeStamp(Date timeStamp, String stamp) {
		SimpleDateFormat format = new SimpleDateFormat(stamp);
		BusinessException busException = null;
		if (null == timeStamp) {
			return busException;
		} else {
			try {
				format.setLenient(false);
				format.format(timeStamp);
			} catch (Exception e) {
				busException = new BusinessException("", "",
						CheckError.DATE_ERROR + ":" + timeStamp);
			}
		}
		return busException;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean checkIP(String addr) {
		if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
			return false;
		}
		// 判断IP格式和范围
		String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		Pattern pat = Pattern.compile(rexp);
		Matcher mat = pat.matcher(addr);
		return mat.find();
	}

	/**
	 * @Title: filter
	 * @Description: 特殊字符判断
	 */
	public static boolean characterFilter(String character) {
		String rexp = "[^(A-Za-z0-9_\\u4e00-\\u9fa5)]";
		Pattern pat = Pattern.compile(rexp);
		Matcher mat = pat.matcher(character);
		return mat.find();
	}

	public static String checkEnglish(String name, String filter)
			throws BusinessException {
		if (filter != null) {
			if (!VerifyUtil.validateEnglish(filter)) {
				throw new BusinessException("", "", name + ":" + filter + ":"
						+ CheckError.ENGLISH_ERROR);
			}
			return filter.trim();
		}
		return filter;

	}

	public static Integer checkcontractTel(String name, String filter)
			throws BusinessException {
		Integer resource = null;
		if (!VerifyUtil.validateContractTel(filter)
				&& !VerifyUtil.validateFax(filter)) {
			throw new BusinessException("", "", filter + ":"
					+ CheckError.CONTRACTEL_ERROR);
		}
		return resource;

	}

	public static Integer checkfax(String name, String filter)
			throws BusinessException {
		Integer resource = null;
		if (!VerifyUtil.validateFax(filter)) {
			throw new BusinessException("", "", filter + ":"
					+ CheckError.FAX_ERROR);
		}
		return resource;

	}

	public static Integer checkTaxNo(String name, String filter)
			throws BusinessException {
		Integer resource = null;
		if (!VerifyUtil.validateTaxNo(filter)) {
			throw new BusinessException("", "", filter + ":"
					+ CheckError.TAXNO_ERROR);
		}
		return resource;

	}

	/**
	 * @Title: filter
	 * @Description: email格式校验
	 */
	public static boolean checkEmailFilter(String character) {
		String rexp = "/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$/";
		Pattern pat = Pattern.compile(rexp);
		Matcher mat = pat.matcher(character);
		return mat.find();
	}

	/**
	 * @Title: checkMatcher
	 * @Description: 数字字母下划线验证
	 */
	public static Matcher checkMatcher(String field) {
		Matcher matcher = Pattern.compile(regexForCode).matcher(field);
		return matcher;
	}

	/**
	 * @Title: checkMatcher
	 * @Description: 判断enabled只为0,1验证
	 */
	public static Matcher checkEnabledMatcher(String field) {
		Matcher matcher = Pattern.compile("^[0,1]$").matcher(field);
		return matcher;
	}

	/**
	 * @Title: checkMatcher
	 * @Description: 判断enabled只为0,1验证
	 */
	public static Matcher checkIsAdminMatcher(String field) {
		Matcher matcher = Pattern.compile("[0,1]").matcher(field);
		return matcher;
	}

	/**
	 * @Title: checkMatcher
	 * @Description: 判断enabled只为0,1,2验证
	 */
	public static Matcher checkUserClassMatcher(String field) {
		Matcher matcher = Pattern.compile("[0,1,2]").matcher(field);
		return matcher;
	}

	/**
	 * @Title: checkMatcher
	 * @Description: 汉字、数字、字母、下划线验证
	 */
	public static Matcher checkStringMatcher(String field) {
		Matcher matcher = Pattern.compile(regexForName).matcher(field);
		return matcher;
	}

	/**
	 * starttime必须小于endtime
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean compareTime(String startTime, String endTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		format.setLenient(false);
		try {
			Date startDate = format.parse(startTime);
			Date endDate = format.parse(endTime);
			if (startDate.before(endDate)) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * @Title: checkMatcher
	 * @Description: 数字字母下划线验证
	 */
	public static Matcher checkMatcherName(String field) {
		Matcher matcher = Pattern.compile("^[a-zA-Z\\u4e00-\\u9fa5]+$")
				.matcher(field);
		return matcher;
	}

	public static boolean checkIsDateTime(String field) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			format.setLenient(false);
			format.parse(field.trim());
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	/**
	 * 参数值去空格
	 * 
	 * @return
	 */
	public static String trim(String str) {
		String newStr = str;
		if (str == null) {
			return null;
		}
		if (str.contains(" ") || str.contains("　")) {
			newStr = str.replace("　", "").replace(" ", "");
		}
		return newStr;
	}

	/**
	 * 校验url中id
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public static boolean checkId(String id) throws BusinessException {
		if (id == null) {
			throw new BusinessException("", "", "URL中id不能为空");
		}
		if (!CheckUtil.validateSortNum(id)) {
			throw new BusinessException("", "", CheckError.ID_NOT);
		}
		try {
			Integer.parseInt(id);
		} catch (NumberFormatException e) {

			throw new BusinessException("", "", "id不能超过2147483647");
		}
		return true;
	}

	/**
	 * 校验skip和top
	 * top:非负整数,不能为0,可以为null和""
	 * skip:非负整数,可以为0,可以为null和""
	 * @param field
	 * @param name
	 * @return
	 * @throws BusinessException
	 */
	
	public static String checkSkipAndTop(String field, String name)
			throws BusinessException {
		field = field == null ? null : field.trim();
		if (field == null || "".equals(field)) {
			return null;
		} else {
			if (!CheckUtil.validateSortNum(field)) {
				throw new BusinessException("", "", name + "应为正整数");
			}
			try {
				Integer.parseInt(field);
			} catch (NumberFormatException e) {

				throw new BusinessException("", "", name + "的取值2147483647");
			}
			if ("0".equals(field) && "top".equals(name)) {
				throw new BusinessException("", "", "top不能为0");
			}
			return field;
		}
	}
	public static Integer checkTop(String top) throws BusinessException {
		Integer result = null;
		if(StringUtils.isNotEmpty(top)) {
			if (!CheckUtil.validateTop(top)) {
				throw new BusinessException("", "", "top只能为不超过2147483647的正整数！");
			}
			
			try {
				result = Integer.parseInt(top);
			} catch (NumberFormatException e) {
				
				throw new BusinessException("", "", top + "的取值不能超过2147483647");
			}
		}
		return result;
	}
	public static Integer checkSkip(String skip) throws BusinessException {
		Integer result = 0;
		if(StringUtils.isNotEmpty(skip)) {
			if (!CheckUtil.validateSkip(skip)) {
				throw new BusinessException("", "", "skip只能为0和不超过2147483647的正整数");
			}
			
			try {
				result = Integer.parseInt(skip);
			} catch (NumberFormatException e) {
				throw new BusinessException("", "", skip + "的取值不能超过2147483647");
			}
		}
		return result;
	}
	public static boolean checkInput(String top, String skip)
			throws BusinessException {
		boolean check = true;
		// 特殊字符判断
		if (!"".equals(skip)) {
			if (CheckUtil.checkStringIsNotNull(skip)
					&& CheckUtil.characterFilter(skip)) {
				check = false;
				throw new BusinessException("", "", CheckError.SKIP);
			}
			if (CheckUtil.checkStringIsNotNull(skip)
					&& !CheckUtil.checkDigit(skip)) {
				check = false;
				throw new BusinessException("", "", CheckError.SKIP);
			}
			if (CheckUtil.checkStringIsNotNull(skip)
					&& Integer.parseInt(skip) < 0) {
				check = false;
				throw new BusinessException("", "", CheckError.SKIP);
			}
			if (null != skip && Integer.parseInt(skip) > 2147483647) {
				check = false;
				throw new BusinessException("", "", CheckError.SKIP);
			}
		}
		// 特殊字符判断
		if (!"".equals(top)) {
			if (CheckUtil.checkStringIsNotNull(top)
					&& CheckUtil.characterFilter(top)) {
				check = false;
				throw new BusinessException("", "", CheckError.TOP);
			}
			if (CheckUtil.checkStringIsNotNull(top)
					&& !CheckUtil.checkDigit(top)) {
				check = false;
				throw new BusinessException("", "", CheckError.TOP);
			}
			if (CheckUtil.checkStringIsNotNull(top)
					&& Integer.parseInt(top) <= 0) {
				check = false;
				throw new BusinessException("", "", CheckError.TOP);
			}
			if (null != top && Integer.parseInt(top) > 2147483647) {
				check = false;
				throw new BusinessException("", "", CheckError.TOP);
			}
		}
		/*
		 * if (CheckUtil.checkStringIsNotNull(nameList)) { //校验maneList
		 * checkNameList(nameList); }
		 */
		return check;
	}

	/**
	 * 将字符穿转换为integer
	 * 
	 * @param filter
	 * @return
	 * @throws BusinessException
	 */
	public static Integer strToInt(String name, String filter)
			throws BusinessException {
		Integer resource = null;
		if (null != filter) {
			if (VerifyUtil.validateIntegerFormat(filter)) {
				resource = Integer.valueOf(filter);
			} else {
				throw new BusinessException("", "", name + ":" + filter + ":"
						+ CheckError.STR_TO_INT_ERROR);
			}
		}
		return resource;

	}

	/**
	 * 将enabled校验
	 * 
	 * @param filter
	 * @return
	 * @throws BusinessException
	 */
	public static Integer enabled(String name, String filter)
			throws BusinessException {
		Integer resource = null;
		if (null != filter) {
			if (VerifyUtil.validateEnabled(filter)) {
				resource = Integer.valueOf(filter);
			} else {
				throw new BusinessException("", "", filter + ":"
						+ CheckError.CHECK_ERROR);
			}
		}
		return resource;

	}

	
	// --- 把codes字符串集合转换成为List
	public static List<String> checkCodeList(String codes)
			throws BusinessException {
		List<String> codeList = new ArrayList<>();
		if (codes != null) {
			String[] arrCodes = codes.split(",", -1);
			for (String code : arrCodes) {
				String codet=code.trim();
				if (VerifyUtil.validateCode(codet)) {
					if (!"".equals(codet)) {
						codeList.add(codet);
					}
				} else {
					throw new BusinessException("", "", CheckError.CODELIST_ERR);
				}
			}
		}
		return codeList;
	}

	/**
	 * 忽略code为空或空格
	 * 
	 * @param codes
	 * @return
	 * @throws BusinessException
	 */
	public static List<String> checkCodeLists(String codes)
			throws BusinessException {
		List<String> codeList = new ArrayList<>();
		if (codes != null) {
			String[] arrCodes = codes.split(",", -1);
			for (String code : arrCodes) {
				if (code == null || "".equals(code.trim())) {
					continue;
				}
				if (!VerifyUtil.validateCodeFormat(code.trim())) {
					throw new BusinessException("", "", CheckError.CODELIST_ERR);
				}
				codeList.add(code.trim());
			}
		}
		return codeList;
	}

	public static boolean checkIds(String id) throws BusinessException {
		if (!CheckUtil.validateSortNum(id)) {
			throw new BusinessException("", "", CheckError.IDLIST_ERR);
		}
		try {
			Integer.parseInt(id);
		} catch (NumberFormatException e) {

			throw new BusinessException("", "", "idList中id不能超过2147483647");
		}
		return true;
	}
	/*
	 * 判断是否为数字
	 */
	public static boolean checkNumber(String id) {
		if (!CheckUtil.validateSortNum(id)) {
			return false;
		}
		return true;
	}

	public static List<Integer> checkIdLists(String ids)
			throws BusinessException {
		List<Integer> idList = new ArrayList<>();
		if (ids != null) {
			String[] arrIds = ids.toString().split(",", -1);
			for (String id : arrIds) {
				if (id == null || "".equals(id.trim())) {
					continue;
				}
				if (checkIds(id.trim())) {
					idList.add(Integer.parseInt(id.trim()));
				}
			}
		}
		return idList;
	}

	/**
	 * 将ids字符串集合转换成为idList
	 * 
	 * @param ids
	 * @return
	 * @throws BusinessException
	 */
	public static List<Integer> checkIdList(String ids)
			throws BusinessException {
		List<Integer> idList = new ArrayList<>();
		if (ids != null) {
			String[] arrIds = ids.toString().split(",", -1);
			for (String id : arrIds) {
				if (CheckUtil.checkStringIsNotNull(id)
						&& CheckUtil.checkDigit(id)) {
					idList.add(Integer.parseInt(id));
				} else {
					throw new BusinessException("", "", CheckError.IDLIST_ERR);
				}
			}
		}
		return idList;
	}

	/**
	 * @Title: validateIdIntegerException
	 * @Description: 校验id是否为integer类型
	 * @param name
	 * @param id
	 * @return integer
	 * @throws BusinessException
	 * @date 2017年6月15日
	 * @return: Integer
	 * @author zhenqiang.zhao
	 */
	public static Integer validateIdIntegerException(String name, String id)
			throws BusinessException {
		if (VerifyUtil.validateIntegerFormat(id)) {
			try {
				return Integer.valueOf(id);
			} catch (Exception e) {
				throw new BusinessException("", "", name + ":"
						+ CheckError.INTEGER_RANGE_ERR);
			}

		} else {
			throw new BusinessException("", "", name + ":"
					+ CheckError.INTEGER_ERR);
		}
	}

	/**
	 * @Title: validateIdIntegerMybeNullException
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param name
	 * @param id
	 *            校验id是否可以转换为Integer类型,可以为null,例如enable 条件查询
	 * @return
	 * @throws BusinessException
	 * @date 2017年7月20日
	 * @return: Integer
	 * @author zhenqiang.zhao
	 */
	public static Integer validateIdIntegerMybeNullException(String name,
			String id) throws BusinessException {
		if (!"".equals(id) && id != null) {
			Integer value = null;
			if (VerifyUtil.validateIntegerFormat(id)) {
				try {
					value = Integer.valueOf(id);
				} catch (Exception e) {
					throw new BusinessException("", "", name + ":大小不能超过"+Integer.MAX_VALUE+"");
				}
				
			} else {
				throw new BusinessException("", "", name + ":"
						+ CheckError.INTEGER_ERR);
			}
			return value;
		}
		return null;
	}

	// 非 负数,校验
	public static Integer validateNegativeIntegerFormat(String name, String id)
			throws BusinessException {
		if (VerifyUtil.validateNegativeIntegerFormat(id)) {
			return Integer.valueOf(id);
		} else {
			throw new BusinessException("", "", name + ":"
					+ CheckError.INTEGER_ERR);
		}
	}

	/**
	 * @Title: validateNegativeTopIntegerFormat
	 * @Description: top 不能为0,并指定范围和格式
	 * @param name
	 * @param id
	 * @return
	 * @throws BusinessException
	 * @date 2017年7月28日
	 * @return: Integer
	 * @author zhenqiang.zhao
	 */
	public static Integer validateNegativeTopIntegerFormat(String name,
			String id) throws BusinessException {
		if (VerifyUtil.validateNegativeIntegerFormat(id)) {
			try {
				if (0 == Integer.valueOf(id)) {
					throw new BusinessException("", "", name + ":"
							+ CheckError.INTEGER_RANGE_ZERO_ERR);
				}
			} catch (NumberFormatException e) {
				throw new BusinessException("", "", name + ":"
						+ CheckError.INTEGER_RANGE_ONE_ERR);
			}
			return Integer.valueOf(id);
		} else {
			throw new BusinessException("", "", name + ":"
					+ CheckError.INTEGER_ERR);
		}
	}

	// 正整数校验
	public static Integer validatePositiveIntegerFormat(String name, String data)
			throws BusinessException {
		// try {
		// int rslt = Integer.parseInt(data);
		// if (rslt > 0) {
		// return rslt;
		// } else {
		// throw new BusinessException("","",name + CheckError.INTEGER_ERR);
		// }
		// } catch(Exception e) {
		// throw new BusinessException("","",name + CheckError.INTEGER_ERR);
		// }
		if (VerifyUtil.validatePositiveIntegerFormat(data)) {
			return Integer.valueOf(data);
		} else {
			throw new BusinessException("", "", name + CheckError.INTEGER_ERR);
		}
	}
	// 正整数校验返回long型
	public static Long validatePositiveLongFormat(String name, String data)
			throws BusinessException {
		// try {
		// int rslt = Integer.parseInt(data);
		// if (rslt > 0) {
		// return rslt;
		// } else {
		// throw new BusinessException("","",name + CheckError.INTEGER_ERR);
		// }
		// } catch(Exception e) {
		// throw new BusinessException("","",name + CheckError.INTEGER_ERR);
		// }
		if (VerifyUtil.validatePositiveIntegerFormat(data)) {
			return Long.valueOf(data).longValue();
		} else {
			throw new BusinessException("", "", name + CheckError.INTEGER_ERR);
		}
	}

	/**
	 * Validate negative integer mybe null format integer.
	 *非 负数,校验,可以为空 //可以为0
	 * @param name the name
	 * @param id the id
	 * @return the integer
	 * @throws BusinessException the business exception
	 */
	public static Integer validateNegativeIntegerMybeNullFormat(String name,
			String id) throws BusinessException {
		if ("".equals(id)  || id == null) {
			return null;
		}else{	if (VerifyUtil.validateNegativeIntegerFormat(id)) {
			Integer i=0;
			try {
				i=Integer.valueOf(id);
			} catch (NumberFormatException e) {
				throw new BusinessException("", "", name + ":"
						+ CheckError.INTEGER_RANGE_ONE_ERR);
			}
			return i;
		} else {
			throw new BusinessException("", "", name + ":"
					+ CheckError.INTEGER_ERR);
		}

			}
	}
	
	public static Integer validateTopIntegerMybeNullFormat(String name,
			String id) throws BusinessException {
		if ("".equals(id)  || id == null) {
			return null;
		}else{	if (VerifyUtil.validateTopIntegerFormat(id)) {
			Integer i=0;
			try {
				i=Integer.valueOf(id);
			} catch (NumberFormatException e) {
				throw new BusinessException("", "", name + ":"
						+ CheckError.INTEGER_RANGE_ONE_ERR);
			}
			return i;
		} else {
			throw new BusinessException("", "", name + ":只能为正整数！"
					);
		}

			}
	}

	/**
	 * @Title: validateIdOfIdListIntegerException
	 * @Description: 检验id,空格不校验,该方法主要用于返回idlist所用
	 * @param name
	 * @param id
	 * @return
	 * @throws BusinessException
	 * @date 2017年7月20日
	 * @return: Integer
	 * @author zhenqiang.zhao
	 */
	public static Integer validateIdOfIdListIntegerException(String name,
			String id) throws BusinessException {
		if ("".equals(id)) {
			return new Integer(-1);
		}
		if (VerifyUtil.validateIntegerFormat(id)) {
			return Integer.valueOf(id);
		} else {
			throw new BusinessException("", "", name + ":"
					+ CheckError.INTEGER_ERR);
		}
	}

	/**
	 * @Title: validateCodeException
	 * @Description: 如果code为空或者格式不正确,则抛出异常
	 * @param code
	 * @throws BusinessException
	 * @date 2017年5月25日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public static String validateCodeException(String name, String code)
			throws BusinessException {
		if (VerifyUtil.validateCodeFormat(code)) {
			return code.trim();
		} else {
			throw new BusinessException("", "", name + ":" + code + ":"
					+ CheckError.CODE_ERR);

		}
	}

	public static String validateCodeStringMybeNullException(String name,
			String code) throws BusinessException {
		if (!"".equals(code) && code != null) {
			if (VerifyUtil.validateCodeFormat(code)) {
				return code.trim();
			} else {
				throw new BusinessException("", "", name + ":"
						+ CheckError.CODE_ERR);
			}
		}
		return null;
	}

	/**
	 * @Title: validateCodeOfCodeListException
	 * @Description: 空格不校验,String字符串转为list ,将空格去掉
	 * @param name
	 * @param code
	 * @return
	 * @throws BusinessException
	 * @date 2017年7月20日
	 * @return: String
	 * @author zhenqiang.zhao
	 */
	public static String validateCodeOfCodeListException(String name,
			String code) throws BusinessException {
		if ("".equals(code)) {
			return null;
		}
		if (VerifyUtil.validateCodeFormat(code)) {
			return code.trim();
		} else {
			throw new BusinessException("", "", name + ":" + code + ":"
					+ CheckError.CODE_ERR);
		}
	}

	public static BusinessException validateCodeException(String name,
			String code, int length) {
		BusinessException busException = null;
		if (!VerifyUtil.validateCodeFormat(code)) {
			busException = new BusinessException("", "", name + ":" + code
					+ ":" + CheckError.CODE_ERR);
		}
		if (null != code && !"".equals(code) && code.length() > length) {
			busException = new BusinessException("", "", name + ":" + code
					+ ":" + CheckError.VALUE_LENGTH + length);
		}
		return busException;
	}
	
	public static BusinessException validateCodeForRentException(String name,
			String code, int length) {
		BusinessException busException = null;
		if (!VerifyUtil.validateCodeFormatForRent(code)) {
			busException = new BusinessException("", "", name + ":" + code
					+ ":" + CheckError.CODE_ERR_FORRENT);
		}
		if (null != code && !"".equals(code) && code.length() > length) {
			busException = new BusinessException("", "", name + ":" + code
					+ ":" + CheckError.VALUE_LENGTH + length);
		}
		return busException;
	}
	
	public static BusinessException validatesourceDataTypeException(String code, int length) {
		BusinessException busException = null;
		if (null != code && !"".equals(code) && code.length() > length) {
			busException = new BusinessException("", "", "sourceDataType:" + code
					+ ":" + CheckError.VALUE_LENGTH + length);
		}
		return busException;
	}

	public static BusinessException validateNameException(String name,
			String nameValue, int length) {
		BusinessException busException = null;
		if (!VerifyUtil.validateNameFormat(nameValue)) {
			busException = new BusinessException("", "", name + ":" + nameValue
					+ ":" + CheckError.NAME_ERR);
		}
		if (null != nameValue && !"".equals(nameValue)
				&& nameValue.length() > length) {
			busException = new BusinessException("", "", name + ":" + nameValue
					+ ":" + CheckError.VALUE_LENGTH + length);
		}
		return busException;
	}

	/**
	 * @Title: validateNameStrMayBeNullException
	 * @Description: 对name类型进行校验,可以为null的字段,进行特殊字符校验
	 * @param name
	 *            报错的具体字段名称
	 * @param nameValue
	 *            具体的值
	 * @param length
	 *            限制长度
	 * @return
	 * @date 2017年7月6日
	 * @return: BusinessException
	 * @author zhenqiang.zhao
	 */
	public static BusinessException validateNameStrMayBeNullException(
			String name, String nameValue, int length) {
		BusinessException busException = null;
		if (null != nameValue && !"".equals(nameValue)) {
			if (!VerifyUtil.validateNameFormat(nameValue)) {
				busException = new BusinessException("", "", name + ":"
						+ nameValue + ":" + CheckError.NAMEMAYBENULL_ERR);
			}
			if (null != nameValue && !"".equals(nameValue)
					&& nameValue.length() > length) {
				busException = new BusinessException("", "", name + ":"
						+ nameValue + ":" + CheckError.VALUE_LENGTH + length);
			}
		}
		return busException;
	}

	public static BusinessException validateNameStrMayBeNullException(
			String name, String nameValue) {
		BusinessException busException = null;
		if (null != nameValue && !"".equals(nameValue)) {
			if (!VerifyUtil.validateNameFormat(nameValue)) {
				busException = new BusinessException("", "", name + ":"
						+ nameValue + ":" + CheckError.NAME_ERR);
			}
		}
		return busException;
	}

	/**
	 * @Title: validateintMaybeNullException
	 * @Description: 对可以为null 的 Integer字段进行校验,是否为Integer类型
	 * @param name
	 *            报错的具体字段名称
	 * @param value
	 *            被校验的值
	 * @return
	 * @date 2017年7月6日
	 * @return: BusinessException
	 * @author zhenqiang.zhao
	 */
	public static BusinessException validateintMaybeNullException(String name,
			Integer value) {
		BusinessException busException = null;
		if (null != value) {
			if (!VerifyUtil.validateIntegerFormat(Integer.toString(value
					.intValue())))
				busException = new BusinessException("", "", name + ":"
						+ CheckError.INTEGER_ERR);
		}
		return busException;
	}
	/**
	 * @Title: validateintMaybeNullException
	 * @Description: 对可以为null 的 Long字段进行校验,是否为Lng类型
	 * @param name
	 *            报错的具体字段名称
	 * @param value
	 *            被校验的值
	 * @return
	 * @date 2017年7月6日
	 * @return: BusinessException
	 * @author zhenqiang.zhao
	 */
	public static BusinessException validateintMaybeNullAndLongException(String name,
			Long value) {
		BusinessException busException = null;
		if (null != value) {
			if (!VerifyUtil.validateIntegerFormat(Long.toString(value
					.intValue())))
				busException = new BusinessException("", "", name + ":"
						+ CheckError.INTEGER_ERR);
		}
		return busException;
	}

	public static BusinessException validateLengthException(String name,
			String nameValue, int length) {
		BusinessException busException = null;
		if (nameValue != null) {
			// if(nameValue.contains("<script") ||
			// nameValue.contains("</script>"))busException = new
			// BusinessException("","",name+":"+nameValue+":"+CheckError.SCRIPT_ERROR);
			if (!"".equals(nameValue) && nameValue.length() > length) {
				busException = new BusinessException("", "", name + ":"
						+ nameValue + ":" + CheckError.VALUE_LENGTH + length);
			}
		}
		return busException;
	}

	/**
	 * @Title: validateIdException
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param name
	 * @param value
	 * @return
	 * @date 2017年7月20日
	 * @return: BusinessException
	 * @author zhenqiang.zhao
	 */
	public static BusinessException validateIdException(String name,
			Integer value) {
		BusinessException busException = null;
		if (!VerifyUtil.validateIntegerFormat((null == value) ? null : Integer
				.toString(value.intValue())))
			busException = new BusinessException("", "", name + ":"
					+ CheckError.INTEGER_ERR);
		return busException;
	}

	// validateNegativeIntegerFormat
	public static BusinessException validateNegativeIdException(String name,
			Integer value) {
		BusinessException busException = null;
		if (!VerifyUtil.validateNegativeIntegerFormat((null == value) ? null
				: Integer.toString(value.intValue())))
			busException = new BusinessException("", "", name + ":"
					+ CheckError.INTEGER_ERR);
		return busException;
	}

	public static BusinessException validateEnabled(String name, Integer value) {
		BusinessException busException = null;
	   if (null != value) {
			if (!VerifyUtil.validateFlag(Integer.toString(value.intValue())))
				busException = new BusinessException("", "", name + ":"
						+ CheckError.CHECK_ERROR);
			if (value != 1 && value != 0)
				busException = new BusinessException("", "", name + ":"
						+ CheckError.CHECK_ERROR);
		}
		return busException;
	}
	//Long 检验
	public static BusinessException validateEnabledInLong(String name, Long value) {
		BusinessException busException = null;
		if (null != value) {
			if (!VerifyUtil.validateFlag(Long.toString(value.intValue())))
				busException = new BusinessException("", "", name + ":"
						+ CheckError.FLAG_ERR);
			if (value != 1 && value != 0)
				busException = new BusinessException("", "", name + ":"
						+ CheckError.FLAG_ERR);
		}
		return busException;
	}

	/**
	 * @Title: validateNameException
	 * @Description: 验证name:汉字、数字、字母、下划线不能以下划线开头和结尾的字符串
	 * @param name
	 * @param resource
	 * @return
	 * @throws BusinessException
	 * @date 2017年6月9日
	 * @return: String
	 * @author zhenqiang.zhao
	 */
	public static String validateNameException(String name, String resource)
			throws BusinessException {
		if (VerifyUtil.validateNameFormat(resource.toString())) {
			return resource.trim();
		} else {
			throw new BusinessException("", "", name + ":" + resource + ":"
					+ CheckError.NAME_ERR);

		}
	}

	/**
	 * @Title: buildStringToListInteger
	 * @Description: TODO
	 * @param codes
	 * @return
	 * @throws BusinessException
	 * @date 2017年5月25日
	 * @return: List<Integer>
	 * @author zhenqiang.zhao
	 */
	public static List<Integer> buildStringToListInteger(String codes)
			throws BusinessException {
		List<Integer> strData = new ArrayList<>();
		if (null != codes) {
			String[] $codeList = codes.split(",");
			if (!"".equals(codes) && $codeList.length > 0) {
				for (int i = 0; i < $codeList.length; i++) {
					try {
						strData.add(validateIdIntegerException("",
								trim($codeList[i])));
					} catch (BusinessException e) {
						throw new BusinessException("", "", CheckError.ID_LIST
								+ ":" + trim($codeList[i]) + ":"
								+ CheckError.INTEGER_ERR);
					}
				}
			}
		} else {
			throw new BusinessException("", "", "$idList"
					+ CheckError.IS_NOT_EMPTY);
		}
		return strData;
	}

	/**
	 * @Title: buildStringToListInteger
	 * @Description: 校验Integer类型和返回list 去掉ids 中的空格
	 * @param name
	 * @return
	 * @throws BusinessException
	 * @date 2017年5月26日
	 * @return: List<Integer>
	 * @author zhenqiang.zhao
	 */
	public static List<Integer> buildStringToListInteger(String name, String ids)
			throws BusinessException {
		if (null != ids) {
			List<Integer> strData = new ArrayList<>();
			String[] $codeList = ids.split(",");
			if (!"".equals(ids) && $codeList.length > 0) {
				for (int i = 0; i < $codeList.length; i++) {
					try {
						strData.add(validateIdOfIdListIntegerException("",
								trim($codeList[i])));
					} catch (BusinessException e) {
						throw new BusinessException("", "", name + ":"
								+ trim($codeList[i]) + ":"
								+ CheckError.INTEGER_ERR);
					}
				}
			}
			return strData;
		}
		return null;

	}

	/**
	 * @Title: buildStringToList
	 * @Description: String字符串转为list
	 * @param name
	 * @param codes
	 * @return
	 * @throws BusinessException
	 * @date 2017年6月15日
	 * @return: List<String>
	 * @author zhenqiang.zhao
	 */
	public static List<String> buildStringToList(String name, String codes)
			throws BusinessException {
		if (null != codes) {
			List<String> strData = new ArrayList<>();
			String[] $codeList = codes.split(",");
			if (!"".equals(codes) && $codeList.length > 0) {
				for (int i = 0; i < $codeList.length; i++) {
					try {
						strData.add(validateCodeException(name,
								trim($codeList[i])));
					} catch (BusinessException e) {
						throw new BusinessException("", "",
								CheckError.CODE_LIST + ":" + trim($codeList[i])
										+ ":" + CheckError.CODE_ERR);
					}
				}
				return strData;
			} else {
				return null;
			}
		}
		return null;

	}

	/**
	 * @Title: buildStringToListString
	 * @Description: String字符串转为list ,将空格去掉
	 * @param name
	 * @param codes
	 * @return
	 * @throws BusinessException
	 * @date 2017年7月20日
	 * @return: List<String>
	 * @author zhenqiang.zhao
	 */
	public static List<String> buildStringToListString(String name, String codes)
			throws BusinessException {
		if (null != codes) {
			List<String> strData = new ArrayList<>();
			String[] $codeList = codes.split(",");
			if (!"".equals(codes) && $codeList.length > 0) {
				for (int i = 0; i < $codeList.length; i++) {
					try {
						if (null != validateCodeOfCodeListException(name,
								trim($codeList[i]))) {
							strData.add(validateCodeOfCodeListException(name,
									trim($codeList[i])));
						}
					} catch (BusinessException e) {
						throw new BusinessException("", "",
								CheckError.CODE_LIST + ":" + trim($codeList[i])
										+ ":" + CheckError.CODE_ERR);
					}
				}
				return strData;
			} else {
				return null;
			}
		}
		return null;

	}

	/**
	 * @Title: buildStringToListString
	 * @Description: String字符串转为list ,将空格去掉
	 * @param name
	 * @return
	 * @throws BusinessException
	 * @date 2017年7月20日
	 * @return: List<String>
	 * @author zhenqiang.zhao
	 */
	public static List<String> buildStringToListStringNoCheck(String name,
			String list) throws BusinessException {
		if (null != list) {
			List<String> strData = new ArrayList<>();
			String[] $codeList = list.split(",");
			if (!"".equals(list) && $codeList.length > 0) {
				for (int i = 0; i < $codeList.length; i++) {
					if (null != trim($codeList[i])) {
						strData.add(trim($codeList[i]));
					}
				}
				return strData;
			} else {
				return null;
			}
		}
		return null;

	}

	public static <T> List removeBlankEntity(List<T> list)
			throws IllegalArgumentException, IllegalAccessException {
		List<T> lists = new ArrayList<>();
		for (T t : list) {
			Class clazz = t.getClass();

			Field[] fields = clazz.getDeclaredFields();
			int i = 0;
			for (Field field : fields) {
				field.setAccessible(true);
				Object object = field.get(t);
				String name = field.getName();
				if (object == null) {
					i++;
				} else {
					String str = object.toString();
					if ("".equals(str.trim())|| str.isEmpty()) {
						i++;
					}
					if (name.equals("serialVersionUID")) {
						i++;
					}

				}
			}
			if (i != fields.length) {
				lists.add(t);
			}
		}
		return lists;
	}

	/**
	 * @Title: getUniqueOrgRelationException
	 * @Description: 提示哪一条记录重复
	 * @date 2017年7月25日
	 * @return: BusinessException
	 * @author zhenqiang.zhao
	 */
	public static BusinessException getUniqueOrgRelationException(
			OrgRelation orgRelationEnt) {
		BusinessException busException = null;
		if (null != orgRelationEnt) {
			busException = new BusinessException("", "", CheckError.IS_EXIST_
					+ ":orgRelationId= " + orgRelationEnt.getOrgRelationId());
		}
		return busException;
	}

	/**
	 * @Title: getUniqueConnectionsException
	 * @Description: 连接的唯一性校验
	 * @param connectionsEnt
	 * @return
	 * @date 2017年8月10日
	 * @return: BusinessException
	 * @author 赵振强
	 */
	public static BusinessException getUniqueConnectionsException(
			Connections connectionsEnt) {
		BusinessException busException = null;
		if (null != connectionsEnt) {
			busException = new BusinessException("", "", CheckError.IS_EXIST_
					+ ":connectionId= " + connectionsEnt.getConnectionId());
		}
		return busException;
	}

	/**
	 * @Title: getUniqueRelationsException
	 * @Description: 关联的唯一性校验
	 * @param relationsEnt
	 * @return
	 * @date 2017年8月10日
	 * @return: BusinessException
	 * @author 赵振强
	 */
	public static BusinessException getUniqueRelationsException(
			Relations relationsEnt) {
		BusinessException busException = null;
		if (null != relationsEnt) {
			busException = new BusinessException("", "", CheckError.IS_EXIST_
					+ ":relationsId= " + relationsEnt.getRelationId());
		}
		return busException;
	}

	/**
	 * @Title: getUrlNameByTargetType
	 * @Description: 返回相应的url目标路径中目标类型名称
	 * @param targetType
	 * @return
	 * @date 2017年8月3日
	 * @return: String
	 * @author zhenqiang.zhao
	 */
	public static String getUrlNameByTargetType(String targetType) {
		if ("Plant".equals(targetType)) {// 装置
			return "plants";
		} else if ("TankArea".equals(targetType)) {// 罐区
			return "tankAreas";
		} else if ("Warehouse".equals(targetType)) {// 仓库
			return "warehouses";
		} else if ("LoadingDock".equals(targetType)) {// 装卸台
			return "loadingDocks";
		} else if ("Administration".equals(targetType)) {// 办公区
			return "administrations";
		} else if ("Community".equals(targetType)) {// 生活区
			return "communities";
		} else if ("PipeNetwork".equals(targetType)) {// 管网
			return "pipeNetworks";
		} else {
			return targetType;
		}
	}

	/**
	 * 将对象转换成 String 。
	 * 
	 * @param obj
	 *            :
	 * @return String : 如果对象为NULL,则返回NULL.
	 * @see <br>
	 *      author 赵振强
	 */
	public static String stringValue(Object obj) {
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	public static void checkCode(String field) throws BusinessException {
		if (field == null || field.trim().length() <= 0
				|| field.trim().length() > 49 || "".equals(field)) {
			throw new BusinessException("", "", "code不能为空");
		}
		if (!checkStringMatchers(field).find()) {
			throw new BusinessException("", "", CheckError.CODE_NOT);
		}
	}
	/*
	 * 获取url中所需字段
	 */
	public static String getParams(String url, String params) {
		String[] strs = url.split("/");
		String result = null;
		switch (params) {
		case"factoryType":
			result = strs[2];
			break;
		case"factoryCode":
			result = strs[3];
			break;
		case"areaType":
			result = strs[4];
			break;
		case"areaCode":
			result = strs[5];
			break;
		case"nodeType":
			result = strs[6];
			break;
		case"nodeCode":
			result = strs[7];
			break;
		}
		
		return result;
	}
	/**   
	 * @Title: getUriEndType   
	 * @Description: 新增区域或者节点时 ，获取url末尾的关键字
	 * @param uri
	 * @return
	 * @date 2017年11月2日      
	 * @return: String
	 */
	
	public static String getUriEndType(String uri,String updateCode) {
			 uri = subUrl(uri);
			 uri = uri.substring(0, uri.lastIndexOf(updateCode)-1);
			return getUriEndType(uri);
	}
	public static String getUriEndType(String uri) {
			uri = subUrl(uri);
			return uri.substring(uri.lastIndexOf("/")+1, uri.length());
	}
	/*url中有问号的情况*/
	private static String subUrl(String uri) {
		if(uri.contains("?"))
			uri = uri.substring(0, uri.lastIndexOf("?"));
		return uri;
	}
	/**
	 * 从url中获取区域类型 "/"和"?"之间的
	 * @param uri
	 * @return
	 */
	public static String getUrlType(String uri) {
		if(uri.contains("?")) {
			uri = uri.substring(0, uri.lastIndexOf("?"));
		}
	    return uri.split("/")[4];
	}
	/**韩啸
	 * 校验String类型的参数
	 * @param name 字段名称
	 * @param param 字段值
	 * @param length 字段长度
	 * @param flag 是否可以null，true表示可以，false表示不可以
	 * @return 最终处理结果  
	 * @throws BusinessException
	 */
	public static String getParamForString (String name,String param,Integer length,Boolean flag) throws BusinessException{
		//判断是否可以空
		if((param == null || param.isEmpty())) {
			if(flag) {
				return null;
			}else {
				throw new BusinessException("", "", name + ":" + param + ":不能为空！");
				
			}
		}	
		if (VerifyUtil.validateNameFormat(param.trim())) {
			if(param.length() > length) {
				throw new BusinessException("", "", name + ":" + param + ":字段长度不能大于"+length+"位！");
			}
				return param.trim();
		} else {
			throw new BusinessException("", "", name + ":" + param + ":不能含有特殊字符");

		}
	}
	//韩啸   校验传入参数，并转为int
	public static Integer getParamForInteger (String name,String param,Integer length,Boolean flag) throws BusinessException{
		Integer id = null;
		//判断是否可以空
		if((param == null)) {
			if(flag) {
				return null;
			}else {
				throw new BusinessException("", "", name + ":" + param + ":不能为空！");
				
			}
		}
		if (CheckUtil.validateSortNum(param)) {
			if(param.length() > length) {
				throw new BusinessException("", "", name + ":" + param + ":字段长度不能大于"+length+"位！");
			}
				return Integer.parseInt(param.trim());
		} else {
			throw new BusinessException("", "",  name + ":" + param + ":只能为零和正整数！");

		}
		
	}
	public static Integer checkEnabled(String enabled) throws  com.pcitc.fms.exception.BusinessException {
		if(enabled != null) {
			if(enabled.isEmpty()) {
				return null;
			}
			if(!enabled.equals("1") && !enabled.equals("0") && !enabled.equals("-1")) {
				throw new BusinessException("","", "enabled只能为0，1！");
			}
			return Integer.parseInt(enabled);
		}
		return null;
	}
	public static Integer checkInUse(String inuse) throws  com.pcitc.fms.exception.BusinessException {
		if(inuse != null) {
			if(inuse.isEmpty()) {
				return null;
			}
			if(!inuse.equals("1") && !inuse.equals("0") && !inuse.equals("-1")) {
				throw new BusinessException("","", "inUse只能为0，1！");
			}
			return Integer.parseInt(inuse);
		}
		return null;
	}
	public static void checkEnabled(Integer enabled) throws BusiException {
		if(enabled == null) {
			throw new BusiException("", "enabled不能为空！");
		}
		if(!enabled.equals(1) && !enabled.equals(0)) {
			throw new BusiException("", "enabled只能为0或1！");
		}

	}
	public static BusinessException validateIdExceptions(String name, Integer value) {
		BusinessException busException = null;
		if (!VerifyUtil.validateIntegerTypeFormat((null == value) ? null : Integer
				.toString(value.intValue())))
			busException = new BusinessException("", "", name + ":"
					+ CheckError.INTEGER_ERR);
		return busException;
	}
	public static Exception catchException(Exception e) {
		if(e.toString().contains("AK_PM_ARERALIAS")) {
			return new Exception("shortName不能重复！");
		}
		else if(e.toString().contains("AK_PM_ARERCODE") || e.toString().contains("UK_CODE")) {
			return new Exception("code不能重复！");
		}
		else if(e.toString().contains("AK_PM_ARERNAME") || e.toString().contains("UK_NAME")) {
			return new Exception("name不能重复！");
		}
		else if(e.toString().contains("FK_PM_AREA_REF_AREATYPE")) {
			return new Exception("不存在此类型的区域！");
		}
		else if(e.toString().contains("FK_PM_AREA_REF_FACTORY")) {
			return new Exception("不存在此类型的机构单元！");
		}
		else if(e.toString().contains("REF_TECHNIC")|| e.toString().contains("REF_WARETYPE")) {
			return new Exception("不存在此类工艺类型！");
		}
		else if(e.toString().contains("REF_UNITTYPE") || e.toString().contains("REF_TANKAREATYPE") || e.toString().contains("REF_WARETYPE")) {
			return new Exception("不存在此业务类型！");
		}
		else if(e.toString().contains("UK_STAALGR_NAME")) {
			return new Exception("name在数据库已存在，不能重复!");
		}
		else if(e.toString().contains("UK_STAALGR_CODE")) {
			return new Exception("code在数据库已存在，不能重复!");
		}
		else if(e.toString().contains("FK_T_OPM_STAALGR_R_T_OPM_MONLE") || e.toString().contains("FK_T_OPM_STAALGR_R_T_OPM_MONLE")) {
			return new Exception("不存在此类监控级别");
		}
		else if(e.toString().contains("FK_T_OPM_STAALGR_R_T_OPM_STAAL")) {
			return new Exception("不存在此类操作平稳率算法");
		}
		else if(e.toString().contains("FK_T_OPM_STAALGR_R_T_OPM_EQUIP") || e.toString().contains("FK_T_OPM_STAALGR_R_T_OPM_EQUIP")) {
			return new Exception("不存在此装置ID");
		}
		else if(e.toString().contains("FK_T_OPM_STAALGR_R_T_OPM_CRAFT")) {
			return new Exception("不存在此工艺方案");
		}
		else if(e.toString().contains("FK_T_OPM_STAALGR_R_STAALGRCONF")) {
			return new Exception("不存在此平稳率配置");
		}
		else if(e.toString().contains("UK_STAALGRCONFITEM_OPEINDEX_ID")) {
			return new Exception("新增数据的操作平稳率计算配置ID, 操作指标已经存在");
		}
		else if(e.toString().contains("UK_STAALGRCONFITEM_1")) {
			return new Exception("数据的操作平稳率计算配置ID和名称已经存在");
		}else if(e.toString().contains("UK_STAALGRCONF_EQUIP_ID")) {
			return new Exception("此设备和监控级别已经存在");
		}
		return e;
	}

	/**
	 * Gets partent code.
	 * @param uri the href base
	 * @return the partent code
	 *  FactoryModelService/bizOrgMains/Auto_biz1/bizOrgDTLs/AutobizOrgMains_teams1
	 */
	public static String getPartentCode(String uri) {
			uri = subUrl(uri);
//		  uri =  uri.substring(uri.lastIndexOf("bizOrgMains"),uri.lastIndexOf("bizOrgDTLs")+1);
		int a = uri.lastIndexOf("bizOrgDTLs");
		uri = uri.substring(0,a-1);
		int startNum = uri.lastIndexOf("/");
		String paratentCode = uri.substring(startNum+1,uri.length());
		return   paratentCode ;
	}
	
	
	/**
	 * @Title: validateLongitudeStrMayBeNullException
	 * @Description: 对Longitude类型进行校验,可以为null的字段,进行特殊字符校验
	 * @param name
	 *            报错的具体字段名称
	 * @param nameValue
	 *            具体的值
	 * @param length
	 *            限制长度
	 * @return
	 * @date 2018年3月1日
	 * @return: BusinessException
	 * @author he.yang
	 */
	public static BusinessException validateLongitudeStrMayBeNullException(
			String name, String nameValue, int length) {
		BusinessException busException = null;
		if (null != nameValue && !"".equals(nameValue)) {
			if (!VerifyUtil.validateLongitudeFormat(nameValue)) {
				busException = new BusinessException("", "", name + ":"
						+ nameValue + ":" + CheckError.NAME_ERR);
			}
			if (null != nameValue && !"".equals(nameValue)
					&& nameValue.length() > length) {
				busException = new BusinessException("", "", name + ":"
						+ nameValue + ":" + CheckError.VALUE_LENGTH + length);
			}
		}
		return busException;
	}
	
	/**
	 * @Title: validateLatitudeStrMayBeNullException
	 * @Description: 对Latitude类型进行校验,可以为null的字段,进行特殊字符校验
	 * @param name
	 *            报错的具体字段名称
	 * @param nameValue
	 *            具体的值
	 * @param length
	 *            限制长度
	 * @return
	 * @date 2018年3月1日
	 * @return: BusinessException
	 * @author he.yang
	 */
	public static BusinessException validateLatitudeStrMayBeNullException(
			String name, String nameValue, int length) {
		BusinessException busException = null;
		if (null != nameValue && !"".equals(nameValue)) {
			if (!VerifyUtil.validateLatitudeFormat(nameValue)) {
				busException = new BusinessException("", "", name + ":"
						+ nameValue + ":" + CheckError.NAME_ERR);
			}
			if (null != nameValue && !"".equals(nameValue)
					&& nameValue.length() > length) {
				busException = new BusinessException("", "", name + ":"
						+ nameValue + ":" + CheckError.VALUE_LENGTH + length);
			}
		}
		return busException;
	}
	public static String getList(List<String> codeList) {
		List<String> list = new ArrayList<>();
		for(int i=0;i<codeList.size();i++) {
			list.add("'"+codeList.get(i)+"'");
		}
		String string = list.toString().replace("[", "(").replace("]", ")");
		return string;
	}
}
