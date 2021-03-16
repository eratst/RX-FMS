package com.pcitc.fms.bll.itf;

import java.util.List;

import com.pcitc.fms.bll.entity.OrgTree;


public interface TPmOrgService {
	
	public List<OrgTree> getAllTrees(Long orgId);
	
	public List<OrgTree> getBranchTrees(Long orgId);

}
