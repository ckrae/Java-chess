package ckrae.chess;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.Validate;

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

			System.out.println(move);
			System.out.println(start.equals(target, 0, 1));
			System.out.println(!board.isOccupied(target));

			if (start.equals(target, 0, 1) && !board.isOccupied(target))
				return true;

			if (start.getX() == target.getX() + 1 && start.getY() == target.getY() - 1 && board.isOccupied(target)
					&& !board.isOwner(player, target))
				return true;

			if (start.getX() == target.getX() - 1 && start.getY() == target.getY() - 1 && board.isOccupied(target)
					&& !board.isOwner(player, target))
				return true;

			return false;

		}

		if (this.getColor() == Color.BLACK) {

			if (start.getX() == target.getX() && start.getY() == target.getY() + 1 && !board.isOccupied(target))
				return true;

			if (start.getX() == target.getX() + 1 && start.getY() == target.getY() + 1 && board.isOccupied(target)
					&& !board.isOwner(player, target))
				return true;

			if (start.getX() == target.getX() - 1 && start.getY() == target.getY() + 1 && board.isOccupied(target)
					&& !board.isOwner(player, target))
				return true;

		}

		return false;
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
