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

	/**
	 * Performs a move on the given board. Includes special pawn movements. e.g en
	 * passant and promotion.
	 */
	@Override
	public void performMove(final Move move, final Board board) {

		final Coordinates start = move.getStart();
		final Coordinates target = move.getTarget();

		if (isEnPassant(move, board)) {
			board.removePiece(board.getLastMove().getTarget());
		}

		if (isPromotion(move, board)) {
			final Color movingColor = board.getPiece(start).getColor();
			board.addPiece(target, new Queen(movingColor));

		} else {
			board.addPiece(target, this);
		}

		board.removePiece(start);

	}

	@Override
	public boolean isLegal(final Move move, final Board board) {

		Validate.notNull(move);
		Validate.notNull(board);
		Validate.validState(getColor() == Color.WHITE || getColor() == Color.BLACK);

		final Coordinates start = move.getStart();
		final Coordinates target = move.getTarget();

		if (isEnPassant(move, board))
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

	/**
	 * Check if this move is a en passant capture on a given board.
	 *
	 * @param board
	 * @return true if move is en passant.
	 */
	public boolean isEnPassant(final Move move, final Board board) {

		if (board.getLastMove() == null)
			return false;

		final Coordinates lastTarget = board.getLastMove().getTarget();
		if (board.getPiece(lastTarget).getType() != PieceType.PAWN)
			return false;

		if (!(move.isDiagonal() && move.distance() == 2))
			return false;

		if (lastTarget.getY() != 4 && lastTarget.getY() != 5)
			return false;

		if (lastTarget.getX() != move.getTarget().getX())
			return false;

		return (Math.abs(lastTarget.getY() - move.getTarget().getY()) == 1);

	}

	/**
	 * Check if this move is a pawn promotion.
	 * 
	 * @param board
	 * @return true if pawn can be promoted
	 */
	public boolean isPromotion(final Move move, final Board board) {

		Validate.notNull(board);

		final Piece movingPiece = board.getPiece(move.getStart());

		if (movingPiece.getType() != PieceType.PAWN)
			return false;

		if (move.getTarget().getY() == Board.SIZE && movingPiece.getColor() == Color.WHITE)
			return true;

		if (move.getTarget().getY() == 1 && movingPiece.getColor() == Color.BLACK)
			return true;

		return false;

	}

	@Override
	public PieceType getType() {
		return PieceType.PAWN;
	}

}
