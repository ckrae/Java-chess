package ckrae.chess;

import org.apache.commons.lang3.Validate;

public class Move {

	private Coordinates start;
	private Coordinates target;

	public static Move parse(String str) {

		Validate.matchesPattern(str, "[a-h][1-8](.*?)[a-h][1-8]", "can not parse move from string " + str);

		String start = str.substring(0, 2);
		String target = str.substring(str.length() - 2);

		return new Move(start, target);

	}

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
