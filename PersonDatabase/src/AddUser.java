import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddUser extends JFrame {
    private JPanel panel1;
    private JTextField firstName;
    private JTextField lastName;
    private JSpinner bdaySpinner;
    private JComboBox userGroup;
    public JButton addToDB;
    private JButton cancelDB;

    AddUser() {
        // Create window
        setResizable(false);
        setVisible(true);
        setTitle("Add User...");
        setContentPane(panel1);
        setLocationRelativeTo(null);
        setSize(520, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set modal on spinner
        bdaySpinner.setModel(new SpinnerDateModel());
        bdaySpinner.setEditor(new JSpinner.DateEditor(bdaySpinner, "MM/dd/yyyy"));

        // Connect signals
        clampInput(firstName, 20);
        clampInput(lastName, 20);
        initializeNameField(firstName, "First Name");
        initializeNameField(lastName, "Last Name");
    }

    // Clamps text input to a specified length
    private void clampInput(JTextField object, int max) {
        object.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Do not allow whitespaces.
                if (Character.isWhitespace(e.getKeyChar())) return;
                // Do not allow typing after 20 characters.
                if (object.getText().length() <= max) {
                    super.keyTyped(e);
                }
            }
        });
    }

    // Connects generic name input signals to provided JTextField object
    private void initializeNameField(JTextField object, String name_default) {
        // Set text
        object.setForeground(Color.GRAY);
        object.setText(name_default);

        // Connect signal
        object.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                // Check if field is empty
                if (object.getText().isEmpty()) {
                    object.setText(name_default);
                    object.setForeground(Color.GRAY);
                }
            }
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                // Check if field is default
                if (Objects.equals(object.getText(), name_default)) {
                    object.setText("");
                    object.setForeground(Color.BLACK);
                }
            }
        });
    }

    // Sets first name field.
    public void setFirstName(String name) {
        firstName.setText(name);
    }

    // Sets last name field.
    public void setLastName(String name) {
        lastName.setText(name);
    }

    // Sets birthday field.
    public void setBirthday(Date date) {
        bdaySpinner.setValue(date);
    }

    // Creates a person class.
    public Person createPerson() {
        Person np = new Person();
        np.setName(firstName.getText(), lastName.getText());
        np.setBirthday((Date)bdaySpinner.getValue());
        np.setUserGroup(userGroup.getSelectedIndex());
        return np;
    }
}
