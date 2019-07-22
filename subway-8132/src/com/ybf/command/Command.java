package com.ybf.command;

import java.util.List;

import com.ybf.data.Line;

public abstract class Command{
	public static final String name = "";
	
	
	public abstract void execute(List<Line> lineList, String outputPath);
}
