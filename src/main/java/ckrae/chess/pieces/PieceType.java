package ckrae.chess.pieces;

public enum PieceType {
	PAWN("P", 1), BISHOP("B", 3), KNIGHT("H", 3), ROOK("R", 5), QUEEN("Q", 9), KING("K", 100);

	/**
	 * Textual representation of this piece during a console game.
	 */
	public final String letter;

	/**
	 * Value for this piece in the evaluation of the game state.
	 **/
	public final int score;

	PieceType(final String letter, final int score) {
		this.letter = letter;
		this.score = score;
	}

}