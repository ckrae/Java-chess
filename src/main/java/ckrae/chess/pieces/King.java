package ckrae.chess.pieces;

import org.apache.commons.lang3.Validate;

import ckrae.chess.Board;
import ckrae.chess.Coordinates;
import ckrae.chess.Move;
import ckrae.chess.Player;

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

	@Override
	public String getLetter() {
		return "K";
	}

}
