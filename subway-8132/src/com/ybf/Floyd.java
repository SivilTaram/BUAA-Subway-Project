package com.ybf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.ybf.data.Line;
import com.ybf.data.Station;

public class Floyd {
	private static final int INF = Integer.MAX_VALUE;
	
	public List<String> planeShortestPath(List<Line> data, 
			String start, String end){
		HashMap<String,Integer> edge = generateEdge(data);
		int[][] matrix = generateMatrix(data, edge);
		
		int[][] path = floyd(matrix, edge);
		
		//生成路线
		Integer startIndex = edge.get(start);
		Integer endIndex = edge.get(end);
		
		int[] resultIndex = generateRoute(path, startIndex, endIndex);
		ArrayList<String> stationNameList = new ArrayList<String>();
		for (int index : resultIndex) {
			Set<String> keySet = edge.keySet();
			for (String stationName : keySet) {
				if(edge.get(stationName) == index){
					stationNameList.add(stationName);					
				}
			}
		}
		return stationNameList;
	}
	
	private int[][] floyd(int[][] matrix, HashMap<String, Integer> edge){
		int[][] path = new int[edge.size()][edge.size()];
		int[][] dist = new int[edge.size()][edge.size()];
		
		//floyd算法
	    for (int i = 0; i < edge.size(); i++) {
	        for (int j = 0; j < edge.size(); j++) {
	            dist[i][j] = matrix[i][j];    // "顶点i"到"顶点j"的路径长度为"i到j的权值"。
	            path[i][j] = j;                // "顶点i"到"顶点j"的最短路径是经过顶点j。
	        }
	    }
		
		for (int k = 0; k < edge.size(); k++) {
	        for (int i = 0; i < edge.size(); i++) {
	            for (int j = 0; j < edge.size(); j++) {

	                // 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
	            	int tmp = (dist[i][k]==INF || dist[k][j]==INF) ? INF : (dist[i][k] + dist[k][j]);
	            	if (dist[i][j] > tmp) {
	                    // "i到j最短路径"对应的值设，为更小的一个(即经过k)
	                    dist[i][j] = tmp;
	                    // "i到j最短路径"对应的路径，经过k
	                    path[i][j] = path[i][k];
	                }
	            }
	        }
	    }
		return path;
	}
	
	private HashMap<String, Integer> generateEdge(List<Line> lineList){
		HashMap<String,Integer> hashMap = new HashMap<String, Integer>();
		for (Line line : lineList) {
			ArrayList<Station> stationList = line.getStationList();
			for (Station station : stationList) {
				if(!hashMap.keySet().contains(station.getName())){
					hashMap.put(station.getName(), hashMap.size());
				}
			}
		}
		return hashMap;
	}
	
	private int[][] generateMatrix(List<Line> lineList, HashMap<String, Integer> edge){
		int[][] matrix = new int[edge.size()][edge.size()];
		
		for(int i = 0 ; i < edge.size(); i++){
			for(int j = 0; j < edge.size(); j++){
				if(i == j){
					matrix[i][j] = 0;
				}else{
					matrix[i][j] = INF;
				}
			}
		}
		
		Station previous = null, current = null;
		for (Line line : lineList) {
			ArrayList<Station> stationList = line.getStationList();
			previous = current = null;
			for (Station station : stationList) {
				
				if(previous == null){
					previous = station;
					continue;
				}else{
					current = station;
				}
				
				int i = edge.get(current.getName());
				int j = edge.get(previous.getName());
				matrix[i][j] = 1;
				matrix[j][i] = 1;
				
				previous = current;
			}
			
		}
		
		return matrix;
	}
	
	private int[] generateRoute(int[][] path, int startIndex, int endIndex){
		
		if(path[startIndex][endIndex] == endIndex){
			return new int[]{startIndex, endIndex};
		}else{
			int temp = path[startIndex][endIndex];
			int[] before = generateRoute(path, startIndex, temp);
			int[] after = generateRoute(path, temp, endIndex);
			
			int[] result = Arrays.copyOf(before, 
					after.length + before.length - 1);
			System.arraycopy(after, 0, result, 
					before.length - 1, after.length);
			return result;
		}
		
	}
}
