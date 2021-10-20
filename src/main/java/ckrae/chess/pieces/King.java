package ckrae.chess.pieces;

import org.apache.commons.lang3.Validate;

import ckrae.chess.Board;
import ckrae.chess.Color;
import ckrae.chess.Move;

public class King extends Piece {

	public King(final Color white) {
		super(white);

	}

	@Override
	public boolean isLegal(final Move move, final Board board) {

		Validate.notNull(move);
		Validate.notNull(board);

		if (move.isDiagonal() && move.distance() != 2)
			return false;

		if (!move.isDiagonal() && move.distance() != 1)
			return false;

		return (!board.isOwner(getColor(), move.getTarget()));

	}

	@Override
	public PieceType getType() {
		return PieceType.KING;
	}

}
