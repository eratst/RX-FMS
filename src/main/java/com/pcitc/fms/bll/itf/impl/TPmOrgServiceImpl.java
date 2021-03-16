package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.OrgTree;
import com.pcitc.fms.bll.itf.TPmOrgService;
import com.pcitc.fms.dal.dao.TPmOrgDao;

@Service
public class TPmOrgServiceImpl implements TPmOrgService {

	@Autowired
	private TPmOrgDao tPmOrgDao;

	@Override
	public List<OrgTree> getAllTrees(Long orgId) {
		List<Object> allTrees = tPmOrgDao.getAllTrees(orgId);
		List<OrgTree> tPmOrgLists = convertTool(allTrees);
		return tPmOrgLists;
	}

	@Override
	public List<OrgTree> getBranchTrees(Long orgId) {

		List<OrgTree> result = new ArrayList<>();

		// 向下查
		List<OrgTree> sonTPmOrgs = getAllTrees(orgId);

		// 向上查
		List<Object> branchTrees = tPmOrgDao.getBranchTrees(orgId);
		List<OrgTree> dadTPmOrgs = convertTool(branchTrees);
		for (int i = dadTPmOrgs.size() - 1; i >= 0; i--) {
			if (orgId != null && dadTPmOrgs.get(i).getOrgId() != null) {
				System.out.println(i);
				if (!orgId.toString().equals(dadTPmOrgs.get(i).getOrgId().toString())) {
					result.add(dadTPmOrgs.get(i));
				}
			}
		}

		result.addAll(sonTPmOrgs);

		return result;
	}

	private List<OrgTree> convertTool(List<Object> allTrees) {
		List<OrgTree> tPmOrgLists = new ArrayList<OrgTree>();
		for (int i = 0; i < allTrees.size(); i++) {

			Object[] objs = (Object[]) allTrees.get(i);

			OrgTree tPmOrg = new OrgTree();
			tPmOrg.setOrgId(Long.valueOf(objs[0].toString()));
			if (null != objs[1]) {
				tPmOrg.setUpperOrgId(Long.valueOf(objs[1].toString()));
			}
			tPmOrg.setUpperOrgCode((String) objs[2]);
			tPmOrg.setUpperOrgName((String) objs[3]);
			tPmOrg.setUpperOrgAlias((String) objs[4]);
			tPmOrg.setOrgCode((String) objs[5]);
			tPmOrg.setOrgName((String) objs[6]);
			tPmOrg.setOrgAlias((String) objs[7]);
			tPmOrg.setOrgTypeId(Long.valueOf(objs[8].toString()));
			tPmOrg.setOrgTypeCode((String) objs[9]);
			tPmOrg.setOrgTypeName((String) objs[10]);

			tPmOrgLists.add(tPmOrg);
		}
		return tPmOrgLists;
	}

}
