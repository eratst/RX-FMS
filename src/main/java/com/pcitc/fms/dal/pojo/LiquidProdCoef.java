package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.SpecialResource;

@Entity
@Table(name = "T_IC_LIQPRODCOEF")
public class LiquidProdCoef implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// 液化产品系数ID
		@Id
		@Column(name = "LIQPRODCOEF_ID")
		private Long liqProdId;
		
		@SpecialResource(name="l.mtrlId")
		@Column(name = "MTRL_ID")
		private Long mtrlId;

		@SpecialResource(name="m.mtrlCode")
	    @Transient
		private String mtrlCode;// 物料编码
	    
		@SpecialResource(name="m.mtrlName")
	    @Transient
		private String mtrlName;// 物料名称
	 
		// 密度温度系数
		@SpecialResource(name="l.denTempCofe")
		@Column(name = "DEN_TEMP_COFE")
		private Double denTempCofe;

		// 体积温度系数(温度)
		@SpecialResource(name="l.cubaTempCofe")
		@Column(name = "CUBA_TEMP_COFE")
		private Double cubaTempCofe;

		// 密度
		@SpecialResource(name="l.den")
		@Column(name = "den")
		private Double den;

		
		@Column(name = "CRTUSER_CODE")
		private String crtUserCode; // 创建人ID

		@Column(name = "CRTUSER_NAME")
		private String crtUserName;// 创建人名称

		@Column(name = "CRTDATE")
		private Date crtDate;// 创建时间

		@Column(name = "MNTUSER_CODE")
		private String mntUserCode;// 最后维护人ID

		@Column(name = "MNTUSER_NAME")
		private String mntUserName;// 最后维护人名称

		@Column(name = "MNTDATE")
		private Date mntDate;//维护时间
		
		@Column(name = "DES")
		@SpecialResource(name="l.des")
		private String des;
		
		@Column(name = "INUSE")
		@SpecialResource(name="l.inUse")
		private Integer inUse;
		
		@Column(name="SORT_NUM")
		@SpecialResource(name="l.sortNum")
		private Integer sortNum;
		
		@Column(name="VERSION")
		private Integer version;

		
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

		public Integer getInUse() {
			return inUse;
		}

		public void setInUse(Integer inUse) {
			this.inUse = inUse;
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
		
		public LiquidProdCoef(Long liqProdId, Double denTempCofe, Double cubaTempCofe, Double den, String crtUserCode,
				String crtUserName, Date crtDate, String mntUserCode, String mntUserName, Date mntDate, String des,
				Integer sortNum, Integer version, Long mtrlId, String mtrlCode, String mtrlName, Integer inUse) {
			super();
			this.liqProdId = liqProdId;
			this.denTempCofe = denTempCofe;
			this.cubaTempCofe = cubaTempCofe;
			this.den = den;
			this.crtUserCode = crtUserCode;
			this.crtUserName = crtUserName;
			this.crtDate = crtDate;
			this.mntUserCode = mntUserCode;
			this.mntUserName = mntUserName;
			this.mntDate = mntDate;
			this.des = des;
			this.sortNum = sortNum;
			this.version = version;
			this.mtrlId = mtrlId;
			this.mtrlCode = mtrlCode;
			this.mtrlName = mtrlName;
			this.inUse = inUse;
		}
	
}
