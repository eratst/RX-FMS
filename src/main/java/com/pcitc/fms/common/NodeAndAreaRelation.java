package com.pcitc.fms.common;

import java.util.ArrayList;
import java.util.List;

import com.pcitc.fms.exception.BusinessException;

public class NodeAndAreaRelation {
	public static void getNodeAndAreaRelation (String Type, String nodeTypeName) throws BusinessException {
		
		Integer countor = 0;
		String nodeAndAreaStr = Type+nodeTypeName;
		
		List<String> strList = new ArrayList<>();
		
		//罐区
		strList.add("tankAreastanks");
		strList.add("tankAreassamplePoints");
		strList.add("tankAreasoutlets");
		strList.add("tankAreasvalves");
		strList.add("tankAreasequipments");
		strList.add("tankAreasetees");
		strList.add("tankAreastubulations");
		strList.add("tankAreasplates");
		
		//装置
		strList.add("plantssilos");
		strList.add("plantssamplePoints");
		strList.add("plantssideLines");
		strList.add("plantsoutlets");
		strList.add("plantsvalves");
		strList.add("plantsequipments");
		strList.add("plantstees");
		strList.add("plantsplates");
		
		//管网
		strList.add("pipeNetworkssamplePoints");
		strList.add("pipeNetworksoutlets");
		strList.add("pipeNetworkstees");
		strList.add("pipeNetworkstubulations");
		strList.add("pipeNetworksplates");
		
		//仓库
		strList.add("warehousessamplePoints");
		strList.add("warehousesoutlets");
		strList.add("warehousesvalves");
		strList.add("warehousesequipments");
		strList.add("warehousestees");
		strList.add("warehousestubulations");
		strList.add("warehousesstocks");
		strList.add("warehousesplates");
		
		//装卸台
		strList.add("loadingDocksoutlets");
		strList.add("loadingDocksvalves");
		strList.add("loadingDocksequipments");
		strList.add("loadingDockstees");
		strList.add("loadingDockstubulations");
		strList.add("loadingDocksplates");
		strList.add("loadingDocksedgePoints");
		
		//办公区
		strList.add("administrationssamplePoints");
		strList.add("administrationsvalves");
		strList.add("administrationsequipments");
		strList.add("administrationstees");
		strList.add("administrationstubulations");
		strList.add("administrationsplates");
		
		//生活区
		strList.add("communitiessamplePoints");
		strList.add("communitiesvalves");
		strList.add("communitiesequipments");
		strList.add("communitiestees");
		strList.add("communitiestubulations");
		strList.add("communitiesplates");
		
		for (String str : strList) {
			if (str.equals(nodeAndAreaStr)) {
				countor = countor+1;
			} 
		}
		
		if (countor != 1) {
			throw new BusinessException("", "", ""+nodeTypeName+"节点与"+Type+"区域不匹配！");
		}
		
	}
}
