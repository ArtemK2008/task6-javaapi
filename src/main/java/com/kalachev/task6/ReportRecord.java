package com.kalachev.task6;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class ReportRecord implements Comparable<ReportRecord> {
	String abbreviations;
	Instant endTime;
	String name;
	Instant startTime;
	String team;

	public Instant getEndTime() {
		return endTime;
	}

	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}

	public Instant getStartTime() {
		return startTime;
	}

	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}

	public String getAbbreviations() {
		return abbreviations;
	}

	public String getName() {
		return name;
	}

	public String getTeam() {
		return team;
	}

	public ReportRecord(String abbreviations, String name, String team) {
		super();
		this.abbreviations = abbreviations;
		this.name = name;
		this.team = team;
	}

	@Override
	public int hashCode() {
		return Objects.hash(abbreviations);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportRecord other = (ReportRecord) obj;
		return Objects.equals(abbreviations, other.abbreviations);
	}

	private long caclulateLapTime(Instant start, Instant end) {
		return start.until(end, ChronoUnit.MICROS);
	}

	@Override
	public int compareTo(ReportRecord otherRacer) {
		int otherRacerTime = (int) otherRacer.caclulateLapTime(otherRacer.getStartTime(), otherRacer.getEndTime());
		int thisRacerTime = (int) this.caclulateLapTime(this.getStartTime(), this.getEndTime());
		return thisRacerTime- otherRacerTime;
	}

}
