package ckrae.chess;

import org.apache.commons.lang3.Validate;

public class Coordinates {

	int x;
	int y;

	public Coordinates(String str) {

		Validate.notNull(str);
		Validate.matchesPattern(str, "[a-h][1-8]", "no valid coordinate");

		this.x = str.charAt(0) - 'a' + 1;
		this.y = Integer.parseInt(str.substring(1, 2));

	}

	public Coordinates(int x, int y) {

		Validate.inclusiveBetween(1, 8, x);
		Validate.inclusiveBetween(1, 8, y);

		this.x = x;
		this.y = y;
	}

	public Coordinates(char x, int y) {

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

	public boolean equals(Coordinates target, int x, int y) {

		if (!(this.getX() + x == target.getX()))
			return false;

		if (!(this.getY() + y == target.getY()))
			return false;

		return true;

	}

	public Coordinates getIncrementX(int x) {
		return new Coordinates(this.x + x, this.y);
	}

	public Coordinates getIncrementY(int y) {
		return new Coordinates(this.x, this.y + y);
	}

	public Coordinates getIncrementXY(int x, int y) {
		return new Coordinates(this.x + x, this.y + y);
	}

	@Override
	public String toString() {
		return "Coordinates [x=" + x + ", y=" + y + "]";
	}

}
