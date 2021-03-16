package com.pcitc.fms.service.stormModel;
/**
 * 
 * @author lzp
 *
 */
public class PipeNetTankCoef {
	private boolean isChanged; //
	private boolean isDeleted;
	
	private int intmonth; //月份
	
	private Double coefficient;//系数
	
	public int getIntmonth() {
		return intmonth;
	}
	public void setIntmonth(int intmonth) {
		this.intmonth = intmonth;
	}
	public Double getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	 public PipeNetTankCoef(){
			
	}
	 public boolean isChanged() {
		return isChanged;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void MarkAsDeleted(){
		isDeleted = true;
		isChanged = true;
	}
	
}

