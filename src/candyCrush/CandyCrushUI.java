package candyCrush;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;

public class CandyCrushUI extends JFrame {

    public CandyCrushUI() throws IOException {
        int size = 800;
        setVisible(true);
        setContentPane(new Canvas(size));
        setSize(size + 10, size + 30);
    }

    public static void main(String[] args) throws Exception {
        new CandyCrushUI();
    }
}

class Canvas extends JPanel {
    private AudioInputStream explosionAudioStream = null;
    private HashMap<Character, Image> images;
    private int size = 800;
    private int tileSize;
    private CandyCrushLogic logic;
    private JPanel panel;
    private HashMap<Character, Color> tileColors;
    private int x1 = -1;
    private int y1 = -1;
    private Clip explosion = null;

    public Canvas(int size) throws IOException {
        try {
            this.explosion = AudioSystem.getClip();
            this.explosion.open(AudioSystem.getAudioInputStream(
                    getClass().getResourceAsStream("/Explosion.wav")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.size = size;
        setSize(this.size, this.size);
        this.tileSize = this.size / CandyCrushLogic.FIELD_SIZE;
        this.panel = new JPanel();
        this.logic = new CandyCrushLogic();
        this.images = new HashMap<>();
        this.images.put(CandyCrushLogic.SYMBOLS[0], ImageIO.read(getClass().getResource("/images/Blue.png")));
        this.images.put(CandyCrushLogic.SYMBOLS[1], ImageIO.read(getClass().getResource("/images/Green.png")));
        this.images.put(CandyCrushLogic.SYMBOLS[2], ImageIO.read(getClass().getResource("/images/Orange.png")));
        this.images.put(CandyCrushLogic.SYMBOLS[3], ImageIO.read(getClass().getResource("/images/Purple.png")));
        this.images.put(CandyCrushLogic.SYMBOLS[4], ImageIO.read(getClass().getResource("/images/Red.png")));
        this.images.put(CandyCrushLogic.SYMBOLS[5], ImageIO.read(getClass().getResource("/images/Yellow.png")));
        this.images.put(' ', new ImageIcon(getClass().getResource("/images/blast.gif")).getImage());

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

        new Timer(20, (e) -> {
            this.repaint();
        }).start();

        while (this.logic.removeMatchingSymbols()) {
            this.logic.fillField();
        }
    }

    private void mouseClick(MouseEvent e) {
        int x = e.getX() / tileSize;
        int y = (e.getY()) / tileSize;
        if (e.getButton() == 1) {
            this.x1 = x;
            this.y1 = y;
        } else if (e.getButton() == 3) {
            if (this.x1 != -1 && this.y1 != -1) {
                try {
                    this.logic.swap(this.y1, this.x1, y, x);
                    gameStep();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, String.format("kein gültiger Zug! y1:%d, x1:%d ---> y2:%d, x2:%d", this.y1, this.x1, y, x));
                }
                this.x1 = -1;
                this.y1 = -1;
            }
        }
    }

    private void gameStep() {
        boolean match = this.logic.removeMatchingSymbols();
        if (!match) {
            return;
        } else {
            this.explosion.stop();
            this.explosion.setFramePosition(0);
            this.explosion.start();
            Timer t = new Timer(571, (e) -> {
                this.logic.fillField();
                gameStep();
            });
            t.setRepeats(false);
            t.start();
        }

    }

    private BufferedImage renderGame() {
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        char[][] field = this.logic.getField();
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field.length; x++) {
                g2.drawImage(this.images.get(field[y][x]), x * tileSize, y * tileSize, tileSize, tileSize, null);
            }
        }
        if (this.x1 != -1 && this.y1 != -1) {
            g2.setColor(Color.cyan);
            g2.drawRect(this.x1 * tileSize, this.y1 * tileSize, tileSize, tileSize);
        }
        return img;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, size, size);
        g.drawImage(renderGame(), 0, 0, size, size, null);

    }
}
