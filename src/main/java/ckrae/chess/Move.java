package ckrae.chess;

import org.apache.commons.lang3.Validate;

public class Move {

	private Coordinates start;
	private Coordinates target;

	public Move(Coordinates start, Coordinates target) {

		Validate.notNull(start);
		Validate.notNull(target);

		this.start = start;
		this.target = target;

	}

	public Move(String start, String target) {

		this(new Coordinates(start), new Coordinates(target));

	}

	public Coordinates getStart() {
		return start;
	}

	public Coordinates getTarget() {
		return target;
	}

	@Override
	public String toString() {
		return "Move [start=" + start + ", target=" + target + "]";
	}

}
