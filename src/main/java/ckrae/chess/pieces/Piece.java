package ckrae.chess.pieces;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.Validate;

import ckrae.chess.Board;
import ckrae.chess.Color;
import ckrae.chess.Coordinates;
import ckrae.chess.Move;

/**
 * A chess piece.
 *
 */
public abstract class Piece {

	/**
	 * Defines the color of this piece.
	 */
	private final Color color;

	/**
	 * Create a new piece.
	 *
	 * @param color The color of this piece
	 */
	protected Piece(final Color color) {

		Validate.notNull(color);

		this.color = color;

	}

	/**
	 * Return if a specified move is possible with this kind of piece.
	 *
	 * @param move
	 * @param board
	 * @return true if the move is legal
	 */
	public abstract boolean isLegal(Move move, Board board);

	/**
	 * Get all target fields that are reachable by this piece
	 *
	 * @param start
	 * @param board
	 * @return
	 */
	public Collection<Move> getLegalMoves(final Coordinates start, final Board board) {

		final Collection<Move> res = new ArrayList<>();

		for (int x = 1; x <= Board.SIZE; x++) {
			for (int y = 1; y <= Board.SIZE; y++) {
				final Coordinates target = new Coordinates(x, y);
				final Move move = new Move(start, target);
				if (isLegal(move, board)) {
					res.add(move);
				}
			}
		}

		return res;

	}

	/**
	 * Return the color of this piece.
	 *
	 * @return the color of this piece
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Return the type of this piece.
	 *
	 * @return a string of size one
	 */
	public abstract PieceType getType();

}
