package com.jeff.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Board extends JPanel {
    // Board inputs are stored as two different sets of 9 bits
    private short[] userInputs = { 0b000000000, 0b000000000 };
    private short[] winningInputs = {
        // each row
        0b000000111,
        0b000111000,
        0b111000000,
        // each column
        0b100100100,
        0b010010010,
        0b001001001,
        // each diagonal
        0b100010001,
        0b001010100
    };

    // Set of listeners (e.g. from class TicTacToe) are stored here.
    private ArrayList<BoardListener> listeners = new ArrayList<BoardListener>();

    private final String[] pString = { "Player 1", "Player 2" };
    private HashMap<JButton,Integer> hashButton = new HashMap<>();
    private JLabel titleLabel;
    private int player = 0;

    public Board() {
        // Configure self
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 0;
        gbc.gridheight = 2;
        gbc.insets = new Insets(16, 8, 16, 8);

        // Create header
        titleLabel = new JLabel();
        titleLabel.setText(pString[player] + ", select a tile!");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel, gbc);

        // Create 3x3 grid
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3));
        gridPanel.setPreferredSize(new Dimension(300, 300));
        gridPanel.setAlignmentX(CENTER_ALIGNMENT);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                JButton button = new JButton();
                connectButtonSignal(button);
                hashButton.put(button, (x * 3) + (y + 1) - 1);
                gridPanel.add(button);
            }
        }
        this.add(gridPanel);
    }

    // Handles connecting signals on each button
    private void connectButtonSignal(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Determine button marking
                String mark;
                if (player == 0)   mark = "X";
                else               mark = "O";

                // Update button that has been pressed
                button.setEnabled(false);
                button.setText(mark);

                // Update input bitfield
                userInputs[player] |= (short) (0b1 << (hashButton.get(button)));

                // Check for solution
                boolean result = findSolution(player);
                // If there is a result, we need to break and send a signal
                if (result) {
                    for (BoardListener bl : listeners)
                        bl.boardComplete(player);
                    return;
                }

                // Swap
                swapTurns();
            }
        });
    }

    // Handles swapping player turns.
    private void swapTurns() {
        // Set turn and label
        player ^= 1;
        titleLabel.setText(pString[player] + ", select a tile!");
    }

    // Attempts to find a solution in the current board.
    private boolean findSolution(int pNum) {
        // Iterate over winning inputs and compare
        for (int i = 0; i < winningInputs.length; i++) {
            if ((userInputs[pNum] & winningInputs[i]) == winningInputs[i]) return true;
        }
        return false;
    }

    // Adds a listener to list of listeners
    public void addBoardListener(BoardListener listener) {
        listeners.add(listener);
    }
}
