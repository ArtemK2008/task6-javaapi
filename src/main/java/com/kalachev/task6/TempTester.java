package com.kalachev.task6;

import java.io.IOException;

public class TempTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ReportBuilder rb = new ReportBuilder();
		String reportRace = rb.prepareReport();
		System.out.println(reportRace);
	}

}
