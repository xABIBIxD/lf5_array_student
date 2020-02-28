package threeWins;

import java.util.Scanner;

public class ThreeWinsUI {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Creating the matchfield.
		System.out.print("Wie viele Röhren gibt es? ");
		int columns = scanner.nextInt();
		System.out.print("Wie viele Plätze gibt es pro Röhre? ");
		int rows = scanner.nextInt();
		ThreeWins game = new ThreeWins(rows, columns);
		displayMatchfield(game.getMatchfield());
		// Playing the game.
		boolean loop = true;
		boolean ok = true;
		do {
			for (int player = 1; player <= 2; player++) {
				do {
					System.out.print("Spieler " + player + ", in welche Röre soll die Kugel geworfen werden? ");
					int target = scanner.nextInt();
					try {
						ok = game.placeGamePiece(player, target);
					} catch (IllegalArgumentException e) {
						System.out.println("Wähle eine vorhandene Röhre.");
						ok = false;
					}
				} while (ok == false);
				displayMatchfield(game.getMatchfield());
				loop = !game.checkEndOfGame();
				if (loop == false) {
					break;
				}
			}
		} while (loop == true);
		// Evaluating the result.
		System.out.println("Das Spiel ist beendet!");
		int pointsPlayer1 = game.getFinalScore(1);
		int pointsPlayer2 = game.getFinalScore(2);
		System.out.println("Das Ergebnis lautet " + pointsPlayer1 + ":" + pointsPlayer2 + ".");
	}
	
	/**
	 * Displays the matchfield on the console.
	 * @param matchfield	The matchfield to display.
	 */
	private static void displayMatchfield(String[][] matchfield) {
		for (int i = 0; i < matchfield.length; i++) {
			System.out.print(" | ");
			for (int j = 0; j < matchfield[i].length; j++) {
				System.out.print(matchfield[i][j] + " | ");
			}
			System.out.println();
		}
	}

}
