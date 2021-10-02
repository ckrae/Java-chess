package ckrae.chess;

import org.apache.commons.lang3.Validate;

public class King extends Piece {

	public King(Player player) {
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

		int distance = x + y;

		if (distance != 1)
			return false;

		if (!board.isOwner(player, target))
			return false;

		return true;

	}

}
