package com.pcitc.fms.common.annotation;


 /**
 * Title: CheckNameType
* Description: TODO task mark zhenqiang.zhao
 * @author zhenqiang.zhao
 * @date 2017年7月6日
 * @version 1.0
 */
public enum CheckNameType {
			/** 对ID类型进行校验,不能为null,而且是数值类型 */
		 	ID,
		 	/** 对 CODE类型进行校验,不能为null,并且是由字母,数字,下划线构成*/
		    CODE,
		    /** 不能为null,可以为汉字,并且是由字母,数字,下划线构成*/
		    NAME,
			/** 不能为null,可以为汉字,并且是由字母,数字,正斜杠构成 */
			UNITNAME,
		    /** 可以为null,并限制长度 */
		    DES,
		    /**可以为null,校验是否为汉字,并且是由字母,数字,下划线构成 */
		    NAMEMAYBENULL,
		    /**可以为null,校验是否数字构成 */
		    IDMAYBENULL,
		    /** 创建时间的校验) */
		    CREATETIME,
		    /** 修改时间的校验 */
		    MAINTAINTIME,
		    /**启用标识的校验,默认值为1 */
		    ENABLED, CREATOR, EDITOR, 
		    //非负数
		    NEGATIVEID,
		    //修改人ID
		    EDITORID, 
		    //创建人ID
		    CREATORID,
		    SOURCEDATATYPE,
		    IDTYPE,
	      SORTNUM,
	      OBJECTVALUE,
	      ORDER,
	      PAGEINFO,
	      ENTITY,
	      CODELIST,
	      TREE,
	      RENTCODE,
	      CONDITION,
	      IDFORQUERY
}
