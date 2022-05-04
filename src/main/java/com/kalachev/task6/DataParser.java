package com.kalachev.task6;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class DataParser {
	final String DATE_TIME_PATTERN = "yyyy-MM-dd_HH:mm:ss.SSS";
	final String TIME_ZONE = "Europe/Monaco";
	static final String UNDERSCORE = "_";
	

	public List<ReportRecord> initializeNewRaportRecords() throws IOException {
		List<ReportRecord> racers = new ArrayList<>();
		List<String> abbreviations = retrieveAbbreviation();
		Map<String, String> persons = retrievePersonsByAbbreviation();
		Map<String, String> teams = retrieveTeamByAbbreviation();
		Map<String, Instant> startTimes = retrieveStartTimeByAbbreviation();
		Map<String, Instant> endTimes = retrieveEndTimeByAbbreviation();
		for (String s : abbreviations) {
			ReportRecord tempRecord = new ReportRecord(s, persons.get(s), teams.get(s));
			tempRecord.setStartTime(startTimes.get(s));
			tempRecord.setEndTime(endTimes.get(s));
			racers.add(tempRecord);
		}
		return racers;
	}

	private List<String> retrieveAbbreviation() throws IOException {
		DataReader reader = new DataReader();
		List<String> abbreviations = reader.readFile("abbreviations.txt");
		abbreviations = abbreviations.stream().map(line -> line.substring(0, 3)).collect(Collectors.toList());
		return abbreviations;
	}

	private Map<String, Instant> retrieveEndTimeByAbbreviation() throws IOException {
		DataReader reader = new DataReader();
		List<String> racersLapEndTimes = reader.readFile("end.log");
		Map<String, String> endTimes = racersLapEndTimes.stream()
				.collect(Collectors.toMap(p -> p.substring(0, 3), p -> p.substring(3)));
		Map<String, Instant> endTimesConverted = new HashMap<>();
		for (Map.Entry<String,String> entry : endTimes.entrySet()) {
			String temp = entry.getValue();
			Instant tempInstant = convertTimeFormat(temp);
			endTimesConverted.put(entry.getKey(), tempInstant);
		}
		return endTimesConverted;
	}

	private Map<String, Instant> retrieveStartTimeByAbbreviation() throws IOException {
		DataReader reader = new DataReader();
		List<String> racersLapStartTimes = reader.readFile("start.log");
		Map<String, String> startTimes = racersLapStartTimes.stream()
				.collect(Collectors.toMap(p -> p.substring(0, 3), p -> p.substring(3)));
		Map<String, Instant> startTimesConverted = new HashMap<>();
		for (Map.Entry<String,String> entry : startTimes.entrySet()) {
			String temp = entry.getValue();
			Instant tempInstant = convertTimeFormat(temp);
			startTimesConverted.put(entry.getKey(), tempInstant);
		}
		return startTimesConverted;
	}

	private Map<String, String> retrievePersonsByAbbreviation() throws IOException {
		DataReader reader = new DataReader();
		List<String> persons = reader.readFile("abbreviations.txt");
		return persons.stream().collect(Collectors.toMap(p -> p.substring(0, 3), this::retrieveBetweenDashsElement));
	}

	private Map<String, String> retrieveTeamByAbbreviation() throws IOException {
		DataReader reader = new DataReader();
		List<String> teams = reader.readFile("abbreviations.txt");
		return teams.stream().collect(Collectors.toMap(p -> p.substring(0, 3), this::retrieveElementAfterLastDash));
	}

	private String retrieveBetweenDashsElement(String line) {
		int indexOfFirstDash = line.indexOf(UNDERSCORE);
		int indexOfSecondDash = line.indexOf(UNDERSCORE, indexOfFirstDash + 1);
		return line.substring(indexOfFirstDash + 1, indexOfSecondDash);
	}

	private String retrieveElementAfterLastDash(String line) {
		int indexOfLastDash = line.lastIndexOf(UNDERSCORE);
		return line.substring(indexOfLastDash + 1);
	}

	private Instant convertTimeFormat(String time) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN, Locale.US);
		LocalDateTime ldt = LocalDateTime.parse(time, f);
		ZonedDateTime zdt = ldt.atZone(ZoneId.of(TIME_ZONE));
		return zdt.toInstant();
	}
}
