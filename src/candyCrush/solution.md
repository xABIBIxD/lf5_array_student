## Lösung CandyCrushLogic

````
package candyCrush;

import org.junit.platform.commons.util.StringUtils;

import java.util.HashMap;

public class CandyCrushLogic {

    public static final char[] SYMBOLS = new char[]{'*', '+', '-', '~', '?', '<'};
    public static final boolean ANIMATET_EXPLOSION_ = false;

    public static final int FIELD_SIZE = 10;
    private char[][] field;

    private HashMap<Character, Character> symToDelSym = new HashMap<>();
    private HashMap<Character, Character> delSymTosym = new HashMap<>();

    private final int standAufgabe = 4;

    /**
     * Constructor
     * is called to create an object of type CandyCrushLogic
     */
    public CandyCrushLogic() {
        if (this.standAufgabe > 0) {

            this.field = new char[FIELD_SIZE][FIELD_SIZE];
            for (int y = 0; y < FIELD_SIZE; y++) {
                for (int x = 0; x < FIELD_SIZE; x++) {
                    this.field[y][x] = SYMBOLS[(int) (Math.random() * SYMBOLS.length)];
                }
            }

            char[] delSyms = new char[]{'1', '2', '3', '4', '5', '6'};
            for (int i = 0; i < SYMBOLS.length; i++) {
                symToDelSym.put(SYMBOLS[i], delSyms[i]);
                delSymTosym.put(delSyms[i], SYMBOLS[i]);
                symToDelSym.put(delSyms[i], delSyms[i]);
            }
            symToDelSym.put(' ', ' ');
            delSymTosym.put(' ', ' ');
        }
    }

    public char[][] getField() {
        return this.field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }

    public void swap(int y1, int x1, int y2, int x2) {
        if (this.standAufgabe > 1) {
            if (Math.abs(Math.abs(x1 + y1) - Math.abs(x2 + y2)) != 1) {
                throw new IllegalArgumentException();
            }
            if (!isInsideField(y1) || !isInsideField(x1) || !isInsideField(y2) || !isInsideField(x2)) {
                throw new IllegalArgumentException();
            }
            char t = this.field[y1][x1];
            this.field[y1][x1] = this.field[y2][x2];
            this.field[y2][x2] = t;
        }
    }

    private boolean isInsideField(int i) {
        if (i < 0 || i > FIELD_SIZE - 1) {
            return false;
        }
        return true;
    }

    public void print() {
        System.out.println("******* Field ************");
        for (char[] row : this.field) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public boolean removeMatchingSymbols() {
        if (this.standAufgabe >= 3) {
            boolean match = false;
            char[][] zField = new char[FIELD_SIZE + 2][FIELD_SIZE + 2];
            for (int y = 0; y < FIELD_SIZE; y++) {
                for (int x = 0; x < FIELD_SIZE; x++) {
                    zField[y + 1][x + 1] = this.field[y][x];
                }
            }

            for (int y = 1; y < FIELD_SIZE + 1; y++) {
                for (int x = 1; x < FIELD_SIZE + 1; x++) {
                    char current = zField[y][x];

                    int sameTop = 0;
                    for (int yc = y - 1; yc > 0; yc--) {
                        if (symbolEq(zField[yc][x], current)) {
                            sameTop++;
                        } else {
                            yc = 0;
                        }
                    }
                    int sameBottom = 0;
                    for (int yc = y + 1; yc < FIELD_SIZE; yc++) {
                        if (symbolEq(zField[yc][x], current)) {
                            sameBottom++;
                        } else {
                            yc = FIELD_SIZE;
                        }
                    }
                    int sameLeft = 0;
                    for (int xc = x - 1; xc > 0; xc--) {
                        if (symbolEq(zField[y][xc], current)) {
                            sameLeft++;
                        } else {
                            xc = 0;
                        }
                    }
                    int sameRight = 0;
                    for (int xc = x + 1; xc < FIELD_SIZE; xc++) {
                        if (symbolEq(zField[y][xc], current)) {
                            sameRight++;
                        } else {
                            xc = FIELD_SIZE;
                        }
                    }

                    if ((sameLeft + sameRight + 1) >= 3) {
//                    System.out.println(String.format("Y: %d, X:%d, SameLeft:%d, SameRight:%d, SameTop:%d, SameBot:%d", y, x, sameLeft, sameRight, sameTop, sameBottom));

                        match = true;
                        zField[y][x] = this.symToDelSym.get(current);
                        while (sameLeft > 0) {
                            zField[y][x - sameLeft] = this.symToDelSym.get(current);
                            sameLeft--;
                        }
                        while (sameRight > 0) {
                            zField[y][x + sameRight] = this.symToDelSym.get(current);
                            sameRight--;
                        }
                    }
                    if ((sameBottom + sameTop + 1) >= 3) {
//                    System.out.println(String.format("Y: %d, X:%d, SameLeft:%d, SameRight:%d, SameTop:%d, SameBot:%d", y, x, sameLeft, sameRight, sameTop, sameBottom));
                        match = true;
                        zField[y][x] = this.symToDelSym.get(current);
                        while (sameTop > 0) {
                            zField[y - sameTop][x] = this.symToDelSym.get(current);
                            sameTop--;
                        }
                        while (sameBottom > 0) {
                            zField[y + sameBottom][x] = this.symToDelSym.get(current);
                            sameBottom--;
                        }
                    }
                }
            }
            if (!match) {
                return false;
            }
//        System.out.println("____ vorher ____");
//        print();
            for (int y = 0; y < FIELD_SIZE; y++) {
                for (int x = 0; x < FIELD_SIZE; x++) {
                    char fill = '-';
                    if (delSymTosym.get(zField[y + 1][x + 1]) != null) {
                        fill = ' ';
                    } else {
                        fill = zField[y + 1][x + 1];
                    }
                    this.field[y][x] = fill;
                }
            }
            return true;
        }
        return false;
//        System.out.println("____ nachher ____");
//        print();
    }


    private boolean symbolEq(char sym1, char sym2) {
        if (sym1 == ' ') { // ZeroPadding-Border
            return false;
        }
        try {
            if (sym1 == sym2 || symToDelSym.get(sym1) == sym2 || symToDelSym.get(sym2) == sym1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("sym1:'" + sym1 + "'     sym2:'" + sym2 + "'");
        }
        return false;
    }

    public void fillField() {
        if (this.standAufgabe >= 4) {
            for (int y = 0; y < FIELD_SIZE; y++) {
                for (int x = 0; x < FIELD_SIZE; x++) {
                    if (this.field[y][x] == ' ') {
                        for (int yt = y - 1; yt >= 0; yt--) {
                            this.field[yt + 1][x] = this.field[yt][x];
                        }
                        this.field[0][x] = SYMBOLS[(int) (Math.random() * SYMBOLS.length)];
                    }
                }
            }
        }
    }
}

````