package com.kalachev.task6;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataReader {

	public List<String> readFile(String filename) {
		if(filename ==null) {
			throw new IllegalArgumentException("cant read null ");
		}
		
		if(filename.isEmpty()) {
			throw new IllegalArgumentException("cant read empty string "); 
		}
		
		if (getClass().getClassLoader().getResourceAsStream(filename) == null) {
			throw new IllegalArgumentException("file not found! " + filename); 
		}
		
		List<String> fileLines = new ArrayList<>();
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
			Stream<String> lines = new BufferedReader(new InputStreamReader(is)).lines();
			fileLines = lines.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileLines;
	}
}
