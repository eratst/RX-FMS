package com.pcitc.fms.bll.itf;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.Deupdownlimit;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.Pager;

@Service
public interface DeupdownlimitService {

	public List<Deupdownlimit> getPageDeupdownlimits(com.pcitc.fms.service.model.Deupdownlimit deupdownlimit)
			throws BusinessException, SQLException;


}
