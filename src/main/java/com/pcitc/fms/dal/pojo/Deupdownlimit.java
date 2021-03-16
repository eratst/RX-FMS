package com.pcitc.fms.dal.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pcitc.fms.common.annotation.RegionMember;
@Entity
@Table(name = "T_OPM_DEUPDOWNLIMIT")
public class Deupdownlimit {
	@Id
	@Column(name = "DEUPDOWNLIMIT_ID")
	private Long deupDownLimitId;
	@Column(name = "UNIQUE_CODE")
	private String uniqueId;//唯一性标识
	@Column(name = "OPEINDEX_ID")
	private Long opeindexId;//操作指标ID
	@Column(name = "MONLEVEL_ID")
	private Long monLevelId;//监控级别ID
	@Column(name = "CRAFTSCHEME_ID")
	private Long craftSchemeId;//工艺方案ID
	@Column(name = "UP_LIMIT_VALUE")
	private Integer upLimitValue;//上限值
	@Column(name = "DOWN_LIMIT_VALUE")
	private Integer downLimitValue;//下限值
	@Column(name = "ATTENTION_VALUE")
	private Integer attentionValue;//名称
	@Column(name = "THRESHOLD_VALUE")
	private Integer thresholdValue;//阈值
	@Column(name = "MIN_DVT_TIME")
	private Integer minDvtTime;//最小偏差时间（分钟）（监控使用）
	@Column(name = "MAX_CHANGE_RATE")
	private Integer maxChangeTate;//最大变化率（巡检使用）
	@Column(name = "TECHCARD_CTL_FLAG")
	private Integer techcardCtlFlag;//是否工艺卡片控制项
	@Column(name = "MON_FLAG")
	private Integer monFlag;//是否监控
	@Column(name = "PTRL_FLAG")
	private Integer ptrlFlag;//是否巡检
	@Column(name = "START_TIME")
	private Date startTime;//起效时间
	@Column(name = "STATUS")
	private Integer status;//状态
	@Column(name = "MNT_FLAG")
	private Integer mntFlag;//修改标识
	@Column(name = "INUSE")
	private Integer inUse;//是否启用
	
	@Column(name = "CRTUSER_CODE")
	private String crtUserCode;//创建人ID
	@Column(name = "CRTUSER_NAME")
	private String crtUserName;//创建人名称
	@Column(name = "CRTDATE")
	private Date crtDate;//创建时间
	
	@Column(name = "CMTUSER_CODE")
	private String cmtUserCode;//提交人ID
	@Column(name = "CMTUSER_NAME")
	private String cmtUserName;//提交人名称
	@Column(name = "CMTDATE")
	private Date cmtDate;//提交时间
	
	@Column(name = "RLSUSER_CODE")
	private String rlsUserCode;//发布人ID
	@Column(name = "RLSUSER_NAME")
	private String rlsUserName;//创建人名称
	@Column(name = "RLSDATE")
	private Date rlsDate;//创建时间
	
	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;//最后维护人ID
	@Column(name = "MNTUSER_NAME")
	private String mntUserName;//最后维护人名称
	@Column(name = "MNTDATE")
	private Date mntDate;//维护日期
	@Column(name = "DES")
	private String des;//说明

	@Column(name = "APPLY_REASON")
	private String applyReason;//申请原因
	@Column(name = "RISK_AND_SOLUTION")
	private String riskAndSolution;//风险评估及相应措施
	@Column(name = "UP_LIMIT_FORMULA")
	private String upLimitFormula;//上限值公式
	@Column(name = "DOWN_LIMIT_FORMULA")
	private String downLimitFormula;//下限值公式
	@Column(name = "ATTENTION_FORMULA")
	private String attentionFormula;//关注值（平均值）公式
	@Transient
	private String monLevel;//监控级别
	@Transient
	private String craftScheme;//工艺方案

	


	public Deupdownlimit(Long deupDownLimitId, String uniqueId, Long opeindexId, Integer upLimitValue,
			Integer downLimitValue, Integer attentionValue, Integer thresholdValue, Integer minDvtTime,
			Integer maxChangeTate, Integer techcardCtlFlag, Integer monFlag, Integer ptrlFlag, Date startTime,
			Integer status, Integer mntFlag, Integer inUse, String crtUserCode, String crtUserName, Date crtDate,
			String cmtUserCode, String cmtUserName, Date cmtDate, String rlsUserCode, String rlsUserName, Date rlsDate,
			String mntUserCode, String mntUserName, Date mntDate, String des, String applyReason, String riskAndSolution,
			String upLimitFormula, String downLimitFormula, String attentionFormula, String monLevel,
			String craftScheme) {
		super();
		this.deupDownLimitId = deupDownLimitId;
		this.uniqueId = uniqueId;
		this.opeindexId = opeindexId;
		this.upLimitValue = upLimitValue;
		this.downLimitValue = downLimitValue;
		this.attentionValue = attentionValue;
		this.thresholdValue = thresholdValue;
		this.minDvtTime = minDvtTime;
		this.maxChangeTate = maxChangeTate;
		this.techcardCtlFlag = techcardCtlFlag;
		this.monFlag = monFlag;
		this.ptrlFlag = ptrlFlag;
		this.startTime = startTime;
		this.status = status;
		this.mntFlag = mntFlag;
		this.inUse = inUse;
		this.crtUserCode = crtUserCode;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.cmtUserCode = cmtUserCode;
		this.cmtUserName = cmtUserName;
		this.cmtDate = cmtDate;
		this.rlsUserCode = rlsUserCode;
		this.rlsUserName = rlsUserName;
		this.rlsDate = rlsDate;
		this.mntUserCode = mntUserCode;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
		this.des = des;
		this.applyReason = applyReason;
		this.riskAndSolution = riskAndSolution;
		this.upLimitFormula = upLimitFormula;
		this.downLimitFormula = downLimitFormula;
		this.attentionFormula = attentionFormula;
		this.monLevel = monLevel;
		this.craftScheme = craftScheme;
	}

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
