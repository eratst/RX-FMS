package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import cc.aicode.e2e.annotation.ExcelEntity;
import cc.aicode.e2e.annotation.ExcelProperty;
import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;
@ExcelEntity
@ResourceContract(ReadOnly = false)
@QueryContract(href="",rel="search",prompt="列表查询")
@QueryContract(href="",rel="condition",prompt="条件查询（名称、简称支持模糊查询）")
public class Openindex extends BaseResRep implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ExcelProperty("操作指标id")
	@ResourceMember(InTemplate = false)
	private Long openindexId;//操作指标id
	@ExcelProperty("操作指标名称")
	@CheckField(CheckName = CheckNameType.NAME ,StrLength=50)
	@ResourceMember(InQueries="condition",Name="name")
	private String name;//名称
	@ExcelProperty("标准编码")
	@CheckField(CheckName = CheckNameType.CODE ,StrLength=36)
	private String code;//标准编码
	@ExcelProperty("装置id")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Long equipId;//装置Id
	@ExcelProperty("操作指标类型Id")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Long opeindexClassId;//操作指标类型Id
	@ExcelProperty("控制部门Id")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Long controlDepId;//控制部门Id
	@ExcelProperty("计算公式")
	private String calcFormula;//计算公式
	@ExcelProperty("量纲Id")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Long measUnitId;//量纲Id
	@ExcelProperty("工艺方案公用标识")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Integer isPublic;//工艺方案公用标识
	@ExcelProperty("设备Id")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Integer unitId;//设备Id
	@ExcelProperty("设备Id")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Integer isInnerOp;//是否内操
	@ExcelProperty("设备Id")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private Integer inUse;//是否启用
	//创建人Id
	@ExcelProperty("创建人ID")
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	private String crtUserCode;
	//创建人名称
	@ExcelProperty("创建人名称")
	@ResourceMember(OnlyQuery = true)
	@CheckField(CheckName = CheckNameType.CREATOR ,StrLength=50)
	private String crtUserName;
