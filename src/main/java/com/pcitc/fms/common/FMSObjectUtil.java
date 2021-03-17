package com.pcitc.fms.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FMSObjectUtil {
	
	private static Map<String,String> maps = new ConcurrentHashMap<String ,String>();
	
	static {
		maps.put("企业（分、子公司）", "组织机构");
		maps.put("公司处室", "组织机构");
		maps.put("生产工厂（分厂）", "组织机构");
		maps.put("科室", "组织机构");
		maps.put("车间", "组织机构");
		maps.put("班组", "组织机构");
		maps.put("总部", "组织机构");
		maps.put("事业部", "组织机构");
		maps.put("plant", "区域");
		maps.put("tankArea", "区域");
		maps.put("warehouse", "区域");
		maps.put("loadingDock", "区域");
		maps.put("pipeNetwork", "区域");
		maps.put("administration", "区域");
		maps.put("communitie", "区域");
		maps.put("sideLine", "节点");
		maps.put("tank", "节点");
		maps.put("silo", "节点");
		maps.put("stock", "节点");
		maps.put("edgePoint", "节点");
		maps.put("samplePoint", "节点");
		maps.put("outlet", "节点");
		maps.put("equipment", "节点");
		maps.put("tubulation", "节点");
		maps.put("valve", "节点");
		maps.put("plate", "节点");
		maps.put("tee", "节点");
		maps.put("大气温度", "度量指标");
		maps.put("水尺液位", "度量指标");
		maps.put("电压", "度量指标");
		maps.put("电流", "度量指标");
		maps.put("开度", "度量指标");
		maps.put("电量", "度量指标");
		maps.put("经度", "度量指标");
		maps.put("纬度", "度量指标");
		maps.put("心率", "度量指标");
		maps.put("阀门", "度量指标");
		maps.put("空", "度量指标");
		maps.put("流量", "度量指标");
		maps.put("油尺液位", "度量指标");
		maps.put("压力", "度量指标");
		maps.put("油温", "度量指标");
		maps.put("密度", "度量指标");
		maps.put("水分", "度量指标");
		maps.put("浓度", "度量指标");
		maps.put("体积", "度量指标");
		maps.put("位移", "度量指标");
		maps.put("厚度", "度量指标");
		maps.put("转速", "度量指标");
		maps.put("计量", "度量指标");
		maps.put("过程量", "度量指标");
		maps.put("震动", "度量指标");
		maps.put("流速", "度量指标");
	}
	
	public static String getFMSObjectRelation(String key) { 
		String result = "";
		if (maps != null) {
			result = maps.get(key);
		}
		return result;
	}

}
