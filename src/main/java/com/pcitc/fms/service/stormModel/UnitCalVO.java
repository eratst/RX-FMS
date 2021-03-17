package com.pcitc.fms.service.stormModel;

public class UnitCalVO {
	private String deviceID;// 设备ID
	private String plantId;
	private String operationIndexID;// 指标ID
	private String upperLowerID;// 上下限ID
	private double concernValue;//关注值
	private int weightFactor;//权重
	private String upperLimit;// 上限值
	private String lowerLimit;// 下限值
	private int minDeviationTime;// 最小偏差时间 	
	private Double threshold;// 阈值 
	private int calcModel;	// 平稳率算法
	private String monLevel;//监控级别
	
	public UnitCalVO() {
		super();
	}
	
	public String getPlantId() {
		return plantId;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}

	public String getMonLevel() {
		return monLevel;
	}

	public void setMonLevel(String monLevel) {
		this.monLevel = monLevel;
	}

	public int getMinDeviationTime() {
		return minDeviationTime;
	}

	public void setMinDeviationTime(int minDeviationTime) {
		this.minDeviationTime = minDeviationTime;
	}

	public Double getThreshold() {
		return threshold;
	}

	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}

	public int getCalcModel() {
		return calcModel;
	}

	public void setCalcModel(int calcModel) {
		this.calcModel = calcModel;
	}

	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getOperationIndexID() {
		return operationIndexID;
	}
	public void setOperationIndexID(String operationIndexID) {
		this.operationIndexID = operationIndexID;
	}
	public String getUpperLowerID() {
		return upperLowerID;
	}
	public void setUpperLowerID(String upperLowerID) {
		this.upperLowerID = upperLowerID;
	}
	public double getConcernValue() {
		return concernValue;
	}
	public void setConcernValue(double concernValue) {
		this.concernValue = concernValue;
	}
	public int getWeightFactor() {
		return weightFactor;
	}
	public void setWeightFactor(int weightFactor) {
		this.weightFactor = weightFactor;
	}
	public String getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(String upperLimit) {
		this.upperLimit = upperLimit;
	}
	public String getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(String lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	
	
}
