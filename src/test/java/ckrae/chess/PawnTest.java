package ckrae.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ckrae.chess.pieces.Pawn;
import ckrae.chess.pieces.Piece;
import ckrae.chess.pieces.PieceType;

class PawnTest {

	@ParameterizedTest
	@DisplayName("legal moves white player")
	@CsvSource({ "a2, a3", "a2, a4", "b2, b3", "d6, e7", "f6, g7" })
	void isLegalTest(final String start, final String target) {

		final Piece piece = new Pawn(Color.WHITE);

		final Move move = new Move(start, target);
		assertTrue(piece.isLegal(move, Board.chess()));

	}

	@ParameterizedTest
	@DisplayName("invalid moves white player")
	@CsvSource({ "a2, a5", "b2, c3", "d6, d7", "f6, f5", "d2,d2" })
	void invalidMovesWhite(final String start, final String target) {

		final Piece piece = new Pawn(Color.WHITE);

		final Move move = new Move(start, target);
		assertFalse(piece.isLegal(move, Board.chess()));

	}

	@ParameterizedTest
	@DisplayName("legal moves black player")
	@CsvSource({ "a7, a6", "a7, a5", "b7, b6", "d3, e2", "f3, g2" })
	void isLegalB(final String start, final String target) {

		final Piece piece = new Pawn(Color.BLACK);

		final Move move = new Move(start, target);
		assertTrue(piece.isLegal(move, Board.chess()));

	}

	@ParameterizedTest
	@DisplayName("invalid moves black player")
	@CsvSource({ "a7, a4", "b7, c6", "d3, d2", "d2,d2", "a7,b6" })
	void invalidMovesB(final String start, final String target) {

		final Piece piece = new Pawn(Color.BLACK);

		final Move move = new Move(start, target);
		assertFalse(piece.isLegal(move, Board.chess()));

	}

	@Nested
	@DisplayName("En passant")
	class EnPassant {

		@Test
		@DisplayName("white")
		void white() {

			final Piece white = new Pawn(Color.WHITE);
			final Piece black = new Pawn(Color.BLACK);

			final Board board = Board.empty();
			board.addPiece(1, 7, black);
			board.addPiece(2, 5, white);

			final Move move1 = new Move(1, 7, 1, 5);
			board.move(move1);

			final Move move2 = new Move(2, 5, 1, 6);
			assertTrue(white.isLegal(move2, board));

			board.move(move2);
			assertTrue(board.isOwner(Color.WHITE, 1, 6));
			assertFalse(board.isOccupied(1, 5));

		}

		@Test
		@DisplayName("black")
		void black() {

			final Piece white = new Pawn(Color.WHITE);
			final Piece black = new Pawn(Color.BLACK);

			final Board board = Board.empty();
			board.addPiece(5, 4, black);
			board.addPiece(4, 2, white);

			Move move = new Move(4, 2, 4, 4);
			board.move(move);

			move = new Move(5, 4, 4, 3);
			assertTrue(black.isLegal(move, board));

			board.move(move);
			assertFalse(board.isOccupied(4, 4));

		}

		@Test
		@DisplayName("No En passant")
		void noEnPassant() {

			final Board board = Board.chess();

			Move move = new Move(1, 2, 1, 4);
			board.move(move);

			move = new Move(2, 7, 1, 6);
			assertFalse(board.isLegal(move));

		}

	}

	@Test
	@DisplayName("promotion")
	void promotion() {

		final Piece piece = new Pawn(Color.WHITE);

		final Board board = Board.empty();
		board.addPiece(1, 7, piece);

		final Move move = new Move(1, 7, 1, 8);
		board.move(move);

		assertTrue(board.isOwner(Color.WHITE, 1, 8));
		assertFalse(board.isOccupied(1, 7));
		assertEquals(PieceType.QUEEN, board.getPiece(1, 8).getType());

	}

}
