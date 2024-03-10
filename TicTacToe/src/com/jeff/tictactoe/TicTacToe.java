/*
    TIC-TAC-TOE APPLET
 */
package com.jeff.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private static TicTacToe game;

    public static void main(String[] args) {
        // Create game
        game = new TicTacToe();
    }

    public TicTacToe() {
        // Create window
        setMinimumSize(new Dimension(400, 500));
        //setResizable(false);
        setTitle("Tic-Tac-Toe!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Create board
        Board board = new Board();
        add(board);
        pack();

        // Create listener
        board.addBoardListener(new BoardListener() {
            @Override
            public void boardComplete(int player) {
                completeGame(player);
            }
        });
    }

    // Ran when a game of TicTacToe has completed.
    public void completeGame(int player) {
        // Remove existing game
        getContentPane().removeAll();
        game.dispose();
        revalidate();
        repaint();

        // Create prompt panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(16, 16, 16, 16);
        gbc.gridwidth = 0;
        gbc.gridheight = 1;
        this.add(panel);

        // Create label and button
        JLabel congratsLabel = new JLabel();
        JButton retryButton = new JButton();
        congratsLabel.setText("Congratulations, Player " + (player + 1) + " has won the game!" );
        retryButton.setText("Press to restart");
        panel.add(congratsLabel, gbc);
        panel.add(retryButton, gbc);

        // Connect signal
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Create game, again
                getContentPane().removeAll();
                setVisible(false);
                game = new TicTacToe();
            }
        });
        setVisible(true);

    }
}
