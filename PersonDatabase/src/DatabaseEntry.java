import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.beans.PropertyChangeListener;
import java.util.Vector;

public class DatabaseEntry {
    private int entryID;
    public JPanel dbEntry;
    private JLabel dbFirstName;
    private JLabel dbLastName;
    private JLabel dbBirthday;
    private JLabel dbUserGroup;
    private JButton dbEdit;
    private JButton dbRemove;

    DatabaseEntry(Person person, int eID) {
        // Set ID
        this.entryID = eID;

        // Set fields
        dbFirstName.setText(person.getFirstName());
        dbLastName.setText(person.getLastName());
        dbBirthday.setText(person.getBirthdayString());
        dbUserGroup.setText(person.getWorkGroupFormatted());
    }

    // Connects signals to passed in DatabaseView
    public void connectSignal(DatabaseView view) {
        dbEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Button pressed, make edit window
                AddUser window = new AddUser();
                window.addToDB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        // Added to DB, close and edit current
                        Main.gDatabase.set(entryID, window.createPerson());
                        view.refreshView();
                        window.dispose();
                    }
                });
            }
        });
        dbRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Remove entry and refresh
                Main.gDatabase.remove(entryID);
                view.refreshView();
            }
        });
    }

    // Returns entry ID
    public int getEID() {
        return entryID;
    }

}
