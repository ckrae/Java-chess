package ckrae.chess.ai;

import ckrae.chess.Board;
import ckrae.chess.Color;
import ckrae.chess.Move;

public interface AIAlgorithm {

	/**
	 * Compute the next move for an AI player based on the given board state.
	 *
	 * @param color
	 * @param board
	 * @return move
	 */
	Move compute(Color color, Board board);

}
