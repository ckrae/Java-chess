package ckrae.chess.ai;

import org.apache.commons.lang3.Validate;

import ckrae.chess.Board;
import ckrae.chess.Color;
import ckrae.chess.Move;
import ckrae.chess.Player;

public class AIPlayer extends Player {

	/**
	 * The algorithm that this player uses to determine the next move.
	 */
	private final AIAlgorithm algorithm;

	public AIPlayer(final Color color, final AIAlgorithm algorithm) {
		super(color);
		this.algorithm = new MinMaxAlgorithm();
	}

	@Override
	public Move turn(final Board board) {

		Validate.notNull(board);

		return this.algorithm.compute(getColor(), board);

	}

}
