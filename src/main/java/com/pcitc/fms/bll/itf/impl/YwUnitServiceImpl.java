package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.itf.YwUnitService;
import com.pcitc.fms.dal.dao.YwUnitRepository;
import com.pcitc.fms.dal.pojo.YwUnit;
import com.pcitc.fms.service.model.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pcitc.imp.common.ettool.utils.ObjectConverter;

import java.util.List;

@SuppressWarnings("unchecked")
@Service
public class YwUnitServiceImpl implements YwUnitService {

    @Autowired
    private YwUnitRepository ywUnitRepository;

    @Override
    public Pager<YwUnit> getYwUnits(com.pcitc.fms.service.model.YwUnit ywUnit, Pageable pageable) throws Exception {
        Pager<YwUnit> pageData = new Pager<>();
        Page<YwUnit> page = ywUnitRepository.findYwUnits(ywUnit, pageable);
        List<YwUnit> entityList = ObjectConverter.listConverter(page.getContent(), YwUnit.class);
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
