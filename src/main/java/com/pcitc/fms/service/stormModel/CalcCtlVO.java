package com.pcitc.fms.service.stormModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 计算控制信息
 * @author haiwen.wang
 *
 */
public class CalcCtlVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tankID;//罐id
	
	private int frequency;//计算间隔

	private int deviation;//时间偏移量
	
	private String configID;//配置id
	
	private int agentid;//agentid
	
	private String appcode;//应用编码

	public String getTankID() {
		return tankID;
	}

	public void setTankID(String tankID) {
		this.tankID = tankID;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getDeviation() {
		return deviation;
	}

	public void setDeviation(int deviation) {
		this.deviation = deviation;
	}

	public String getConfigID() {
		return configID;
	}

	public void setConfigID(String configID) {
		this.configID = configID;
	}

	public int getAgentid() {
		return agentid;
	}

	public void setAgentid(int agentid) {
		this.agentid = agentid;
	}

	public String getAppcode() {
		return appcode;
	}

	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}
	
	
	
}
