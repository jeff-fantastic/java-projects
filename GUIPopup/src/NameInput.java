import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameInput extends JFrame {
    private JTextField nameInput;
    private JLabel labelEnterName;
    private JButton acceptButton;
    private JPanel NameInputPanel;

    public NameInput() {
        // Setup
        setContentPane(NameInputPanel);
        setTitle("Name Input");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        // Button signal
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(NameInput.this, "Hello, " + nameInput.getText() + "!");
            }
        });
    }
}
