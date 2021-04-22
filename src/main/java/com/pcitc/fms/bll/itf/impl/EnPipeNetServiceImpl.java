package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.itf.EnPipeNetService;
import com.pcitc.fms.dal.dao.EnPipeNetRepository;
import com.pcitc.fms.dal.pojo.EnPipeNet;
import com.pcitc.fms.service.model.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pcitc.imp.common.ettool.utils.ObjectConverter;

import java.util.List;

@SuppressWarnings("unchecked")
@Service
public class EnPipeNetServiceImpl implements EnPipeNetService {

    @Autowired
    private EnPipeNetRepository enPipeNetRepository;

    @Override
    public Pager<EnPipeNet> getEnPipeNets(com.pcitc.fms.service.model.EnPipeNet enPipeNet, Pageable pageable)
            throws Exception {
        Pager<EnPipeNet> pageData = new Pager<>();
        Page<EnPipeNet> page = enPipeNetRepository.findEnPipeNets(enPipeNet, pageable);
        List<EnPipeNet> entityList = ObjectConverter.listConverter(page.getContent(), EnPipeNet.class);
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
