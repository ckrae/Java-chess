package ckrae.chess.pieces;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.Validate;

import ckrae.chess.Board;
import ckrae.chess.Color;
import ckrae.chess.Coordinates;
import ckrae.chess.Move;
import ckrae.chess.Player;

public class Pawn extends Piece {

	public Pawn(Player player) {
		super(player);

	}

	@Override
	public Collection<Coordinates> getLegalTargets(Coordinates coor, Board board) {

		Validate.notNull(coor);
		Validate.notNull(board);

		if (this.getColor() == Color.WHITE)
			return this.getLegalTargetsWhite(coor, board);

		return this.getLegalTargetsBlack(coor, board);

	}

	public boolean isLegal(Move move, Board board) {

		Validate.notNull(move);
		Validate.notNull(board);
		Validate.validState(this.getColor() == Color.WHITE || this.getColor() == Color.BLACK);

		Coordinates start = move.getStart();
		Coordinates target = move.getTarget();

		if (this.getColor() == Color.WHITE) {

			// one step forward
			if (start.equals(target, 0, 1) && !board.isOccupied(target))
				return true;

			// two step forward first move only
			if (start.getY() == 2 && start.equals(target, 0, 2) && !board.isOccupied(target))
				return true;

			// diagonal capturing
			if (start.equals(target, 1, 1) && board.isOccupied(target) && !board.isOwner(player, target))
				return true;

			// diagonal capturing
			if (start.equals(target, -1, 1) && board.isOccupied(target) && !board.isOwner(player, target))
				return true;

			return false;

		}

		if (this.getColor() == Color.BLACK) {

			// one step forward
			if (start.equals(target, 0, -1) && !board.isOccupied(target))
				return true;

			// two step forward
			if (start.getY() == 7 && start.equals(target, 0, -2) && !board.isOccupied(target))
				return true;

			// diagonal capturing
			if (start.equals(target, 1, -1) && board.isOccupied(target) && !board.isOwner(player, target))
				return true;

			// diagonal capturing
			if (start.equals(target, -1, -1) && board.isOccupied(target) && !board.isOwner(player, target))
				return true;

			System.out.println(start.equals(target, 1, -1));
			System.out.println(board.isOccupied(target));
			System.out.println(board.isOwner(player, target));
			System.out.println(move);

		}

		return false;
	}

	@Override
	public String getLetter() {
		return "P";
	}

	private Collection<Coordinates> getLegalTargetsWhite(Coordinates coor, Board board) {

		Collection<Coordinates> res = new ArrayList<>();

		Coordinates candidate = coor.getIncrementY(1);
		if (!board.isOccupied(candidate))
			res.add(candidate);

		try {
			candidate = coor.getIncrementXY(1, 1);
			if (board.isOccupied(candidate) && !board.isOwner(player, candidate))
				res.add(candidate);
		} catch (IllegalArgumentException e) {
		}

		try {
			candidate = coor.getIncrementXY(-1, 1);
			if (board.isOccupied(candidate) && !board.isOwner(player, candidate))
				res.add(candidate);
		} catch (IllegalArgumentException e) {
		}

		return res;
	}

	private Collection<Coordinates> getLegalTargetsBlack(Coordinates coor, Board board) {

		Collection<Coordinates> res = new ArrayList<>();

		Coordinates candidate = coor.getIncrementY(-1);
		if (!board.isOccupied(candidate))
			res.add(candidate);

		try {
			candidate = coor.getIncrementXY(1, -1);
			if (board.isOccupied(candidate) && !board.isOwner(player, candidate))
				res.add(candidate);
		} catch (IllegalArgumentException e) {
		}

		try {
			candidate = coor.getIncrementXY(-1, -1);
			if (board.isOccupied(candidate) && !board.isOwner(player, candidate))
				res.add(candidate);
		} catch (IllegalArgumentException e) {
		}

		return res;
	}

}
