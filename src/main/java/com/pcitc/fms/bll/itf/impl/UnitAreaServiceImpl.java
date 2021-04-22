package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.itf.UnitAreaService;
import com.pcitc.fms.dal.dao.UnitAreaRepository;
import com.pcitc.fms.dal.pojo.UnitArea;
import com.pcitc.fms.service.model.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pcitc.imp.common.ettool.utils.ObjectConverter;

import java.util.List;

/**
 * @author yalin.zhao
 * @date 2021/4/21 9:41
 */
@SuppressWarnings("unchecked")
@Service
public class UnitAreaServiceImpl implements UnitAreaService {

    @Autowired
    private UnitAreaRepository unitAreaRepository;

    @Override
    public Pager<UnitArea> getUnitAreas(com.pcitc.fms.service.model.UnitArea unitArea, Pageable pageable)
            throws Exception {
        Pager<UnitArea> pageData = new Pager<>();
        Page<com.pcitc.fms.dal.pojo.UnitArea> page = unitAreaRepository.findUnitAreas(unitArea, pageable);
        List<UnitArea> entityList = ObjectConverter.listConverter(page.getContent(), UnitArea.class);
        pageData.setContent(entityList);
        pageData.setFirst(page.isFirst());
        pageData.setLast(page.isLast());
        pageData.setNumber(page.getNumber());
        pageData.setNumberOfElements(page.getNumberOfElements());
        pageData.setSize(page.getSize());
        pageData.setSort(page.getSort());
        pageData.setTotalElements(page.getTotalElements());
        pageData.setTotalPages(page.getTotalPages());
        return pageData;
    }
}
