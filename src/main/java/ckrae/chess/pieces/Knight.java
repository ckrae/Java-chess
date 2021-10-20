package ckrae.chess.pieces;

import org.apache.commons.lang3.Validate;

import ckrae.chess.Board;
import ckrae.chess.Color;
import ckrae.chess.Coordinates;
import ckrae.chess.Move;

public class Knight extends Piece {

	public Knight(final Color white) {
		super(white);

	}

	@Override
	public boolean isLegal(final Move move, final Board board) {

		Validate.notNull(move);
		Validate.notNull(board);

		final Coordinates start = move.getStart();
		final Coordinates target = move.getTarget();

		final int x = Math.abs(start.getX() - target.getX());
		if (x == 0)
			return false;

		final int y = Math.abs(start.getY() - target.getY());
		if (y == 0)
			return false;

		final int distance = x + y;
		if (distance != 3)
			return false;

		return (!board.isOwner(getColor(), target));

	}

	@Override
	public PieceType getType() {
		return PieceType.KNIGHT;
	}

}
