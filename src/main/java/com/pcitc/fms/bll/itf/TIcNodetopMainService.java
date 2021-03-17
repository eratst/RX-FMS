package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.TIcNodetopMain;

public interface TIcNodetopMainService {

	public List addNodetopMains(List<com.pcitc.fms.bll.entity.TIcNodetopMain> ticNodetopMainList) throws Exception;

	public void updateNodetopMain(List<com.pcitc.fms.bll.entity.TIcNodetopMain> tIcNodetopMainEntityList, String topCode) throws Exception;

	public void deleteNodetopMainByCode(String topCode) throws Exception;

	public List<com.pcitc.fms.bll.entity.TIcNodetopMain> getNodetopMain(String topCode) throws Exception;

	public Pager<com.pcitc.fms.bll.entity.TIcNodetopMain> getNodetopMains(TIcNodetopMain nodetopMainsTableModel,
			Pageable pageable) throws Exception;
}
