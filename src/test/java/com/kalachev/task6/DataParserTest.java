package com.kalachev.task6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class DataParserTest {
	
	static final String NEWLINE = System.lineSeparator();

	private String expected = "DRR Daniel Ricciardo RED BULL RACING TAG HEUER 2018-05-24T10:14:12.054Z 2018-05-24T10:15:24.067Z\r\n"
			                + "SVF Sebastian Vettel FERRARI 2018-05-24T10:02:58.917Z 2018-05-24T10:04:03.332Z\r\n"
			                + "LHM Lewis Hamilton MERCEDES 2018-05-24T10:18:20.125Z 2018-05-24T10:19:32.585Z";

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
		filesContentHolder.setAbbreviateion("abbreviationsShort.txt");
		filesContentHolder.setStartTimesFile("startShort.log");
		filesContentHolder.setEndTimesFile("endShort.log");
		
		List<ReportRecord> records = parser.initializeNewRaportRecords(filesContentHolder);
		String actual = records.stream().map(Object::toString).collect(Collectors.joining(NEWLINE));
		assertEquals(expected, actual);
		
	}

}
