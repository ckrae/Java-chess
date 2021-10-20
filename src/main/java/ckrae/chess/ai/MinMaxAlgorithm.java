package ckrae.chess.ai;

import ckrae.chess.Board;
import ckrae.chess.Color;
import ckrae.chess.Move;

public class MinMaxAlgorithm implements AIAlgorithm {

	/**
	 * The heuristic that this algorithm uses to determine a preferable game state.
	 */
	AIHeuristic heuristic = new MaterialHeuristic();

	/**
	 * The maximal depth for the search tree.
	 */
	private static final int maxDepth = 3;

	/**
	 * The color this algorithm wants to optimize for.
	 */
	private Color color;

	/**
	 * The currently best move.
	 */
	private Move bestMove;

	@Override
	public synchronized Move compute(final Color color, final Board board) {

		this.color = color;
		this.bestMove = null;

		maxi(MinMaxAlgorithm.maxDepth, board);

		return this.bestMove;
	}

	private int maxi(final int depth, final Board board) {

		if (depth == 0)
			return this.heuristic.evaluate(board, this.color);

		int max = Integer.MIN_VALUE;

		for (final Move move : board.getLegalMoves(this.color)) {
			final int score = mini(depth - 1, Board.copy(board).move(move));
			if (score > max) {
				max = score;
				if (depth == maxDepth) {
					this.bestMove = move;
				}
			}
		}

		return max;
	}

	private int mini(final int depth, final Board board) {

		if (depth == 0)
			return this.heuristic.evaluate(board, this.color);

		int min = Integer.MAX_VALUE;
		for (final Move move : board.getLegalMoves(this.color.opposite())) {
			final int score = maxi(depth - 1, Board.copy(board).move(move));
			if (score < min) {
				min = score;
			}
		}

		return min;

	}
}
