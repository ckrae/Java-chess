package ckrae.chess;

import org.apache.commons.lang3.Validate;

public class Coordinates {

	int x;
	int y;

	public Coordinates(final String str) {

		Validate.notNull(str);
		Validate.matchesPattern(str, "[a-h][1-8]", "no valid coordinate");

		this.x = str.charAt(0) - 'a' + 1;
		this.y = Integer.parseInt(str.substring(1, 2));

	}

	public Coordinates(final int x, final int y) {

		Validate.inclusiveBetween(1, 8, x);
		Validate.inclusiveBetween(1, 8, y);

		this.x = x;
		this.y = y;
	}

	public Coordinates(final char x, final int y) {

		Validate.inclusiveBetween(97, 105, x);
		Validate.inclusiveBetween(1, 8, y);

		this.x = x - 'a' + 1;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean equals(final Coordinates target, final int x, final int y) {

		if (!(getX() + x == target.getX()))
			return false;

		if (!(getY() + y == target.getY()))
			return false;

		return true;

	}

	public boolean equals(final int x, final int y) {
		return this.x == x && this.y == y;
	}

	public Coordinates getIncrementXY(final int x, final int y) {
		return new Coordinates(this.x + x, this.y + y);
	}

	@Override
	public String toString() {
		return "" + Character.forDigit(this.x + 9, 20) + this.y;
	}

}
