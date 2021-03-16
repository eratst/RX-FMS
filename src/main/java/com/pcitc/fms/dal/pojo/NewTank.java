package com.pcitc.fms.dal.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

@Entity
@Table(name = "T_PM_TANK")
@PrimaryKeyJoinColumn(name = "NODE_ID", referencedColumnName = "NODE_ID")
public class NewTank extends NewNode {

	private static final long serialVersionUID = 1L;

	// 罐类型ID
	@Column(name = "TANK_TYPE_ID")
	private Integer tankTypeId;

	// 罐高
	@Column(name = "TANK_HGT")
	private Integer tankHgt;

	// 罐公称容积
	@Column(name = "TANK_TOTL_CUBA")
	private Integer tankTotlCuba;

	/** 浮盘重 **/
	@Column(name = "FLT_PLAT_WGT")
	private Integer fltPlatWgt;

	// 浮盘前高
	@Column(name = "FLT_PLAT_PERHGT")
	private Integer fltPlatPerhgt;

	// 浮顶最低点
	@Column(name = "FLT_TIP_LST")
	private Integer fltTipLst;

	// 罐安全高度
	@Column(name = "MAX_TANK_HGT")
	private Integer maxTankHgt;

	// 罐底高度
	@Column(name = "MIN_TANK_HGT")
	private Integer minTankHgt;

	// 安全罐量上限
	@Column(name = "MAX_TANK_STOARGE")
	private Integer maxTankStoarge;

	// 安全罐量下限
	@Column(name = "MIN_TANK_STOARGE")
	private Integer minTankStoarge;

	// 是否保温罐
	@Column(name = "HT_PRET_TANK")
	private Integer htPretTank;

	@Column(name = "CRT_USER_ID")
	private String crtUserId;

	@Column(name = "CRT_USER_NAME")
	private String crtUserName;

	@Column(name = "CRT_DATE")
	private Date crtDate;

	@Column(name = "MNT_USER_ID")
	private String mntUserId;

	@Column(name = "MNT_USER_NAME")
	private String mntUserName;

	@Column(name = "MNT_DATE")
	private Date mntDate;

	@CheckField(CheckName = CheckNameType.OBJECTVALUE)
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TANK_TYPE_ID", insertable = false, updatable = false)
	private TankType tankType;

	public NewTank() {
		super();
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

	public TankType getTankType() {
		return tankType;
	}

	public void setTankType(TankType tankType) {
		this.tankType = tankType;
	}

	public Integer getTankTypeId() {
		return tankTypeId;
	}

	public void setTankTypeId(Integer tankTypeId) {
		this.tankTypeId = tankTypeId;
	}

	public Integer getTankHgt() {
		return tankHgt;
	}

	public void setTankHgt(Integer tankHgt) {
		this.tankHgt = tankHgt;
	}

	public Integer getTankTotlCuba() {
		return tankTotlCuba;
	}

	public void setTankTotlCuba(Integer tankTotlCuba) {
		this.tankTotlCuba = tankTotlCuba;
	}

	public Integer getFltPlatWgt() {
		return fltPlatWgt;
	}

	public void setFltPlatWgt(Integer fltPlatWgt) {
		this.fltPlatWgt = fltPlatWgt;
	}

	public Integer getFltPlatPerhgt() {
		return fltPlatPerhgt;
	}

	public void setFltPlatPerhgt(Integer fltPlatPerhgt) {
		this.fltPlatPerhgt = fltPlatPerhgt;
	}

	public Integer getFltTipLst() {
		return fltTipLst;
	}

	public void setFltTipLst(Integer fltTipLst) {
		this.fltTipLst = fltTipLst;
	}

	public Integer getMaxTankHgt() {
		return maxTankHgt;
	}

	public void setMaxTankHgt(Integer maxTankHgt) {
		this.maxTankHgt = maxTankHgt;
	}

	public Integer getMinTankHgt() {
		return minTankHgt;
	}

	public void setMinTankHgt(Integer minTankHgt) {
		this.minTankHgt = minTankHgt;
	}

	public Integer getMaxTankStoarge() {
		return maxTankStoarge;
	}

	public void setMaxTankStoarge(Integer maxTankStoarge) {
		this.maxTankStoarge = maxTankStoarge;
	}

	public Integer getMinTankStoarge() {
		return minTankStoarge;
	}

	public void setMinTankStoarge(Integer minTankStoarge) {
		this.minTankStoarge = minTankStoarge;
	}

	public Integer getHtPretTank() {
		return htPretTank;
	}

	public void setHtPretTank(Integer htPretTank) {
		this.htPretTank = htPretTank;
	}

}
