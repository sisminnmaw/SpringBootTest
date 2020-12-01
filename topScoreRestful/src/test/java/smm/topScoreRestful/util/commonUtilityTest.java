package smm.topScoreRestful.util;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class commonUtilityTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@SuppressWarnings("deprecation")
	@Test
	void testStringToDate() throws ParseException {

		// Test input Data (valid format)
		String testString = "2020-01-01 00:00:00";

		// Test result Data
		Date resultDate = commonUtility.stringToDate(testString);

		// Test expected Data
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2020); // Year 20200
		c1.set(Calendar.DAY_OF_YEAR, 1); // January 1st
		Date targetDate = c1.getTime();

		assertAll(
			() ->assertNotNull(resultDate),
			() ->assertEquals(targetDate.getYear(), resultDate.getYear()),
			() ->assertEquals(targetDate.getMonth(), resultDate.getMonth()),
			() ->assertEquals(targetDate.getDate(), resultDate.getDate())
		);
	}

	@Test
	void testStringToDate_throw() throws ParseException {
		// Test input Data (invalid format)
		String testString = "20200000 1000";
		assertThrows(ParseException.class, () -> {
			commonUtility.stringToDate(testString);
		});
	}

	@Test
	void testStringListToLowerCase() {

		// Test input Data
		List<String> names = Arrays.asList("Upper", "CasE", "NaMe");

		// Test result Data
		List<String> result = commonUtility.stringListToLowerCase(names);

		// Test expected Data
		String s1 = "upper";
		String s2 = "case";
		String s3 = "name";

		assertAll(
			() -> assertEquals(s1, result.get(0)), 
			() -> assertEquals(s2, result.get(1)),
			() -> assertEquals(s3, result.get(2))
		);
	}

}
