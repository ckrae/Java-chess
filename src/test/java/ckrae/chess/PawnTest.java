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
	@CsvSource({ "a2, a3", "b2, b3", "d6, e7", "f6, g7" })
	public void isLegalTest(String start, String target) {

		when(player.getColor()).thenReturn(Color.WHITE);

		Piece piece = new Pawn(player);

		Move move = new Move(start, target);
		assertTrue(piece.isLegal(move, new Board(player, new Player(Color.BLACK))));

	}

	@ParameterizedTest
	@DisplayName("test invalid moves white player")
	@CsvSource({ "a2, a4", "b2, c3", "d6, d7", "f6, f5", "d2,d2" })
	public void invalidMovesWhite(String start, String target) {

		when(player.getColor()).thenReturn(Color.WHITE);

		Piece piece = new Pawn(player);

		Move move = new Move(start, target);
		assertFalse(piece.isLegal(move, new Board(player, new Player(Color.BLACK))));

	}

}
