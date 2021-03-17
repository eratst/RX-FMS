package com.pcitc.fms.common;

public class Vertex {
	//存放点信息
	public String data;
	//与该点邻接的第一个边节点
	public Edge firstEdge;
	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Edge getFirstEdge() {
		return firstEdge;
	}
	public void setFirstEdge(Edge firstEdge) {
		this.firstEdge = firstEdge;
	}
}
