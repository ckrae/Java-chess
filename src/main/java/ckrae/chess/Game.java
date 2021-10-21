package ckrae.chess;

import org.apache.commons.lang3.Validate;

import ckrae.chess.ai.AIPlayer;
import ckrae.chess.ai.MinMaxAlgorithm;

public class Game {

	private final Player playerWhite;
	private final Player playerBlack;
	private Player activePlayer;

	private final Board board;

	public Game() {

		this.playerWhite = new ConsolePlayer(Color.WHITE);
		this.playerBlack = new AIPlayer(Color.BLACK, new MinMaxAlgorithm());
		this.board = Board.chess();

	}

	/**
	 * Start the game loop.
	 */
	public void loop() {

		Validate.notNull(this.playerWhite, "player white is null");
		Validate.notNull(this.playerBlack, "player black is null");
		Validate.notNull(this.board, "game board is null");

		this.activePlayer = this.playerWhite;
		while (true) {

			nextTurn();

			if (winCondition()) {
				break;
			}

			nextPlayer();

		}

		ConsolePlayer.printBoard(this.board);
		System.out.println();
		System.out.println(this.activePlayer.getColor() + " won the game!");

	}

	/**
	 * Perform one turn of the game loop
	 * 
	 */
	private void nextTurn() {

		Validate.notNull(this.activePlayer);
		Validate.validState(this.activePlayer.equals(this.playerWhite) || this.activePlayer.equals(this.playerBlack));

		final Move move = this.activePlayer.turn(this.board);
		this.board.move(move);

	}

	/**
	 * Check if the active player reached a win condition.
	 * 
	 * @return
	 */
	private boolean winCondition() {
		return this.board.winCondition(this.activePlayer);
	}

	/**
	 * Set the next player as active player
	 */
	private void nextPlayer() {

		if (this.activePlayer == this.playerWhite) {
			this.activePlayer = this.playerBlack;
		} else {
			this.activePlayer = this.playerWhite;
		}
	}

	/**
	 * Get the game board.
	 *
	 * @return board of this game
	 */
	public Board getBoard() {
		return this.board;
	}

}
