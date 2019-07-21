package com.ybf.command;

import java.util.ArrayList;
import java.util.List;

import com.ybf.data.DataWriter;
import com.ybf.data.Line;
import com.ybf.data.Station;

public class QueryCommand extends Command{
	public static final String name = "query";
	private String lineName;
	public QueryCommand(String lineName) {
		this.lineName = lineName;
	}
	public String getLineName() {
		return lineName;
	}
	
	@Override
	public void execute(List<Line> lineList, String outputPath) {
		Line resultLine = null;
		for (Line line : lineList) {
			if(line.getName().equals(lineName)){
				resultLine = line;
				break;
			}
		}
		
		if(resultLine != null){
			//Ð´ÎÄ¼þ
			DataWriter writer = new DataWriter(outputPath);
			
			ArrayList<String> result = new ArrayList<String>();
			ArrayList<Station> stationList = resultLine.getStationList();
			for (Station station : stationList) {
				result.add(station.getName());
			}
			
			writer.writeResultList(result);
		}
		
	}
}
