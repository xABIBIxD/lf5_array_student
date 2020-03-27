package gameOfLife;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameOfLifeUI extends JFrame {

    private GameOfLife game;
    private JPanel gameField;

    public GameOfLifeUI() {
        game = new GameOfLife();
        game.initializeBoard(30);
        setVisible(true);
        setSize(800, 850);
        setLayout(null);
        this.gameField = new JPanel();
        this.gameField.setLayout(new GridLayout(10, 10));
        this.gameField.setBackground(Color.yellow);
        this.gameField.setBounds(0, 50, 800, 800);
        getContentPane().add(this.gameField);
        JButton btn = new JButton("neue Generation");
        btn.setBounds(0, 0, 800, 50);
        btn.addActionListener((e) -> {
            if (game.simulationOver()) {
                JOptionPane.showMessageDialog(this, "Game over!");
            } else {
                for (char[] row : this.game.getBoard()) {
                    System.out.println(row);
                }
                System.out.println();
                System.out.println();
                
                game.simulate();
                for (int y = 0; y < 10; y++) {
                    for (int x = 0; x < 10; x++) {
                        ((JLabel) this.gameField.getComponent(y * 10 + x)).setText(game.getCellStatus(y, x) + "");
                    }
                }
                SwingUtilities.updateComponentTreeUI(this);
            }
        });
        getContentPane().add(btn);

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                GameFieldJLabel label = new GameFieldJLabel(y, x);
                label.setText(game.getCellStatus(y, x) + "");
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        GameFieldJLabel clicked = (GameFieldJLabel) e.getSource();
                        game.setAliveCell(clicked.getyPosi() + 1, clicked.getxPosi() + 1);
                        System.out.println((clicked.getyPosi() + 1) + "  " + (clicked.getxPosi() + 1));
                        clicked.setText("O");
                    }
                });
                this.gameField.add(label);
            }
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    public static void main(String[] args) {
        new GameOfLifeUI();
    }
}

class GameFieldJLabel extends JLabel {

    private int xPosi;
    private int yPosi;

    public int getxPosi() {
        return xPosi;
    }

    public int getyPosi() {
        return yPosi;
    }

    public GameFieldJLabel(int yPosi, int xPosi) {
        super();
        this.xPosi = xPosi;
        this.yPosi = yPosi;
        setSize(80, 80);
        setOpaque(true);
    }

    public void setText(String text) {
        if (text.equals("O")) {
            setBackground(Color.green);
        } else {
            setBackground(Color.WHITE);
        }
    }
}
