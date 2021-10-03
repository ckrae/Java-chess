package ckrae.chess;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println("Press any key to start a chess game");
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();

		Game game = new Game();
		game.loop();

	}

}
