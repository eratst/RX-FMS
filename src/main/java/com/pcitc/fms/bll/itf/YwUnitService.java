package com.pcitc.fms.bll.itf;

import com.pcitc.fms.dal.pojo.YwUnit;
import com.pcitc.fms.service.model.Pager;
import org.springframework.data.domain.Pageable;

public interface YwUnitService {

    Pager<YwUnit> getYwUnits(com.pcitc.fms.service.model.YwUnit ywUnit, Pageable pageable) throws Exception;

}
