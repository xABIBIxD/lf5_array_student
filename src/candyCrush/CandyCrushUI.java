package candyCrush;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class CandyCrushUI extends JFrame {

    private final int SIZE = 800;
    private int tileSize;
    private CandyCrushLogic logic;
    private JPanel panel;
    private HashMap<Character, Color> tileColors;
    private int x1 = -1;
    private int y1 = -1;


    public CandyCrushUI() {
        setVisible(true);
        setSize(SIZE, SIZE + 30);
        this.tileSize = SIZE / CandyCrushLogic.FIELD_SIZE;
        this.panel = new JPanel();
        this.logic = new CandyCrushLogic();
        this.tileColors = new HashMap<>();
        this.tileColors.put(CandyCrushLogic.SYMBOLS[0], Color.red);
        this.tileColors.put(CandyCrushLogic.SYMBOLS[1], Color.green);
        this.tileColors.put(CandyCrushLogic.SYMBOLS[2], Color.black);
        this.tileColors.put(CandyCrushLogic.SYMBOLS[3], Color.yellow);
        this.tileColors.put(CandyCrushLogic.SYMBOLS[4], Color.blue);
        this.tileColors.put(CandyCrushLogic.SYMBOLS[5], Color.magenta);
        this.tileColors.put(' ', Color.white);
        this.repaint();
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClick(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        Thread t = new Thread(() -> {
            while (true) {
                this.logic.removeMatchingSymbols();
                this.repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private void mouseClick(MouseEvent e) {
        int x = e.getX() / tileSize;
        int y = e.getY() / tileSize;
        if (e.getButton() == 1) {
            this.x1 = x;
            this.y1 = y;
        } else if (e.getButton() == 3) {
            if (this.x1 != -1 && this.y1 != -1) {
                try {
                    this.logic.swap(this.y1, this.x1, y, x);
                    this.x1 = -1;
                    this.y1 = -1;
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "kein gültiger Zug!");
                }
            }
        }

    }

    private BufferedImage renderGame() {
        System.out.println("render");
        BufferedImage img = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        char[][] field = this.logic.getField();
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field.length; x++) {
                g.setColor(tileColors.get(field[y][x]));
                g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }
        return img;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(renderGame(), 0, 30, SIZE, SIZE, null);
    }

    public static void main(String[] args) {
        new CandyCrushUI();
    }

}
