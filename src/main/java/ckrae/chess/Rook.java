package ckrae.chess;

import org.apache.commons.lang3.Validate;

public class Rook extends Piece {

	public Rook(Player player) {
		super(player);
	}

	@Override
	public boolean isLegal(Move move, Board board) {

		Validate.notNull(move);
		Validate.notNull(board);

		Coordinates start = move.getStart();
		Coordinates target = move.getTarget();

		int x = start.getX() - target.getX();
		int y = start.getY() - target.getY();

		if (Math.abs(x) != 0 && Math.abs(y) != 0)
			return false;

		return true;
	}
}
