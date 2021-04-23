package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.itf.EnNodeTypeService;
import com.pcitc.fms.dal.dao.EnNodeTypeDao;
import com.pcitc.fms.dal.pojo.EnNodeType;
import com.pcitc.fms.service.model.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pcitc.imp.common.ettool.utils.ObjectConverter;

import java.util.List;

@SuppressWarnings("unchecked")
@Service
public class EnNodeTypeServiceImpl implements EnNodeTypeService {

    @Autowired
    private EnNodeTypeDao enNodeTypeRepository;

    public Pager<EnNodeType> getEnNodeTypes(com.pcitc.fms.service.model.EnNodeType enNodeType, Pageable pageable)
            throws Exception {
        Pager<EnNodeType> pageData = new Pager<>();
        Page<EnNodeType> page = enNodeTypeRepository.findEnNodeTypes(enNodeType, pageable);
        List<EnNodeType> entityList = ObjectConverter.listConverter(page.getContent(), EnNodeType.class);
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
