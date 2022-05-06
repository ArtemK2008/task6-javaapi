package com.kalachev.task6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DataParserTest {
	

	private List<String> expected = new ArrayList<String>() {
		{
			add("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER");
			add("SVF_Sebastian Vettel_FERRARI");
			add("LHM_Lewis Hamilton_MERCEDES");
		}
	};

	@Test
	void initializeNewRaportRecords_shouldThrowException_WhenArgumentIsNull() {

		DataParser parser = new DataParser();
		FilesContentHolder holder = null;
		assertThrows(IllegalArgumentException.class, () -> parser.initializeNewRaportRecords(holder));
	}
	
	@Test
	void initializeNewRaportRecords_shouldCreateCollectionOfRecords_WhenArgumentIsRealFile() {

		DataParser parser = new DataParser();
		FilesContentHolder filesContentHolder = new FilesContentHolder();
		parser.initializeNewRaportRecords(filesContentHolder);
		
	}

}
