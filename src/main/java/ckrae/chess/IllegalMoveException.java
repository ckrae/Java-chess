package ckrae.chess;

public class IllegalMoveException extends RuntimeException {

	public IllegalMoveException(String errorMessage) {
		super(errorMessage);
	}

}
