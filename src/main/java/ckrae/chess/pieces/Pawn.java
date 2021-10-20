package ckrae.chess.pieces;

import org.apache.commons.lang3.Validate;

import ckrae.chess.Board;
import ckrae.chess.Color;
import ckrae.chess.Coordinates;
import ckrae.chess.Move;

public class Pawn extends Piece {

	public Pawn(final Color color) {

		super(color);

	}

	@Override
	public boolean isLegal(final Move move, final Board board) {

		Validate.notNull(move);
		Validate.notNull(board);
		Validate.validState(getColor() == Color.WHITE || getColor() == Color.BLACK);

		final Coordinates start = move.getStart();
		final Coordinates target = move.getTarget();

		if (move.isEnPassant(board))
			return true;

		if (getColor() == Color.WHITE)
			return isLegalMoveWhite(start, target, board);

		return isLegalMoveBlack(start, target, board);
	}

	private boolean isLegalMoveWhite(final Coordinates start, final Coordinates target, final Board board) {

		// one step forward
		if (start.equals(target, 0, 1) && !board.isOccupied(target))
			return true;

		// two step forward first move only
		if (start.getY() == 2 && start.equals(target, 0, 2) && !board.isOccupied(target))
			return true;

		// diagonal capturing
		if (start.equals(target, 1, 1) && board.isOccupied(target) && !board.isOwner(getColor(), target))
			return true;

		// diagonal capturing
		if (start.equals(target, -1, 1) && board.isOccupied(target) && !board.isOwner(getColor(), target))
			return true;

		return false;
	}

	private boolean isLegalMoveBlack(final Coordinates start, final Coordinates target, final Board board) {

		// one step forward
		if (start.equals(target, 0, -1) && !board.isOccupied(target))
			return true;

		// two step forward
		if (start.getY() == 7 && start.equals(target, 0, -2) && !board.isOccupied(target))
			return true;

		// diagonal capturing
		if (start.equals(target, 1, -1) && board.isOccupied(target) && !board.isOwner(getColor(), target))
			return true;

		// diagonal capturing
		if (start.equals(target, -1, -1) && board.isOccupied(target) && !board.isOwner(getColor(), target))
			return true;

		return false;

	}

	@Override
	public PieceType getType() {
		return PieceType.PAWN;
	}

}