//		创建时间
	@ResourceMember(OnlyQuery = true)
	private Date crtDate;
	//最后维护人Id
	@ExcelProperty("最后维护人ID")
	@CheckField(CheckName = CheckNameType.ID ,StrLength=10)
	@ResourceMember(OnlyQuery = true)
	private String mntUserCode;
	//最后维护人
	@ExcelProperty("最后维护人名称")
	@CheckField(CheckName = CheckNameType.EDITOR ,StrLength=50)
	@ResourceMember(OnlyQuery = true)
	private String mntUserName;
	//描述
	@ExcelProperty("说明")
	@CheckField(CheckName = CheckNameType.DES ,StrLength=200)
	private String des;
	//维护日期
	@ResourceMember(OnlyQuery = true)
	private Date mntDate;
	//乐观锁版本
	private Integer version;
	private Integer sortnum;//排序
	@ResourceMember(InTemplate = false)
	private String openindexClass;//操作指标类型
	@ResourceMember(InTemplate = false)
	private String controlDep;//控制
	@ResourceMember(InTemplate = false)
	private String measUnit;//量纲
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="type")
	private String type;//不属于操作指标字段，他标识unit或者eqipment
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private String mcode;//装置或设备code
	@ResourceMember(InTemplate = false,OnlyQuery=true)
	private Integer monLevelId;//监控级别id
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$top")
	private Integer top;
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$skip")
	private Integer skip;
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$codeList")
	private List<String> codeList;
	
	@ResourceMember(InTemplate = false ,InQueries="search",OnlyQuery=true,Name="$orderby")
	private String orderby;
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String rentCode;
	
	
	@CheckField(CheckName = CheckNameType.CODE)
	@ResourceMember(OnlyQuery = true)
	private String bizCode;
	
	
	
	public String getRentCode() {
		return rentCode;
	}


	public void setRentCode(String rentCode) {
		this.rentCode = rentCode;
	}


	public String getBizCode() {
		return bizCode;
	}


	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}


	public String getOrderby() {
		return orderby;
	}


	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}


	public Openindex(Long equipId, Integer unitId, Integer skip, Integer top, String type,String orderby) {
		super();
		this.equipId = equipId;
		this.unitId = unitId;
		this.skip = skip;
		this.top = top;
		this.type = type;
		this.orderby = orderby;
	}

	
	public Openindex(Integer skip, Integer top, String type, String mcode) {
		super();
		this.skip = skip;
		this.top = top;
		this.type = type;
		this.mcode = mcode;
	}
	
	public Openindex(Integer skip, Integer top, String type, String mcode,Integer monLevelId,String orderby,String rentCode,String bizCode) {
		super();
		this.skip = skip;
		this.top = top;
		this.type = type;
		this.mcode = mcode;
		this.monLevelId = monLevelId;
		this.orderby = orderby;
		this.rentCode=rentCode;
		this.bizCode=bizCode;
	}

	
	
	public Long getOpenindexId() {
		return openindexId;
	}


	public void setOpenindexId(Long openindexId) {
		this.openindexId = openindexId;
	}


	public Long getEquipId() {
		return equipId;
	}


	public void setEquipId(Long equipId) {
		this.equipId = equipId;
	}


	public Long getOpeindexClassId() {
		return opeindexClassId;
	}


	public void setOpeindexClassId(Long opeindexClassId) {
		this.opeindexClassId = opeindexClassId;
	}


	public Long getControlDepId() {
		return controlDepId;
	}


	public void setControlDepId(Long controlDepId) {
		this.controlDepId = controlDepId;
	}


	public Long getMeasUnitId() {
		return measUnitId;
	}


	public void setMeasUnitId(Long measUnitId) {
		this.measUnitId = measUnitId;
	}


	public String getCrtUserCode() {
		return crtUserCode;
	}


	public void setCrtUserCode(String crtUserCode) {
		this.crtUserCode = crtUserCode;
	}


	public String getCrtUserName() {
		return crtUserName;
	}


	public void setCrtUserName(String crtUserName) {
		this.crtUserName = crtUserName;
	}


	public Date getCrtDate() {
		return crtDate;
	}


	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}


	public String getMntUserCode() {
		return mntUserCode;
	}


	public void setMntUserCode(String mntUserCode) {
		this.mntUserCode = mntUserCode;
	}


	public String getMntUserName() {
		return mntUserName;
	}


	public void setMntUserName(String mntUserName) {
		this.mntUserName = mntUserName;
	}


	public Date getMntDate() {
		return mntDate;
	}


	public void setMntDate(Date mntDate) {
		this.mntDate = mntDate;
	}


	public Integer getMonLevelId() {
		return monLevelId;
	}


	public void setMonLevelId(Integer monLevelId) {
		this.monLevelId = monLevelId;
	}


	public List<String> getCodeList() {
		return codeList;
	}


	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
	}


	public String getMcode() {
		return mcode;
	}


	public void setMcode(String mcode) {
		this.mcode = mcode;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getOpenindexClass() {
		return openindexClass;
	}

	public void setOpenindexClass(String openindexClass) {
		this.openindexClass = openindexClass;
	}

	public String getControlDep() {
		return controlDep;
	}

	public void setControlDep(String controlDep) {
		this.controlDep = controlDep;
	}

	public String getMeasUnit() {
		return measUnit;
	}

	public void setMeasUnit(String measUnit) {
		this.measUnit = measUnit;
	}

	public Openindex() {
		super();
	}
	
	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCalcFormula() {
		return calcFormula;
	}
	public void setCalcFormula(String calcFormula) {
		this.calcFormula = calcFormula;
	}
	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	public Integer getIsInnerOp() {
		return isInnerOp;
	}
	public void setIsInnerOp(Integer isInnerOp) {
		this.isInnerOp = isInnerOp;
	}
	public Integer getInUse() {
		return inUse;
	}
	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Integer getSortnum() {
		return sortnum;
	}
	public void setSortnum(Integer sortnum) {
		this.sortnum = sortnum;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
