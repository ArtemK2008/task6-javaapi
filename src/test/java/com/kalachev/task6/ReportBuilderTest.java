package com.kalachev.task6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReportBuilderTest {

	@Test
	void buildReport_shouldOutlineEverythingCorrectly_WhenAllFilesAreValid() {
		
		ReportBuilder builder = new ReportBuilder();
		String actual = builder.buildReport();
	}

}
