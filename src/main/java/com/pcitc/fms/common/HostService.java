package com.pcitc.fms.common;



public class HostService {
	static String http = "http://";
	static String aaaUrl = "/OrgAndUserService/orgs/";
	
	public static String getOrgUnitMeta(String orgCode,String orgUnitCode){
		StringBuffer urlSB = new StringBuffer(http);
		urlSB.append(HostConfigure.AAAHOST);
		urlSB.append(aaaUrl);
		urlSB.append(orgCode);
		urlSB.append("/orgUnits/");
		urlSB.append(orgUnitCode);
		urlSB.append("?opertype=leaves");
		return MicroServiceClient.getRequest(urlSB.toString(),null);
		
	}
	
	
}
