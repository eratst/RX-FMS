package com.pcitc.fms.dal.dao;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Station;
import com.pcitc.imp.common.exception.BusiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.SortParam;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

@Service
public class StationRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public Page<Station> findStations(com.pcitc.fms.service.model.Station station, Pageable pageable) {
        String stations = "select count(1) "
                + " from Station station,Area area,AreaType areaType,Org org "
                + "where org.orgId = area.orgId and area.areaId = station.areaId and area.areaTypeId = areaType.areaTypeId ";
        StringBuilder dataSql = new StringBuilder();
        dataSql.append(AreaNodeBasicSql.stations);

        StringBuilder countSql = new StringBuilder();
        countSql.append(stations);
        Map<String, Object> parameterMap = new HashMap<>();

        if (null != station.getOrgAlias() && !StringUtils.isEmpty(station.getOrgAlias())) {
            countSql.append(" and org.orgAlias like :orgAlias");
            dataSql.append(" and org.orgAlias like :orgAlias");
            parameterMap.put("orgAlias", "%" + station.getOrgAlias() + "%");
        }

        if (null != station.getAreaCode() && !StringUtils.isEmpty(station.getAreaCode())) {
            countSql.append(" and station.areaCode like :areaCode");
            dataSql.append(" and station.areaCode like :areaCode");
            parameterMap.put("areaCode", "%" + station.getAreaCode() + "%");
        }
        if (null != station.getAreaName() && !StringUtils.isEmpty(station.getAreaName())) {
            countSql.append(" and area.areaName like :areaName");
            dataSql.append(" and area.areaName like :areaName");
            parameterMap.put("areaName", "%" + station.getAreaName() + "%");
        }
        if (null != station.getAreaAlias() && !StringUtils.isEmpty(station.getAreaAlias())) {
            countSql.append(" and area.areaAlias like :areaAlias");
            dataSql.append(" and area.areaAlias like :areaAlias");
            parameterMap.put("areaAlias", "%" + station.getAreaAlias() + "%");
        }
        if (null != station.getInUse() && !StringUtils.isEmpty(String.valueOf(station.getInUse()))) {
            countSql.append(" and area.inUse = :inUse");
            dataSql.append(" and area.inUse = :inUse");
            parameterMap.put("inUse", station.getInUse());
        }
        if (null != station.getCodeList() && !station.getCodeList().isEmpty()) {
            countSql.append(" and station.areaCode in :codeList");
            dataSql.append(" and station.areaCode in :codeList");
            parameterMap.put("codeList", station.getCodeList());
        }

        if (null != station.getOrgCodeList() && !station.getOrgCodeList().isEmpty()) {
            countSql.append(" and org.orgCode in :orgCodes");
            dataSql.append(" and org.orgCode in :orgCodes");
            parameterMap.put("orgCodes", station.getOrgCodeList());
        }

        dataSql.append(" order by area.sortNum asc");
        countSql.append(" order by area.sortNum asc");

        //----------处理租户过滤
        if(StringUtils.isNotEmpty(station.getRentCode())){
            RentCondition<Station> rentCondition = new RentCondition<Station>();
            String field=" org.orgCode ";
            String rentOrgCodes = null;
            try {
                rentOrgCodes = rentCondition.getRentOrgCodeCondition(station.getRentCode(),station.getBizCode(),field);
            } catch (BusiException e) {
                e.printStackTrace();
            }
            if(StringUtils.isNotEmpty(rentOrgCodes)){
                dataSql.append( " and "+rentOrgCodes);
                countSql.append( " and "+rentOrgCodes);
            }else{
                return new MyPageImpl(new ArrayList(), null, 0L);
            }
        }
        //----------处理租户过滤

        if (StringUtils.isNotEmpty(station.getOrderby())) {
            String value = null;
            try {
                value = SortParam.getSortParam(Station.class, station.getOrderby());
            } catch (BusiException e) {
                e.printStackTrace();
            }
            dataSql.append(value);
        }

        Query dataQuery = entityManager.createQuery(dataSql.toString());
        Query countQuery = entityManager.createQuery(countSql.toString());
        for (String parameter : parameterMap.keySet()) {
            countQuery.setParameter(parameter, parameterMap.get(parameter));
            dataQuery.setParameter(parameter, parameterMap.get(parameter));
        }

        // long count = dataQuery.getResultList().size();
        long count = (long) countQuery.getResultList().get(0);
        if (null != pageable) {
            int skip = station.getSkip();
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            return new PageImpl<Station>(dataQuery.getResultList(), pageable, count);
        } else {
            return new PageImpl<Station>(dataQuery.getResultList(), null, count);
        }
    }
}
