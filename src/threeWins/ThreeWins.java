package threeWins;

public class ThreeWins {

    /**
     * Constructor.
     */
    public ThreeWins() {
    }

    /**
     * Constructor.
     *
     * @param height The height of the matchfield.
     * @param width  The width of the matchfield.
     */
    public ThreeWins(int height, int width) {

    }

    /**
     * Gets the current matchfield.
     *
     * @return The current matchfield.
     */
    public String[][] getMatchfield() {
        return null;
    }

    /**
     * Changes the current matchfield.
     *
     * @param matchfield The new matchfield.
     */
    public void setMatchfield(String[][] matchfield) {

    }

    /**
     * A player throws in a game piece.
     *
     * @param player The players number.
     * @param column The column, the player wants to throw the piece in.
     * @return true:	The game piece was thrown in.
     * false:	The game piece couldn't be thrown in.
     * @throws IllegalArgumentException Either the players number or the column number is not valid.
     */
    public boolean placeGamePiece(int player, int column) {
        return true;
    }

    /**
     * Check whether the game is over.
     *
     * @return true: The game is over.
     * false:	The game is still running.
     */
    public boolean checkEndOfGame() {
        return false;
    }

    /**
     * Determines the players final score.
     *
     * @param player The players number.
     * @return The players final score.
     */
    public int getFinalScore(int player) {
        return 0;
    }
}
