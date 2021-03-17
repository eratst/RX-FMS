package com.pcitc.fms.service.stormModel;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

/**
 * 公共的查询参数
 * @author haiwen.wang
 *
 */
public class TankCalcPara extends BaseResRep {

	private String wlbm;// 油品编码
	private double dbTemperatureOfOil;// 油品温度
	private double dbConcentration;// 浓度
	private double dbStress;// 压力
	private double dbDen;// 密度
	private int intType;// 类型
	private String tankID;//罐id
	private double dbHeight;//罐高
	private double cm;//厘米
	private double mm;//毫米
	private int zone_id;//区间id
	private double liquidDen;//液体密度

	public TankCalcPara(String wlbm, double dbTemperatureOfOil, double dbConcentration, double dbStress, double dbDen,
			int intType, String tankID, double dbHeight, double cm, double mm, int zone_id, double liquidDen) {
		super();
		this.wlbm = wlbm;
		this.dbTemperatureOfOil = dbTemperatureOfOil;
		this.dbConcentration = dbConcentration;
		this.dbStress = dbStress;
		this.dbDen = dbDen;
		this.intType = intType;
		this.tankID = tankID;
		this.dbHeight = dbHeight;
		this.cm = cm;
		this.mm = mm;
		this.zone_id = zone_id;
		this.liquidDen = liquidDen;
	}

	public double getLiquidDen() {
		return liquidDen;
	}

	public void setLiquidDen(double liquidDen) {
		this.liquidDen = liquidDen;
	}

	public double getCm() {
		return cm;
	}

	public void setCm(double cm) {
		this.cm = cm;
	}

	public double getMm() {
		return mm;
	}

	public void setMm(double mm) {
		this.mm = mm;
	}

	public int getZone_id() {
		return zone_id;
	}

	public void setZone_id(int zone_id) {
		this.zone_id = zone_id;
	}

	public String getTankID() {
		return tankID;
	}

	public void setTankID(String tankID) {
		this.tankID = tankID;
	}

	public double getDbHeight() {
		return dbHeight;
	}

	public void setDbHeight(double dbHeight) {
		this.dbHeight = dbHeight;
	}

	public String getWlbm() {
		return wlbm;
	}

	public void setWlbm(String wlbm) {
		this.wlbm = wlbm;
	}

	public double getDbTemperatureOfOil() {
		return dbTemperatureOfOil;
	}

	public void setDbTemperatureOfOil(double dbTemperatureOfOil) {
		this.dbTemperatureOfOil = dbTemperatureOfOil;
	}

	public double getDbConcentration() {
		return dbConcentration;
	}

	public void setDbConcentration(double dbConcentration) {
		this.dbConcentration = dbConcentration;
	}

	public double getDbStress() {
		return dbStress;
	}

	public void setDbStress(double dbStress) {
		this.dbStress = dbStress;
	}

	public double getDbDen() {
		return dbDen;
	}

	public void setDbDen(double dbDen) {
		this.dbDen = dbDen;
	}

	public int getIntType() {
		return intType;
	}

	public void setIntType(int intType) {
		this.intType = intType;
	}

}
