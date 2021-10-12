package ckrae.chess;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ckrae.chess.pieces.Pawn;
import ckrae.chess.pieces.Piece;

@ExtendWith(MockitoExtension.class)
public class PawnTest {

	@Mock
	Player player;

	@Mock
	Board board;

	@ParameterizedTest
	@DisplayName("test legal moves white player")
	@CsvSource({ "a2, a3", "a2, a4", "b2, b3", "d6, e7", "f6, g7" })
	public void isLegalTest(String start, String target) {

		when(player.getColor()).thenReturn(Color.WHITE);

		Piece piece = new Pawn(player);

		Move move = new Move(start, target);
		assertTrue(piece.isLegal(move, new Board(player, new Player(Color.BLACK))));

	}

	@ParameterizedTest
	@DisplayName("test invalid moves white player")
	@CsvSource({ "a2, a5", "b2, c3", "d6, d7", "f6, f5", "d2,d2" })
	public void invalidMovesWhite(String start, String target) {

		when(player.getColor()).thenReturn(Color.WHITE);

		Piece piece = new Pawn(player);

		Move move = new Move(start, target);
		assertFalse(piece.isLegal(move, new Board(player, new Player(Color.BLACK))));

	}

	@ParameterizedTest
	@DisplayName("test legal moves black player")
	@CsvSource({ "a7, a6", "a7, a5", "b7, b6", "d3, e2", "f3, g2" })
	public void isLegalB(String start, String target) {

		when(player.getColor()).thenReturn(Color.BLACK);

		Piece piece = new Pawn(player);

		Move move = new Move(start, target);
		assertTrue(piece.isLegal(move, new Board(new Player(Color.WHITE), player)));

	}

	@ParameterizedTest
	@DisplayName("test invalid moves black player")
	@CsvSource({ "a7, a4", "b7, c6", "d3, d2", "d2,d2" })
	public void invalidMovesB(String start, String target) {

		when(player.getColor()).thenReturn(Color.BLACK);

		Piece piece = new Pawn(player);

		Move move = new Move(start, target);
		assertFalse(piece.isLegal(move, new Board(new Player(Color.WHITE), player)));

	}

}
