package threeWins;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ThreeWinsTest {
	
	private ThreeWins threeWins;

	@BeforeEach
	void setUp() throws Exception {
		threeWins = new ThreeWins();
	}

    @ParameterizedTest
    @MethodSource
	void givenNewThreeWins_whenConstructor_matchfieldIsInitialized(int rows, int columns) {
		ThreeWins threeWins = new ThreeWins(rows, columns);
		String[][] field = threeWins.getMatchfield();
		assertNotNull(field);
		assertEquals(rows, field.length, "Invalid row number.");
		assertEquals(columns, field[0].length, "Invalid column number.");
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length; j++) {
				assertEquals(" ", field[i][j], String.format("Invalid game piece on field [%d],[%d].", i, j));
			}
		}

	}
    
    static Stream<Arguments> givenNewThreeWins_whenConstructor_matchfieldIsInitialized() {
        return Stream.of(
                Arguments.of(7, 3),
                Arguments.of(3, 7),
                Arguments.of(4, 4),
                Arguments.of(1, 1)
        );
    }

    @ParameterizedTest
    @MethodSource
    void givenValidParams_whenPlaceGamePiece_returnTrueAndMatchfieldCorrect(String[][] matchfieldStart, int player, int column, String[][] matchfieldEnd) {
		threeWins.setMatchfield(matchfieldStart);
		assertEquals(true, threeWins.placeGamePiece(player, column));
    	assertEquals(true, Arrays.deepEquals(threeWins.getMatchfield(), matchfieldEnd));
    }
    
    static Stream<Arguments> givenValidParams_whenPlaceGamePiece_returnTrueAndMatchfieldCorrect() {
        return Stream.of(
                Arguments.of(new String[][] {
                	{" ", " ", " ", " "},
                	{" ", " ", " ", " "},
                	{" ", " ", " ", " "}
                }, 1, 1, new String[][] {
                	{" ", " ", " ", " "},
                	{" ", " ", " ", " "},
                	{"O", " ", " ", " "}
                }),
                Arguments.of(new String[][] {
                	{" ", " ", " ", " "},
                	{"X", " ", " ", " "},
                	{"O", " ", " ", " "}
                }, 1, 1, new String[][] {
                	{"O", " ", " ", " "},
                	{"X", " ", " ", " "},
                	{"O", " ", " ", " "}
                }),
                Arguments.of(new String[][] {
                	{"O", " ", " ", " "},
                	{"X", " ", " ", " "},
                	{"O", " ", " ", " "}
                }, 2, 4, new String[][] {
                	{"O", " ", " ", " "},
                	{"X", " ", " ", " "},
                	{"O", " ", " ", "X"}
                }),
                Arguments.of(new String[][] {
                	{"O", " ", " ", " "},
                	{"X", " ", " ", "O"},
                	{"O", " ", " ", "X"}
                }, 2, 4, new String[][] {
                	{"O", " ", " ", "X"},
                	{"X", " ", " ", "O"},
                	{"O", " ", " ", "X"}
                })
        );
    }

    @ParameterizedTest
    @MethodSource
    void givenValidParams_whenPlaceGamePiece_returnFalse(String[][] matchfield, int player, int column) {
		threeWins.setMatchfield(matchfield);
		assertEquals(false, threeWins.placeGamePiece(player, column));
    }
    
    static Stream<Arguments> givenValidParams_whenPlaceGamePiece_returnFalse() {
        return Stream.of(
                Arguments.of(new String[][] {
                	{"O", " ", " ", " "},
                	{"X", " ", " ", " "},
                	{"O", " ", " ", " "}
                }, 2, 1),
                Arguments.of(new String[][] {
                	{"O", " ", " ", "X"},
                	{"X", " ", " ", "O"},
                	{"O", " ", " ", "X"}
                }, 1, 4)
        );
    }

    @ParameterizedTest
    @MethodSource
    void givenInvalidPlayer_whenPlaceGamePiece_throwException(int player) {
		ThreeWins dg = new ThreeWins(3, 4);
		assertThrows(IllegalArgumentException.class, () -> dg.placeGamePiece(player, 2));
    }
    
    static Stream<Arguments> givenInvalidPlayer_whenPlaceGamePiece_throwException() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(3)
        );
    }

    @ParameterizedTest
    @MethodSource
    void givenInvalidColumn_whenPlaceGamePiece_throwException(int column) {
		ThreeWins dg = new ThreeWins(3, 4);
		assertThrows(IllegalArgumentException.class, () -> dg.placeGamePiece(1, column));
    }
    
    static Stream<Arguments> givenInvalidColumn_whenPlaceGamePiece_throwException() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(5)
        );
    }

    @Test
    void givenValidField_whenCheckEndOfGame_returnTrue() {
		threeWins.setMatchfield(new String[][] {
                	{"O", "X", "O", "X"},
                	{"X", "O", "X", "O"},
                	{"O", "X", "O", "X"}
                });
		assertEquals(true, threeWins.checkEndOfGame());
    }

    @ParameterizedTest
    @MethodSource
    void givenValidField_whenCheckEndOfGame_returnFalse(String[][] matchfield) {
		threeWins.setMatchfield(matchfield);
		assertEquals(false, threeWins.checkEndOfGame());
    }
    
    static Stream<Arguments> givenValidField_whenCheckEndOfGame_returnFalse() {
        return Stream.of(
                Arguments.of(new String[][] {
                	{" ", " ", " ", " "},
                	{" ", " ", " ", " "},
                	{" ", " ", " ", " "}
                } ),
                Arguments.of(new String[][] {
                	{"O", "X", "O", " "},
                	{"X", "O", "X", "O"},
                	{"O", "X", "O", "X"}
                } )
         );
    }

    @ParameterizedTest
    @MethodSource
    void givenEndField_whenGetFinalScore_returnPlayersPoints(String[][] matchfield, int player, int finalScore) {
		threeWins.setMatchfield(matchfield);
		assertEquals(finalScore, threeWins.getFinalScore(player));
    }
    
    static Stream<Arguments> givenEndField_whenGetFinalScore_returnPlayersPoints() {
        return Stream.of(
                Arguments.of(new String[][] {
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "}
                }, 1, 0),
                Arguments.of(new String[][] {
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "}
                }, 2, 0),
                Arguments.of(new String[][] {
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{"O", "O", "O", " ", " ", " ", " "}
                }, 1, 1),
                Arguments.of(new String[][] {
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{"O", "O", "O", " ", " ", " ", " "}
                }, 2, 0),
                Arguments.of(new String[][] {
                	{"O", " ", " ", " ", " ", " ", " "},
                	{"O", " ", " ", " ", " ", " ", " "},
                	{"O", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "}
                }, 1, 1),
                Arguments.of(new String[][] {
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", "X", "X", "X"}
                }, 2, 1),
                Arguments.of(new String[][] {
                	{" ", " ", " ", " ", " ", " ", "X"},
                	{" ", " ", " ", " ", " ", " ", "X"},
                	{" ", " ", " ", " ", " ", " ", "X"},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "}
                }, 2, 1),
                Arguments.of(new String[][] {
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{"O", "O", "O", "O", "O", "O", "O"},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "}
                }, 1, 1),
                Arguments.of(new String[][] {
                	{" ", " ", "X", " ", " ", " ", " "},
                	{" ", " ", "X", " ", " ", " ", " "},
                	{" ", " ", "X", " ", " ", " ", " "},
                	{" ", " ", "X", " ", " ", " ", " "},
                	{" ", " ", "X", " ", " ", " ", " "},
                	{" ", " ", "X", " ", " ", " ", " "},
                	{" ", " ", "X", " ", " ", " ", " "}
                }, 2, 1),
                Arguments.of(new String[][] {
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{"X", "X", "X", " ", "X", "X", "X"},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "}
                }, 2, 2),
                Arguments.of(new String[][] {
                	{" ", " ", " ", " ", "O", " ", " "},
                	{" ", " ", " ", " ", "O", " ", " "},
                	{" ", " ", " ", " ", "O", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", "O", " ", " "},
                	{" ", " ", " ", " ", "O", " ", " "},
                	{" ", " ", " ", " ", "O", " ", " "}
                }, 1, 2),
                Arguments.of(new String[][] {
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{"X", "X", "X", "X", " ", "X", "X"},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "}
                }, 2, 1),
                Arguments.of(new String[][] {
                	{" ", " ", " ", " ", "O", " ", " "},
                	{" ", " ", " ", " ", "O", " ", " "},
                	{" ", " ", " ", " ", "O", " ", " "},
                	{" ", " ", " ", " ", "O", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", "O", " ", " "},
                	{" ", " ", " ", " ", "O", " ", " "}
                }, 1, 1),
                Arguments.of(new String[][] {
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{"X", "X", "X", " ", "O", "O", "O"},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "}
                }, 2, 1),
                Arguments.of(new String[][] {
                	{" ", " ", " ", " ", "O", " ", " "},
                	{" ", " ", " ", " ", "O", " ", " "},
                	{" ", " ", " ", " ", "O", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", "X", " ", " "},
                	{" ", " ", " ", " ", "X", " ", " "},
                	{" ", " ", " ", " ", "X", " ", " "}
                }, 1, 1),
                Arguments.of(new String[][] {
                	{" ", " ", "O", " ", " ", " ", " "},
                	{" ", " ", "O", " ", " ", " ", " "},
                	{"O", "O", "O", "O", "O", "O", "O"},
                	{" ", " ", "O", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "},
                	{" ", " ", " ", " ", " ", " ", " "}
                }, 1, 2),
                Arguments.of(new String[][] {
                	{" ", " ", "X", " ", " ", " ", " "},
                	{" ", " ", "X", " ", " ", " ", " "},
                	{" ", " ", "X", " ", " ", " ", " "},
                	{" ", " ", "X", " ", " ", " ", " "},
                	{"X", "X", "X", " ", " ", " ", " "},
                	{" ", " ", "X", " ", " ", " ", " "},
                	{" ", " ", "X", " ", " ", " ", " "}
                }, 2, 2),
                Arguments.of(new String[][] {
                	{" ", " ", "O", " ", " ", " ", " "},
                	{" ", " ", "O", " ", " ", " ", " "},
                	{"O", "O", "O", "O", "O", "O", "O"},
                	{" ", " ", "O", " ", " ", " ", " "},
                	{" ", " ", " ", " ", "O", " ", " "},
                	{"O", " ", " ", " ", "O", " ", " "},
                	{"O", " ", " ", " ", "O", " ", " "}
                }, 1, 3),
                Arguments.of(new String[][] {
                	{" ", " ", "X", " ", "X", "X", " "},
                	{" ", " ", "X", " ", " ", " ", " "},
                	{" ", " ", "X", " ", " ", " ", " "},
                	{" ", " ", "X", " ", " ", "X", " "},
                	{"X", "X", "X", " ", " ", "X", " "},
                	{" ", " ", "X", " ", " ", "X", " "},
                	{" ", " ", "X", " ", " ", " ", " "}
                }, 2, 3)
         );
    }
    
}
