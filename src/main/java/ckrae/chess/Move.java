package ckrae.chess;

import org.apache.commons.lang3.Validate;

/**
 * A move represents a movement between a start position and a target position
 * on a game board.
 *
 */
public class Move {

	/**
	 * The position of the moving piece.
	 */
	private final Coordinates start;

	/**
	 * The position of the destination.
	 */
	private final Coordinates target;

	/**
	 * Parses a Move from a textual move description.
	 * 
	 * @param str input string
	 * @return the move
	 */
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

	/**
	 * Return true if this is a horizontal movement. The y-coordinate does not
	 * change.
	 * 
	 * @return true if move is horizontal
	 */
	public boolean isHorizontal() {
		return (this.start.y == this.target.y);
	}

	/**
	 * Return true if this is a vertical movement. The x-coordinate does not change.
	 * 
	 * @return true if move is vertical.
	 */
	public boolean isVertical() {
		return (this.start.x == this.target.x);
	}

	/**
	 * Return true if this move is diagonal. The x-coordinate and the y-coordinate
	 * change by the same amount.
	 * 
	 * @return
	 */
	public boolean isDiagonal() {

		final int x = this.start.x - this.target.x;
		final int y = this.start.y - this.target.y;

		return (Math.abs(x) == Math.abs(y));
	}

	/**
	 * Get the distance between two positions. Counts how many horizontal and
	 * vertical movements are needed to reach the destination.
	 * 
	 * @return
	 */
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

		if (isHorizontal())
			return isBlockedHorizontal(board);

		if (isVertical())
			return isBlockedVertical(board);

		if (isDiagonal())
			return isBlockedDiagonal(board);

		return false;

	}

	private boolean isBlockedHorizontal(final Board board) {

		if (this.start.x < this.target.x) {
			for (int i = this.start.x + 1; i < this.target.x; i++) {
				if (board.isOccupied(i, this.start.y))
					return true;
			}
		}

		if (this.start.x > this.target.x) {
			for (int i = this.start.x - 1; i > this.target.x; i--) {
				if (board.isOccupied(i, this.start.y))
					return true;
			}
		}

		return false;
	}

	private boolean isBlockedVertical(final Board board) {

		if (this.start.y < this.target.y) {
			for (int i = this.start.y + 1; i < this.target.y; i++) {
				if (board.isOccupied(this.start.x, i))
					return true;
			}
		}

		if (this.start.y > this.target.y) {
			for (int i = this.start.y - 1; i > this.target.y; i--) {
				if (board.isOccupied(this.start.x, i))
					return true;
			}
		}

		return false;
	}

	private boolean isBlockedDiagonal(final Board board) {

		if (this.start.y < this.target.y && this.start.x < this.target.x) {
			for (int x = this.start.x + 1, y = this.start.y + 1; x < this.target.x; x++, y++) {
				if (board.isOccupied(x, y))
					return true;
			}
		}

		if (this.start.y < this.target.y && this.start.x > this.target.x) {
			for (int x = this.start.x - 1, y = this.start.y + 1; x > this.target.x; x--, y++) {
				if (board.isOccupied(x, y))
					return true;
			}
		}

		if (this.start.y > this.target.y && this.start.x < this.target.x) {
			for (int x = this.start.x + 1, y = this.start.y - 1; x < this.target.x; x++, y--) {
				if (board.isOccupied(x, y))
					return true;
			}
		}

		if (this.start.y > this.target.y && this.start.x > this.target.x) {
			for (int x = this.start.x - 1, y = this.start.y - 1; x > this.target.x; x--, y--) {
				if (board.isOccupied(x, y))
					return true;
			}
		}

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
