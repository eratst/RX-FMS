package com.pcitc.fms.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.handler.ValveHandler;


/**
 * The type Verify util.
 *
 * @author zhenqiang.zhao
 */
public class VerifyUtil {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(VerifyUtil.class);

	/**
	 * The constant regexForCode.
	 */
// 匹配由数字、26个英文字母或者下划线，中划线组成的字符串
//	public static final String regexForCode = "^[0-9a-zA-Z_-]{1,}$";
	public static final String regexForCode = "^[0-9a-zA-Z\\._-]{1,}$";
	/**
	 * The constant regexForString.
	 */
// 匹配由数字、26个英文字母或者汉字或者下划线组成的字符串，字符串前后允许有空格
	public static final String regexForString = "^\\s*^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$\\s*$";
//匹配由数字、26个英文字母或者汉字或者下划线组成的字符串，字符串前后允许有空格 中划线 和.
	public static final String NewregexForString = "^[0-9a-zA-Z\u4e00-\u9fa5\\.()_-]{1,}$";
	//由数字、26个英文字母或者汉字或者正斜杠组成
	public static final String NewregexForUnitName = "^[0-9a-zA-Z\u4e00-\u9fa5\\/]{1,}$";
	
	/**
	 * The constant regexForKey.
	 */
// 匹配由数字、点、26个英文字母或者下划线组成的字符串
	public static final String regexForKey = "^[0-9a-zA-Z_\\.\\:-]{1,}$";
	/**
	 * The constant regexForName.
	 */
// 匹配汉字、数字、字母、下划线不能以下划线开头和结尾的字符串
	public static final String regexForName = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$";
	/**
	 * The constant regexForSortNum.
	 */
// 只允许正整数和零
	public static final String regexForSortNum = "^[0-9]{1,8}?$";
	/**
	 * The constant regexTaxNo.
	 */
// 验证是否为正整数、负整数、零
	public static final String regexTaxNo  = "^-?[0-9]\\d*$";
	/**
	 * The constant regexForInteger.
	 */
	public static final String regexForInteger = "^[1-9]\\d*$";//不可为0
	/**
	 * The constant regexForLicability.
	 */
	public static final String regexForLicability = "^[0-2]\\d*$";
	/**
	 * The constant regexForEnabled.
	 */
	public static final String regexForEnabled = "^[0-1]\\d*$";
	/**
	 * The constant regexForScript.
	 */
	public static final String regexForScript = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
	/**
	 * The constant regexForNegativeInteger.
	 */
	public static final String regexForNegativeInteger = "^\\d+$";//非负整数
	/**
	 * The constant regexForNegativeIntegerType.
	 */
	public static final String regexForNegativeIntegerType = "^([1-9]\\d*|[0]{1,1})$";//校验为0和正整数
//	public static final String regexForNegativeInteger = "^(567[0-8])|^(56[0-6]\\d)|^(5[0-5]\\d{2})|^([1-4]\\d{3})|^([1-9]\\d{0,2})$";

	/**
	 * The constant regexForPositiveInteger.
	 */
// 只允许正整数
	public static final String regexForPositiveInteger = "^[0-9]*[1-9][0-9]*$";
	/**
	 * The constant regexForEnglish.
	 */
// 验证是否为英文
	public static final String regexForEnglish = "^[A-Za-z]+$";
	/**
	 * The constant regexForEmail.
	 */
// 验证是否为邮箱
	public static final String regexForEmail = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	/**
	 * The constant regexForContractTel.
	 */
// 验证是否为手机号码
	public static final String regexForContractTel  = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	/**
	 * The constant regexFax.
	 */
// 验证是否为传真
	public static final String regexFax  = "^(\\d{3,4}-)?\\d{6,8}$";//"^[0][1-9]{2,3}-[0-9]{5,10}$";
	/**
	 * The constant regexForURL.
	 */
//验证是否为网址
	public static final String regexForURL = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
	/**
	 * The constant regexForDouble.
	 */
// 验证是否为浮点数
	public static final String regexForDouble = "^(-?\\d+)(\\.\\d+)?$";
	/**
	 * The constant regexForIntegerSize.
	 */
//限制数字长度1-9
	public static final String regexForIntegerSize = "^[1-9]$";
	/**
	 * The constant regexForIntegerSize1.
	 */
//限制数字长度1-6
	public static final String regexForIntegerSize1 = "^[1-6]$";
	/**
	 * The constant regexForIntegerSize2.
	 */
//只能为正整数
	public static final String regexForIntegerSize2 = "^[0-9]*$";
	/**
	 * The constant CODEMAXLENG.
	 */
// code值的最大长度
	public static final int CODEMAXLENG = 36;
	/**
	 * The constant CRTIDMAXLENG.
	 */
// 创建者id的最大长度
	public static final int CRTIDMAXLENG = 20;
	/**
	 * The constant NAMEMAXLENG.
	 */
// name值的最大长度
	public static final int NAMEMAXLENG = 50;
	/**
	 * The constant DESMAXLENG.
	 */
// des值的最大长度
	public static final int DESMAXLENG = 100;
	/**
	 * The constant SEVEN_DAYS.
	 */
// 七天的秒数
	public static final int SEVEN_DAYS = 7 * 24 * 60 * 60;

