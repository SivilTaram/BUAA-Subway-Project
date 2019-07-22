package com.ybf.data;

public class Station {
	String name;
	
	public Station(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Station [name=" + name + "]";
	}
	
	
}
