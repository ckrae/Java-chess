package ckrae.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import ckrae.chess.pieces.Pawn;

public class MoveTest {

	@Test
	void parseTest() {

		final Move move = Move.parse("a2 to a3");
		assertEquals(move.getStart().getX(), 1);
		assertEquals(move.getStart().getY(), 2);
		assertEquals(move.getTarget().getX(), 1);
		assertEquals(move.getTarget().getY(), 3);

	}

	@ParameterizedTest
	@DisplayName("test is vertical")
	@ValueSource(strings = { "d4 to d6", "a8 to a1" })
	void isVerticalTest(final String str) {

		final Move move = Move.parse(str);
		assertFalse(move.isHorizontal());
		assertTrue(move.isVertical());
		assertFalse(move.isDiagonal());
	}

	@ParameterizedTest
	@DisplayName("test is horizontal")
	@ValueSource(strings = { "d4 to g4", "a7 to h7" })
	void isHorizontalTest(final String str) {

		final Move move = Move.parse(str);
		assertTrue(move.isHorizontal());
		assertFalse(move.isVertical());
		assertFalse(move.isDiagonal());
	}

	@ParameterizedTest
	@DisplayName("test is diagonal")
	@ValueSource(strings = { "a1 to h8", "h8 to a1", "a8 to h1", "h1 to a8", "d1 to e2" })
	void isDiagonalTest(final String str) {

		final Move move = Move.parse(str);
		assertFalse(move.isHorizontal());
		assertFalse(move.isVertical());
		assertTrue(move.isDiagonal());
	}

	@Nested
	@DisplayName("isBlocked Tests")
	class BLocked {

		@ParameterizedTest
		@DisplayName("horizontal")
		@CsvSource({ "1, 8", "8, 1" })
		void horizontal(final int start, final int target) {
			final Board board = Board.empty();
			final Move move = new Move(1, start, 1, target);
			assertFalse(move.isBlocked(board));
		}

		@ParameterizedTest
		@DisplayName("horizontal blocked")
		@CsvSource({ "1, 8", "8, 1" })
		void horizontalBlocked(final int start, final int target) {
			final Board board = Board.empty();
			board.addPiece(1, 4, new Pawn(Color.WHITE));
			final Move move = new Move(1, start, 1, target);
			assertTrue(move.isBlocked(board));
		}

		@ParameterizedTest
		@DisplayName("vertical")
		@CsvSource({ "1, 8", "8, 1", "1, 3" })

		void vertical(final int start, final int target) {
			final Board board = Board.empty();
			final Move move = new Move(start, 1, target, 1);
			assertFalse(move.isBlocked(board));

		}

		@ParameterizedTest
		@DisplayName("vertical blocked")
		@CsvSource({ "1, 8", "8, 1" })
		void verticalBlocked(final int start, final int target) {
			final Board board = Board.empty();
			board.addPiece(4, 4, new Pawn(Color.WHITE));
			final Move move = new Move(start, 4, target, 4);
			assertTrue(move.isBlocked(board));

		}

		@ParameterizedTest
		@CsvSource({ "1, 1, 8, 8", "8, 8, 1, 1", "1, 8, 8, 1", "8, 1, 1, 8" })
		void diagonal(final int a, final int b, final int c, final int d) {
			final Board board = Board.empty();
			board.addPiece(4, 4, new Pawn(Color.WHITE));
			board.addPiece(4, 5, new Pawn(Color.WHITE));
			final Move move = new Move(a, b, c, d);
			assertTrue(move.isBlocked(board));

		}
	}

}
