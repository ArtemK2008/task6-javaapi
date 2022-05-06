package com.kalachev.task6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DataReaderTest {

	private List<String> expected = new ArrayList<String>() {
		{
			add("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER");
			add("SVF_Sebastian Vettel_FERRARI");
			add("LHM_Lewis Hamilton_MERCEDES");
		}
	};

	@Test
	void readFile_shouldConvertFileContentToStringList_whenFileExistsInResourses() {
		// Given
		DataReader reader = new DataReader();

		List<String> actual = reader.readFile("abbreviationsShort.txt");

		assertEquals(expected, actual);
	}

	@Test
	void readFile_shouldThrowIlligalArgument_whenFileDoesNotExist() {

		DataReader reader = new DataReader();

		assertThrows(IllegalArgumentException.class, () -> reader.readFile("noSuchFile"));
	}

	@Test
	void readFile_shouldThrowIlligalArgument_whenArgumentIsNull() {

		DataReader reader = new DataReader();

		assertThrows(IllegalArgumentException.class, () -> reader.readFile(null));
	}

	@Test
	void readFile_shouldThrowIlligalArgument_whenArgumentIsEmpty() {

		DataReader reader = new DataReader();

		assertThrows(IllegalArgumentException.class, () -> reader.readFile(""));
	}

}
