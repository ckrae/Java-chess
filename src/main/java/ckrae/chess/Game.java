package ckrae.chess;

import org.apache.commons.lang3.Validate;

public class Game {

	private Player playerWhite;
	private Player playerBlack;
	private Player activePlayer;

	private Board board;

	public void play() {

		Validate.notNull(playerWhite, "player white is null");
		Validate.notNull(playerBlack, "player black is null");
		Validate.notNull(board, "game board is null");

		activePlayer = playerWhite;
		while (!board.victory())
			activePlayer = nextTurn();

	}

	public Player nextTurn() {

		Validate.notNull(activePlayer);
		Validate.validState(activePlayer.equals(playerWhite) || activePlayer.equals(playerBlack));

		activePlayer.turn(board);

		if (activePlayer == playerWhite)
			return playerBlack;
		return playerWhite;

	}

}
