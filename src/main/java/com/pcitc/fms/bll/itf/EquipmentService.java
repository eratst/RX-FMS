package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.service.model.Equipment;
import com.pcitc.fms.service.model.Pager;

/**
 * The interface Equipment service.
 */
@Service
public interface EquipmentService {
	/**
	 * Gets page equipments.
	 *
	 * @param equipmentEntity the equipment entity
	 * @param pageable the pageable
	 * @param factoryCode the factory code
	 * @return the page equipments
	 * @throws Exception the exception
	 */
	public Pager<com.pcitc.fms.bll.entity.Equipment> getPageEquipments(com.pcitc.fms.service.model.Equipment equipmentEntity, Pageable pageable) throws BusinessException;

}
