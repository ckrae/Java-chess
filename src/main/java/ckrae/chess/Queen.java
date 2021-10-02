package ckrae.chess;

import org.apache.commons.lang3.Validate;

public class Queen extends Piece {

	public Queen(Player player) {
		super(player);

	}

	@Override
	public boolean isLegal(Move move, Board board) {

		Validate.notNull(move);
		Validate.notNull(board);

		Coordinates start = move.getStart();
		Coordinates target = move.getTarget();

		int x = Math.abs(start.getX() - target.getX());
		int y = Math.abs(start.getY() - target.getY());

		// like a bishop
		if (x == y) {
			return true;

		}

		// like a rook
		if (x == 0 || y == 0) {
			return true;

		}

		return false;
	}

}
