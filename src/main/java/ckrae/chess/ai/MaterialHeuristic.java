package ckrae.chess.ai;

import ckrae.chess.Board;
import ckrae.chess.Color;
import ckrae.chess.pieces.Piece;

/**
 * A heuristic that evaluates the game state by comparing the individual piece
 * scores.
 *
 */
public class MaterialHeuristic implements AIHeuristic {

	@Override
	public int evaluate(final Board board, final Color color) {

		int res = 0;

		for (final Piece piece : board.getPieces(color)) {
			res = res + piece.getType().score;
		}

		for (final Piece piece : board.getPieces(color.opposite())) {
			res = res - piece.getType().score;
		}

		return res;

	}

}
