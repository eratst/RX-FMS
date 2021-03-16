package com.pcitc.fms.service.stormModel;


import java.io.Serializable;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;



/**
 * 流转的数据对象
 * 
 * @author pcitc
 *
 */
public class TupleVO extends BaseResRep implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CalcCtlVO calcCtlVO;// 

	private CalcParamVO calcParamVO;// 

	private MatInitVO matInitVO;
	
	private TankCapacityParamVO tankCapacityParamVO;
	
	private TankConfigVO tankConfigVO;
	
	private TankInitVO tankInitVO;
	
	private TankOutputVO tankOutputVO;

	private SystemParamVO dbParamVO;// 配置文件信息

	private String tankid;//罐ID
	
	private boolean isFinish;//是否结束本次计算

	public CalcCtlVO getCalcCtlVO() {
		return calcCtlVO;
	}

	public void setCalcCtlVO(CalcCtlVO calcCtlVO) {
		this.calcCtlVO = calcCtlVO;
	}

	public CalcParamVO getCalcParamVO() {
		return calcParamVO;
	}

	public void setCalcParamVO(CalcParamVO calcParamVO) {
		this.calcParamVO = calcParamVO;
	}

	public MatInitVO getMatInitVO() {
		return matInitVO;
	}

	public void setMatInitVO(MatInitVO matInitVO) {
		this.matInitVO = matInitVO;
	}

	public TankCapacityParamVO getTankCapacityParamVO() {
		return tankCapacityParamVO;
	}

	public void setTankCapacityParamVO(TankCapacityParamVO tankCapacityParamVO) {
		this.tankCapacityParamVO = tankCapacityParamVO;
	}

	public TankConfigVO getTankConfigVO() {
		return tankConfigVO;
	}

	public void setTankConfigVO(TankConfigVO tankConfigVO) {
		this.tankConfigVO = tankConfigVO;
	}

	public TankInitVO getTankInitVO() {
		return tankInitVO;
	}

	public void setTankInitVO(TankInitVO tankInitVO) {
		this.tankInitVO = tankInitVO;
	}

	public TankOutputVO getTankOutputVO() {
		if(tankOutputVO == null){
			tankOutputVO = new TankOutputVO();
		}
		return tankOutputVO;
	}

	public void setTankOutputVO(TankOutputVO tankOutputVO) {
		this.tankOutputVO = tankOutputVO;
	}



	public SystemParamVO getDbParamVO() {
		return dbParamVO;
	}

	public void setDbParamVO(SystemParamVO dbParamVO) {
		this.dbParamVO = dbParamVO;
	}

	public String getTankid() {
		return tankid;
	}

	public void setTankid(String tankid) {
		this.tankid = tankid;
	}

	public boolean isFinish() {
		return isFinish;
	}

	public void setFinish(boolean isFinish) {
		this.isFinish = isFinish;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	

}

