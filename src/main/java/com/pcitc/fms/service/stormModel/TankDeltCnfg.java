package com.pcitc.fms.service.stormModel;

/**
 * 罐默认配置
 * @author haiwen.wang
 *
 */
public class TankDeltCnfg {
	
	private String delt_cnfg_id;
	/// 名称
    private String strName;
    /// 值
    private String strValue;
    /// 分类
    private String strType;
    /// 描述信息
    private String strDescribe;
    
    public String getDelt_cnfg_id() {
		return delt_cnfg_id;
	}
	public void setDelt_cnfg_id(String delt_cnfg_id) {
		this.delt_cnfg_id = delt_cnfg_id;
	}
    
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getStrValue() {
		return strValue;
	}
	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}
	public String getStrType() {
		return strType;
	}
	public void setStrType(String strType) {
		this.strType = strType;
	}
	public String getStrDescribe() {
		return strDescribe;
	}
	public void setStrDescribe(String strDescribe) {
		this.strDescribe = strDescribe;
	}
    
}
