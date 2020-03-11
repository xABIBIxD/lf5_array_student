package gameOfLife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class GameOfLifeTest {

	private GameOfLife game;
	
	@BeforeEach
	void setUp() {
		game = new GameOfLife();
	}

	@Test
	void givenNoBoard_WhenInitializeBoardWithSpaces_ThenReturnEmptyBoard() {
		game.initializeBoard(0);
		char[][] board= getEmptyArray(10, 10);
		assertArrayEquals(board, game.getBoard());
	}

	@Test
	void givenEmptyBoard_WhenSetAliveCellAtOneOne_ThenReturnOAtOneOne(){
		game.initializeBoard(0);
		game.setAliveCell(1,1);
		assertEquals('O', game.getCellStatus(1, 1));
	}

	@Test
	public void givenBoardWith2AliveCells_WhenGetAliveCells_ThenReturn2(){
		game.initializeBoard(0);
		game.setAliveCell(1,1);
		game.setAliveCell(1,2);
		assertEquals(2, game.getAliveCells());
	}

	@Test
	void givenBoardWith5AliveCells_WhenGetBoard_ThenReturnRightBoard(){
		game.initializeBoard(0);
		game.setAliveCell(1,1);
		game.setAliveCell(1,10);
		game.setAliveCell(10,1);
		game.setAliveCell(10,10);
		game.setAliveCell(5,5);

		char[][] testBoard = getEmptyArray(10, 10);
		testBoard[0][0]='O';
		testBoard[0][9]='O';
		testBoard[4][4]='O';
		testBoard[9][9]='O';
		testBoard[9][0]='O';

		assertArrayEquals(testBoard, game.getBoard());
	}

	@ParameterizedTest(name = "number={0}")
	@CsvSource(value = {"1", "5", "10", "20", "30", "45", "50", "60"})
	public void givenStartOfGame_WhenInitializedBoard_ThenReturnRightNumberOfAliveCells(int number){
		game.initializeBoard(number);
		assertEquals(number, game.getAliveCells());
	}
	
	@Test
	public void givenCellWithNoNeighbour_WhenSimulate_ThenCellIsDead(){
		game.initializeBoard(0);
		game.setAliveCell(2,2);
		game.simulate();
		assertEquals(' ', game.getCellStatus(2,2));
	}
	
	@Test
	public void givenCellWithOneNeighbour_WhenSimulate_ThenCellIsDead(){
		game.initializeBoard(0);
		game.setAliveCell(2,2);
		game.setAliveCell(2, 3);
		game.simulate();
		assertEquals(' ', game.getCellStatus(2,2));
	}

	@Test
	public void givenCellWithFourNeighbours_WhenSimulate_ThenCellIsDead(){
		game.initializeBoard(0);
		game.setAliveCell(2, 2);
		game.setAliveCell(2, 3);
		game.setAliveCell(3, 1);
		game.setAliveCell(3, 2);
		game.setAliveCell(3, 3);
		game.simulate();
		assertEquals(' ', game.getCellStatus(2,2));
	}
	
	@Test
	public void givenCellWithThreeNeighbours_WhenSimulate_ThenCellIsAlive(){
		game.initializeBoard(0);
		game.setAliveCell(2, 3);
		game.setAliveCell(3, 3);
		game.setAliveCell(3, 1);
		game.simulate();
		assertEquals('O', game.getCellStatus(2,2));
	}
	
	@Test
	public void givenAPopulation_WhenSimulate_ThenReturnTruePopulation(){
		game.initializeBoard(0);
		game.setAliveCell(1, 1);
		game.setAliveCell(1, 2);
		game.setAliveCell(2, 1);
		game.setAliveCell(3, 5);
		game.setAliveCell(3, 6);
		game.setAliveCell(6, 5);
		game.setAliveCell(7, 4);
		game.setAliveCell(7, 5);
		game.setAliveCell(7, 6);
		game.setAliveCell(8, 5);
		
		game.simulate();
				
		char[][] secondGeneration = getEmptyArray(10, 10);

		secondGeneration[0][0]= 'O';
		secondGeneration[0][1]= 'O';
		secondGeneration[1][0]= 'O';
		secondGeneration[1][1]= 'O';
		secondGeneration[5][3]= 'O';
		secondGeneration[5][4]= 'O';
		secondGeneration[5][5]= 'O';
		secondGeneration[6][3]= 'O';
		secondGeneration[6][5]= 'O';
		secondGeneration[7][3]= 'O';
		secondGeneration[7][4]= 'O';
		secondGeneration[7][5]= 'O';
		
		char[][] simulatedBoard = game.getBoard();
		assertArrayEquals(simulatedBoard, secondGeneration);		
	}
	
	@Test
	public void givenAllCellsAreDead_WhenSimulationOver_ThenReturnTrue(){
		game.initializeBoard(0);
		assertTrue(game.simulationOver());
	}

	@Test
	public void testSimulationOverSameBoard(){
		game.initializeBoard(0);
		game.setAliveCell(2, 2);
		game.setAliveCell(2, 3);
		game.setAliveCell(3, 1);
		game.setAliveCell(3, 4);
		game.setAliveCell(4, 2);
		game.setAliveCell(4, 4);
		game.setAliveCell(5, 3);
		game.simulate();
		assertTrue(game.simulationOver());		
	}

	private char[][] getEmptyArray(int rowNumber, int colNumber) {
		char[][] petrischale = new char[rowNumber][rowNumber];
		for(int i= 0; i<petrischale.length; i++){
			for(int j = 0; j<petrischale[i].length; j++){
				petrischale[i][j] = ' ';
			}
		}
		return petrischale;
	}
}
