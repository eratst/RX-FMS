package com.pcitc.fms.bll.itf;

import com.pcitc.fms.bll.entity.CacheInfoParam;

public interface CacheRentService {

	public void getCacheInfo();

	public void getCacheInfoByRentCode(CacheInfoParam cacheInfoParam);

//	public void getCacheInfoForBiz();

}
