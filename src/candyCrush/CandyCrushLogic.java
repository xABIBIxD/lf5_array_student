package candyCrush;

public class CandyCrushLogic {
    public static final char[] SYMBOLS = new char[]{'*', '+', '-', '~', '?', '<'};
    public static final boolean ANIMATET_EXPLOSION_ = false;
    public static final int FIELD_SIZE = 10;
    private char[][] field;

    /**
     * Constructor
     * is called to create an object of type CandyCrushLogic
     */
    public CandyCrushLogic() {

    }

    public char[][] getField() {
        return this.field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }

    public void swap(int y1, int x1, int y2, int x2) {

    }


    public boolean removeMatchingSymbols() {
        return false;
    }


    public void fillField() {
    }
}
