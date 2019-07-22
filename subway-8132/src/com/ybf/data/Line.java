package com.ybf.data;
import java.util.ArrayList;

public class Line {
	private String name;
	private ArrayList<Station> stationList;
	
	public Line(String name, ArrayList<Station> stationList) {
		this.name = name;
		this.stationList = stationList;
	}
	
	public Line(String name) {
		this();
		this.name = name;
	}
	
	public Line() {
		stationList = new ArrayList<Station>();
	}
	
	
	
	public void appendStation(Station station){
		stationList.add(station);
	}
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Station> getStationList() {
		return stationList;
	}
	public void setStationList(ArrayList<Station> stationList) {
		this.stationList = stationList;
	}

	@Override
	public String toString() {
		return "Line [name=" + name + ", stationList=" + stationList + "]";
	}
	
}
