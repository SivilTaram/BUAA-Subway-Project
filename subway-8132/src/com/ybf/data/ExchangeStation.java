package com.ybf.data;
import java.util.ArrayList;
import java.util.List;

public class ExchangeStation extends Station{
	private List<Integer> exchangeLineList;
	
	public ExchangeStation(String name) {
		super(name);
		this.exchangeLineList = new ArrayList<Integer>();
	}
	
	public ExchangeStation(String name, List<Integer> exchangeLine) {
		super(name);
		this.exchangeLineList = exchangeLine;
	}
	
	public void appendExchangeLine(int line){
		exchangeLineList.add(line);
	}

	public List<Integer> getExchangeLineList() {
		return exchangeLineList;
	}

	public void setExchangeLineList(List<Integer> exchangeLineList) {
		this.exchangeLineList = exchangeLineList;
	}
	
}
