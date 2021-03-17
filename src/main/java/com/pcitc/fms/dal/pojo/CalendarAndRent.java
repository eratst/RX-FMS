package com.pcitc.fms.dal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "T_PM_CALENDARANDRENT")
public class CalendarAndRent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CALENDARANDRENT_ID")
	private Long calendarAndRentId;
	
	@Column(name = "RENT_ID")
	private Long rentId;
	
	@Column(name = "TEAMCODE")
	private String TeamCode;
	
	@Column(name = "TEAMNAME")
	private String TeamName;
	
	@Column(name = "CRTUSER_CODE")
	private String crtUserCode;
	
	@Column(name = "CRTUSER_NAME")
	private String crtUserName;
	
	@Column(name = "CRTDATE")
	private Date crtDate;
	
	@Column(name = "MNTUSER_CODE")
	private String mntUserCode;
	
	@Column(name = "MNTUSER_NAME")
	private String mntUserName;
	
	@Column(name = "MNTDATE")
	private Date mntDate;
	
	@Column(name = "DES")
	private String des;
	
	@Column(name = "VERSION")
	private Integer version;
	
	@Column(name = "INUSE")
	private Integer inUse;
	
	@Column(name = "SORT_NUM")
	private Integer sortNum;

	public Long getCalendarAndRentId() {
		return calendarAndRentId;
	}

	public void setCalendarAndRentId(Long calendarAndRentId) {
		this.calendarAndRentId = calendarAndRentId;
	}

	public Long getRentId() {
		return rentId;
	}

	public void setRentId(Long rentId) {
		this.rentId = rentId;
	}

	public String getTeamCode() {
		return TeamCode;
	}

	public void setTeamCode(String teamCode) {
		TeamCode = teamCode;
	}

	public String getTeamName() {
		return TeamName;
	}

	public void setTeamName(String teamName) {
		TeamName = teamName;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	
	
}
