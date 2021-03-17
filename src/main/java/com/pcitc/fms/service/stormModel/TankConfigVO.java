package com.pcitc.fms.service.stormModel;

import java.io.Serializable;

/**
 * 罐配置信息
 * @author haiwen.wang
 *
 */
public class TankConfigVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String configID;//配置ID;
	
	private String tankID;//罐ID;
	
	private String tankCode;//罐编码;
	
	private String tankName;//罐名称;
	
	private int tankType;//罐类型ID;
	
	private String tankTypeName;//罐类型名称;
	
	private String denForm;//标准密度公式;
	
	private String formVTF;//VTF公式;
	
	private String formVPF;//总尺VPF公式;
	
	private String formVPFSC;//水尺VPF公式;
	
	private String formYHCPXS;//液化产品系数公式;
	
	private String formVCF;//VCF公式;
	
	private String xswVCF;//VCF小数位;
	
	private String tankForm;//罐公式;
	
	private String tankGasForm;//球罐气体公式;
	
	private String revChkForm;//修正检尺公式;
	
	private String tankAirDen;//罐空气密度式;
	
	private String tankFormMode;//罐体积方式;
	
	private String stdCubaForm;//罐标准体积公式;

	public String getConfigID() {
		return configID;
	}

	public void setConfigID(String configID) {
		this.configID = configID;
	}

	public String getTankID() {
		return tankID;
	}

	public void setTankID(String tankID) {
		this.tankID = tankID;
	}

	public String getTankCode() {
		return tankCode;
	}

	public void setTankCode(String tankCode) {
		this.tankCode = tankCode;
	}

	public String getTankName() {
		return tankName;
	}

	public void setTankName(String tankName) {
		this.tankName = tankName;
	}

	public int getTankType() {
		return tankType;
	}

	public void setTankType(int tankType) {
		this.tankType = tankType;
	}

	public String getTankTypeName() {
		return tankTypeName;
	}

	public void setTankTypeName(String tankTypeName) {
		this.tankTypeName = tankTypeName;
	}

	public String getDenForm() {
		return denForm;
	}

	public void setDenForm(String denForm) {
		this.denForm = denForm;
	}

	public String getFormVTF() {
		return formVTF;
	}

	public void setFormVTF(String formVTF) {
		this.formVTF = formVTF;
	}

	public String getFormVPF() {
		return formVPF;
	}

	public void setFormVPF(String formVPF) {
		this.formVPF = formVPF;
	}

	public String getFormVPFSC() {
		return formVPFSC;
	}

	public void setFormVPFSC(String formVPFSC) {
		this.formVPFSC = formVPFSC;
	}

	public String getFormYHCPXS() {
		return formYHCPXS;
	}

	public void setFormYHCPXS(String formYHCPXS) {
		this.formYHCPXS = formYHCPXS;
	}

	public String getFormVCF() {
		return formVCF;
	}

	public void setFormVCF(String formVCF) {
		this.formVCF = formVCF;
	}

	public String getXswVCF() {
		return xswVCF;
	}

	public void setXswVCF(String xswVCF) {
		this.xswVCF = xswVCF;
	}

	public String getTankForm() {
		return tankForm;
	}

	public void setTankForm(String tankForm) {
		this.tankForm = tankForm;
	}

	public String getTankGasForm() {
		return tankGasForm;
	}

	public void setTankGasForm(String tankGasForm) {
		this.tankGasForm = tankGasForm;
	}

	public String getRevChkForm() {
		return revChkForm;
	}

	public void setRevChkForm(String revChkForm) {
		this.revChkForm = revChkForm;
	}

	public String getTankAirDen() {
		return tankAirDen;
	}

	public void setTankAirDen(String tankAirDen) {
		this.tankAirDen = tankAirDen;
	}

	public String getTankFormMode() {
		return tankFormMode;
	}

	public void setTankFormMode(String tankFormMode) {
		this.tankFormMode = tankFormMode;
	}

	public String getStdCubaForm() {
		return stdCubaForm;
	}

	public void setStdCubaForm(String stdCubaForm) {
		this.stdCubaForm = stdCubaForm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
