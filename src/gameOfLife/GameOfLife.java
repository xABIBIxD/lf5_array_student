package gameOfLife;

public class GameOfLife {

	public void initializeBoard(int number) {

	}

	public char[][] getBoard() {
		char[][] result =
				{
						{'O', 'O', 'O','O','O','O','O','O','O','O'},
						{'O', 'O', 'O','O','O','O','O','O','O','O'},
						{'O', 'O', 'O','O','O','O','O','O','O','O'},
						{'O', 'O', 'O','O','O','O','O','O','O','O'},
						{'O', 'O', 'O','O','O','O','O','O','O','O'},
						{'O', 'O', 'O','O','O','O','O','O','O','O'},
						{'O', 'O', 'O','O','O','O','O','O','O','O'},
						{'O', 'O', 'O','O','O','O','O','O','O','O'},
						{'O', 'O', 'O','O','O','O','O','O','O','O'},
						{'O', 'O', 'O','O','O','O','O','O','O','O'}
				};
		return result;
	}

	public boolean setAliveCell(int row, int col) {
		return true;
	}

	public char getCellStatus(int row, int col) {
		return '-';
	}

	private boolean cellDead(int row, int col) {
		return true;
	}

	public int getAliveCells() {
		return 0;
	}

	public void simulate() {

	}

	public boolean simulationOver() {
		return false;
	}
}