package com.ybf.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.ybf.InputVariables;
import com.ybf.data.DataReader;
import com.ybf.data.Line;

public class FileInputTest {
	
	@Test
	public void testNormalFileInput(){
		String[] args = {"-map",  "subway.txt", "-o", 
				"station.txt", "-a", "1∫≈œﬂ"};
		InputVariables inputVariables = new InputVariables(args);
		
		String dataPath = inputVariables.getInputDataPath();
		DataReader dataLoader = new DataReader(dataPath);
		List<Line> lineList = dataLoader.readData();
		
		System.out.println(lineList);
	}
	
	@Test
	public void testErrorFileInput(){
		String[] args = {"-map", "subway_error.txt", "-o", 
				"station.txt", "-a", "1∫≈œﬂ"};
		InputVariables inputVariables = new InputVariables(args);
		
		String dataPath = inputVariables.getInputDataPath();
		DataReader dataLoader = new DataReader(dataPath);
		List<Line> lineList = dataLoader.readData();
		
		assertEquals(lineList.size(), 0);
	}
	
	@Test
	public void testNoExistFileInput(){
		String[] args = {"-map", "123.txt", "-o", 
				"station.txt", "-a", "1∫≈œﬂ"};
		InputVariables inputVariables = new InputVariables(args);
		
		String dataPath = inputVariables.getInputDataPath();
		DataReader dataLoader = new DataReader(dataPath);
		List<Line> lineList = dataLoader.readData();
		
		assertEquals(lineList.size(), 0);
	}
}
