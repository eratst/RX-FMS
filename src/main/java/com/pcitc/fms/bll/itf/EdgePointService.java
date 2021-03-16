package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.EdgePoint;
import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.service.model.Pager;

import com.pcitc.fms.exception.BusinessException;


/**
 * The interface Edge point service.
 */
public interface EdgePointService {

  /**
   * Gets edge point by model.
   *
   * @param edgePointModel the edge point model
   * @param pageable the pageable
   * @param areaCode the area code
   * @return the edge point by model
   * @throws BusinessException the business exception
   */
  Pager<EdgePoint> getEdgePointByModel(com.pcitc.fms.service.model.EdgePoint edgePointModel,
	  Pageable pageable) throws BusinessException;


  /**
   * Gets edge pointes.
   *
   * @param parentCode the parent code
   * @param parentType the parent type
   * @return the edge pointes
   * @throws BusinessException the business exception
   */
  public List<EdgePoint> getEdgePointes(String parentCode, String parentType) throws BusinessException;

  /**
   * Gets edge pointes by id links.
   *
   * @param parentType the parent type
   * @param parentCode the parent code
   * @param edgePointCode the edge point code
   * @return the edge pointes by id links
   * @throws BusinessException the business exception
   */
  public List<EdgePoint> getEdgePointesByIdLinks(String parentType, String parentCode,
      String edgePointCode) throws BusinessException;

  /**
   * Gets edge pointes by links.
   *
   * @param parentType the parent type
   * @param parentCode the parent code
   * @return the edge pointes by links
   * @throws BusinessException the business exception
   */
  public List<EdgePoint> getEdgePointesByLinks(String parentType, String parentCode)
      throws BusinessException;

  /**
   * Gets edge pointes.
   *
   * @param parentType the parent type
   * @return the edge pointes
   * @throws BusinessException the business exception
   */
  public List<EdgePoint> getEdgePointes(String parentType) throws BusinessException;

  List<EdgePoint> getEdgePointsByIdList(String parentCode, String parentType, List<Integer> idList)
		throws BusinessException;
}
