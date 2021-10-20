package ckrae.chess.pieces;

import org.apache.commons.lang3.Validate;

import ckrae.chess.Board;
import ckrae.chess.Color;
import ckrae.chess.Coordinates;
import ckrae.chess.Move;

public class Bishop extends Piece {

	public Bishop(final Color white) {
		super(white);

	}

	@Override
	public boolean isLegal(final Move move, final Board board) {

		Validate.notNull(move);
		Validate.notNull(board);

		final Coordinates start = move.getStart();
		final Coordinates target = move.getTarget();

		final int x = start.getX() - target.getX();
		final int y = start.getY() - target.getY();

		if (Math.abs(x) != Math.abs(y))
			return false;

		if (board.isOwner(getColor(), target))
			return false;

		return !move.isBlocked(board);
	}

	@Override
	public PieceType getType() {
		return PieceType.BISHOP;
	}

}
