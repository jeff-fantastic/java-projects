import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DatabaseView extends JFrame {
    private JPanel databaseContainer;
    private JButton addButton;
    private JPanel mainContainer;
    private JScrollPane scroll;
    private JPanel optionsContainer;
    private JSplitPane splitContainer;
    private JButton clearButton;

    DatabaseView() {
        // Create window
        setVisible(true);
        setTitle("Database Viewer");
        setContentPane(mainContainer);
        setLocationRelativeTo(null);
        setSize(850, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Change databaseContainer
        databaseContainer.setLayout(new BoxLayout(databaseContainer, BoxLayout.Y_AXIS));
        databaseContainer.setAlignmentY(TOP_ALIGNMENT);

        // Signal
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Clear database and refresh
                Main.gDatabase.clear();
                refreshView();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Create new window
                AddUser window = new AddUser();

                // Wait for completion
                window.addToDB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        // Add to database
                        Main.gDatabase.add(window.createPerson());
                        refreshView();

                        // Close window
                        window.dispose();
                    }
                });
            }
        });
    }

    // Refreshes view based on database.
    public void refreshView() {
        // Clear container
        databaseContainer.removeAll();

        // Repopulate
        for (int i = 0; i < Main.gDatabase.size(); i++) {
            DatabaseEntry entry = new DatabaseEntry(Main.gDatabase.get(i), i);
            entry.connectSignal(this);
            entry.dbEntry.setAlignmentY(TOP_ALIGNMENT);
            databaseContainer.add(entry.dbEntry, i);
        }

        // Refresh
        SwingUtilities.updateComponentTreeUI(databaseContainer);
    }
}
