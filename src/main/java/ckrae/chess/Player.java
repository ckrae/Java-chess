package ckrae.chess;

public abstract class Player {

	/**
	 * The color of this players pieces.
	 */
	private final Color color;

	/**
	 * @param color
	 */
	protected Player(final Color color) {
		this.color = color;
	}

	/**
	 * Let the player decide a move to perform, based on the given game board.
	 *
	 * @param board
	 * @return a move
	 */
	public abstract Move turn(Board board);

	/**
	 * Get the color of this player.
	 *
	 * @return the players color
	 */
	public Color getColor() {
		return this.color;
	}

}
