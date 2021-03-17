package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;


/**
 * @ClassName: LiquidProdCoef
 * @Description: 液化产品系数表实体类 model
 * @author
 * @date
 */
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "列表查询")
@QueryContract(href = "", rel = "condition", prompt = "查询")
public class LiquidProdCoef extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;

		// 液化产品系数ID
		@ResourceMember(InTemplate = false)
		private Long liqProdId;

		// 物料ID
		@CheckField(CheckName = CheckNameType.ID)
		private Long mtrlId;

		// 密度温度系数
		@CheckField(CheckName = CheckNameType.IDMAYBENULL)
		private Double denTempCofe;

		// 体积温度系数(温度)
		@CheckField(CheckName = CheckNameType.IDMAYBENULL)
		private Double cubaTempCofe;

		// 密度
		@CheckField(CheckName = CheckNameType.IDMAYBENULL)
		private Double den;

		// 创建人ID
		@CheckField(CheckName = CheckNameType.CREATORID, StrLength = 20)
		@ResourceMember(OnlyQuery = true)
		private String crtUserCode;

		// 创建人名称
		@CheckField(CheckName = CheckNameType.CREATOR, StrLength = 50)
		@ResourceMember(OnlyQuery = true)
		private String crtUserName;

		// 创建时间
		@CheckField(CheckName = CheckNameType.CREATETIME)
		@ResourceMember(OnlyQuery = true)
		private Date crtDate;

		// 最后维护人ID
		@CheckField(CheckName = CheckNameType.EDITORID, StrLength = 20)
		@ResourceMember(OnlyQuery = true)
		private String mntUserCode;

		// 最后维护人名称
		@CheckField(CheckName = CheckNameType.EDITOR, StrLength = 50)
		@ResourceMember(OnlyQuery = true)
		private String mntUserName;

		// 维护日期
		@CheckField(CheckName = CheckNameType.MAINTAINTIME)
		@ResourceMember(OnlyQuery = true)
		private Date mntDate;

		// 描述
		@CheckField(CheckName = CheckNameType.DES, StrLength = 200)
		private String des;

		@CheckField(CheckName = CheckNameType.ENABLED)
		private Integer inUse;
		
		// 排序
		@CheckField(CheckName = CheckNameType.SORTNUM, StrLength = 10)
		private Integer sortNum;

		// 乐观锁版本
		private Integer version;

		@ResourceMember(InQueries = "condition", InTemplate = false)
		private String mtrlCode;// 物料编码

		private String mtrlName;// 物料名称

		@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$top")
		private Integer top;

		@ResourceMember(InQueries = "search,condition", OnlyQuery = true, Name = "$skip")
		private Integer skip = 0;

		@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$mtrlCodeList")
		private List<String> mtrlCodeList;
		
		@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$orderby")
		private String orderby;

		public Integer getInUse() {
			return inUse;
		}

		public void setInUse(Integer inUse) {
			this.inUse = inUse;
		}

		public String getOrderby() {
			return orderby;
		}

		public void setOrderby(String orderby) {
			this.orderby = orderby;
		}

		public List<String> getMtrlCodeList() {
			return mtrlCodeList;
		}

		public void setMtrlCodeList(List<String> mtrlCodeList) {
			this.mtrlCodeList = mtrlCodeList;
		}

		public Double getDenTempCofe() {
			return denTempCofe;
		}

		public void setDenTempCofe(Double denTempCofe) {
			this.denTempCofe = denTempCofe;
		}

		public Double getCubaTempCofe() {
			return cubaTempCofe;
		}

		public void setCubaTempCofe(Double cubaTempCofe) {
			this.cubaTempCofe = cubaTempCofe;
		}

		public Double getDen() {
			return den;
		}

		public void setDen(Double den) {
			this.den = den;
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
		
		public Long getLiqProdId() {
			return liqProdId;
		}

		public void setLiqProdId(Long liqProdId) {
			this.liqProdId = liqProdId;
		}

		public Long getMtrlId() {
			return mtrlId;
		}

		public void setMtrlId(Long mtrlId) {
			this.mtrlId = mtrlId;
		}

		public String getCrtUserCode() {
			return crtUserCode;
		}

		public void setCrtUserCode(String crtUserCode) {
			this.crtUserCode = crtUserCode;
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

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}

		public Integer getSortNum() {
			return sortNum;
		}

		public void setSortNum(Integer sortNum) {
			this.sortNum = sortNum;
		}

		public Integer getVersion() {
			return version;
		}

		public void setVersion(Integer version) {
			this.version = version;
		}

		public String getMtrlCode() {
			return mtrlCode;
		}

		public void setMtrlCode(String mtrlCode) {
			this.mtrlCode = mtrlCode;
		}

		public String getMtrlName() {
			return mtrlName;
		}

		public void setMtrlName(String mtrlName) {
			this.mtrlName = mtrlName;
		}

		public Integer getTop() {
			return top;
		}

		public void setTop(Integer top) {
			this.top = top;
		}

		public Integer getSkip() {
			return skip;
		}

		public void setSkip(Integer skip) {
			this.skip = skip;
		}
		
		public LiquidProdCoef() {
			super();
		}
		
		public LiquidProdCoef(String mtrlCode, String mtrlName, Integer top, Integer skip,  List<String> mtrlCodeList,String orderby) {
			super();
			this.mtrlCode = mtrlCode;
			this.mtrlName = mtrlName;
			this.top = top;
			this.skip = skip;
			this.mtrlCodeList = mtrlCodeList;
			this.orderby = orderby;
		}

}
