package ckrae.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CoordinatesTest {

	@ParameterizedTest
	@DisplayName("valid string values")
	@CsvSource({ "a1, 1, 1", "b2, 2, 2", "c1, 3, 1", "d2,4,2", "g8,7,8", "e2,5,2", "b8,2,8", "f1,6,1", "h4,8,4" })
	void validCharValues(String str, int x, int y) {

		Coordinates coor = new Coordinates(str);
		assertNotNull(coor);
		assertEquals(x, coor.getX());
		assertEquals(y, coor.getY());

	}

	@ParameterizedTest
	@DisplayName("invalid string values")
	@ValueSource(strings = { "a9, b11, z1, t5, asdg" })
	void invalid(String value) {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			Coordinates coor = new Coordinates(1, -1);
		});

	}

	@ParameterizedTest
	@DisplayName("modified equals test positive")
	@CsvSource({ "a1,a2,0,1", "b4,c4,1,0", "d3,a5,-3,2", "h8,a1,-7,-7" })
	void modifiedEquals(String strStart, String strTarget, int x, int y) {

		Coordinates start = new Coordinates(strStart);
		Coordinates target = new Coordinates(strTarget);

		assertTrue(start.equals(target, x, y));

	}

	@ParameterizedTest
	@DisplayName("modified equals test negative")
	@CsvSource({ "a1,a3,0,1", "b4,c2,1,0", "d3,a7,-3,2", "h8,a3,-7,-7" })
	void modifiedEqualsNegative(String strStart, String strTarget, int x, int y) {

		Coordinates start = new Coordinates(strStart);
		Coordinates target = new Coordinates(strTarget);

		assertFalse(start.equals(target, x, y));

	}

	@Test
	@DisplayName("valid integer values")
	void validValues() {

		for (int x = 1; x <= 8; x++)
			for (int y = 1; y <= 8; y++) {
				Coordinates coor = new Coordinates(x, y);
				assertNotNull(coor);
				assertEquals(x, coor.getX());
				assertEquals(y, coor.getY());
			}

	}

	@ParameterizedTest
	@DisplayName("valid char values")
	@CsvSource({ "a,1", "b,2", "c,3" })
	void validCharValues(char x, int res) {

		Coordinates coor = new Coordinates(x, 1);
		assertNotNull(coor);
		assertEquals(res, coor.getX());

	}

	@Test
	@DisplayName("negative values")
	void negativeValues() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			Coordinates coor = new Coordinates(1, -1);
		});

		exception = assertThrows(IllegalArgumentException.class, () -> {
			Coordinates coor = new Coordinates(-1, 1);
		});

	}

	@Test
	@DisplayName("too high values")
	void tooHighValues() {

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			Coordinates coor = new Coordinates(1, 9);
		});

		exception = assertThrows(IllegalArgumentException.class, () -> {
			Coordinates coor = new Coordinates(9, 1);
		});

	}

}
