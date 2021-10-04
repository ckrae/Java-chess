package ckrae.chess;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ckrae.chess.pieces.Knight;
import ckrae.chess.pieces.Pawn;

public class BoardTest {

	private Player white;
	private Player black;

	@BeforeEach
	public void setup() {
		this.white = new Player(Color.WHITE);
		this.black = new Player(Color.BLACK);
	}

	@Test
	@DisplayName("initialize board")
	void init() {

		Board board = new Board(white, black);

		int y = 1;
		for (int x = 1; x <= 8; x++) {
			Coordinates coor = new Coordinates(x, y);
			assertTrue(board.isOccupied(coor));
			assertTrue(board.isOwner(white, coor));
		}

		y = 2;
		for (int x = 1; x <= 8; x++) {
			Coordinates coor = new Coordinates(x, y);
			assertTrue(board.isOccupied(coor));
			assertTrue(board.isOwner(white, coor));
			assertTrue(board.getPiece(coor) instanceof Pawn);
		}

		y = 8;
		for (int x = 1; x <= 8; x++) {
			Coordinates coor = new Coordinates(x, y);
			assertTrue(board.isOccupied(coor));
			assertTrue(board.isOwner(black, coor));
		}

		y = 7;
		for (int x = 1; x <= 8; x++) {
			Coordinates coor = new Coordinates(x, y);
			assertTrue(board.isOccupied(coor));
			assertTrue(board.isOwner(black, coor));
			assertTrue(board.getPiece(coor) instanceof Pawn);
		}

		for (int x = 1; x <= 8; x++) {
			for (y = 3; y <= 6; y++) {
				Coordinates coor = new Coordinates(x, y);
				assertFalse(board.isOccupied(coor));
			}
		}

	}

	@Test
	@DisplayName("try move non existing piece")
	void testNonExistingMove() {

		Board board = new Board(white, black);

		Move move = new Move("a5", "a5");

		assertThrows(IllegalArgumentException.class, () -> {
			board.move(white, move);
		});

	}

	@Test
	@DisplayName("try move piece of another player")
	void testWrongPlayerMove() {

		Board board = new Board(white, black);

		Move move = new Move("d7", "d6");

		assertThrows(IllegalArgumentException.class, () -> {
			board.move(white, move);
		});

	}

	@Test
	@DisplayName("make a move")
	void testMove() {

		Board board = new Board(white, black);

		Move move = new Move("a2", "a3");
		board.move(white, move);
		assertTrue(board.isOccupied(1, 3));
		assertFalse(board.isOccupied(1, 2));

		board.move(black, "d7", "d6");
		assertTrue(board.isOccupied(4, 6));
		assertFalse(board.isOccupied(4, 7));

		board.move(white, "a1", "a2");
		assertTrue(board.isOccupied(1, 2));
		assertFalse(board.isOccupied(1, 1));

		board.move(black, "c8", "f5");
		assertTrue(board.isOccupied(6, 5));
		assertFalse(board.isOccupied(3, 8));

		assertTrue(board.getPiece(2, 1) instanceof Knight);
		board.move(white, "b1", "c3");
		assertTrue(board.isOccupied(1, 2));
		assertFalse(board.isOccupied(1, 1));

	}

}
