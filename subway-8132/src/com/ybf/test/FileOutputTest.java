package com.ybf.test;

import java.util.List;

import org.junit.Test;

import com.ybf.InputVariables;
import com.ybf.command.Command;
import com.ybf.data.DataReader;
import com.ybf.data.Line;

public class FileOutputTest {
	
	@Test
	public void testNormalFileOutput(){
		String[] args = {"-map",  "subway.txt", "-o", 
				"station.txt", "-a", "1∫≈œﬂ"};
		InputVariables inputVariables = new InputVariables(args);
		
		String dataPath = inputVariables.getInputDataPath();
		DataReader dataLoader = new DataReader(dataPath);
		List<Line> lineList = dataLoader.readData();
		
		Command command = inputVariables.getCommand();
		command.execute(lineList, inputVariables.getOutputFilePath());
		
	}
	
	@Test
	public void testNoExistFileOutput(){
		String[] args = {"-map",  "subway.txt", "-o", 
				"station_2.txt", "-a", "1∫≈œﬂ"};
		InputVariables inputVariables = new InputVariables(args);
		
		String dataPath = inputVariables.getInputDataPath();
		DataReader dataLoader = new DataReader(dataPath);
		List<Line> lineList = dataLoader.readData();
		
		Command command = inputVariables.getCommand();
		command.execute(lineList, inputVariables.getOutputFilePath());
	}
}
