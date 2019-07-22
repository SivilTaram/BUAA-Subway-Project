package com.ybf.command;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.ybf.Floyd;
import com.ybf.data.DataWriter;
import com.ybf.data.Line;
import com.ybf.data.Station;

public class PlaneCommand extends Command{
	public static final String name = "plane";
	private String startStationName;
	private String endStationName;
	public PlaneCommand(String startStationName, String endStationName) {
		this.startStationName = startStationName;
		this.endStationName = endStationName;
	}
	public String getStartStationName() {
		return startStationName;
	}
	public String getEndStationName() {
		return endStationName;
	}
	
	@Override
	public void execute(List<Line> lineList, String outputPath) {
		Floyd floyd = new Floyd();
		List<String> route = floyd.planeShortestPath(lineList, startStationName, 
				endStationName);
		
		DataWriter writer = new DataWriter(outputPath);
		List<String> result = new ArrayList<String>();
		
		List<String> previous = null, after;
		ListIterator<String> listIterator = route.listIterator();
		boolean isFirst = true;
		while(listIterator.hasNext()){
			String station = listIterator.next();
			
			List<String> lineNames = findLineName(lineList, station);
			
			if(lineNames.size() == 1){
				previous = lineNames;
				if(isFirst){
					result.add(lineNames.get(0));
					isFirst = false;					
				}
			}else{
				//≈–∂œ «∑Òªª≥À
				after = findLineName(lineList, listIterator.next());
				listIterator.previous();
				
				if(after != null && after.size() == 1){
					if(!previous.get(0).equals(after.get(0))){
						// «ªª≥À’æ
						result.add(after.get(0));
					}
				}
			}
			result.add(station);
		}
		writer.writeResultList(result);
	}
	
	private List<String> findLineName(List<Line> lineList, String stationName){
		List<String> result = new ArrayList<String>();
		
		for (Line line : lineList) {
			for (Station station : line.getStationList()) {
				if(station.getName().equals(stationName)){
						result.add(line.getName());
				}
			}
		}
		
		return result;
	}
}
