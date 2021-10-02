package ckrae.chess;

import org.apache.commons.collections4.map.MultiKeyMap;
import org.apache.commons.lang3.Validate;

public class Board {

	public static int size = 8;

	public GameStatus status;

	private MultiKeyMap<Integer, Piece> map;

	public Board(Player white, Player black) {
		init(white, black);
		this.status = GameStatus.READY;
	}

	private void init(Player white, Player black) {

		Validate.notNull(white);
		Validate.notNull(black);

		map = new MultiKeyMap<Integer, Piece>();
		setup(map, white, 2);
		setup(map, black, 7);

	}

	private void setup(MultiKeyMap<Integer, Piece> map, Player player, int y) {

		Validate.notNull(map);
		Validate.isTrue(y == 2 || y == 7);

		for (int x = 1; x <= 8; x++)
			map.put(x, y, new Pawn(player));

		if (y == 2)
			y = 1;
		else
			y = 8;

		map.put(1, y, new Rook(player));
		map.put(2, y, new Knight(player));
		map.put(3, y, new Bishop(player));
		map.put(4, y, new Queen(player));
		map.put(5, y, new King(player));
		map.put(6, y, new Bishop(player));
		map.put(7, y, new Knight(player));
		map.put(8, y, new Rook(player));

	}

	public void move(Player player, String x, String y) {
		move(player, new Move(x, y));
	}

	public void move(Player player, Move move) {

		Validate.notNull(move);
		Validate.notNull(player);
		Validate.isTrue(isOccupied(move.getStart()), "no piece on " + move.getStart());
		Validate.isTrue(this.isOwner(player, move.getStart()),
				" piece on " + move.getStart() + "can't move piece of another player");

		Piece piece = this.getPiece(move.getStart());
		if (!piece.isLegal(move, this))
			throw new IllegalMoveException("this move is illegal " + move);

		if (this.isOccupied(move.getTarget()) && this.getPiece(move.getTarget()) instanceof King)
			this.status = GameStatus.END;
		else
			this.status = GameStatus.RUNNING;

		this.map.removeMultiKey(move.getStart().getX(), move.getStart().getY());
		this.map.put(move.getTarget().getX(), move.getTarget().getY(), piece);

	}

	public boolean isOccupied(Coordinates coor) {

		return this.isOccupied(coor.getX(), coor.getY());
	}

	public boolean isOccupied(int x, int y) {

		return this.map.containsKey(x, y);
	}

	public boolean isOwner(Player player, Coordinates coor) {

		Validate.notNull(player);
		Validate.notNull(coor);

		if (!this.isOccupied(coor))
			return false;

		return this.getPiece(coor).getPlayer().equals(player);

	}

	public Piece getPiece(Coordinates coor) {

		Validate.notNull(coor);
		Validate.validState(this.isOccupied(coor));

		return getPiece(coor.getX(), coor.getY());
	}

	public Piece getPiece(int x, int y) {

		return this.map.get(x, y);
	}

	public boolean victory() {

		return this.status == GameStatus.END;
	}

	/*
	 * public Optional<Coordinates> getNextOccupiedTop(Coordinates coor) {
	 * 
	 * Validate.notNull(coor);
	 * 
	 * if (coor.getY() >= size) return Optional.empty();
	 * 
	 * for (int i = coor.getY() + 1; i <= size; i++) if
	 * (this.isOccupied(coor.getX(), i)) return Optional.of(new
	 * Coordinates(coor.getX(), i));
	 * 
	 * return Optional.empty(); }
	 * 
	 * public Optional<Coordinates> getNextOccupiedBot(Coordinates coor) {
	 * 
	 * Validate.notNull(coor);
	 * 
	 * if (coor.getY() <= 1) return Optional.empty();
	 * 
	 * for (int i = coor.getY() - 1; i >= 1; i--) if (this.isOccupied(coor.getX(),
	 * i)) return Optional.of(new Coordinates(coor.getX(), i));
	 * 
	 * return Optional.empty(); }
	 * 
	 * public Optional<Coordinates> getNextOccupiedRight(Coordinates coor) {
	 * 
	 * Validate.notNull(coor);
	 * 
	 * if (coor.getX() >= size) return Optional.empty();
	 * 
	 * for (int i = coor.getX() + 1; i <= size; i++) if (this.isOccupied(i,
	 * coor.getY())) return Optional.of(new Coordinates(i, coor.getY()));
	 * 
	 * return Optional.empty(); }
	 * 
	 * public Optional<Coordinates> getNextOccupiedLeft(Coordinates coor) {
	 * 
	 * Validate.notNull(coor);
	 * 
	 * if (coor.getX() <= 1) return Optional.empty();
	 * 
	 * for (int i = coor.getX() - 1; i >= 1; i--) if (this.isOccupied(i,
	 * coor.getY())) return Optional.of(new Coordinates(i, coor.getY()));
	 * 
	 * return Optional.empty(); }
	 */

}
