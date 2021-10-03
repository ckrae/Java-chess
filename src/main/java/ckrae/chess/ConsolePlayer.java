package ckrae.chess;

import java.util.Scanner;

import org.apache.commons.lang3.Validate;

public class ConsolePlayer extends Player {

	public ConsolePlayer(Color color) {
		super(color);
	}

	public void turn(Board board) {

		Validate.notNull(board);

		printBoard(board);

		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();

		Move move = null;
		try {

			move = Move.parse(input);

		} catch (IllegalArgumentException e) {
			System.out.println("invalid input");
			System.out.println("please use the form: a1 to a2");
			System.out.println("");
			this.turn(board);
		}

		try {

			board.move(this, move);
			System.out.println("");

		} catch (IllegalArgumentException e) {

			System.out.println("You are not allowed to do this move");
			System.out.println("Try a diffrent one");
			System.out.println("");
			this.turn(board);

		} catch (IllegalMoveException e) {
			System.out.println("You are not allowed to do this move");
			System.out.println("Try a diffrent one");
			System.out.println("");
			this.turn(board);

		}

	}

	public static void printBoard(Board board) {

		for (int y = Board.size; y >= 1; y--) {

			System.out.print(y + "| ");

			for (int x = 1; x <= Board.size; x++) {
				if (board.isOccupied(x, y))
					System.out.print(board.getPiece(x, y).getLetter() + " ");
				else
					System.out.print("- ");
			}
			System.out.println();
		}

		System.out.print("   ");
		for (int x = 1; x <= Board.size; x++)
			System.out.print("\u2013 ");
		System.out.println();

		System.out.print("   ");
		for (int x = 1; x <= Board.size; x++)
			System.out.print(x + " ");
		System.out.println();

	}
}
