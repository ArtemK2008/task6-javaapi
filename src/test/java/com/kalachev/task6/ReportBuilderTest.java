package com.kalachev.task6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class ReportBuilderTest {
	static final String NEWLINE = System.lineSeparator();
	
	@Test
	void buildReport_shouldOutlineEverythingCorrectly_WhenAllFilesAreValid() {
		
		ReportBuilder builder = new ReportBuilder();
		String actual = builder.buildReport();
		
		DataReader reader = new DataReader();
		List<String> expectedData = reader.readFile("expectedResult.txt");
		String expected = expectedData.stream().map(Object::toString).collect(Collectors.joining(NEWLINE));
		assertEquals(expected, actual);
		
	}

}
