package ckrae.chess;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ckrae.chess.pieces.Pawn;
import ckrae.chess.pieces.Rook;

public class RookTest {

	@Test
	@DisplayName("not moving")
	void notMovingTest() {

		final Board board = Board.empty();
		final Rook rook = new Rook(Color.WHITE);

		board.addPiece(1, 1, rook);

		final Move move = new Move(1, 1, 1, 1);
		assertFalse(rook.isLegal(move, board));

	}

	@Test
	@DisplayName("legal vertical move")
	void legalVerticalTest() {

		final Board board = Board.empty();
		final Rook rook = new Rook(Color.WHITE);

		board.addPiece(1, 1, rook);

		final Move move = new Move(1, 1, 1, 3);
		assertTrue(move.isVertical());
		assertFalse(board.isOwner(Color.WHITE, new Coordinates(1, 8)));
		assertFalse(move.isBlocked(board));
		assertTrue(rook.isLegal(move, board));

	}

	@Test
	@DisplayName("legal horizontal move")
	void legalHorizontalTest() {

		final Board board = Board.empty();
		final Rook rook = new Rook(Color.WHITE);

		board.addPiece(1, 1, rook);

		final Move move = new Move(1, 1, 6, 1);
		assertTrue(move.isHorizontal());
		assertFalse(move.isBlocked(board));
		assertTrue(rook.isLegal(move, board));

	}

	@Test
	@DisplayName("blocked vertical moves")
	void blockedVerticalTest() {

		final Board board = Board.empty();
		final Rook rook = new Rook(Color.WHITE);

		board.addPiece(4, 4, rook);
		board.addPiece(4, 6, new Pawn(Color.WHITE));

		final Move move = new Move(4, 4, 4, 8);
		assertFalse(rook.isLegal(move, board));
	}

	@Test
	@DisplayName("blocked horizontal moves")
	void blockedHorizontalTest() {

		final Board board = Board.empty();
		final Rook rook = new Rook(Color.WHITE);

		board.addPiece(4, 4, rook);
		board.addPiece(6, 4, new Pawn(Color.WHITE));

		final Move move = new Move(4, 4, 8, 4);
		assertFalse(rook.isLegal(move, board));
	}

}
