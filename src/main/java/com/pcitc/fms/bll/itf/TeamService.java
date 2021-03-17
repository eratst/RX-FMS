package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Team;
import com.pcitc.fms.service.model.Pager;

 /**
 * Title: TeamService
* Description: TODO task mark zhenqiang.zhao
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
public interface TeamService {

	 public Pager<Team> getTeams(com.pcitc.fms.service.model.Team Team, Pageable pageable)throws BusinessException;

	 public Team getTeamByCode(String teamCode)throws BusinessException;

}
