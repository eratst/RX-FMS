package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.IcStangasden;
import com.pcitc.fms.service.model.Pager;

public interface IcStangasdenService {

	public Pager<IcStangasden> findIcStangasdens(com.pcitc.fms.service.model.IcStangasden icStangasden, Pageable pageable)
			throws Exception;

	public List<IcStangasden> findIcStangasdenById(Long icStangasdenId) throws Exception;

}
