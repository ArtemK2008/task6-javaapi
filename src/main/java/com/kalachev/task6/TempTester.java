package com.kalachev.task6;

import java.io.IOException;
import java.util.List;

public class TempTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * ReportBuilder rb = new ReportBuilder(); String reportRace = rb.buildReport();
		 * System.out.println(reportRace);
		 */
		ReportBuilder builder = new ReportBuilder();
		String actual = builder.buildReport();
		System.out.println(actual);
	}

}
