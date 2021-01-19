package com.aoc.days.datareader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import com.aoc.data.model.Data;
import com.aoc.data.structure.DataFactory;
import com.aoc.data.structure.DataLinkedList;
import com.aoc.days.visitor.IDataVisitor;

public abstract class AbstractReader<T, D extends Data<T>> {
	private final DataLinkedList<T, D> dataList;
	private String line = null;
	public AbstractReader(DataLinkedList<T,D> inData) {
		dataList = inData;
	}
	
	protected BufferedReader createReader(String fileName) {
		try {
			URL url = this.getClass().getClassLoader().getResource("day1_data/"+fileName);
			File dataFile = new File(url.toURI());
			if (dataFile.exists()) {
				FileInputStream fis = new FileInputStream(dataFile);
				BufferedReader sbr = new BufferedReader(new InputStreamReader(fis));
				return sbr;
			}
		}catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void read(String fileName, boolean multi_lines) {
		BufferedReader reader = this.createReader(fileName);
		int lineNo = 0;
		try {
			StringBuffer lineBuffer = multi_lines ? readMultiLines(reader) : readLine(reader) ;
			
			while (line != null) {
				dataList.addData((D)this.getData(lineNo, lineBuffer.toString()), getVisitor());
				lineBuffer = multi_lines ?  readMultiLines(reader) : readLine(reader);
				lineNo+=1;
			}
			if(line == null && lineBuffer.length()>0) {
				dataList.addData((D)this.getData(lineNo, lineBuffer.toString()), getVisitor());
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected StringBuffer readLine(BufferedReader reader) {
		try {
			StringBuffer buffer = new StringBuffer();
			line =  reader.readLine();
			if(line != null) {
				buffer.append(line);
			}
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected StringBuffer readMultiLines(BufferedReader reader) {
		StringBuffer buffer = new StringBuffer();
		
		try {
			line = reader.readLine();
			while(line != null && line.length() > 0) {
				
				if(buffer.length()>0)
					buffer.append(" ");
				buffer.append(line);
				line = reader.readLine();
			}
			if(line != null && line.trim().length() == 0) {
				return buffer;
			}
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private <T,D extends Data<T>> Data<T> getData(int lineNo, String dataStr) {
		Data<T> data = DataFactory.getInstance().getData(lineNo, dataStr, getType());
		return data;
	}
	
	public abstract String getType();
	
	protected abstract IDataVisitor<T,D> getVisitor();
}
