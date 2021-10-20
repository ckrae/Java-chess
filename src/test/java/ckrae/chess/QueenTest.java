package ckrae.chess;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ckrae.chess.pieces.Piece;
import ckrae.chess.pieces.Queen;

public class QueenTest {

	@Test
	@DisplayName("not moving")
	void notMovingTest() {

		final Board board = Board.empty();
		final Piece piece = new Queen(Color.WHITE);

		board.addPiece(4, 4, piece);

		final Move move = new Move(4, 4, 4, 4);
		assertFalse(piece.isLegal(move, board));

	}

	@Test
	@DisplayName("legal vertical move")
	void legalVerticalTest() {

		final Board board = Board.empty();
		final Piece piece = new Queen(Color.WHITE);

		board.addPiece(1, 1, piece);

		final Move move = new Move(1, 1, 1, 3);
		assertTrue(piece.isLegal(move, board));

	}

	@Test
	@DisplayName("legal horizontal move")
	void legalHorizontalTest() {

		final Board board = Board.empty();
		final Piece piece = new Queen(Color.WHITE);

		board.addPiece(1, 1, piece);

		final Move move = new Move(1, 1, 6, 1);
		assertTrue(piece.isLegal(move, board));

	}

	@Test
	@DisplayName("legal diagonal move")
	void legalDiagonalTest() {

		final Board board = Board.empty();
		final Piece piece = new Queen(Color.WHITE);

		board.addPiece(1, 1, piece);

		final Move move = new Move(1, 1, 8, 8);
		assertTrue(move.isDiagonal());
		assertFalse(move.isBlocked(board));
		assertTrue(piece.isLegal(move, board));

	}
}
