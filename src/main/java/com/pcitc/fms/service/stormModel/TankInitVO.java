package com.pcitc.fms.service.stormModel;

import java.io.Serializable;

/**
 * 罐信息
 * @author haiwen.wang
 *
 */
public class TankInitVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tankID;//罐ID;
	
	private int bwTank;//是否保温罐;
	
	private int tankType;//罐类型;
	
	private Double dbTankHgt;//罐高;
	
	private Double dbFLT_PLAT_WGT;//浮盘重;
	
	private Double dbTankTotalCuba;//罐公称容积;
	
	private Double dbFLT_PLAT_PERHGT;//浮盘前高;
	
	private Double dbFLT_TIP_LST;//浮顶最低点;
	
	private Double maxTankHgt;//罐安全高度;
	
	private Double minTankHgt;//罐底高度;
	
	private int stdTnkFltFlatType;//立罐浮盘类型：1，内浮顶，2外浮顶，3拱顶 

	public String getTankID() {
		return tankID;
	}

	public void setTankID(String tankID) {
		this.tankID = tankID;
	}

	public int getBwTank() {
		return bwTank;
	}

	public void setBwTank(int bwTank) {
		this.bwTank = bwTank;
	}

	public int getTankType() {
		return tankType;
	}

	public void setTankType(int tankType) {
		this.tankType = tankType;
	}

	public Double getDbTankHgt() {
		return dbTankHgt;
	}

	public void setDbTankHgt(Double dbTankHgt) {
		this.dbTankHgt = dbTankHgt;
	}

	public Double getDbFLT_PLAT_WGT() {
		return dbFLT_PLAT_WGT;
	}

	public void setDbFLT_PLAT_WGT(Double dbFLT_PLAT_WGT) {
		this.dbFLT_PLAT_WGT = dbFLT_PLAT_WGT;
	}

	public Double getDbTankTotalCuba() {
		return dbTankTotalCuba;
	}

	public void setDbTankTotalCuba(Double dbTankTotalCuba) {
		this.dbTankTotalCuba = dbTankTotalCuba;
	}

	public Double getDbFLT_PLAT_PERHGT() {
		return dbFLT_PLAT_PERHGT;
	}

	public void setDbFLT_PLAT_PERHGT(Double dbFLT_PLAT_PERHGT) {
		this.dbFLT_PLAT_PERHGT = dbFLT_PLAT_PERHGT;
	}

	public Double getDbFLT_TIP_LST() {
		return dbFLT_TIP_LST;
	}

	public void setDbFLT_TIP_LST(Double dbFLT_TIP_LST) {
		this.dbFLT_TIP_LST = dbFLT_TIP_LST;
	}

	public Double getMaxTankHgt() {
		return maxTankHgt;
	}

	public void setMaxTankHgt(Double maxTankHgt) {
		this.maxTankHgt = maxTankHgt;
	}

	public Double getMinTankHgt() {
		return minTankHgt;
	}

	public void setMinTankHgt(Double minTankHgt) {
		this.minTankHgt = minTankHgt;
	}

	public int getStdTnkFltFlatType() {
		return stdTnkFltFlatType;
	}

	public void setStdTnkFltFlatType(int stdTnkFltFlatType) {
		this.stdTnkFltFlatType = stdTnkFltFlatType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
