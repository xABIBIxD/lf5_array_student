**Lösung Game of Life**

```
package gameOfLife;

import java.util.Random;

public class GameOfLife {
	private char[][] petrischale = new char[12][12];
	private boolean simulationOver = false;

	public void initializeBoard(int number) throws RuntimeException{
		if(number<0 || number>100){
			throw new RuntimeException("Die Anzahl der Zellen muss im Intervall 1 bis 100 liegen!");
		}
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

	public boolean setAliveCell(int row, int col) {
		if(cellDead(row, col)){
			petrischale[row][col] ='O';
			return true;
		}
		return false;
	}

	public char getCellStatus(int row, int col) {
		return petrischale[row][col];
	}

	private void fillPetrischale(int number) {
		Random generator = new Random();
		int counter = 0;
		int row = 0;
		int col = 0;
		while(counter < number){
			do{
				row = generator.nextInt(petrischale.length-2)+1;
				col = generator.nextInt(petrischale.length-2)+1;
			}while(setAliveCell(row, col)==false);
			counter++;
		}
	}

	private boolean cellDead(int row, int col) {
		return petrischale[row][col]==' ';
	}

	public int getAliveCells() {
		int number = 0;
		for(int row= 1; row<petrischale.length-1; row++){
			for(int col = 0; col<petrischale[row].length-1; col++){
				if(petrischale[row][col] == 'O'){
					number++;
				}
			}
		}
		return number;
	}

	public void simulate() {
		char[][] newPetrischale = copyPetrischale();
		for(int row = 1; row<=10; row++){
			for(int col=1; col<=10; col++){
				newPetrischale[row][col]= simulateCell(row,col);
			}
		}
		simulationOver= sameBoard(petrischale, newPetrischale);
		petrischale = newPetrischale;
	}

	private boolean sameBoard(char[][] boardOne, char[][] boardTwo){
		for(int row = 0; row<boardOne.length; row++){
			for(int col = 0; col<boardOne[row].length; col++){
				if(boardOne[row][col]!= boardTwo[row][col]){
					return false;
				}
			}
		}
		return true;
	}

	public char simulateCell(int row, int col){
		int number = getNeigbourNumber(row,col);
		if(number <2||number > 3){
			return ' ';
		}
		if (number == 3 && petrischale[row][col]==' '){
			return 'O';

		}
		return petrischale[row][col];
	}

	private int getNeigbourNumber(int row, int col) {
		int number = 0;
		if(petrischale[row-1][col-1]=='O')
			number++;
		if(petrischale[row-1][col]=='O')
			number++;
		if(petrischale[row-1][col+1]=='O')
			number++;
		if(petrischale[row][col-1]=='O')
			number++;
		if(petrischale[row][col+1]=='O')
			number++;
		if(petrischale[row+1][col-1]=='O')
			number++;
		if(petrischale[row+1][col]=='O')
			number++;
		if(petrischale[row+1][col+1]=='O')
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
```