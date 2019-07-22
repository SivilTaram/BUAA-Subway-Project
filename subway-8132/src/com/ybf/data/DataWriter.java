package com.ybf.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class DataWriter {
	private String outputPath;

	public DataWriter(String outputPath) {
		super();
		this.outputPath = outputPath;
	}
	
	public void writeResultList(List<String> resultList){
		BufferedWriter bw = null;
		try {
			File outputFile = new File(outputPath);
			bw = new BufferedWriter(
					new FileWriter(outputFile));
		
			for (String result : resultList) {
				bw.write(result + "\r\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
