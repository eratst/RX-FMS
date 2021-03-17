package com.pcitc.fms.dal.pojo;


 /**
 * Title: INodeAndArea
* Description: 用于返回公共数据的pojo实体
 * @author 赵振强
 * @date 2017年8月6日
 * @version 1.0
 */
public interface INodeAndArea {
	public Integer getId();
	
	public void setId(Integer id);
	
	public String getCode();
	
	public void setCode(String nodeCode);
	
	public String getName() ;
	
	public void setName(String nodeName) ;
	
	public String getType() ;
	
	public void setType(String nodeType);
	
	public Integer getParentId();
	
	public void setParentId(Integer id);
	
	public String getParentCode();
	
	public void setParentCode(String code);
	
	public String getParentType() ;
	
	public void setParentType(String nodeType);
}

