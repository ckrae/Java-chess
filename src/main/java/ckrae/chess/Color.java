package ckrae.chess;

public enum Color {

	WHITE("\033[0;36m"), BLACK("\033[0;35m");

	private final String code;

	Color(final String code) {
		this.code = code;
	}

	public String code() {
		return this.code;
	}

	/**
	 * Returns the opposite color. It returns BLACK if given WHITE and WHITE if
	 * given BLACK.
	 *
	 * @return opposite color
	 */
	public Color opposite() {
		if (this == WHITE)
			return BLACK;
		return WHITE;
	}
}