package game;

import javax.swing.JFrame;
import java.awt.*;

public class Breakout extends JFrame{

    public Breakout() {
        initUI();
    }

    public void initUI() {
        add(new Board());
        setTitle("Breakout!");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Breakout game = new Breakout();
            game.setVisible(true);
        });
    }

}
