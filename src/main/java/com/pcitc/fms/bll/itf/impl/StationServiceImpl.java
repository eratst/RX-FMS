package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.itf.StationService;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.StationRepository;
import com.pcitc.fms.dal.pojo.Station;
import com.pcitc.fms.service.model.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pcitc.imp.common.ettool.utils.ObjectConverter;

import java.util.List;

@SuppressWarnings({"unchecked", "unused"})
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private DbPrimaryIdDao dbPrimaryIdDao;

    @Override
    public Pager<Station> findAllStation(com.pcitc.fms.service.model.Station station, Pageable pageable)
            throws Exception {
        Pager<Station> pageData = new Pager<>();
        Page<com.pcitc.fms.dal.pojo.Station> page = stationRepository.findStations(station, pageable);
        List<Station> entityList = ObjectConverter.listConverter(page.getContent(), Station.class);
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
