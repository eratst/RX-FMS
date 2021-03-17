package com.pcitc.fms.service.stormModel;

import java.io.Serializable;
import java.util.Date;

import com.pcitc.fms.common.enums.TankCalucationResultType;

/**
 * 计算结果
 * 
 * @author haiwen.wang
 *
 */
public class TankOutputVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String node_id; // 罐ID

	private Double dbNetOfOil;// 净油量;
	
	private Double dbMaxNetOfOil;// 最大安全罐存;
	
	private Double dbMinNetOfOil;// 最小罐存;
	
	private Double dbCanfulNetOfOil;// 满罐存;

	private Double dbVCF;// 体积修正系数;

	private Double dbVPF;// 立罐静压修正系数;

	private Double dbVPF_SC;// 立罐水尺修正系数;

	private Double dbVTF;// 温度修正系数;

	private Double dbFLT_PLAT_WGT;// 浮盘重;

	private Double dbAdjustedCheck;// 修正检尺;

	private Double dbAdjustedCheckOfWater;// 修正水尺;

	private Double dbVolumn;// 体积;

	private Double dbStandardVolumn;// 标准体积;

	private Double dbAdjustedEcho;// 管网罐修正系数;

	private Double dbDensityOut;// 密度;

	private Double dbDensityOfAtmosphere;// 油品空气中密度;

	private Double dbDensityAtmosphereArea;// 油品气态部分密度;

	private Double dbVolumnAtmosphereArea;// 油品气态部分体积;

	private Double dbNetOfGas;// 油品气态部分质量;

	private Double dbWaterVolumn;// 水体积;

	private Double dbInputTotalDimension;// 输入油尺;

	private Double dbTemperatureOfAtmosphere;// 大气温度

	private Double dbTemperatureOfOil;// 油品温度
	
	private Double Weight;// 摩尔质量

	private Double dbStress;// 压力;

	private Double dbHeightOfWater;//  水尺
	
	private TankCalucationResultType resultType;// 返回标志;

	private String resultMessage;// 返回信息;

	private String intime;// 黄宏要的计算时间

	private String rtdbdatatime;// 黄宏要的实时数据库取值时间

	private String MaterialID;// 物料ID

	private String end_time;// 存入mongoDB时间

	private long dtDate;// 计量时间;-----检尺时间
	
	private long RealDateTime ;//实际检尺时间;
	

	public long getRealDateTime() {
		return RealDateTime;
	}

	public void setRealDateTime(long realDateTime) {
		RealDateTime = realDateTime;
	}

	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}

	public Double getDbMaxNetOfOil() {
		return dbMaxNetOfOil;
	}

	public void setDbMaxNetOfOil(Double dbMaxNetOfOil) {
		this.dbMaxNetOfOil = dbMaxNetOfOil;
	}

	public Double getDbMinNetOfOil() {
		return dbMinNetOfOil;
	}

	public void setDbMinNetOfOil(Double dbMinNetOfOil) {
		this.dbMinNetOfOil = dbMinNetOfOil;
	}

	public Double getDbCanfulNetOfOil() {
		return dbCanfulNetOfOil;
	}

	public void setDbCanfulNetOfOil(Double dbCanfulNetOfOil) {
		this.dbCanfulNetOfOil = dbCanfulNetOfOil;
	}

	public void setDbNetOfOil(Double dbNetOfOil) {
		this.dbNetOfOil = dbNetOfOil;
	}

	public void setDbVCF(Double dbVCF) {
		this.dbVCF = dbVCF;
	}

	public void setDbVPF(Double dbVPF) {
		this.dbVPF = dbVPF;
	}

	public void setDbVPF_SC(Double dbVPF_SC) {
		this.dbVPF_SC = dbVPF_SC;
	}

	public void setDbVTF(Double dbVTF) {
		this.dbVTF = dbVTF;
	}

	public void setDbFLT_PLAT_WGT(Double dbFLT_PLAT_WGT) {
		this.dbFLT_PLAT_WGT = dbFLT_PLAT_WGT;
	}

	public void setDbAdjustedCheck(Double dbAdjustedCheck) {
		this.dbAdjustedCheck = dbAdjustedCheck;
	}

	public void setDbAdjustedCheckOfWater(Double dbAdjustedCheckOfWater) {
		this.dbAdjustedCheckOfWater = dbAdjustedCheckOfWater;
	}

	public void setDbVolumn(Double dbVolumn) {
		this.dbVolumn = dbVolumn;
	}

	public void setDbStandardVolumn(Double dbStandardVolumn) {
		this.dbStandardVolumn = dbStandardVolumn;
	}

	public void setDbAdjustedEcho(Double dbAdjustedEcho) {
		this.dbAdjustedEcho = dbAdjustedEcho;
	}

	public void setDbDensityOut(Double dbDensityOut) {
		this.dbDensityOut = dbDensityOut;
	}

	public void setDbDensityOfAtmosphere(Double dbDensityOfAtmosphere) {
		this.dbDensityOfAtmosphere = dbDensityOfAtmosphere;
	}

	public void setDbDensityAtmosphereArea(Double dbDensityAtmosphereArea) {
		this.dbDensityAtmosphereArea = dbDensityAtmosphereArea;
	}

	public void setDbVolumnAtmosphereArea(Double dbVolumnAtmosphereArea) {
		this.dbVolumnAtmosphereArea = dbVolumnAtmosphereArea;
	}

	public void setDbNetOfGas(Double dbNetOfGas) {
		this.dbNetOfGas = dbNetOfGas;
	}

	public void setDbWaterVolumn(Double dbWaterVolumn) {
		this.dbWaterVolumn = dbWaterVolumn;
	}

	public void setDbInputTotalDimension(Double dbInputTotalDimension) {
		this.dbInputTotalDimension = dbInputTotalDimension;
	}

	public void setDbTemperatureOfAtmosphere(Double dbTemperatureOfAtmosphere) {
		this.dbTemperatureOfAtmosphere = dbTemperatureOfAtmosphere;
	}

	public void setDbTemperatureOfOil(Double dbTemperatureOfOil) {
		this.dbTemperatureOfOil = dbTemperatureOfOil;
	}

	public void setWeight(Double weight) {
		Weight = weight;
	}

	public void setDbStress(Double dbStress) {
		this.dbStress = dbStress;
	}

	public void setDbHeightOfWater(Double dbHeightOfWater) {
		this.dbHeightOfWater = dbHeightOfWater;
	}

	public void setResultType(TankCalucationResultType resultType) {
		this.resultType = resultType;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public void setRtdbdatatime(String rtdbdatatime) {
		this.rtdbdatatime = rtdbdatatime;
	}

	public void setMaterialID(String materialID) {
		MaterialID = materialID;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public void setDtDate(long dtDate) {
		this.dtDate = dtDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNode_id() {
		return node_id;
	}

	public Double getDbNetOfOil() {
		return dbNetOfOil;
	}

	public Double getDbVCF() {
		return dbVCF;
	}

	public Double getDbVPF() {
		return dbVPF;
	}

	public Double getDbVPF_SC() {
		return dbVPF_SC;
	}

	public Double getDbVTF() {
		return dbVTF;
	}

	public Double getDbFLT_PLAT_WGT() {
		return dbFLT_PLAT_WGT;
	}

	public Double getDbAdjustedCheck() {
		return dbAdjustedCheck;
	}

	public Double getDbAdjustedCheckOfWater() {
		return dbAdjustedCheckOfWater;
	}

	public Double getDbVolumn() {
		return dbVolumn;
	}

	public Double getDbStandardVolumn() {
		return dbStandardVolumn;
	}

	public Double getDbAdjustedEcho() {
		return dbAdjustedEcho;
	}

	public Double getDbDensityOut() {
		return dbDensityOut;
	}

	public Double getDbDensityOfAtmosphere() {
		return dbDensityOfAtmosphere;
	}

	public Double getDbDensityAtmosphereArea() {
		return dbDensityAtmosphereArea;
	}

	public Double getDbVolumnAtmosphereArea() {
		return dbVolumnAtmosphereArea;
	}

	public Double getDbNetOfGas() {
		return dbNetOfGas;
	}

	public Double getDbWaterVolumn() {
		return dbWaterVolumn;
	}

	public Double getDbInputTotalDimension() {
		return dbInputTotalDimension;
	}

	public Double getDbTemperatureOfAtmosphere() {
		return dbTemperatureOfAtmosphere;
	}

	public Double getDbTemperatureOfOil() {
		return dbTemperatureOfOil;
	}

	public Double getWeight() {
		return Weight;
	}

	public Double getDbStress() {
		return dbStress;
	}

	public Double getDbHeightOfWater() {
		return dbHeightOfWater;
	}

	public TankCalucationResultType getResultType() {
		return resultType;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public String getIntime() {
		return intime;
	}

	public String getRtdbdatatime() {
		return rtdbdatatime;
	}

	public String getMaterialID() {
		return MaterialID;
	}

	public String getEnd_time() {
		return end_time;
	}

	public long getDtDate() {
		return dtDate;
	}

	
    
}
