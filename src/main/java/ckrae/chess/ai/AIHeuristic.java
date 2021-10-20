package ckrae.chess.ai;

import ckrae.chess.Board;
import ckrae.chess.Color;

public interface AIHeuristic {

	/**
	 * Compute a heuristic value based on the board state for a color. A higher
	 * value represents a more favorable situation for the given color.
	 *
	 * @param board
	 * @param color
	 * @return integer value
	 */
	int evaluate(Board board, Color color);

}
