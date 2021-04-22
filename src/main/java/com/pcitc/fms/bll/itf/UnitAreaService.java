package com.pcitc.fms.bll.itf;

import com.pcitc.fms.dal.pojo.UnitArea;
import com.pcitc.fms.service.model.Pager;
import org.springframework.data.domain.Pageable;

/**
 * @author yalin.zhao
 * @date 2021/4/21 9:38
 */
public interface UnitAreaService {

    Pager<UnitArea> getUnitAreas(com.pcitc.fms.service.model.UnitArea unitArea, Pageable pageable) throws Exception;

}
