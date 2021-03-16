package com.pcitc.fms.bll.itf.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.ManagementTank;
import com.pcitc.fms.bll.itf.ManagementTankService;
import com.pcitc.fms.dal.dao.ManagementTankDao;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
@Component
public class ManagementTankServiceImpl implements ManagementTankService {

	@Autowired
	private ManagementTankDao managementTankDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<ManagementTank> getManagementTanks(com.pcitc.fms.service.model.ManagementTank managementTank, Pageable pageable)
			throws Exception {
		if (null != managementTank.getInUse()) {
			if (0 != managementTank.getInUse() && -1 != managementTank.getInUse() && 1 != managementTank.getInUse()) {
				throw new BusiException("", "数据状态只能是0，-1，1");
			}
		}
		Pager<ManagementTank> pageData = new Pager<>();
		Page<com.pcitc.fms.dal.pojo.ManagementTank> properties = managementTankDao.findManagementTanks(managementTank, pageable);
		List<ManagementTank> entityManagementTanks = ObjectConverter.listConverter(properties.getContent(), ManagementTank.class);
		pageData.setContent(entityManagementTanks);
		pageData.setFirst(properties.isFirst());
		pageData.setLast(properties.isLast());
		pageData.setNumber(properties.getNumber());
		pageData.setNumberOfElements(properties.getNumberOfElements());
		pageData.setSize(properties.getSize());
		pageData.setSort(properties.getSort());
		pageData.setTotalElements(properties.getTotalElements());
		pageData.setTotalPages(properties.getTotalPages());
		return pageData;
	}

}
