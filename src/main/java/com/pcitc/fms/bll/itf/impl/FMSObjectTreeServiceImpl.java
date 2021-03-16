package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.FMSObjectTree;
import com.pcitc.fms.bll.itf.FMSObjectTreeService;
import com.pcitc.fms.common.FMSObjectUtil;
import com.pcitc.fms.dal.dao.BizOrgDTLDao;
import com.pcitc.fms.dal.dao.RentDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.pojo.Rent;
import com.pcitc.fms.dal.pojo.TPmOrg;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class FMSObjectTreeServiceImpl implements FMSObjectTreeService {

	@Autowired
	private BizOrgDTLDao bizOrgDTLDao;

	@Autowired
	private TPmOrgDao orgDao;

	@Autowired
	private RentDao rentDao;

	@Override
	public List<FMSObjectTree> getFMSObjectTreeToNode(String rentCode, String bizCode, String orgCode, String type)
			throws BusiException {

		List<FMSObjectTree> result = new ArrayList<>();

		// 指定了type的值
		if (StringUtils.isNotEmpty(type)) {
			if (type.equals("node")) {
				resultForNode(rentCode, bizCode, orgCode, result);
			}

			if (type.equals("area")) {
				resultForArea(rentCode, bizCode, orgCode, result);
			}

			if (type.equals("org")) {
				resultForOrg(rentCode, bizCode, orgCode, result);
			}

			if (type.equals("measurement")) {
				resultForMeasurement(rentCode, bizCode, orgCode, result);
			}
		} else {
			// 未指定type的值，默认是查出org
			resultForOrg(rentCode, bizCode, orgCode, result);
		}

		return result;
	}

	// 结果到组织机构
	private void resultForOrg(String rentCode, String bizCode, String orgCode, List<FMSObjectTree> result)
			throws BusiException {

		List<Object> lists = new ArrayList<>();

		// 如果没有传bizCode或者orgCode或者传了bizCode，但是bizCode是标准业务域，（标准业务域对应的具体数据在org中），则组织机构的数据从标准树上取
		if (StringUtils.isEmpty(bizCode) || StringUtils.isEmpty(orgCode) || bizCode.contains("_SYSTEM_STANDARD_BIZ")) {
			// 获取租户
			Rent rent = rentDao.getRentByRentCode(rentCode);
			if (rent == null) {
				throw new BusiException("", "租户不存在");
			}
			if (rent.getOrgId() == null) {
				throw new BusiException("", "租户未指定组织机构根");
			}
			// 向下查出本级及以下
			lists = bizOrgDTLDao.getFMSOrgFromStandard(rent.getOrgId());
			// 根据租户信息中的orgID向上查出父节点
			List<Object> temp = bizOrgDTLDao.getFMSOrgFromStandardToUp(rent.getOrgId());

			// 由于上条查询中会多查出一条本级数据，以下去重
			removeOrgDuplicates(lists, rent, temp);
		} else {
			// 如果传了bizCode，并且不是标准业务域，按照普通业务域查数据（从dtl中）
			lists = bizOrgDTLDao.getFMSObjectTreeToOrg(bizCode, orgCode);
		}

		// 封装结果
		for (int i = 0; i < lists.size(); i++) {
			Object obj = lists.get(i);
			Object[] strs = (Object[]) obj;

			if (strs[0] != null) {
				FMSObjectTree fMSObjectTreeOrg = new FMSObjectTree();
				fMSObjectTreeOrg.setCode(strs[0] == null ? "" : (String) strs[0]);
				fMSObjectTreeOrg.setName(strs[1] == null ? "" : (String) strs[1]);
				fMSObjectTreeOrg.setAlias(strs[2] == null ? "" : (String) strs[2]);
				fMSObjectTreeOrg.setTypeName(strs[3] == null ? "" : (String) strs[3]);
				fMSObjectTreeOrg.setUpperCode(strs[4] == null ? "" : (String) strs[4]);
				fMSObjectTreeOrg.setUpperName(strs[5] == null ? "" : (String) strs[5]);
				fMSObjectTreeOrg.setUpperAlias(strs[6] == null ? "" : (String) strs[6]);

				String hierarchyTypeNameOrg = FMSObjectUtil
						.getFMSObjectRelation(strs[3] == null ? "" : (String) strs[3]);
				fMSObjectTreeOrg.setHierarchyTypeName(hierarchyTypeNameOrg);
				result.add(fMSObjectTreeOrg);
			}

		}

	}

	// 结果到区域
	private void resultForArea(String rentCode, String bizCode, String orgCode, List<FMSObjectTree> result)
			throws BusiException {

		List<Object> lists = new ArrayList<>();

		// 如果没有传bizCode或者orgCode，则组织机构的数据从标准树上取
		if (StringUtils.isEmpty(bizCode) || StringUtils.isEmpty(orgCode) || bizCode.contains("_SYSTEM_STANDARD_BIZ")) {
			Rent rent = rentDao.getRentByRentCode(rentCode);
			if (rent == null) {
				throw new BusiException("", "租户不存在");
			}
			if (rent.getOrgId() == null) {
				throw new BusiException("", "租户未指定组织机构根");
			}

			// 向下查出本级及以下（包括关联查出区域）
			lists = bizOrgDTLDao.getFMSAreaFromStandard(rent.getOrgId());

			// 根据租户信息中的orgID先向上查出父节点
			List<Object> temp = bizOrgDTLDao.getFMSOrgFromStandardToUp(rent.getOrgId());

			// 由于上条查询中会多查出一条本级数据，以下去重
			removeOrgDuplicates(lists, rent, temp);
		} else {
			lists = bizOrgDTLDao.getFMSObjectTreeToArea(bizCode, orgCode);
		}

		for (int i = 0; i < lists.size(); i++) {
			Object obj = lists.get(i);
			Object[] strs = (Object[]) obj;

			if (strs[0] != null) {
				FMSObjectTree fMSObjectTreeArea = new FMSObjectTree();
				fMSObjectTreeArea.setCode(strs[0] == null ? "" : (String) strs[0]);
				fMSObjectTreeArea.setName(strs[1] == null ? "" : (String) strs[1]);
				fMSObjectTreeArea.setAlias(strs[2] == null ? "" : (String) strs[2]);
				fMSObjectTreeArea.setTypeName(strs[3] == null ? "" : (String) strs[3]);
				fMSObjectTreeArea.setUpperCode(strs[4] == null ? "" : (String) strs[4]);
				fMSObjectTreeArea.setUpperName(strs[5] == null ? "" : (String) strs[5]);
				fMSObjectTreeArea.setUpperAlias(strs[6] == null ? "" : (String) strs[6]);

				String hierarchyTypeNameArea = FMSObjectUtil
						.getFMSObjectRelation(strs[3] == null ? "" : (String) strs[3]);
				fMSObjectTreeArea.setHierarchyTypeName(hierarchyTypeNameArea);
				result.add(fMSObjectTreeArea);
			}

			// 上面代码中“List<Object> temp =
			// bizOrgDTLDao.getFMSOrgFromStandardToUp(rent.getOrgId());”查出的每个结果只有7个字段
			if (strs.length > 7) {
				boolean isExistOrg = false;
				for (FMSObjectTree res : result) {
					if (res.getCode().equals(strs[4] == null ? "" : (String) strs[4])) {
						isExistOrg = true;
						break;
					}
				}

				if (isExistOrg == false) {
					if (strs[4] != null) {
						FMSObjectTree fMSObjectTreeOrg = new FMSObjectTree();
						fMSObjectTreeOrg.setCode(strs[4] == null ? "" : (String) strs[4]);
						fMSObjectTreeOrg.setName(strs[5] == null ? "" : (String) strs[5]);
						fMSObjectTreeOrg.setAlias(strs[6] == null ? "" : (String) strs[6]);
						fMSObjectTreeOrg.setTypeName(strs[7] == null ? "" : (String) strs[7]);
						fMSObjectTreeOrg.setUpperCode(strs[8] == null ? "" : (String) strs[8]);
						fMSObjectTreeOrg.setUpperName(strs[9] == null ? "" : (String) strs[9]);
						fMSObjectTreeOrg.setUpperAlias(strs[10] == null ? "" : (String) strs[10]);

						String hierarchyTypeNameOrg = FMSObjectUtil
								.getFMSObjectRelation(strs[7] == null ? "" : (String) strs[7]);
						fMSObjectTreeOrg.setHierarchyTypeName(hierarchyTypeNameOrg);
						result.add(fMSObjectTreeOrg);
					}
				}
			}

		}

	}

	// 结果到节点
	private void resultForNode(String rentCode, String bizCode, String orgCode, List<FMSObjectTree> result)
			throws BusiException {

		List<Object> lists = new ArrayList<>();

		if (StringUtils.isEmpty(bizCode) || StringUtils.isEmpty(orgCode) || bizCode.contains("_SYSTEM_STANDARD_BIZ")) {
			Rent rent = rentDao.getRentByRentCode(rentCode);
			if (rent == null) {
				throw new BusiException("", "租户不存在");
			}
			if (rent.getOrgId() == null) {
				throw new BusiException("", "租户未指定组织机构根");
			}

			// 向下查出本级及以下（包括关联查出区域和节点）
			lists = bizOrgDTLDao.getFMSNodeFromStandard(rent.getOrgId());

			// 根据租户信息中的orgID先向上查出父节点
			List<Object> temp = bizOrgDTLDao.getFMSOrgFromStandardToUp(rent.getOrgId());

			// 由于上条查询中会多查出一条本级数据，以下去重
			removeOrgDuplicates(lists, rent, temp);
		} else {
			lists = bizOrgDTLDao.getFMSObjectTreeToNode(bizCode, orgCode);
		}

		for (int i = 0; i < lists.size(); i++) {
			Object obj = lists.get(i);
			Object[] strs = (Object[]) obj;

			if (strs[0] != null) {
				FMSObjectTree fMSObjectTreeNode = new FMSObjectTree();
				fMSObjectTreeNode.setCode(strs[0] == null ? "" : (String) strs[0]);
				fMSObjectTreeNode.setName(strs[1] == null ? "" : (String) strs[1]);
				fMSObjectTreeNode.setAlias(strs[2] == null ? "" : (String) strs[2]);
				fMSObjectTreeNode.setTypeName(strs[3] == null ? "" : (String) strs[3]);
				fMSObjectTreeNode.setUpperCode(strs[4] == null ? "" : (String) strs[4]);
				fMSObjectTreeNode.setUpperName(strs[5] == null ? "" : (String) strs[5]);
				fMSObjectTreeNode.setUpperAlias(strs[6] == null ? "" : (String) strs[6]);

				String hierarchyTypeName = FMSObjectUtil.getFMSObjectRelation(strs[3] == null ? "" : (String) strs[3]);
				fMSObjectTreeNode.setHierarchyTypeName(hierarchyTypeName);
				result.add(fMSObjectTreeNode);
			}

			boolean isExistArea = false;
			for (FMSObjectTree res : result) {
				if (res.getCode().equals(strs[4] == null ? "" : (String) strs[4])) {
					isExistArea = true;
					break;
				}
			}

			// 上面代码中“List<Object> temp =
			// bizOrgDTLDao.getFMSOrgFromStandardToUp(rent.getOrgId());”查出的每个结果只有7个字段
			if (strs.length > 7) {
				if (isExistArea == false) {

					if (strs[4] != null) {
						FMSObjectTree fMSObjectTreeArea = new FMSObjectTree();
						fMSObjectTreeArea.setCode(strs[4] == null ? "" : (String) strs[4]);
						fMSObjectTreeArea.setName(strs[5] == null ? "" : (String) strs[5]);
						fMSObjectTreeArea.setAlias(strs[6] == null ? "" : (String) strs[6]);
						fMSObjectTreeArea.setTypeName(strs[7] == null ? "" : (String) strs[7]);
						fMSObjectTreeArea.setUpperCode(strs[8] == null ? "" : (String) strs[8]);
						fMSObjectTreeArea.setUpperName(strs[9] == null ? "" : (String) strs[9]);
						fMSObjectTreeArea.setUpperAlias(strs[10] == null ? "" : (String) strs[10]);

						String hierarchyTypeNameArea = FMSObjectUtil
								.getFMSObjectRelation(strs[7] == null ? "" : (String) strs[7]);
						fMSObjectTreeArea.setHierarchyTypeName(hierarchyTypeNameArea);
						result.add(fMSObjectTreeArea);
					}
				}

				// 上面代码中“List<Object> temp =
				// bizOrgDTLDao.getFMSOrgFromStandardToUp(rent.getOrgId());”查出的每个结果只有7个字段
				if (strs.length > 11) {
					boolean isExistOrg = false;
					for (FMSObjectTree res : result) {
						if (res.getCode().equals(strs[8] == null ? "" : (String) strs[8])) {
							isExistOrg = true;
							break;
						}
					}

					if (isExistOrg == false) {

						if (strs[8] != null) {
							FMSObjectTree fMSObjectTreeOrg = new FMSObjectTree();
							fMSObjectTreeOrg.setCode(strs[8] == null ? "" : (String) strs[8]);
							fMSObjectTreeOrg.setName(strs[9] == null ? "" : (String) strs[9]);
							fMSObjectTreeOrg.setAlias(strs[10] == null ? "" : (String) strs[10]);
							fMSObjectTreeOrg.setTypeName(strs[11] == null ? "" : (String) strs[11]);
							fMSObjectTreeOrg.setUpperCode(strs[12] == null ? "" : (String) strs[12]);
							fMSObjectTreeOrg.setUpperName(strs[13] == null ? "" : (String) strs[13]);
							fMSObjectTreeOrg.setUpperAlias(strs[14] == null ? "" : (String) strs[14]);
							String hierarchyTypeNameOrg = FMSObjectUtil
									.getFMSObjectRelation(strs[11] == null ? "" : (String) strs[11]);
							fMSObjectTreeOrg.setHierarchyTypeName(hierarchyTypeNameOrg);
							result.add(fMSObjectTreeOrg);
						}
					}
				}

			}
		}
	}

	// 结果到度量指标
	private void resultForMeasurement(String rentCode, String bizCode, String orgCode, List<FMSObjectTree> result)
			throws BusiException {
		List<Object> lists = new ArrayList<>();

		if (StringUtils.isEmpty(bizCode) || StringUtils.isEmpty(orgCode) || bizCode.contains("_SYSTEM_STANDARD_BIZ")) {
			Rent rent = rentDao.getRentByRentCode(rentCode);
			if (rent == null) {
				throw new BusiException("", "租户不存在");
			}
			if (rent.getOrgId() == null) {
				throw new BusiException("", "租户未指定组织机构根");
			}

			// 向下查出本级及以下（包括关联查出区域和节点、度量指标）
			lists = bizOrgDTLDao.getFMSMeasurementFromStandard(rent.getOrgId());

			// 根据租户信息中的orgID先向上查出父节点
			List<Object> temp = bizOrgDTLDao.getFMSOrgFromStandardToUp(rent.getOrgId());

			// 由于上条查询中会多查出一条本级数据，以下去重
			removeOrgDuplicates(lists, rent, temp);
		} else {
			lists = bizOrgDTLDao.getFMSObjectTreeToMeasurement(bizCode, orgCode);
		}

		for (int i = 0; i < lists.size(); i++) {
			Object obj = lists.get(i);
			Object[] strs = (Object[]) obj;

			if (strs[0] != null) {
				FMSObjectTree fMSObjectTreeMes = new FMSObjectTree();
				fMSObjectTreeMes.setCode(strs[0] == null ? "" : (String) strs[0]);
				fMSObjectTreeMes.setName(strs[1] == null ? "" : (String) strs[1]);
				fMSObjectTreeMes.setAlias(strs[2] == null ? "" : (String) strs[2]);
				fMSObjectTreeMes.setTypeName(strs[3] == null ? "" : (String) strs[3]);
				fMSObjectTreeMes.setUpperCode(strs[4] == null ? "" : (String) strs[4]);
				fMSObjectTreeMes.setUpperName(strs[5] == null ? "" : (String) strs[5]);
				fMSObjectTreeMes.setUpperAlias(strs[6] == null ? "" : (String) strs[6]);

				String hierarchyTypeName = FMSObjectUtil.getFMSObjectRelation(strs[3] == null ? "" : (String) strs[3]);
				fMSObjectTreeMes.setHierarchyTypeName(hierarchyTypeName);
				result.add(fMSObjectTreeMes);
			}

			boolean isExistNode = false;
			for (FMSObjectTree res : result) {
				if (res.getCode().equals(strs[4] == null ? "" : (String) strs[4])) {
					isExistNode = true;
					break;
				}
			}

			if (strs.length > 7) {
				if (isExistNode == false) {
					if (strs[4] != null) {
						FMSObjectTree fMSObjectTreeNode = new FMSObjectTree();
						fMSObjectTreeNode.setCode(strs[4] == null ? "" : (String) strs[4]);
						fMSObjectTreeNode.setName(strs[5] == null ? "" : (String) strs[5]);
						fMSObjectTreeNode.setAlias(strs[6] == null ? "" : (String) strs[6]);
						fMSObjectTreeNode.setTypeName(strs[7] == null ? "" : (String) strs[7]);
						fMSObjectTreeNode.setUpperCode(strs[8] == null ? "" : (String) strs[8]);
						fMSObjectTreeNode.setUpperName(strs[9] == null ? "" : (String) strs[9]);
						fMSObjectTreeNode.setUpperAlias(strs[10] == null ? "" : (String) strs[10]);

						String hierarchyTypeName = FMSObjectUtil
								.getFMSObjectRelation(strs[7] == null ? "" : (String) strs[7]);
						fMSObjectTreeNode.setHierarchyTypeName(hierarchyTypeName);
						result.add(fMSObjectTreeNode);
					}
				}
			}
			boolean isExistArea = false;
			for (FMSObjectTree res : result) {
				if (res.getCode().equals(strs[8] == null ? "" : (String) strs[8])) {
					isExistArea = true;
					break;
				}
			}

			// 上面代码中“List<Object> temp =
			// bizOrgDTLDao.getFMSOrgFromStandardToUp(rent.getOrgId());”查出的每个结果只有7个字段
			if (strs.length > 11) {
				if (isExistArea == false) {

					if (strs[8] != null) {
						FMSObjectTree fMSObjectTreeArea = new FMSObjectTree();
						fMSObjectTreeArea.setCode(strs[8] == null ? "" : (String) strs[8]);
						fMSObjectTreeArea.setName(strs[9] == null ? "" : (String) strs[9]);
						fMSObjectTreeArea.setAlias(strs[10] == null ? "" : (String) strs[10]);
						fMSObjectTreeArea.setTypeName(strs[11] == null ? "" : (String) strs[11]);
						fMSObjectTreeArea.setUpperCode(strs[12] == null ? "" : (String) strs[12]);
						fMSObjectTreeArea.setUpperName(strs[13] == null ? "" : (String) strs[13]);
						fMSObjectTreeArea.setUpperAlias(strs[14] == null ? "" : (String) strs[14]);

						String hierarchyTypeNameArea = FMSObjectUtil
								.getFMSObjectRelation(strs[11] == null ? "" : (String) strs[11]);
						fMSObjectTreeArea.setHierarchyTypeName(hierarchyTypeNameArea);
						result.add(fMSObjectTreeArea);
					}
				}

				// 上面代码中“List<Object> temp =
				// bizOrgDTLDao.getFMSOrgFromStandardToUp(rent.getOrgId());”查出的每个结果只有7个字段
				if (strs.length > 15) {
					boolean isExistOrg = false;
					for (FMSObjectTree res : result) {
						if (res.getCode().equals(strs[12] == null ? "" : (String) strs[12])) {
							isExistOrg = true;
							break;
						}
					}

					if (isExistOrg == false) {

						if (strs[12] != null) {
							FMSObjectTree fMSObjectTreeOrg = new FMSObjectTree();
							fMSObjectTreeOrg.setCode(strs[12] == null ? "" : (String) strs[12]);
							fMSObjectTreeOrg.setName(strs[13] == null ? "" : (String) strs[13]);
							fMSObjectTreeOrg.setAlias(strs[14] == null ? "" : (String) strs[14]);
							fMSObjectTreeOrg.setTypeName(strs[15] == null ? "" : (String) strs[15]);
							fMSObjectTreeOrg.setUpperCode(strs[16] == null ? "" : (String) strs[16]);
							fMSObjectTreeOrg.setUpperName(strs[17] == null ? "" : (String) strs[17]);
							fMSObjectTreeOrg.setUpperAlias(strs[18] == null ? "" : (String) strs[18]);
							String hierarchyTypeNameOrg = FMSObjectUtil
									.getFMSObjectRelation(strs[15] == null ? "" : (String) strs[15]);
							fMSObjectTreeOrg.setHierarchyTypeName(hierarchyTypeNameOrg);
							result.add(fMSObjectTreeOrg);
						}
					}
				}

			}
		}

	}

	private void removeOrgDuplicates(List<Object> lists, Rent rent, List<Object> temp) {
		TPmOrg org = orgDao.getTPmOrgByOrgId(rent.getOrgId());
		for (Object obj : temp) {

			Object[] strs = (Object[]) obj;
			if (!((String) strs[0]).equals(org.getOrgCode())) {
				lists.add(obj);
			}
		}
	}

}
