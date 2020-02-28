package threeWins;

public class ThreeWins {
	
	private static final String EMPTY = " ";
	private static final String GAME_PIECE_PLAYER_1 = "O";
	private static final String GAME_PIECE_PLAYER_2 = "X";
	
	private String[][] matchfield;
	
	/**
	 * Constructor.
	 */
	public ThreeWins() {}
	
	/**
	 * Constructor.
	 * @param height	The height of the matchfield.
	 * @param width		The width of the matchfield.
	 */
	public ThreeWins(int height, int width) {
		initializeMatchfield(height, width);
	}
	
	/**
	 * Gets the current matchfield.
	 * @return The current matchfield.
	 */
	public String[][] getMatchfield() {
		return matchfield;
	}
	
	/**
	 * Changes the current matchfield.
	 * @param matchfield	The new matchfield.
	 */
	public void setMatchfield(String[][] matchfield) {
		this.matchfield = matchfield;
	}

	/**
	 * A player throws in a game piece.
	 * @param player	The players number.
	 * @param column	The column, the player wants to throw the piece in.
	 * @return	true:	The game piece was thrown in.
	 * 			false:	The game piece couldn't be thrown in.
	 * @throws IllegalArgumentException	Either the players number or the column number is not valid.
	 */
	public boolean placeGamePiece(int player, int column) {
		// Check for invalid parameters.
		if (player < 1 || player > 2 || column < 1 || column > matchfield[0].length) {
			throw new IllegalArgumentException();
		}
		// Check whether there is still space in the column.
		int field;
		for (field = matchfield.length; field > 0 ; field--) {
			if (matchfield[field - 1][column - 1].equals(EMPTY)) {
				break;
			}
		}
		// There is no place in the column any more.
		if (field == 0) {
			return false;
		}
		// Setting the game piece in the column.
		else {
			matchfield[field - 1][column - 1] = getGamePiece(player);
			return true;
		}
	}

	/**
	 * Check whether the game is over.
	 * @return	true: 	The game is over.
	 * 			false:	The game is still running.
	 */
	public boolean checkEndOfGame() {
		for (int i = 0; i < matchfield.length; i++) {
			for (int j = 0; j < matchfield[i].length; j++) {
				if (matchfield[i][j].equals(EMPTY)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Determines the players final score.
	 * @param player	The players number.
	 * @return	The players final score.
	 */
	public int getFinalScore(int player) {
		return getFinalScoreColumnsOnly(player) + getFinalScoreRowsOnly(player);
	}
	
	/**
	 * Determines the players final score from the rows.
	 * @param player	The players number.
	 * @return	The players final score from the rows.
	 */
	public int getFinalScoreRowsOnly(int player) {
		int score = 0;
		int counter = 0;
		String playerChar = getGamePiece(player);
		for (int i = 0; i < matchfield.length; i++) {
			counter = 0;
			for (int j = 0; j < matchfield[i].length; j++) {
				if (matchfield[i][j].equals(playerChar)) {
					counter++;
					if (counter == 3) {
						score++;
					}
				}
				else {
					counter = 0;
				}
			}
		}
		return score;
	}


	/**
	 * Determines the players final score from the columns.
	 * @param player	The players number.
	 * @return	The players final score from the columns.
	 */
	public int getFinalScoreColumnsOnly(int player) {
		int score = 0;
		int counter = 0;
		String playerChar = getGamePiece(player);
		for (int j = 0; j < matchfield[0].length; j++) {
			counter = 0;
			for (int i = 0; i < matchfield.length; i++) {
				if (matchfield[i][j].equals(playerChar)) {
					counter++;
					if (counter == 3) {
						score++;
					}
				}
				else {
					counter = 0;
				}
			}
		}
		return score;
	}
	
	/**
	 * Gets the players game piece.
	 * @param player	The players number.
	 * @return	The players game piece.
	 */
	private String getGamePiece(int player) {
		String zeichen;
		if (player == 1) {
			zeichen = GAME_PIECE_PLAYER_1;
		}
		else {
			zeichen = GAME_PIECE_PLAYER_2;
		}
		return zeichen;
	}

	/**
	 * Initializes the matchfield.
	 * @param height	The height of the matchfield.
	 * @param width		The width of the matchfield.
	 */
	private void initializeMatchfield(int height, int width) {
		matchfield = new String[height][width];
		for (int i = 0; i < matchfield.length; i++) {
			for (int j = 0; j < matchfield[i].length; j++) {
				matchfield[i][j] = EMPTY;
			}
		}
	}

}
