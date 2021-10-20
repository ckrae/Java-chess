package ckrae.chess;

import org.apache.commons.lang3.Validate;

import ckrae.chess.pieces.Piece;
import ckrae.chess.pieces.PieceType;

public class Move {

	private final Coordinates start;
	private final Coordinates target;

	public static Move parse(final String str) {

		Validate.matchesPattern(str, "[a-h][1-8](.*?)[a-h][1-8]", "can not parse move from string " + str);

		final String start = str.substring(0, 2);
		final String target = str.substring(str.length() - 2);

		return new Move(start, target);

	}

	public Move(final int startX, final int startY, final int targetX, final int targetY) {
		this(new Coordinates(startX, startY), new Coordinates(targetX, targetY));
	}

	public Move(final Coordinates start, final Coordinates target) {

		Validate.notNull(start);
		Validate.notNull(target);

		this.start = start;
		this.target = target;

	}

	public Move(final String start, final String target) {

		this(new Coordinates(start), new Coordinates(target));

	}

	public boolean isHorizontal() {
		return (this.start.y == this.target.y);
	}

	public boolean isVertical() {
		return (this.start.x == this.target.x);
	}

	public boolean isDiagonal() {

		final int x = this.start.x - this.target.x;
		final int y = this.start.y - this.target.y;

		return (Math.abs(x) == Math.abs(y));
	}

	public int distance() {

		final int x = this.start.x - this.target.x;
		final int y = this.start.y - this.target.y;

		return Math.abs(x) + Math.abs(y);
	}

	/**
	 * Check if there is a piece between the start coordinates and the target
	 * coordinates on the given board.
	 * 
	 * @param board
	 * @return true if there is a piece between the coordinates
	 */
	public boolean isBlocked(final Board board) {

		if (isHorizontal() && this.start.x < this.target.x) {
			for (int i = this.start.x + 1; i < this.target.x; i++) {
				if (board.isOccupied(i, this.start.y))
					return true;
			}
		}

		if (isHorizontal() && this.start.x > this.target.x) {
			for (int i = this.start.x - 1; i > this.target.x; i--) {
				if (board.isOccupied(i, this.start.y))
					return true;
			}
		}

		if (isVertical() && this.start.y < this.target.y) {
			for (int i = this.start.y + 1; i < this.target.y; i++) {
				if (board.isOccupied(this.start.x, i))
					return true;
			}
		}

		if (isVertical() && this.start.y > this.target.y) {
			for (int i = this.start.y - 1; i > this.target.y; i--) {
				if (board.isOccupied(this.start.x, i))
					return true;
			}
		}

		if (isDiagonal() && this.start.y < this.target.y && this.start.x < this.target.x) {
			for (int x = this.start.x + 1, y = this.start.y + 1; x < this.target.x; x++, y++) {
				if (board.isOccupied(x, y))
					return true;
			}
		}

		if (isDiagonal() && this.start.y < this.target.y && this.start.x > this.target.x) {
			for (int x = this.start.x - 1, y = this.start.y + 1; x > this.target.x; x--, y++) {
				if (board.isOccupied(x, y))
					return true;
			}
		}

		if (isDiagonal() && this.start.y > this.target.y && this.start.x < this.target.x) {
			for (int x = this.start.x + 1, y = this.start.y - 1; x < this.target.x; x++, y--) {
				if (board.isOccupied(x, y))
					return true;
			}
		}

		if (isDiagonal() && this.start.y > this.target.y && this.start.x > this.target.x) {
			for (int x = this.start.x - 1, y = this.start.y - 1; x > this.target.x; x--, y--) {
				if (board.isOccupied(x, y))
					return true;
			}
		}

		return false;
	}

	/**
	 * Check if this move is a en passant capture on a given board.
	 *
	 * @param board
	 * @return true if move is en passant.
	 */
	public boolean isEnPassant(final Board board) {

		if (board.getLastMove() == null)
			return false;

		final Coordinates lastTarget = board.getLastMove().getTarget();
		if (board.getPiece(lastTarget).getType() != PieceType.PAWN)
			return false;

		if (!(isDiagonal() && distance() == 2))
			return false;

		if (lastTarget.getY() != 4 && lastTarget.getY() != 5)
			return false;

		if (lastTarget.getX() != this.target.x)
			return false;

		return (Math.abs(lastTarget.getY() - this.target.y) == 1);

	}

	/**
	 * Check if this move is a pawn promotion.
	 * 
	 * @param board
	 * @return true if pawn can be promoted
	 */
	public boolean isPromotion(final Board board) {

		Validate.notNull(board);

		final Piece movingPiece = board.getPiece(this.start);

		if (movingPiece.getType() != PieceType.PAWN)
			return false;

		if (this.target.getY() == Board.SIZE && movingPiece.getColor() == Color.WHITE)
			return true;

		if (this.target.getY() == 1 && movingPiece.getColor() == Color.BLACK)
			return true;

		return false;

	}

	/**
	 * Get the starting position of this move.
	 * 
	 * @return position
	 */
	public Coordinates getStart() {
		return this.start;
	}

	/**
	 * Get the ending position of this move.
	 * 
	 * @return position
	 */
	public Coordinates getTarget() {
		return this.target;
	}

	@Override
	public String toString() {
		return "Move [" + this.start + " to " + this.target + "]";
	}

}
