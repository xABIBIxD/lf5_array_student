package gameOfLife;

import java.util.Random;

public class GameOfLife {
	private char[][] petrischale = new char[12][12];
	private boolean simulationOver = false;
	
	public void initializeBoard(int number) {
		createEmptyPetrischale();		
		fillPetrischale(number);
	}

	private void createEmptyPetrischale() {
		for(int i= 0; i<petrischale.length; i++){
			for(int j = 0; j<petrischale[i].length; j++){
				petrischale[i][j] = ' ';
			}
		}
	}

	public char[][] getBoard() {
		char[][] result = new char[petrischale.length-2][petrischale[0].length-2];
		for(int row = 1; row<=petrischale.length-2; row++){
			for(int column = 1; column<=petrischale[0].length-2; column++){
				result[row-1][column-1]=petrischale[row][column];
			}
		}
		return result;
	}

	public boolean setAliveCell(int i, int j) {
		if(cellDead(i, j)){
			petrischale[i][j] ='O';
			return true;
		}
		return false;
	}

	public char getCellStatus(int i, int j) {
		return petrischale[i][j];
	}

	private void fillPetrischale(int number) {
		Random generator = new Random();
		int counter = 0;
		int i = 0;
		int j = 0;
		while(counter < number){
			do{
				i = generator.nextInt(petrischale.length-2)+1;
				j = generator.nextInt(petrischale.length-2)+1;
			}while(setAliveCell(i, j)==false);
			counter++;
		}
	}

	private boolean cellDead(int i, int j) {
		return petrischale[i][j]==' ';
	}

	public int getAliveCells() {
		int number = 0;
		for(int i= 1; i<petrischale.length-1; i++){
			for(int j = 0; j<petrischale[i].length-1; j++){
				if(petrischale[i][j] == 'O'){
					number++;
				}
			}
		}
		return number;
	}

	public void simulate() {
		char[][] newPetrischale = copyPetrischale();
		for(int i = 1; i<=10; i++){
			for(int j=1; j<=10; j++){
				newPetrischale[i][j]= simulateCell(i,j);
			}
		}
		simulationOver= sameBoard(petrischale, newPetrischale);
		petrischale = newPetrischale;
	}
	
	private boolean sameBoard(char[][] boardOne, char[][] boardTwo){
		for(int i = 0; i<boardOne.length; i++){
			for(int j = 0; j<boardOne[i].length; j++){
				if(boardOne[i][j]!= boardTwo[i][j]){
					return false;
				}
			}
		}
		return true;
	}
	
	public char simulateCell(int i, int j){
		int number = getNeigbourNumber(i,j);
		if(number <2||number > 3){
			return ' ';
		}
		if (number == 3 && petrischale[i][j]==' '){
			return 'O';
			
		}
		return petrischale[i][j];
	}

	private int getNeigbourNumber(int i, int j) {
		int number = 0;
		if(petrischale[i-1][j-1]=='O')
			number++;
		if(petrischale[i-1][j]=='O')
			number++;
		if(petrischale[i-1][j+1]=='O')
			number++;
		if(petrischale[i][j-1]=='O')
			number++;
		if(petrischale[i][j+1]=='O')
			number++;
		if(petrischale[i+1][j-1]=='O')
			number++;
		if(petrischale[i+1][j]=='O')
			number++;
		if(petrischale[i+1][j+1]=='O')
			number++;
		return number;
	}

	private char[][] copyPetrischale() {
		char[][] board = new char[12][12];
		for(int i= 0; i<petrischale.length; i++){
			for(int j = 0; j<petrischale[i].length; j++){
				board [i][j] = petrischale[i][j];
			}
		}
		return board;
	}

	public boolean simulationOver() {
		if(this.getAliveCells()==0 || this.simulationOver)
			return true;
		return false;
	}
}
