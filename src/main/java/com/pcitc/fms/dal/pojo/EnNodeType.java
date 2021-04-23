package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_PM_ENNODETYPE")
public class EnNodeType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "ENNODETYPE_ID")
	private Long enNodeTypeId;

	@Column(name = "ENNODETYPE_CODE")
	private String enNodeTypeCode;

	@Column(name = "ENNODETYPE_NAME")
	private String enNodeTypeName;

    @Column(name = "BIZORGMAIN_ID")
	private Long bizId;
    
    @Transient
    private String bizCode;

	@Column(name = "DES")
	private String des;
	
	@Column(name = "SORT_NUM")
	private Integer sortNum;
	
	@Column(name = "INUSE")
	private Integer inUse;
	
	@Column(name = "VERSION")
	private Integer version;
	
	@Column(name = "CRTUSER_CODE")
	private String crtUserId;
	
	@Column(name = "CRTUSER_NAME")
	private String crtUserName;
	
	@Column(name = "CRTDATE")
	private Date crtDate;
	
	@Column(name = "MNTUSER_CODE")
	private String mntUserId;
	
	@Column(name = "MNTUSER_NAME")
	private String mntUserName;
	
	@Column(name = "MNTDATE")
	private Date mntDate;

    public EnNodeType() {
        super();
    }

	public EnNodeType(Long enNodeTypeId, String enNodeTypeCode, String enNodeTypeName, Long bizId, String bizCode,
			String des, Integer sortNum, Integer inUse, Integer version, String crtUserId, String crtUserName,
			Date crtDate, String mntUserId, String mntUserName, Date mntDate) {
		super();
		this.enNodeTypeId = enNodeTypeId;
		this.enNodeTypeCode = enNodeTypeCode;
		this.enNodeTypeName = enNodeTypeName;
		this.bizId = bizId;
		this.bizCode = bizCode;
		this.des = des;
		this.sortNum = sortNum;
		this.inUse = inUse;
		this.version = version;
		this.crtUserId = crtUserId;
		this.crtUserName = crtUserName;
		this.crtDate = crtDate;
		this.mntUserId = mntUserId;
		this.mntUserName = mntUserName;
		this.mntDate = mntDate;
	}

	public Long getEnNodeTypeId() {
		return enNodeTypeId;
	}

	public void setEnNodeTypeId(Long enNodeTypeId) {
		this.enNodeTypeId = enNodeTypeId;
	}

	public String getEnNodeTypeCode() {
		return enNodeTypeCode;
	}

	public void setEnNodeTypeCode(String enNodeTypeCode) {
		this.enNodeTypeCode = enNodeTypeCode;
	}

	public String getEnNodeTypeName() {
		return enNodeTypeName;
	}

	public void setEnNodeTypeName(String enNodeTypeName) {
		this.enNodeTypeName = enNodeTypeName;
	}

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
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

	public String getCrtUserId() {
		return crtUserId;
	}

	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
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

	public String getMntUserId() {
		return mntUserId;
	}

	public void setMntUserId(String mntUserId) {
		this.mntUserId = mntUserId;
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
	
}
