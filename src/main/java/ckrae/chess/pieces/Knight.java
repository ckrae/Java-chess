package ckrae.chess.pieces;

import org.apache.commons.lang3.Validate;

import ckrae.chess.Board;
import ckrae.chess.Coordinates;
import ckrae.chess.Move;
import ckrae.chess.Player;

public class Knight extends Piece {

	public Knight(Player player) {
		super(player);

	}

	@Override
	public boolean isLegal(Move move, Board board) {

		Validate.notNull(move);
		Validate.notNull(board);

		Coordinates start = move.getStart();
		Coordinates target = move.getTarget();

		int x = Math.abs(start.getX() - target.getX());
		if (x == 0)
			return false;

		int y = Math.abs(start.getY() - target.getY());
		if (y == 0)
			return false;

		int distance = x + y;
		if (distance != 3)
			return false;

		return true;
	}

	@Override
	public String getLetter() {
		return "H";
	}

}
