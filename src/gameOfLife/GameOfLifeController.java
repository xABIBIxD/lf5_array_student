package gameOfLife;

import Prog1Tools.GameEngine;
import Prog1Tools.GameModel;

public class GameOfLifeController implements GameModel {
	private GameOfLife game;

	public GameOfLifeController(){
		game= new GameOfLife();
		game.initializeBoard(30);
	}

	public int rows(){
		return game.getBoard().length;
	}
	
	public int columns(){
		char[][] board = game.getBoard();
		return board[0].length;
	}
	
	public String getMessages() {
		return
	"Spiel das Spiel des Lebens.\n" +
	"Schaue, was passiert,\n" +
	"Wenn das Leben eben\n" +
	"vor sich hinmutiert.";
	}
	
	public String getGameName() {
		return "Game of Life";
	}
	
	public String getFireLabel() {
		return "Naechste Generation";
	}
	
	public char getContent(int row, int col) {
		return game.getCellStatus(row+1, col+1);
	}
	
	public void buttonPressed(int row, int col) {
		game.setAliveCell(row, col);
	}
	
	public void firePressed() {
		game.simulate();
		if(game.simulationOver())
			System.exit(0);
	}
	
	public static void main(String[] args) {
		new GameEngine(
				new GameOfLifeController());
	}
}
