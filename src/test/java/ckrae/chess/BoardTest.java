package ckrae.chess;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ckrae.chess.pieces.Pawn;

class BoardTest {

	@Test
	@DisplayName("initialize chess board")
	void initChessBoard() {

		final Board board = Board.chess();

		int y = 1;
		for (int x = 1; x <= Board.SIZE; x++) {
			final Coordinates coor = new Coordinates(x, y);
			assertTrue(board.isOccupied(coor));
			assertTrue(board.isOwner(Color.WHITE, coor));
		}

		y = 2;
		for (int x = 1; x <= Board.SIZE; x++) {
			final Coordinates coor = new Coordinates(x, y);
			assertTrue(board.isOccupied(coor));
			assertTrue(board.isOwner(Color.WHITE, coor));
			assertTrue(board.getPiece(coor) instanceof Pawn);
		}

		y = 8;
		for (int x = 1; x <= Board.SIZE; x++) {
			final Coordinates coor = new Coordinates(x, y);
			assertTrue(board.isOccupied(coor));
			assertTrue(board.isOwner(Color.BLACK, coor));
		}

		y = 7;
		for (int x = 1; x <= Board.SIZE; x++) {
			final Coordinates coor = new Coordinates(x, y);
			assertTrue(board.isOccupied(coor));
			assertTrue(board.isOwner(Color.BLACK, coor));
			assertTrue(board.getPiece(coor) instanceof Pawn);
		}

		for (int x = 1; x <= Board.SIZE; x++) {
			for (y = 3; y <= 6; y++) {
				final Coordinates coor = new Coordinates(x, y);
				assertFalse(board.isOccupied(coor));
			}
		}

	}

	@Test
	@DisplayName("try move non existing piece")
	void testNonExistingMove() {

		final Board board = Board.chess();

		final Move move = new Move("a5", "a5");

		assertThrows(IllegalMoveException.class, () -> {
			board.move(move);
		});

	}

	@Test
	@DisplayName("make a move")
	void testMove() {

		final Board board = Board.chess();

		final Move move = new Move("a2", "a3");
		board.move(move);
		assertTrue(board.isOccupied(1, 3));
		assertFalse(board.isOccupied(1, 2));

		board.move("d7", "d6");
		assertTrue(board.isOccupied(4, 6));
		assertFalse(board.isOccupied(4, 7));

		board.move("a1", "a2");
		assertTrue(board.isOccupied(1, 2));
		assertFalse(board.isOccupied(1, 1));

		board.move("b8", "c6");
		assertTrue(board.isOccupied(3, 6));
		assertFalse(board.isOccupied(2, 8));

		board.move("b1", "c3");
		assertTrue(board.isOccupied(1, 2));
		assertFalse(board.isOccupied(1, 1));

	}

}
