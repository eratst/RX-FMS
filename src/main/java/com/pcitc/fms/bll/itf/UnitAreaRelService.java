package com.pcitc.fms.bll.itf;

import com.pcitc.fms.dal.pojo.UnitAreaRel;
import com.pcitc.fms.service.model.Pager;
import org.springframework.data.domain.Pageable;

public interface UnitAreaRelService {

    Pager<UnitAreaRel> getUnitAreaRels(com.pcitc.fms.service.model.UnitAreaRel unitAreaRel, Pageable pageable) throws Exception;

}
