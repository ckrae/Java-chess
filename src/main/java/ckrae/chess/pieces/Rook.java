package ckrae.chess.pieces;

import org.apache.commons.lang3.Validate;

import ckrae.chess.Board;
import ckrae.chess.Color;
import ckrae.chess.Move;

public class Rook extends Piece {

	public Rook(final Color white) {
		super(white);
	}

	@Override
	public boolean isLegal(final Move move, final Board board) {

		Validate.notNull(move);
		Validate.notNull(board);

		if (!move.isHorizontal() && !move.isVertical())
			return false;

		if (board.isOwner(getColor(), move.getTarget()))
			return false;

		return !(move.isBlocked(board));

	}

	@Override
	public PieceType getType() {
		return PieceType.ROOK;
	}
}