	/**
	 * 纬度： -90.0～+90.0（整数部分为0～90，必须输入1到5位小数）
	 */
	public static final String regexLatitude = "^[\\-\\+]?([0-8]?\\d{1}\\.\\d{1,5}|90\\.0{1,5})$";
	/**
	 * 经度： -180.0～+180.0（整数部分为0～180，必须输入1到5位小数）
	 */
	public static final String regexLongitude = "^[\\-\\+]?(0?\\d{1,2}\\.\\d{1,5}|1[0-7]?\\d{1}\\.\\d{1,5}|180\\.0{1,5})$";

	/**
	 * Validate latitude format boolean.
	 *
	 * @param resource the resource
	 * @return the boolean
	 */
	public static boolean validateLatitudeFormat(String resource) {
		return null == resource ?  false :resource.matches(regexLatitude);
	}

	/**
	 * Validate longitude format boolean.
	 *
	 * @param resource the resource
	 * @return the boolean
	 */
	public static boolean validateLongitudeFormat(String resource) {
		return null == resource ?  false :resource.matches(regexLongitude);
	}

	/**
	 * Validate code format boolean.
	 *
	 * @param resource the resource
	 * @return the boolean
	 * @Title: validateCodeFormat
	 * @Description: 校验CODE的数据
	 * @return: boolean
	 * @author zhenqiang.zhao
	 */
	public static boolean validateCodeFormat(String resource) {
		return null == resource ?  false :resource.matches(regexForCode);
	}
	
	public static boolean validateCodeFormatForRent(String resource) {
		return null == resource ?  false :resource.matches(NewregexForString);
	}

	/**
	 * 校验code，code可为空
	 *
	 * @param resource the resource
	 * @return boolean
	 */
	public static boolean validateCode(String resource) {
		if(!"".equals(resource)){
			return (null == resource) ?  false :resource.matches(regexForCode);
		}
		return true;
	}

	/**
	 * 校验KEY的数据
	 *
	 * @param resource the resource
	 * @return boolean
	 */
	public static boolean validateKeyFormat(String resource) {
		return (null == resource) ?  false :resource.matches(regexForKey);
	}

	/**
	 * 校验NAME的数据
	 *
	 * @param resource the resource
	 * @return boolean
	 */
	public static boolean validateNameFormat(String resource) {
		return (null == resource) ?  false :resource.matches(regexForString);
	}

	/**
	 * 校验SORTNUM的数据
	 *
	 * @param resource the resource
	 * @return boolean
	 */
	public static boolean validateSortNum(String resource) {
		return (null == resource) ?  false :resource.matches(regexForSortNum);
	}

	/**
	 * 校验INTEGER的数据
	 *
	 * @param integer the integer
	 * @return boolean
	 */
	public static boolean validateNegativeIntegerFormat(String integer) {
		return (null == integer) ?  false :integer.matches(regexForNegativeInteger);
	}
	
	public static boolean validateTopIntegerFormat(String integer) {
		return (null == integer) ?  false :integer.matches(regexForPositiveInteger);
	}

	/**
	 * Validate positive integer format boolean.
	 *
	 * @param data the data
	 * @return boolean
	 * @Title: validatePositiveIntegerFormat
	 * @Description: 只能为正整数
	 * @date 2017年7月14日
	 * @return: boolean
	 * @author ping.wang
	 */
	public static boolean validatePositiveIntegerFormat(String data) {
		return (null == data) ?  false :data.matches(regexForPositiveInteger);
	}

	/**
	 * Validate enabled boolean.
	 *
	 * @param integer the integer
	 * @return boolean
	 * @Title: validateEnabled
	 * @Description: 可以非负数
	 * @date 2017年7月14日
	 * @return: boolean
	 * @author zhenqiang.zhao
	 */
	public static boolean validateEnabled(String integer) {
		return (null == integer) ?  false :integer.matches(regexForEnabled);
	}

