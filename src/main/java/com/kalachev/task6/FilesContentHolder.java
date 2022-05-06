package com.kalachev.task6;

import java.util.List;

public class FilesContentHolder {
	DataReader reader;
	List<String> abbreviateion;
	List<String> startTimesFile;
	List<String> endTimesFile;

	public FilesContentHolder() {
		super();
		this.reader = new DataReader();
		this.abbreviateion = reader.readFile("abbreviations.txt");
		this.startTimesFile = reader.readFile("start.log");
		this.endTimesFile = reader.readFile("end.log");
	}
	
	public List<String> getAbbreviateion() {
		return abbreviateion;
	}

	public List<String> getStartTimesFile() {
		return startTimesFile;
	}

	public List<String> getEndTimesFile() {
		return endTimesFile;
	}


}
