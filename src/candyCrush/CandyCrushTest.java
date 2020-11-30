package candyCrush;

//import com.sun.jdi.connect.Connector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CandyCrushTest {

    private CandyCrushLogic candy;

    @BeforeEach
    public void setUp() {
        this.candy = new CandyCrushLogic();
    }

    private char[][] convertTo2dArrayy(String s) {
        String[] rows = s.split("\n");
        if (rows.length != CandyCrushLogic.FIELD_SIZE) {
            throw new IllegalArgumentException("Field has wrong Size");
        }
        char[][] f = new char[CandyCrushLogic.FIELD_SIZE][CandyCrushLogic.FIELD_SIZE];
        for (int i = 0; i < rows.length; i++) {
            if (rows[i].length() != CandyCrushLogic.FIELD_SIZE) {
                throw new IllegalArgumentException("Field has wrong Size");
            }
            for (int j = 0; j < rows[i].length(); j++) {
                f[i][j] = rows[i].charAt(j);
            }
        }
        return f;
    }

    private char[][] getNewSampleField() {
        return this.convertTo2dArrayy(
                String.join("\n",
                        "<<<--**+?-",
                        "<*-~?-~~+-",
                        "~**<**<?-?",
                        "++<*+-*+-<",
                        "?*?+???-~?",
                        "++-~*+?-<?",
                        "*--+~--~--",
                        "*-<<*++<<<",
                        "?*++*~~-?+",
                        "+*~?<***<*"));
    }

    @Test
    public void givenNewCandyCrushLogic_whenGetField_returnValidCandyCrushArray() {
        char[][] f = this.candy.getField();
        assertNotNull(f);
        assertEquals(f.length, CandyCrushLogic.FIELD_SIZE, "Wrong Field Size");
        assertEquals(f[0].length, CandyCrushLogic.FIELD_SIZE, "Wrong Field Size");
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f.length; j++) {
                boolean isASymbol = false;
                for (char s : CandyCrushLogic.SYMBOLS) {
                    if (s == f[i][j]) {
                        isASymbol = true;
                    }
                }
                assertTrue(isASymbol, String.format("Wrong Symbol at %d/%d", i, j));
            }
        }
    }

    @Test
    public void givenTwoValidPoints_whenSwap_swapTwoSymbols() {
        this.candy.setField(this.getNewSampleField());
        this.candy.swap(4, 5, 4, 4);
        char[][] s = this.getNewSampleField();
        assertEquals(s[4][5], this.candy.getField()[4][4]);
        assertEquals(s[4][4], this.candy.getField()[4][5]);

        this.candy.swap(9, 9, 8, 9);
        assertEquals(s[8][9], this.candy.getField()[9][9]);
        assertEquals(s[9][9], this.candy.getField()[8][9]);
    }


    @ParameterizedTest
    @MethodSource
    public void givenTwoInvalidPoints_whenSwap_throwsException(int y1, int x1, int y2, int x2) {
        assertThrows(IllegalArgumentException.class, () -> this.candy.swap(y1, x1, y2, x2));
    }

    static Stream<Arguments> givenTwoInvalidPoints_whenSwap_throwsException() {
        return Stream.of(
                Arguments.of(0, 0, 1, 2),
                Arguments.of(0, 0, 0, 0),
                Arguments.of(9, 9, 10, 9),
                Arguments.of(3, 5, 3, 3),
                Arguments.of(-1, 0, 0, 0),
                Arguments.of(0, -1, 0, 0),
                Arguments.of(0, 0, -1, 0),
                Arguments.of(0, 0, 0, -1),
                Arguments.of(10, 9, 9, 9),
                Arguments.of(9, 10, 9, 9),
                Arguments.of(9, 9, 10, 9),
                Arguments.of(9, 9, 9, 10),
                Arguments.of(4, 4, 2, 2),
                Arguments.of(4, 4, 1, 1),
                Arguments.of(4, 4, 4, 6),
                Arguments.of(9, 9, 8, 8),
                Arguments.of(8, 8, 9, 9)

        );
    }

    @Test
    public void givenFieldWitHorizontalMatches_whenRemoveMatchingSymbols_returnFieldWithBlanksOnMatchingSymbols() {
        this.candy.setField(convertTo2dArrayy(
                String.join("\n",
                        "*-*--**+?-",
                        "<*-~?-~<<<",
                        "~**<**<?-?",
                        "++<*--*+-<",
                        "?*?+++--~?",
                        "*+-~++?-<-",
                        "-------~-~",
                        "*-<<*++++-",
                        "?****~~-?+",
                        "+*~?<?<***"))
        );
        this.candy.removeMatchingSymbols();

        assertArrayEquals(convertTo2dArrayy(String.join("\n",
                "*-*--**+?-",
                "<*-~?-~   ",
                "~**<**<?-?",
                "++<*--*+-<",
                "?*?   --~?",
                "*+-~++?-<-",
                "       ~-~",
                "*-<<*    -",
                "?    ~~-?+",
                "+*~?<?<   "))
                , this.candy.getField());


        this.candy.setField(this.convertTo2dArrayy(String.join("\n",
                "***--**+?-",
                "<--~?-~~+-",
                "~**<**<?-?",
                "++<*--*+-<",
                "?*?+++--~?",
                "*+-~++?-<-",
                "---+~--~-*",
                "*-<<*++-+-",
                "?*++*~~-?+",
                "++++++++++")));
        this.candy.removeMatchingSymbols();
        assertArrayEquals(this.convertTo2dArrayy(
                String.join("\n",
                        "   --**+?-",
                        "<--~?-~~+-",
                        "~**<**<?-?",
                        "++<*--*+-<",
                        "?*?   --~?",
                        "*+-~++?-<-",
                        "   +~--~-*",
                        "*-<<*++-+-",
                        "?*++*~~-?+",
                        "          ")
        ), this.candy.getField());
    }

    @Test
    public void givenFieldWithVerticalMatches_whenRemoveMatchingSymbols_returnFieldWithBlanksOnMatchingSymbols() {

        this.candy.setField(convertTo2dArrayy(String.join("\n",
                "-??--**+?-",
                "-*-~?-~~+-",
                "-**<**<?--",
                "++<*+-*+--",
                "?*?+-*--~-",
                "++-~*+?-<?",
                "*--+*--~--",
                "*-<<*++<<+",
                "**++*~~-?+",
                "**~?<~~<<+")));
        this.candy.removeMatchingSymbols();

        assertArrayEquals(this.convertTo2dArrayy(String.join("\n",
                " ??--**+? ",
                " *-~?-~~+ ",
                " **<**<?- ",
                "++<*+-*+- ",
                "?*?+-*--~ ",
                "++-~ +?-<?",
                " --+ --~--",
                " -<< ++<< ",
                " *++ ~~-? ",
                " *~?<~~<< ")), this.candy.getField());
    }

    @Test
    public void givenCurrentFieldWithMixedColsAndRowMatching_whenRemoveMatchingSymbols_returnFieldWithBlanksOnMatchingSymbols() {
        this.candy.setField(this.convertTo2dArrayy(String.join("\n",
                "*-*--**+?-",
                "<*-~?-~~+-",
                "~**<**<?-?",
                "++<*+-*+-<",
                "?*?+++--~?",
                "*+-~++?-<-",
                "*--+~--~--",
                "*-<<*++---",
                "*****~~??+",
                "**~?<?<-<*")));
        this.candy.removeMatchingSymbols();
        assertArrayEquals(this.convertTo2dArrayy(
                String.join("\n",
                        "*-*--**+?-",
                        "<*-~?-~~+-",
                        "~**<**<?-?",
                        "++<* -*+-<",
                        "?*?   --~?",
                        " +-~ +?-< ",
                        " --+~--~- ",
                        " -<<*++   ",
                        "     ~~??+",
                        " *~?<?<-<*")
        ), this.candy.getField());
    }

    @Test
    public void givenCurrentFieldWithMultipleMixedColsAndRowMatching_whenRemoveMatchingSymbols_returnFieldWithBlanksOnMatchingSymbols() {
        this.candy.setField(this.convertTo2dArrayy(String.join("\n",
                "*-*--**+?-",
                "<*-~?-~~+-",
                "~**<**<?-?",
                "++<*+-*+-<",
                "?*?+++--~?",
                "*+-~+-?-<-",
                "*--+~--~--",
                "*-<<------",
                "*****~~-?-",
                "**~?<---<-")));
        this.candy.removeMatchingSymbols();
        assertArrayEquals(this.convertTo2dArrayy(
                String.join("\n",
                        "*-*--**+?-",
                        "<*-~?-~~+-",
                        "~**<**<?-?",
                        "++<* -*+-<",
                        "?*?   --~?",
                        " +-~  ?-< ",
                        " --+~ -~- ",
                        " -<<      ",
                        "     ~~ ? ",
                        " *~?<   < ")
        ), this.candy.getField());
    }

    @Test
    public void givenFieldWithNoMatch_whenRemoveMatchingSymbols_returnFalse() {
        this.candy.setField(convertTo2dArrayy(String.join("\n",
                "-??--**+?-",
                "**-~?-~~++",
                "-**<**<?--",
                "++<*+-*+-+",
                "?*?+-*--~-",
                "+-+~*+?-<?",
                "*+-++--~--",
                "--<<*++<<+",
                "**++*~~-??",
                "**~?<~~<<+")));
        assertFalse(this.candy.removeMatchingSymbols());
    }

    @Test
    public void givenFieldWithMatch_whenRemoveMatchingSymbols_returnTrue() {
        this.candy.setField(convertTo2dArrayy(String.join("\n",
                "-??--**+?-",
                "**-~?-~~++",
                "-**<**<?--",
                "++<*+-*+-?",
                "?*?+-*--~-",
                "+++~*+?-<?",
                "*--++--~--",
                "--<<*++<<+",
                "**++*~~-??",
                "**~?<~~<<+")));
        assertTrue(this.candy.removeMatchingSymbols());

        this.candy.setField(convertTo2dArrayy(String.join("\n",
                "-??--**+?-",
                "**-~?-~~++",
                "-**<**<?--",
                "++<*+-*+-+",
                "?*?+-*--~-",
                "+-+~*+?-<?",
                "*+-++--~--",
                "*-<<*++<<+",
                "**++*~~-??",
                "**~?<~~<<+")));
        assertTrue(this.candy.removeMatchingSymbols());
    }


    @Test
    public void givenFieldWithBlanks_whenFillField_fieldIsFilledUp() {
        char[][] origField = convertTo2dArrayy(String.join("\n",
                "-??--**+?-",
                "**-~?-?   ",
                "-**<**<?--",
                "++<*+-*+-+",
                " *?+-*--~-",
                " -+~*+?-<?",
                " +-++--~--",
                "--<<*++<<+",
                "*   *~~-??",
                "**~?<~~<<+"));

        this.candy.setField(convertTo2dArrayy(String.join("\n",
                "-??--**+?-",
                "**-~?-?   ",
                "-**<**<?--",
                "++<*+-*+-+",
                " *?+-*--~-",
                " -+~*+?-<?",
                " +-++--~--",
                "--<<*++<<+",
                "*   *~~-??",
                "**~?<~~<<+")));
        this.candy.fillField();
        assertEquals('+', this.candy.getField()[1][7], "y: 1   x:7");
        assertEquals('?', this.candy.getField()[1][8], "y: 1   x:8");
        assertEquals('-', this.candy.getField()[1][9], "y: 1   x:9");

        for (int y = 8; y > 1; y--) {
            for (int x = 1; x < 4; x++) {
                assertEquals(origField[y - 1][x], this.candy.getField()[y][x], String.format("not correct @ Y:%d/X:%d", y, x));
            }
        }

        for (int y = 6; y > 2; y--) {
            assertEquals(origField[y - 3][0], this.candy.getField()[y][0], String.format("not correct @ Y:%d/X:%d", y, 0));
        }
        List<Character> symbols = new ArrayList<>();
        for (char c : CandyCrushLogic.SYMBOLS) {
            symbols.add(c);
        }
        for (char row[] : this.candy.getField()) {
            for (char c : row) {
                assertTrue(symbols.contains(c));
            }
        }

    }
}