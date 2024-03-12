
/*

    WORD COUNT APPLICATION
    By "Jefftastic"

 */

import com.jeff.wordCount.WordCounter;
import com.jeff.wordCount.WordPopup;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Application extends JFrame {
    public static void main(String[] args) {
        // Create window
        Application app = new Application();
    }

    public Application() {
        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(8,8,8,8));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;

        // Create text input
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JPanel sPanel = new JPanel();
        sPanel.setLayout(new GridLayout());
        JScrollPane scrollPane = new JScrollPane(sPanel);
        gbc.gridwidth = 2;
        gbc.weighty = 99.0f;
        gbc.weightx = 2.0f;
        panel.add(scrollPane, gbc);
        sPanel.add(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Word count label
        JLabel wordLabel = new JLabel();
        wordLabel.setText("Words: 0");
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.weighty = 1.0f;
        panel.add(wordLabel, gbc);

        // Instructions label
        JLabel instructLabel = new JLabel();
        instructLabel.setText("Right Click for more statistics");
        instructLabel.setHorizontalAlignment(JLabel.RIGHT);
        gbc.gridx = GridBagConstraints.REMAINDER;
        panel.add(instructLabel, gbc);

        // Connect signal
        textArea.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent caretEvent) {
                wordLabel.setText("Words: " + WordCounter.getWordCount(textArea.getText()));
            }
        });
        textArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println(mouseEvent.getButton());
                if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                    // Create popup dialog
                    WordPopup popup = new WordPopup(textArea.getText());
                    add(popup);
                    popup.show(textArea, mouseEvent.getX(), mouseEvent.getY());
                }
            }

            public void mousePressed(MouseEvent mouseEvent) {}
            public void mouseReleased(MouseEvent mouseEvent) {}
            public void mouseEntered(MouseEvent mouseEvent) {}
            public void mouseExited(MouseEvent mouseEvent) {}
        });

        // Create window
        this.setTitle("Word Counter");
        this.setSize(500, 500);
        this.setContentPane(panel);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}