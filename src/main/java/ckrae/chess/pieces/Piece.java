package ckrae.chess.pieces;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.Validate;

import ckrae.chess.Board;
import ckrae.chess.Color;
import ckrae.chess.Coordinates;
import ckrae.chess.Move;
import ckrae.chess.Player;

public abstract class Piece {

	public Player player;

	public Piece(Player player) {

		Validate.notNull(player);

		this.player = player;
	}

	public Player getPlayer() {

		return this.player;
	}

	public Color getColor() {
		return this.getPlayer().getColor();
	}

	public abstract boolean isLegal(Move move, Board board);

	public Collection<Coordinates> getLegalTargets(Coordinates start, Board board) {

		Collection<Coordinates> res = new ArrayList<>();

		for (int x = 0; x <= 8; x++) {
			for (int y = 0; y <= 8; y++) {
				Coordinates target = new Coordinates(x, y);
				if (isLegal(new Move(start, target), board))
					res.add(target);
			}
		}

		return res;

	}

	public String getLetter() {

		return "X";
	}

}
