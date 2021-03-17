package com.pcitc.fms.service.stormModel;

import java.io.Serializable;


/**
 * strom计算所用的参数，设置各节点的并行数等信息
 * @author haiwen.wang
 *
 */
public class TankCapacityParamVO extends SystemParamVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// worker
	private int worker_num;

	// 
	private int spout_TankCalcSpout_num;

	private int bolt_CalcParamInitBolt_num;

	private int bolt_CalcRTParamBolt_num;

	private int bolt_ParamFormatBolt_num;
	
	private int bolt_MatInitBolt_num;
	
	private int bolt_TankInitBolt_num;
	
	private int bolt_TankConfigBolt_num;
	
	private int bolt_DealFormulaBolt_num;
	
	private int bolt_SpclTankWgtBolt_num;
	
	private int bolt_SaveResultWithSpclTankBolt_num;
	
	private int bolt_StandardDensityBolt_num;
	
	private int bolt_VTFBolt_num;
	
	private int bolt_VCFBolt_num;
	
	private int bolt_AdjustedCheckBolt_num;
	
	private int bolt_VPFBolt_num;
	
	private int bolt_CubaBolt_num;
	
	private int bolt_DesientyOfAtmosphereBolt_num;
	
	private int bolt_WeightOfDriftingPlateBolt_num;
	
	private int bolt_AdjustedEchoBolt_num;
	
	private int bolt_NetOfOilBolt_num;
	
	private int bolt_StandardCubaBolt_num;
	
	private int bolt_NetOfGasBolt_num;
	
	private int bolt_SaveResultBolt_num;
	
	private int  bolt_CalcBolt_num;
	
	private int bolt_MtrlBolt_num;
	
	private String mongodbCell;
	
	

	public String getMongodbCell() {
		return mongodbCell;
	}

	public void setMongodbCell(String mongodbCell) {
		this.mongodbCell = mongodbCell;
	}

	// tuple的批量
	private int tuple_batch;

	public int getTuple_batch() {
		return tuple_batch;
	}

	public void setTuple_batch(int tuple_batch) {
		this.tuple_batch = tuple_batch;
	}

	public int getSpout_TankCalcSpout_num() {
		return spout_TankCalcSpout_num;
	}

	public void setSpout_TankCalcSpout_num(int spout_TankCalcSpout_num) {
		this.spout_TankCalcSpout_num = spout_TankCalcSpout_num;
	}

	public int getBolt_CalcParamInitBolt_num() {
		return bolt_CalcParamInitBolt_num;
	}

	public void setBolt_CalcParamInitBolt_num(int bolt_CalcParamInitBolt_num) {
		this.bolt_CalcParamInitBolt_num = bolt_CalcParamInitBolt_num;
	}

	public int getBolt_CalcRTParamBolt_num() {
		return bolt_CalcRTParamBolt_num;
	}

	public void setBolt_CalcRTParamBolt_num(int bolt_CalcRTParamBolt_num) {
		this.bolt_CalcRTParamBolt_num = bolt_CalcRTParamBolt_num;
	}

	public int getBolt_ParamFormatBolt_num() {
		return bolt_ParamFormatBolt_num;
	}
	
	public int getBolt_StandardDensityBolt_num() {
		return bolt_StandardDensityBolt_num;
	}
	public int getBolt_CalcBolt_num() {
		return bolt_CalcBolt_num;
	}
	
	public int getBolt_SaveResultBolt_num() {
		return bolt_SaveResultBolt_num;
	}
	
	public void setBolt_ParamFormatBolt_num(int bolt_ParamFormatBolt_num) {
		this.bolt_ParamFormatBolt_num = bolt_ParamFormatBolt_num;
	}

	public int getBolt_MatInitBolt_num() {
		return bolt_MatInitBolt_num;
	}

	public void setBolt_MatInitBolt_num(int bolt_MatInitBolt_num) {
		this.bolt_MatInitBolt_num = bolt_MatInitBolt_num;
	}

	public int getBolt_TankInitBolt_num() {
		return bolt_TankInitBolt_num;
	}

	public void setBolt_TankInitBolt_num(int bolt_TankInitBolt_num) {
		this.bolt_TankInitBolt_num = bolt_TankInitBolt_num;
	}

	public int getBolt_TankConfigBolt_num() {
		return bolt_TankConfigBolt_num;
	}

	public void setBolt_TankConfigBolt_num(int bolt_TankConfigBolt_num) {
		this.bolt_TankConfigBolt_num = bolt_TankConfigBolt_num;
	}

	public int getBolt_DealFormulaBolt_num() {
		return bolt_DealFormulaBolt_num;
	}

	public void setBolt_DealFormulaBolt_num(int bolt_DealFormulaBolt_num) {
		this.bolt_DealFormulaBolt_num = bolt_DealFormulaBolt_num;
	}
	
	public void setbolt_StandardDensityBolt_num(int bolt_StandardDensityBolt_num) {
		this.bolt_StandardDensityBolt_num = bolt_StandardDensityBolt_num;
	}
	
	public void setbolt_SaveResultBolt_num(int bolt_SaveResultBolt_num) {
		this.bolt_SaveResultBolt_num = bolt_SaveResultBolt_num;
	}
	
	public void setbolt_CalcBolt_num(int bolt_CalcBolt_num) {
		this.bolt_CalcBolt_num = bolt_CalcBolt_num;
	}

	public int getBolt_SpclTankWgtBolt_num() {
		return bolt_SpclTankWgtBolt_num;
	}

	public void setBolt_SpclTankWgtBolt_num(int bolt_SpclTankWgtBolt_num) {
		this.bolt_SpclTankWgtBolt_num = bolt_SpclTankWgtBolt_num;
	}

	public int getBolt_SaveResultWithSpclTankBolt_num() {
		return bolt_SaveResultWithSpclTankBolt_num;
	}

	public void setBolt_SaveResultWithSpclTankBolt_num(int bolt_SaveResultWithSpclTankBolt_num) {
		this.bolt_SaveResultWithSpclTankBolt_num = bolt_SaveResultWithSpclTankBolt_num;
	}


	public void setBolt_StandardDensityBolt_num(int bolt_StandardDensityBolt_num) {
		this.bolt_StandardDensityBolt_num = bolt_StandardDensityBolt_num;
	}

	public int getBolt_VTFBolt_num() {
		return bolt_VTFBolt_num;
	}

	public void setBolt_VTFBolt_num(int bolt_VTFBolt_num) {
		this.bolt_VTFBolt_num = bolt_VTFBolt_num;
	}

	public int getBolt_VCFBolt_num() {
		return bolt_VCFBolt_num;
	}

	public void setBolt_VCFBolt_num(int bolt_VCFBolt_num) {
		this.bolt_VCFBolt_num = bolt_VCFBolt_num;
	}

	public int getBolt_AdjustedCheckBolt_num() {
		return bolt_AdjustedCheckBolt_num;
	}

	public void setBolt_AdjustedCheckBolt_num(int bolt_AdjustedCheckBolt_num) {
		this.bolt_AdjustedCheckBolt_num = bolt_AdjustedCheckBolt_num;
	}

	public int getBolt_VPFBolt_num() {
		return bolt_VPFBolt_num;
	}

	public void setBolt_VPFBolt_num(int bolt_VPFBolt_num) {
		this.bolt_VPFBolt_num = bolt_VPFBolt_num;
	}

	public int getBolt_CubaBolt_num() {
		return bolt_CubaBolt_num;
	}

	public void setBolt_CubaBolt_num(int bolt_CubaBolt_num) {
		this.bolt_CubaBolt_num = bolt_CubaBolt_num;
	}

	public int getBolt_DesientyOfAtmosphereBolt_num() {
		return bolt_DesientyOfAtmosphereBolt_num;
	}

	public void setBolt_DesientyOfAtmosphereBolt_num(int bolt_DesientyOfAtmosphereBolt_num) {
		this.bolt_DesientyOfAtmosphereBolt_num = bolt_DesientyOfAtmosphereBolt_num;
	}

	public int getBolt_WeightOfDriftingPlateBolt_num() {
		return bolt_WeightOfDriftingPlateBolt_num;
	}

	public void setBolt_WeightOfDriftingPlateBolt_num(int bolt_WeightOfDriftingPlateBolt_num) {
		this.bolt_WeightOfDriftingPlateBolt_num = bolt_WeightOfDriftingPlateBolt_num;
	}

	public int getBolt_AdjustedEchoBolt_num() {
		return bolt_AdjustedEchoBolt_num;
	}

	public void setBolt_AdjustedEchoBolt_num(int bolt_AdjustedEchoBolt_num) {
		this.bolt_AdjustedEchoBolt_num = bolt_AdjustedEchoBolt_num;
	}

	public int getBolt_NetOfOilBolt_num() {
		return bolt_NetOfOilBolt_num;
	}

	public void setBolt_NetOfOilBolt_num(int bolt_NetOfOilBolt_num) {
		this.bolt_NetOfOilBolt_num = bolt_NetOfOilBolt_num;
	}

	public int getBolt_StandardCubaBolt_num() {
		return bolt_StandardCubaBolt_num;
	}

	public void setBolt_StandardCubaBolt_num(int bolt_StandardCubaBolt_num) {
		this.bolt_StandardCubaBolt_num = bolt_StandardCubaBolt_num;
	}

	public int getBolt_NetOfGasBolt_num() {
		return bolt_NetOfGasBolt_num;
	}

	public void setBolt_NetOfGasBolt_num(int bolt_NetOfGasBolt_num) {
		this.bolt_NetOfGasBolt_num = bolt_NetOfGasBolt_num;
	}


	public void setBolt_SaveResultBolt_num(int bolt_SaveResultBolt_num) {
		this.bolt_SaveResultBolt_num = bolt_SaveResultBolt_num;
	}

	public int getWorker_num() {
		return worker_num;
	}

	public void setWorker_num(int worker_num) {
		this.worker_num = worker_num;
	}

	public int getBolt_MtrlBolt_num() {
		return bolt_MtrlBolt_num;
	}

	public void setBolt_MtrlBolt_num(int bolt_MtrlBolt_num) {
		this.bolt_MtrlBolt_num = bolt_MtrlBolt_num;
	}

	

	
}
