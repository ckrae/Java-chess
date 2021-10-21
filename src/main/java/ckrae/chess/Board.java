package ckrae.chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.apache.commons.lang3.Validate;

import ckrae.chess.pieces.Bishop;
import ckrae.chess.pieces.King;
import ckrae.chess.pieces.Knight;
import ckrae.chess.pieces.Pawn;
import ckrae.chess.pieces.Piece;
import ckrae.chess.pieces.PieceType;
import ckrae.chess.pieces.Queen;
import ckrae.chess.pieces.Rook;

/**
 * The board defines where the pieces are placed.
 *
 */
public class Board {

	/**
	 * Size of the board.
	 */
	public static final int SIZE = 8;

	/**
	 * The game map.
	 */
	private final MultiKeyMap<Integer, Piece> map;

	/**
	 * The last move that was performed on this board.
	 */
	private Move lastMove;

	/**
	 * Create a empty game board.
	 *
	 * @return a new game board
	 */
	public static Board empty() {

		return new Board();
	}

	/**
	 * Create a game board with chess starting positions.
	 *
	 * @return a new game board
	 */
	public static Board chess() {

		final Board board = new Board();

		for (int x = 1; x <= SIZE; x++) {
			board.addPiece(x, 2, new Pawn(Color.WHITE));
			board.addPiece(x, SIZE - 1, new Pawn(Color.BLACK));
		}

		board.addPiece(1, 1, new Rook(Color.WHITE));
		board.addPiece(2, 1, new Knight(Color.WHITE));
		board.addPiece(3, 1, new Bishop(Color.WHITE));
		board.addPiece(4, 1, new Queen(Color.WHITE));
		board.addPiece(5, 1, new King(Color.WHITE));
		board.addPiece(6, 1, new Bishop(Color.WHITE));
		board.addPiece(7, 1, new Knight(Color.WHITE));
		board.addPiece(8, 1, new Rook(Color.WHITE));

		board.addPiece(1, 8, new Rook(Color.BLACK));
		board.addPiece(2, 8, new Knight(Color.BLACK));
		board.addPiece(3, 8, new Bishop(Color.BLACK));
		board.addPiece(4, 8, new Queen(Color.BLACK));
		board.addPiece(5, 8, new King(Color.BLACK));
		board.addPiece(6, 8, new Bishop(Color.BLACK));
		board.addPiece(7, 8, new Knight(Color.BLACK));
		board.addPiece(8, 8, new Rook(Color.BLACK));

		return board;
	}

	/**
	 * Create a game board that is a copy of another game board.
	 *
	 * @param board
	 * @return the new board
	 */
	public static Board copy(final Board board) {

		Validate.notNull(board);

		return new Board(board);
	}

	/**
	 * Constructor that creates a empty board.
	 */
	private Board() {
		this.map = new MultiKeyMap<>();
	}

	/**
	 * Constructor that create a board that is a copy of another board.
	 *
	 * @param board
	 */
	private Board(final Board board) {
		this();
		for (int x = 1; x <= SIZE; x++) {
			for (int y = 1; y <= SIZE; y++) {
				if (board.isOccupied(x, y)) {
					this.addPiece(x, y, board.getPiece(x, y));
				}
			}
		}
		this.lastMove = board.getLastMove();
	}

	/**
	 * Perform a move on this board.
	 *
	 * @param start  String that describes the starting position
	 * @param target String that describes the target position
	 */
	public void move(final String start, final String target) {
		move(new Move(start, target));
	}

	/**
	 * Perform a move on this board. Returns this board in the resulting state.
	 *
	 * @param move Move to perform
	 * @return this board
	 */
	public Board move(final Move move) {

		Validate.notNull(move);

		if (!isOccupied(move.getStart()))
			throw new IllegalMoveException("There is no piece on " + move.getStart());

		if (!isLegal(move))
			throw new IllegalMoveException("This move is illegal " + move);

		final Piece movingPiece = this.getPiece(move.getStart());
		movingPiece.performMove(move, this);

		this.lastMove = move;

		return this;
	}

	/**
	 * Add a piece to the board.
	 *
	 * @param coor
	 * @param piece
	 */
	public void addPiece(final Coordinates coor, final Piece piece) {

		Validate.notNull(coor);

		addPiece(coor.getX(), coor.getY(), piece);
	}

