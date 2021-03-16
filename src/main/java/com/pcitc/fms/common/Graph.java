package com.pcitc.fms.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.pcitc.fms.bll.itf.PathFilterService;
//import com.pcitc.fms.bll.itf.impl.PathFilterServiceImpl;


//public class Graph {
//	//存放点的集合
//	public Map<String,Vertex> vertexMap=new HashMap<String,Vertex>();
//	//点个数
//	public int vertexNum;
//	//返回路径的集合
//	public List<String> list=new ArrayList<String>();
//	
//	
//	public Graph(int vertexNum){
//		this.vertexNum=vertexNum;
//	}
//	//初始化Vertext
//	public void initVertext(Object datas[]){
//		for(int i=0;i<datas.length;i++){
//			Vertex vertext=new Vertex();
//			vertext.data=datas[i].toString();
//			vertext.firstEdge=null;
//			vertexMap.put(datas[i].toString(), vertext);
//		}
//	}
//	//针对x节点添加边节点y
//	public void addEdge(String x,String y){
//		Edge edge=new Edge();
//		edge.setVertexId(y);
//		//第一个边节点
////		System.out.println(vertexList.length);
//		if(null==vertexMap.get(x).firstEdge){
//			vertexMap.get(x).firstEdge=edge;
//			edge.setNext(null);
//		}
//		//不是第一个边节点,则采用头插法
//		else{
//			edge.next=vertexMap.get(x).firstEdge;
//			vertexMap.get(x).firstEdge=edge;
//		}
//	}
//	//得到x的邻接点为y的后一个邻接点位置,为-1说明没有找到
//	public String getNextNode(String x,String y){
//		String next_node="-1";
//		Edge edge=vertexMap.get(x).firstEdge;
//		if(null!=edge&&y.equals("-1")){
//			String n=edge.vertexId;
//			//元素还不在states中
//			if(!states.get(n)){
//				return n;
//			}else {
//				return getNextNode(x,n);
//			}
//		}
//			
//		while(null!=edge){
//			//节点未访问
//			if(edge.vertexId==y){
//				if(null!=edge.next){
//			    next_node=edge.next.vertexId;
//			    //元素还不在stack中
//				if(!states.get(next_node))
//					return next_node;
//				}
//				else
//				return "-1";
//			}
//			edge=edge.next;
//		}
//		return "-1";
//	}
//	//代表某节点是否在stack中,避免产生回路
//	public Map<String,Boolean> states=new HashMap();
//	 
//	//存放放入stack中的节点
//	public Stack<String> stack=new Stack();
//	 
//	//输出2个节点之间的输出路径
//	public List<String> visit(String x,String y){
//		
//	       //初始化所有节点在stack中的情况
//	        for(Map.Entry<String, Vertex> entry : vertexMap.entrySet()){
//			states.put(entry.getKey(),false);
//	        }
//	        //stack top元素
//	        String top_node;
//	        //存放当前top元素已经访问过的邻接点,若不存在则置-1,此时代表访问该top元素的第一个邻接点
//	        String adjvex_node="-1";
//		String next_node;
//		stack.add(x);
//		//put压入栈中
//		states.put(x,true);
//		while(!stack.isEmpty()){
//			//peek取栈顶第一个元素
//			top_node=stack.peek();
//			//找到需要访问的节点
//	        if(top_node.equals(y)){
//				//打印该路径
//				String str=printPath();
//				//pop取栈顶元素并弹出
//				adjvex_node=stack.pop();
//				states.put(adjvex_node,false);
//				list.add(str);
//			}
//			else{
//				//访问top_node的第advex_node个邻接点
//	            next_node=getNextNode(top_node,adjvex_node);
//				if(!next_node.equals("-1")){
//					stack.push(next_node);
//					//置当前节点访问状态为已在stack中
//	                states.put(next_node,true);
//					//临接点重置
//	                adjvex_node="-1";
//				}
//	            //不存在临接点，将stack top元素退出 
//	            else{
//					//当前已经访问过了top_node的第adjvex_node邻接点
//	                adjvex_node=stack.pop();
//					//不在stack中
//					states.put(adjvex_node,false);
//				}
//			}
//		}
//		return list;
//	}
//	 
//	//打印stack中信息,即路径信息
//	 public String printPath(){
//		StringBuilder sb=new StringBuilder();
//		for(String i :stack){
//			sb.append(i+"->");
//		}
//		sb.delete(sb.length()-2,sb.length());
//		return sb.toString();
//	}
//
//}
