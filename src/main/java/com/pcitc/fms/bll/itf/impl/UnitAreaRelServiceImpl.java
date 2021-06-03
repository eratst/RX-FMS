package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.itf.UnitAreaRelService;
import com.pcitc.fms.dal.dao.UnitAreaRelDao;
import com.pcitc.fms.dal.pojo.UnitAreaRel;
import com.pcitc.fms.service.model.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pcitc.imp.common.ettool.utils.ObjectConverter;

import java.util.List;

@SuppressWarnings("unchecked")
@Service
public class UnitAreaRelServiceImpl implements UnitAreaRelService {

    @Autowired
    private UnitAreaRelDao unitAreRelRepository;

    @Override
    public Pager<UnitAreaRel> getUnitAreaRels(com.pcitc.fms.service.model.UnitAreaRel unitAreaRel, Pageable pageable) throws Exception {
        Pager<UnitAreaRel> pageData = new Pager<>();
        Page<com.pcitc.fms.dal.pojo.UnitAreaRel> page = unitAreRelRepository.findUnitAreaRels(unitAreaRel, pageable);
        List<UnitAreaRel> entityList = ObjectConverter.listConverter(page.getContent(), UnitAreaRel.class);
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
