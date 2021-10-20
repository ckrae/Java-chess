package ckrae.chess;

import java.util.Scanner;

import org.apache.commons.lang3.Validate;

/**
 * Represents a human player that plays via the console
 *
 */
public class ConsolePlayer extends Player {

	public ConsolePlayer(final Color color) {
		super(color);
	}

	@Override
	public Move turn(final Board board) {

		Validate.notNull(board);

		printBoard(board);

		Move move = null;
		try {

			final Scanner scanner = new Scanner(System.in);
			final String input = scanner.nextLine();
			move = Move.parse(input);

		} catch (final IllegalArgumentException e) {
			System.out.println("invalid input");
			System.out.println("please use the form: a1 to a2");
			System.out.println("");
		}

		if (move == null)
			return turn(board);

		try {

			Board.copy(board).move(move);
			System.out.println("");

		} catch (final IllegalMoveException e) {
			System.out.println(e.getMessage());
			System.out.println("");
			return turn(board);

		}
		return move;

	}

	public static void printBoard(final Board board) {

		for (int y = Board.SIZE; y >= 1; y--) {

			System.out.print(y + "| ");

			for (int x = 1; x <= Board.SIZE; x++) {
				if (board.isOccupied(x, y)) {
					System.out.print(board.getPiece(x, y).getColor().code());
					System.out.print(board.getPiece(x, y).getType().letter + " ");
					System.out.print("\033[0m");
				} else {
					System.out.print("- ");
				}
			}
			System.out.println();
		}

		System.out.print("   ");
		for (int x = 1; x <= Board.SIZE; x++) {
			System.out.print("\u2013 ");
		}
		System.out.println();

		System.out.print("   ");
		for (int x = 1; x <= Board.SIZE; x++) {
			System.out.print(Character.forDigit(x + 9, 20) + " ");
		}
		System.out.println();

	}

}
