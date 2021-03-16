package com.pcitc.fms.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheRentAndBizInfo {
	
	public static List<RentAndBiz> rabList=new ArrayList<RentAndBiz>();
	
	public static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public static Lock read=rwl.readLock();
	
	public static Lock write=rwl.writeLock();

	public static List<String> getStandardBiz(String rentCode){
		read.lock();
		for(int i=0;i<rabList.size();i++){
			if(rabList.get(i).getRentCode().equals(rentCode)){
				read.unlock();
				return rabList.get(i).getBizList();
			}
		}
		read.unlock();
		return null;
	}
	
	public static void setStandardBiz(RentAndBiz rab){
		
		rabList.add(rab);
	}
	
	public static void remove(){
		write.lock();
		rabList.clear();
		write.unlock();
	}
}
