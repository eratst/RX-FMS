package com.pcitc.fms.bll.itf.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.ManagementTankArea;
import com.pcitc.fms.bll.itf.ManagementTankAreaService;
import com.pcitc.fms.dal.dao.ManagementTankAreaDao;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
@Component
public class ManagementTankAreaServiceImpl implements ManagementTankAreaService {

	@Autowired
	private ManagementTankAreaDao managementTankAreaDao;

	@SuppressWarnings("unchecked")
	@Override
	public Pager<ManagementTankArea> getManagementTankAreas(
			com.pcitc.fms.service.model.ManagementTankArea managementTankArea, Pageable pageable) throws Exception {
		if(managementTankArea.getInUse()!=null){
			if(managementTankArea.getInUse()!=0 && managementTankArea.getInUse()!=1 && managementTankArea.getInUse()!=-1){
				throw new BusiException("", "InUse只能为1，0，-1");
			}
		}
		Pager<ManagementTankArea> pageData = new Pager<>();
		Page<com.pcitc.fms.dal.pojo.ManagementTankArea> properties = managementTankAreaDao
				.getManagementTankAreas(managementTankArea, pageable);
		List<ManagementTankArea> entityList = ObjectConverter.listConverter(properties.getContent(),
				ManagementTankArea.class);
		pageData.setContent(entityList);
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
