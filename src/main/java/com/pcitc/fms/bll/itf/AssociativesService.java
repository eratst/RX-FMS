package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Associatives;
import com.pcitc.fms.service.model.Pager;

public interface AssociativesService {

	public Pager<Associatives> findAssociatives(com.pcitc.fms.service.model.Associatives associatives, Pageable pageable) throws Exception;
}
