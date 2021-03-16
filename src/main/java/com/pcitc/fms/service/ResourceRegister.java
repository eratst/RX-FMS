package com.pcitc.fms.service;

import io.vertx.ext.web.Router;

public interface ResourceRegister{
    String BASEURL = "/FactoryModelService";

    /**
     * 注册资源处理接口
     *
     * @param router
     */
    public void registeResource(Router router);

}
