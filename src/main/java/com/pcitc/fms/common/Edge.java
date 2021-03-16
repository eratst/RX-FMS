package com.pcitc.fms.common;

public class Edge {
	//对应的点下表
	public String vertexId;
	//边的权重
	public int weight;
	//下一个边节点
	public Edge next;
	
	public String getVertexId() {
		return vertexId;
	}
	public void setVertexId(String vertexId) {
		this.vertexId = vertexId;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Edge getNext() {
		return next;
	}
	public void setNext(Edge next) {
		this.next = next;
	}

}
