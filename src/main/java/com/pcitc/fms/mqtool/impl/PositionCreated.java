package com.pcitc.fms.mqtool.impl;

import com.pcitc.fms.mqtool.BaseSyncObject;

import amq.synchronize.model.SyncEvent;
import amq.synchronize.model.SyncPosition;
import amq.synchronize.service.SyncService;




public class PositionCreated extends BaseSyncObject implements SyncService {

    private SyncPosition syncPosition;
    private String currentEventKey = null;

    public SyncPosition getSyncPosition() {
        return syncPosition;
    }

    public String getCurrentEventKey() {
        return currentEventKey;
    }

    @Override
    public void handler(SyncEvent evt) {
        syncPosition = (SyncPosition) evt.getModel();
        currentEventKey = evt.getKey();
        logger.debug("同步"+evt.getType()+"操作"+evt.getOper()+":" + syncPosition.getPostionCode() + "成功");
        process();
    }

    public void process(){
        logger.debug("获得到对象事件："+currentEventKey+"各开发组需要重写此方法实现自己的逻辑");
        logger.debug("当前对象："+syncPosition.toString());
    }
}


