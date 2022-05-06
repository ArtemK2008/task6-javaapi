package com.kalachev.task6;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

public class ReportBuilder {
	private int id = 1;

	static final String NEWLINE = System.lineSeparator();
	static final String TIME_FORMAT = "mm:ss.SSS";
	static final String REPORT_FORMAT = "%2d.%20s |%30s|%s%n";

	public String buildReport() {
		List<ReportRecord> racers = sortRacersRecords();
		StringBuilder sb = new StringBuilder();
		racers.stream().limit(15).forEach(r -> sb.append(formatSingleLine(r)));
		sb.append(unicodeCharLinePrineter());
		racers.stream().skip(15).forEach(r -> sb.append(formatSingleLine(r)));
		return sb.toString();
	}

	private List<ReportRecord> sortRacersRecords() {
		DataParser dp = new DataParser();
		List<ReportRecord> racers = dp.initializeNewRaportRecords(new FilesContentHolder());
		return racers.stream().sorted().collect(Collectors.toList());
	}

	private String caclulateLapTime(Instant start, Instant end) {
		long timeInMseconds = start.until(end, ChronoUnit.MILLIS);
		Date date = new Date(timeInMseconds);
		return new SimpleDateFormat(TIME_FORMAT).format(date);
	}

	private String formatSingleLine(ReportRecord reportRecord) {
		String name = reportRecord.getName();
		String team = reportRecord.getTeam();
		String lapTime = caclulateLapTime(reportRecord.getStartTime(), reportRecord.getEndTime());
		try (Formatter formatter = new Formatter()) {
			return formatter.format(REPORT_FORMAT, id++, name, team, lapTime).toString();
		}
	}

	private String unicodeCharLinePrineter() {
		String dashes = String.join("", Collections.nCopies(Math.max(0, 64), "-"));
		return '\u2022' + dashes + NEWLINE;
	}
}
