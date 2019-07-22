package com.ybf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.ybf.InputVariables;
import com.ybf.command.PlaneCommand;
import com.ybf.command.QueryCommand;

public class InputVariableTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testNoramlQueryInput(){
		String[] args = {"-map",  "subway.txt", "-o", 
				"station.txt", "-a", "1号线"};
		InputVariables inputVariables = new InputVariables(args);
		assertEquals("subway.txt", inputVariables.getInputDataPath());
		assertEquals("station.txt", inputVariables.getOutputFilePath());
		assertEquals("1号线", ((QueryCommand)inputVariables.getCommand()).getLineName());
	}
	
	@Test
	public void testNoramlPlaneInput(){
		String[] args = {"-map",  "subway.txt", "-o", 
				"station.txt", "-b", "海天寺", "天津站"};
		InputVariables inputVariables = new InputVariables(args);
		assertEquals("subway.txt", inputVariables.getInputDataPath());
		assertEquals("station.txt", inputVariables.getOutputFilePath());
		assertEquals("海天寺", ((PlaneCommand)inputVariables.getCommand())
				.getStartStationName());
		assertEquals("天津站", ((PlaneCommand)inputVariables.getCommand())
				.getEndStationName());
	}
	
	//输入错误参数，返回默认值
	@Test
	public void testDefaultInput(){
		String[] args = {"-map", "-o", 
				"station.txt", "-b", "海天寺", "天津站"};
		InputVariables inputVariables = new InputVariables(args);
		assertEquals("subway.txt", inputVariables.getInputDataPath());
		assertEquals("station.txt", inputVariables.getOutputFilePath());
		assertEquals("1号线", ((QueryCommand)inputVariables.getCommand()).getLineName());
	}

}