	/**
	 * Add a piece to the board.
	 *
	 * @param x
	 * @param y
	 * @param piece
	 */
	public void addPiece(final int x, final int y, final Piece piece) {

		Validate.notNull(piece);

		this.map.put(x, y, piece);
	}

	/**
	 * Remove a piece from the board.
	 *
	 * @param coor
	 */
	public void removePiece(final Coordinates coor) {

		Validate.notNull(coor);

		this.map.removeMultiKey(coor.getX(), coor.getY());
	}

	/**
	 * Get the piece of a field.
	 *
	 * @param coor
	 * @return the piece
	 */
	public Piece getPiece(final Coordinates coor) {

		Validate.notNull(coor);

		return getPiece(coor.getX(), coor.getY());
	}

	/**
	 * Get the piece of a field.
	 *
	 * @param x x coordinate of the field
	 * @param y y coordinate of the field
	 * @return the piece
	 */
	public Piece getPiece(final int x, final int y) {

		Validate.validState(this.isOccupied(x, y));

		return this.map.get(x, y);
	}

	/**
	 * Get all Pieces of a specific color that are still on the game board.
	 *
	 * @param color
	 * @return collection of pieces
	 */
	public Collection<Piece> getPieces(final Color color) {
		final Collection<Piece> res = new ArrayList<>();
		for (final Piece piece : this.map.values()) {
			if (piece.getColor() == color) {
				res.add(piece);
			}
		}

		return res;
	}

	/**
	 * Get all coordinates that are holding a piece of a specific color.
	 *
	 * @param color
	 * @return collection of coordinates
	 */
	public Collection<Coordinates> getCoordinates(final Color color) {

		final List<Coordinates> res = new ArrayList<>();
		for (final Entry<MultiKey<? extends Integer>, Piece> entry : this.map.entrySet()) {
			if (entry.getValue().getColor() == color) {
				final Integer x = entry.getKey().getKey(0);
				final Integer y = entry.getKey().getKey(1);
				res.add(new Coordinates(x, y));
			}
		}

		return res;
	}

	/**
	 * Get all moves that are legal for a given color.
	 *
	 * @param color
	 * @return collection of legal moves
	 */
	public Collection<Move> getLegalMoves(final Color color) {

		Validate.notNull(color);

		final Collection<Move> res = new ArrayList<>();
		for (final Coordinates start : getCoordinates(color)) {
			final Piece piece = this.getPiece(start);
			res.addAll(piece.getLegalMoves(start, this));
		}

		return res;
	}

	/**
	 * Check if a move is legal to perform on this board state.
	 * 
	 * @param move
	 * @return
	 */
	public boolean isLegal(final Move move) {

		Validate.notNull(move);

		if (!this.isOccupied(move.getStart()))
			return false;

		return this.getPiece(move.getStart()).isLegal(move, this);

	}

	/**
	 * Check if a position contains a game piece.
	 * 
	 * @param coor position
	 * @return true if game piece is present
	 */
	public boolean isOccupied(final Coordinates coor) {

		return this.isOccupied(coor.getX(), coor.getY());
	}

	/**
	 * Check if a position contains a game piece.
	 * 
	 * @param x value of position
	 * @param y value of position
	 * @return true if game piece is present
	 */
	public boolean isOccupied(final int x, final int y) {

		return this.map.containsKey(x, y);
	}

	public boolean isOwner(final Player player, final Coordinates coor) {

		Validate.notNull(player);

		return this.isOwner(player.getColor(), coor);

	}

	public boolean isOwner(final Color color, final Coordinates coor) {

		Validate.notNull(color);
		Validate.notNull(coor);

		if (!this.isOccupied(coor))
			return false;

		return this.getPiece(coor).getColor().equals(color);
	}

	public boolean isOwner(final Color color, final int x, final int y) {

		if (!this.isOccupied(x, y))
			return false;

		return this.getPiece(x, y).getColor().equals(color);
	}

	/**
	 * Check the player has won the game.
	 *
	 * @return true player of color won the game.
	 */
	public boolean winCondition(final Player activePlayer) {

		final Color color = activePlayer.getColor().opposite();
		for (final Piece piece : getPieces(color)) {
			if (piece.getType() == PieceType.KING)
				return false;
		}

		return true;
	}

	/**
	 * Get the last move that was performed on this game board. Return null if no
	 * move was performed on this board.
	 * 
	 * @return the last move.
	 */
	public Move getLastMove() {
		return this.lastMove;
	}

}
