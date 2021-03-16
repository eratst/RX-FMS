package com.pcitc.fms.service.stormModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 计算参数
 * @author haiwen.wang
 *
 */
public class CalcParamVO implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tankID;//罐ID;
	
	private String tankName;//罐名称;-----参数表中没有
	
	private String materialID;//油品ID;
	
	private int materialType;//油品类型;-------参数表中没有	
	
	private Double dbHeightOfWater;//水尺;
	
	private Date dtDate;//计量时间;-----检尺时间
	
	private long dtdate;//检尺时间 long形式
	
	private Double dbInputDensity;//输入密度;-----密度
	
	private Double dbTemperatureOfGas;//气体温度;-----大气温度
	
	private Double dbEmptyDimension;//空尺;-------手工录入
	
	private Double dbCorrectVal;//量油尺修正值;-----手工录入
	
	private boolean isEmptyDimension;//是否空尺;-----手工录入
	
	private Double dbImmergeOilDimension;//浸油高度;---------手工录入
	
	//-------------实时数据------------有则以实时数据为准，没有则取关系数据库中的数据
	private Double dbTemperatureOfOil;//油品温度;--------温度
	
	private Double dbTemperatureOfAtmosphere;//大气温度;------镇海 9
	
	private Double dbDensity;//密度;
	
	private Double dbPercentOfWater;//含水率;

	private Double dbTotalDimension;//总尺;----------如果实时取不到，取人工检尺高度
	
	private Double dbConcentration;//浓度;
	
	private Double dbStress;//压力;
	
	private Double dbHeight;//罐高;
	
	private Double dbMaxHeight;//最大安全罐高;
	
	private Double dbMinHeight;//最小罐高;
	
	private int stanDenType;//标准密度类型;--------标准密度计算设置的
	
	private long RealDateTime ;//实际检尺时间;
	
	public long getRealDateTime() {
		return RealDateTime;
	}

	public void setRealDateTime(long realDateTime) {
		RealDateTime = realDateTime;
	}

	public Double getDbHeight() {
		return dbHeight;
	}

	public void setDbHeight(Double dbHeight) {
		this.dbHeight = dbHeight;
	}

	public Double getDbMaxHeight() {
		return dbMaxHeight;
	}

	public void setDbMaxHeight(Double dbMaxHeight) {
		this.dbMaxHeight = dbMaxHeight;
	}

	public Double getDbMinHeight() {
		return dbMinHeight;
	}

	public void setDbMinHeight(Double dbMinHeight) {
		this.dbMinHeight = dbMinHeight;
	}

	public long getDtdate() {
		return dtdate;
	}

	public void setDtdate(long dtdate) {
		this.dtdate = dtdate;
	}
	
	public int getStanDenType() {
		return stanDenType;
	}

	public void setStanDenType(int stanDenType) {
		this.stanDenType = stanDenType;
	}

	public Double getDbTemperatureOfOil() {
		return dbTemperatureOfOil;
	}

	public void setDbTemperatureOfOil(Double dbTemperatureOfOil) {
		this.dbTemperatureOfOil = dbTemperatureOfOil;
	}

	public Double getDbTemperatureOfAtmosphere() {
		return dbTemperatureOfAtmosphere;
	}

	public void setDbTemperatureOfAtmosphere(Double dbTemperatureOfAtmosphere) {
		this.dbTemperatureOfAtmosphere = dbTemperatureOfAtmosphere;
	}

	public Double getDbDensity() {
		return dbDensity;
	}

	public void setDbDensity(Double dbDensity) {
		this.dbDensity = dbDensity;
	}

	public Double getDbPercentOfWater() {
		return dbPercentOfWater;
	}

	public void setDbPercentOfWater(Double dbPercentOfWater) {
		this.dbPercentOfWater = dbPercentOfWater;
	}

	public Double getDbTotalDimension() {
		return dbTotalDimension;
	}

	public void setDbTotalDimension(Double dbTotalDimension) {
		this.dbTotalDimension = dbTotalDimension;
	}

	public String getTankID() {
		return tankID;
	}

	public void setTankID(String tankID) {
		this.tankID = tankID;
	}

	public String getTankName() {
		return tankName;
	}

	public void setTankName(String tankName) {
		this.tankName = tankName;
	}

	public String getMaterialID() {
		return materialID;
	}

	public void setMaterialID(String materialID) {
		this.materialID = materialID;
	}

	public int getMaterialType() {
		return materialType;
	}

	public void setMaterialType(int materialType) {
		this.materialType = materialType;
	}

	public Double getDbImmergeOilDimension() {
		return dbImmergeOilDimension;
	}

	public void setDbImmergeOilDimension(Double dbImmergeOilDimension) {
		this.dbImmergeOilDimension = dbImmergeOilDimension;
	}

	public Double getDbHeightOfWater() {
		return dbHeightOfWater;
	}

	public void setDbHeightOfWater(Double dbHeightOfWater) {
		this.dbHeightOfWater = dbHeightOfWater;
	}

	public Double getDbStress() {
		return dbStress;
	}

	public void setDbStress(Double dbStress) {
		this.dbStress = dbStress;
	}

	public Double getDbConcentration() {
		return dbConcentration;
	}

	public void setDbConcentration(Double dbConcentration) {
		this.dbConcentration = dbConcentration;
	}

	public Date getDtDate() {
		return dtDate;
	}

	public void setDtDate(Date dtDate) {
		this.dtDate = dtDate;
	}

	public Double getDbInputDensity() {
		return dbInputDensity;
	}

	public void setDbInputDensity(Double dbInputDensity) {
		this.dbInputDensity = dbInputDensity;
	}

	public Double getDbTemperatureOfGas() {
		return dbTemperatureOfGas;
	}

	public void setDbTemperatureOfGas(Double dbTemperatureOfGas) {
		this.dbTemperatureOfGas = dbTemperatureOfGas;
	}

	public Double getDbEmptyDimension() {
		return dbEmptyDimension;
	}

	public void setDbEmptyDimension(Double dbEmptyDimension) {
		this.dbEmptyDimension = dbEmptyDimension;
	}

	public Double getDbCorrectVal() {
		return dbCorrectVal;
	}

	public void setDbCorrectVal(Double dbCorrectVal) {
		this.dbCorrectVal = dbCorrectVal;
	}

	public boolean isEmptyDimension() {
		return isEmptyDimension;
	}

	public void setEmptyDimension(boolean isEmptyDimension) {
		this.isEmptyDimension = isEmptyDimension;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CalcParamVO [tankID=" + tankID + ", tankName=" + tankName + ", materialID=" + materialID
				+ ", materialType=" + materialType + ", dbHeightOfWater=" + dbHeightOfWater + ", dtDate=" + dtDate
				+ ", dtdate=" + dtdate + ", dbInputDensity=" + dbInputDensity + ", dbTemperatureOfGas="
				+ dbTemperatureOfGas + ", dbEmptyDimension=" + dbEmptyDimension + ", dbCorrectVal=" + dbCorrectVal
				+ ", isEmptyDimension=" + isEmptyDimension + ", dbImmergeOilDimension=" + dbImmergeOilDimension
				+ ", dbTemperatureOfOil=" + dbTemperatureOfOil + ", dbTemperatureOfAtmosphere="
				+ dbTemperatureOfAtmosphere + ", dbDensity=" + dbDensity + ", dbPercentOfWater=" + dbPercentOfWater
				+ ", dbTotalDimension=" + dbTotalDimension + ", dbConcentration=" + dbConcentration + ", dbStress="
				+ dbStress + ", stanDenType=" + stanDenType + "]";
	}
	
}
