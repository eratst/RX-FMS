package com.pcitc.fms.bll.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Transient;

public class Deupdownlimit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long deupDownLimitId;
	private String uniqueId;//唯一性标识
	private Long opeindexId;//操作指标ID
	private Long monLevelId;//监控级别ID
	private Long craftSchemeId;//工艺方案ID
	private Integer upLimitValue;//上限值
	private Integer downLimitValue;//下限值
	private Integer attentionValue;//名称
	private Integer thresholdValue;//阈值
	private Integer minDvtTime;//最小偏差时间（分钟）（监控使用）
	private Integer maxChangeTate;//最大变化率（巡检使用）
	private Integer techcardCtlFlag;//是否工艺卡片控制项
	private Integer monFlag;//是否监控
	private Integer ptrlFlag;//是否巡检
	private Date startTime;//起效时间
	private Integer status;//状态
	private Integer mntFlag;//修改标识
	private Integer inUse;//是否启用
	
	private String crtUserCode;//创建人ID
	private String crtUserName;//创建人名称
	private Date crtDate;//创建时间
	
	private String cmtUserCode;//提交人ID
	private String cmtUserName;//提交人名称
	private Date cmtDate;//提交时间
	
	private String rlsUserCode;//发布人ID
	private String rlsUserName;//创建人名称
	private Date rlsDate;//创建时间
	
	private String mntUserCode;//最后维护人ID
	private String mntUserName;//最后维护人名称
	private Date mntDate;//维护日期
	
	
	
	private String des;//说明
	private Integer sortnum;//排序
	private String applyReason;//申请原因
	private String riskAndSolution;//风险评估及相应措施
	private String upLimitFormula;//上限值公式
	private String downLimitFormula;//下限值公式
	private String attentionFormula;//关注值（平均值）公式
	@Transient
	private String monLevel;//监控级别
	@Transient
	private String craftScheme;//工艺方案


	public Deupdownlimit() {
		super();
	}

	
	public Long getDeupDownLimitId() {
		return deupDownLimitId;
	}


	public void setDeupDownLimitId(Long deupDownLimitId) {
		this.deupDownLimitId = deupDownLimitId;
	}


	public Long getOpeindexId() {
		return opeindexId;
	}


	public void setOpeindexId(Long opeindexId) {
		this.opeindexId = opeindexId;
	}


	public Long getMonLevelId() {
		return monLevelId;
	}


	public void setMonLevelId(Long monLevelId) {
		this.monLevelId = monLevelId;
	}


	public Long getCraftSchemeId() {
		return craftSchemeId;
	}


	public void setCraftSchemeId(Long craftSchemeId) {
		this.craftSchemeId = craftSchemeId;
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


	public String getCmtUserCode() {
		return cmtUserCode;
	}


	public void setCmtUserCode(String cmtUserCode) {
		this.cmtUserCode = cmtUserCode;
	}


	public String getCmtUserName() {
		return cmtUserName;
	}


	public void setCmtUserName(String cmtUserName) {
		this.cmtUserName = cmtUserName;
	}


	public String getRlsUserCode() {
		return rlsUserCode;
	}


	public void setRlsUserCode(String rlsUserCode) {
		this.rlsUserCode = rlsUserCode;
	}


	public String getRlsUserName() {
		return rlsUserName;
	}


	public void setRlsUserName(String rlsUserName) {
		this.rlsUserName = rlsUserName;
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


	public Date getCmtDate() {
		return cmtDate;
	}

	public void setCmtDate(Date cmtDate) {
		this.cmtDate = cmtDate;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Integer getUpLimitValue() {
		return upLimitValue;
	}

	public void setUpLimitValue(Integer upLimitValue) {
		this.upLimitValue = upLimitValue;
	}

	public Integer getDownLimitValue() {
		return downLimitValue;
	}

	public void setDownLimitValue(Integer downLimitValue) {
		this.downLimitValue = downLimitValue;
	}

	public Integer getAttentionValue() {
		return attentionValue;
	}

	public void setAttentionValue(Integer attentionValue) {
		this.attentionValue = attentionValue;
	}

	public Integer getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(Integer thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	public Integer getMinDvtTime() {
		return minDvtTime;
	}

	public void setMinDvtTime(Integer minDvtTime) {
		this.minDvtTime = minDvtTime;
	}

	public Integer getMaxChangeTate() {
		return maxChangeTate;
	}

	public void setMaxChangeTate(Integer maxChangeTate) {
		this.maxChangeTate = maxChangeTate;
	}

	public Integer getTechcardCtlFlag() {
		return techcardCtlFlag;
	}

	public void setTechcardCtlFlag(Integer techcardCtlFlag) {
		this.techcardCtlFlag = techcardCtlFlag;
	}

	public Integer getMonFlag() {
		return monFlag;
	}

	public void setMonFlag(Integer monFlag) {
		this.monFlag = monFlag;
	}

	public Integer getPtrlFlag() {
		return ptrlFlag;
	}

	public void setPtrlFlag(Integer ptrlFlag) {
		this.ptrlFlag = ptrlFlag;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMntFlag() {
		return mntFlag;
	}

	public void setMntFlag(Integer mntFlag) {
		this.mntFlag = mntFlag;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public Date getRlsDate() {
		return rlsDate;
	}

	public void setRlsDate(Date rlsDate) {
		this.rlsDate = rlsDate;
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

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public String getRiskAndSolution() {
		return riskAndSolution;
	}

	public void setRiskAndSolution(String riskAndSolution) {
		this.riskAndSolution = riskAndSolution;
	}

	public String getUpLimitFormula() {
		return upLimitFormula;
	}

	public void setUpLimitFormula(String upLimitFormula) {
		this.upLimitFormula = upLimitFormula;
	}

	public String getDownLimitFormula() {
		return downLimitFormula;
	}

	public void setDownLimitFormula(String downLimitFormula) {
		this.downLimitFormula = downLimitFormula;
	}

	public String getAttentionFormula() {
		return attentionFormula;
	}

	public void setAttentionFormula(String attentionFormula) {
		this.attentionFormula = attentionFormula;
	}

	public String getMonLevel() {
		return monLevel;
	}

	public void setMonLevel(String monLevel) {
		this.monLevel = monLevel;
	}

	public String getCraftScheme() {
		return craftScheme;
	}

	public void setCraftScheme(String craftScheme) {
		this.craftScheme = craftScheme;
	}
	

}