	/**
	 * Validate integer format boolean.
	 *
	 * @param integer the integer
	 * @return boolean
	 * @Title: validateIntegerFormat
	 * @Description: 可以非负数
	 * @date 2017年7月14日
	 * @return: boolean
	 * @author zhenqiang.zhao
	 */
	public static boolean validateIntegerFormat(String integer) {
		return (null == integer) ?  false :integer.matches(regexForInteger);
	}

	/**
	 * Validate integer type format boolean.
	 *
	 * @param integer the integer
	 * @return the boolean
	 */
	public static boolean validateIntegerTypeFormat(String integer) {
		return (null == integer) ?  false :integer.matches(regexForNegativeIntegerType);
	}

	/**
	 * Validate licability format boolean.
	 *
	 * @param integer the integer
	 * @return the boolean
	 */
	public static boolean validateLicabilityFormat(String integer) {
		return (null == integer) ?  false :integer.matches(regexForLicability);
	}

	/**
	 * 校验有脚本的内容
	 *
	 * @param integer the integer
	 * @return boolean
	 */
	public static boolean validateScriptFormat(String integer) {
		boolean matches = true;
		if(null != integer){
			matches = integer.matches(regexForScript);
		}
		return matches;
	}

	/**
	 * 校验表示符
	 *
	 * @param integer the integer
	 * @return boolean
	 */
	public static boolean validateFlag(String integer) {
		if(integer != null ){
			int parseInt = Integer.parseInt(integer);
			return (parseInt != 0 && parseInt != 1) ? false : true;
		}
		return true;
	}

	/**
	 * 校验英文名称的数据
	 *
	 * @param word the word
	 * @return boolean
	 */
	public static boolean validateEnglish(String word) {
		boolean matches = true;
		if(null != word && !"".equals(word)){
			matches = word.matches(regexForEnglish);
		}
		return matches;
	}

	/**
	 * 校验邮箱的数据
	 *
	 * @param resource the resource
	 * @return boolean
	 */
	public static boolean validateEmail(String resource) {
		boolean matches = true;
		if(null !=resource && !"".equals(resource)){
			matches = resource.matches(regexForEmail);
		}
		return matches;
	}

	/**
	 * 校验联系电话的数据
	 *
	 * @param resource the resource
	 * @return boolean
	 */
	public static boolean validateContractTel(String resource) {
		boolean matches = true;
		if(null !=resource && !"".equals(resource)){
			matches = resource.matches(regexForContractTel);
		}
		return matches;
	}

	/**
	 * 校验传真的数据
	 *
	 * @param resource the resource
	 * @return boolean
	 */
	public static boolean validateFax(String resource) {
		boolean matches = true;
		if(null !=resource && !"".equals(resource)){
			matches = resource.matches(regexFax);
		}
		return matches;
	}

	/**
	 * Validate url boolean.
	 *
	 * @param resource the resource
	 * @return the boolean
	 */
	public static boolean validateURL(String resource) {
		boolean matches = true;
		if(null !=resource && !"".equals(resource)){
			matches = resource.matches(regexForURL);
		}
		return matches;
	}

	/**
	 * 校验税号的数据
	 *
	 * @param resource the resource
	 * @return boolean
	 */
	public static boolean validateTaxNo(String resource) {
		boolean matches = true;
		if(null !=resource && !"".equals(resource)){
			matches = resource.matches(regexTaxNo);
		}
		return matches;
	}

	/**
	 * 校验DOUBLE的数据
	 *
	 * @param integer the integer
	 * @return boolean
	 */
	public static boolean validateDoubleFormat(String integer) {
		return (null == integer) ?  false :integer.matches(regexForDouble);
	}

	/**
	 * 参数值去空格
	 *
	 * @param str the str
	 * @return string
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
	 * 获取基础的HREF
	 *
	 * @param href the href
	 * @return base href
	 */
	public static String getBaseHref(String href) {
		String hrefBase = "";
		String baseHref = "";
		if (!StringUtils.isEmpty(href)) {
			// 顶层HREF
			int endIndex = href.lastIndexOf("/");
			hrefBase = href.substring(0, endIndex);
		}
		try {
			hrefBase = URLDecoder.decode(hrefBase, "UTF-8");
			baseHref = hrefBase.replaceAll(" ", "").replaceAll("　", "");
		} catch (UnsupportedEncodingException e) {
			log.error("At verifyUtil excute getBaseHref", e);
		}
		return baseHref;
	}

