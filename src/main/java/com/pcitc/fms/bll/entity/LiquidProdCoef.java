package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * entity 液化产品系数表实体类
 * @author xin.kou
 *
 */
public class LiquidProdCoef implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// 液化产品系数ID
		private Long liqProdId;
		
		// 物料ID
		private Long mtrlId;

		// 密度温度系数
		private Double denTempCofe;

		// 体积温度系数(温度)
		private Double cubaTempCofe;

		// 密度
		private Double den;

		// 创建人ID
		private String crtUserCode;

		// 创建人名称
		private String crtUserName;

		// 创建时间
		private Date crtDate;

		// 最后维护人ID
		private String mntUserCode;

		// 最后维护人名称
		private String mntUserName;

		// 维护日期
		private Date mntDate;

		// 描述
		private String des;
		
		private Integer inUse;

		// 排序
		private Integer sortNum;

		// 乐观锁版本
		private Integer version;
		
		private String mtrlCode;// 物料编码
		
		private String mtrlName;// 物料名称
		
		
		public String getCrtUserCode() {
			return crtUserCode;
		}

		public void setCrtUserCode(String crtUserCode) {
			this.crtUserCode = crtUserCode;
		}

		public Integer getInUse() {
			return inUse;
		}

		public void setInUse(Integer inUse) {
			this.inUse = inUse;
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

		public String getMntUserCode() {
			return mntUserCode;
		}

		public void setMntUserCode(String mntUserCode) {
			this.mntUserCode = mntUserCode;
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
		
		
		
}