	/**
	 * 将基础的HREF去空格
	 *
	 * @param href the href
	 * @return string
	 */
	public static String trimBaseHref(String href) {
		String baseHref = "";
		try {
			if (!StringUtils.isEmpty(href)) {
				href = URLDecoder.decode(href, "UTF-8");
				baseHref = href.replaceAll(" ", "").replaceAll("　", "");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("At verifyUtil excute getBaseHref", e);
		}
		return baseHref;
	}

	/**
	 * 从HREF中获取KEY值
	 *
	 * @param href the href
	 * @return url last element
	 * @throws Exception the exception
	 */
	public static String getUrlLastElement(String href) throws Exception {
		String lastElement = "";
		if (!StringUtils.isEmpty(href)) {
			// 顶层HREF
			int endIndex = href.lastIndexOf("/");
			lastElement = href.substring(endIndex + 1, href.length());
		}
		return URLDecoder.decode(lastElement, "UTF-8").replace(" ", "").replace(" ", "");
	}

	/**
	 * 获取基础的HREF
	 *
	 * @param key the key
	 * @return base key
	 */
	public static String getBaseKey(String key) {
		String baseKey = "";
		if (!StringUtils.isEmpty(key)) {
			// 顶层HREF
			int endIndex = key.lastIndexOf("_");
			baseKey = key.substring(endIndex + 1, key.length());
		}
		return baseKey;
	}


	/**
	 * Check string is null boolean.
	 *
	 * @param field the field
	 * @return boolean
	 */
	public static boolean checkStringIsNull(String field) {
		if (field == null || field.trim().length() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * Check string is not null boolean.
	 *
	 * @param field the field
	 * @return boolean
	 */
	public static boolean checkStringIsNotNull(String field) {
		if (field != null && field.trim().length() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Character filter boolean.
	 *
	 * @param character the character
	 * @return the boolean
	 */
	public static boolean characterFilter(String character) {
		String rexp = "[^(a-zA-Z0-9_.\\u4e00-\\u9fa5)]+$";
//		String rexp = "[^(?!_)(?!.*?_$)a-zA-Z0-9_\u4e00-\u9fa5]+$";
		Pattern pat = Pattern.compile(rexp);
		Matcher mat = pat.matcher(character);
		boolean ipAddress = mat.find();
		return ipAddress;
	}

	/**
	 * Character filter 2 boolean.
	 *
	 * @param character the character
	 * @return boolean
	 * @Title: characterFilter2
	 * @Description: 中文校验
	 * @date 2017年6月14日
	 * @return: boolean
	 * @author zhenqiang.zhao
	 */
	public static boolean characterFilter2(String character) {
		String rexp = "[^(a-zA-Z0-9_.\\u4e00-\\u9fa5)]";
//		String rexp = "[^(?!_)(?!.*?_$)a-zA-Z0-9_\u4e00-\u9fa5]+$";
		Pattern pat = Pattern.compile(rexp);
		Matcher mat = pat.matcher(character);
		boolean ipAddress = mat.find();
		return ipAddress;
	}

	/**
	 * Check digit boolean.
	 *
	 * @param value the value
	 * @return boolean
	 */
	public static boolean checkDigit(String value) {
		try {
			int value1 = Integer.parseInt(value);
			if(value1<0){
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 校验字符串长度
	 *
	 * @param str the str
	 * @param maxLength the max length
	 * @return boolean
	 */
	public static boolean checkStrLength(String str,int maxLength) {
		if(null != str && !str.equals("") && str.length() > maxLength){
			return false;
		}
		return true;
		
	}


	/**
	 * Check no blank matcher.
	 *
	 * @param field the field
	 * @return the matcher
	 * @Title: checkNoBlank
	 * @Description: 前后空格验证
	 */
	public static Matcher checkNoBlank(String field) {
		field = field.replaceAll("\n", "");
		Pattern p = Pattern.compile("(^\\s*)|(\\s*$)");
		Matcher m = p.matcher(field);
		return m;
	}

	/**
	 * Get character position int.
	 *
	 * @param wholeStr 待截取的字符串
	 * @param sub 传入的标识字符
	 * @param frequency 标识字符出现的第几次
	 * @return 标准字符出现第几次位置的数组下标 int
	 */
	public static int getCharacterPosition(String wholeStr,String sub,int frequency){
	    //这里是获取"/"符号的位置
	    Matcher slashMatcher = Pattern.compile(sub).matcher(wholeStr);
	    int mIdx = 0;
	    while(slashMatcher.find()) {
	       mIdx++;
	       //当"/"符号第三次出现的位置
	       if(mIdx == frequency){
	          break;
	       }
	    }
	    return slashMatcher.start();
	 }

	/**
	 * 获取某个字符出现的次数
	 *
	 * @param wholeStr the whole str
	 * @param sub the sub
	 * @return int
	 */
	public static int getCharacter(String wholeStr,String sub){
	    //这里是获取"/"符号的位置
	    Matcher slashMatcher = Pattern.compile(sub).matcher(wholeStr);
	    int mIdx = 0;
	    while(slashMatcher.find()) {
	       mIdx++;
	    }
	    return mIdx;
	 }

	/**
	 * 获取URL中上级类型
	 *
	 * @param uri the uri
	 * @param entyiyName the entyiy name
	 * @return string
	 */
	public static String getParentType(String uri,String entyiyName){
		String str = uri.substring(0, uri.lastIndexOf(entyiyName)-1);
		int j = getCharacter(str,"/");
		int k = getCharacterPosition(str,"/",j-1);
		int l = getCharacterPosition(str,"/",j);
		String parentType = str.substring(k+1, l);
		return parentType;
		
	}

	/**
	 * Gets parent id.
	 *
	 * @param uri the uri
	 * @param entyiyName the entyiy name
	 * @return the parent id
	 * @throws BusinessException the business exception
	 */
	public static Integer getParentId(String uri,String entyiyName) throws BusinessException{
		String str = uri.substring(0, uri.lastIndexOf(entyiyName)-1);
		int j = getCharacter(str,"/");
		int l = getCharacterPosition(str,"/",j);
		String parentCode = str.substring(l+1, str.length());
		
		return CheckUtil.strToInt("上级ID", parentCode);
		
	}

	/**
	 * 获取URL中上级code
	 *
	 * @param uri the uri
	 * @param entyiyName the entyiy name
	 * @return parent code
	 * @throws BusinessException the business exception
	 */
	public static String getParentCode(String uri,String entyiyName) throws BusinessException{
		String str = uri.substring(0, uri.lastIndexOf(entyiyName)-1);
		int j = getCharacter(str,"/");
		int l = getCharacterPosition(str,"/",j);
		String parentCode = str.substring(l+1, str.length());
		
		return parentCode;
		
	}

	/**
	 * Gets entity id.
	 *
	 * @param uri the uri
	 * @param entyiyName the entyiy name
	 * @return the entity id
	 * @throws BusinessException the business exception
	 */
	public static Integer getEntityId(String uri,String entyiyName) throws BusinessException{
		Integer entityId = null;
		if(!uri.endsWith(entyiyName)){
			String entityIdStr = uri.substring(uri.lastIndexOf(entyiyName+"/")+entyiyName.length()+1, uri.length());
			entityId = CheckUtil.strToInt("", entityIdStr);
		}
		
		return entityId;
		
	}

	/**
	 * Gets entity code.
	 *
	 * @param uri the uri
	 * @param entyiyName the entyiy name
	 * @return the entity code
	 * @throws BusinessException the business exception
	 */
	public static String getEntityCode(String uri,String entyiyName) throws BusinessException{
		String entityCode = null;
		if(!uri.endsWith(entyiyName)){
			 entityCode = uri.substring(uri.lastIndexOf(entyiyName+"/")+entyiyName.length()+1, uri.length());
		}
		return entityCode;
		
	}

	/**
	 * 判断是null或者empty
	 *
	 * @param data the data
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(Object data) {
		return ("".equals(data) || data == null) ? true : false;
	}

	/**
	 * 限制数字长度
	 *
	 * @param integer the integer
	 * @return the boolean
	 */
//1-9
	public static boolean valueSize(String integer) {
		return (null == integer) ?  false :integer.matches(regexForIntegerSize);
	}

	/**
	 * Value size 1 boolean.
	 *
	 * @param integer the integer
	 * @return the boolean
	 */
//1-6
	public static boolean valueSize1(String integer) {
		return (null == integer) ?  false :integer.matches(regexForIntegerSize1);
	}

	/**
	 * Value size 2 boolean.
	 *
	 * @param integer the integer
	 * @return the boolean
	 */
//1-29
	public static boolean valueSize2(String integer) {
		return (null == integer) ?  false :integer.matches(regexForIntegerSize2);
	}

	/**
	 * Validate integer type format boolean.
	 *
	 * @param object the object
	 * @return the boolean
	 */
	public static boolean validateIntegerTypeFormat(Object object) {
		// TODO Auto-generated method stub
		return false;
	}
}
