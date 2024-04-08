package Hospital;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class View_Patient {
    JFrame frame;
    JTable table;
    JTextField usernameField;
    JButton deleteButton;
    JButton editButton;
    JButton cancelButton; // Added cancel button
    DefaultTableModel model;
    String aname;

    View_Patient(String name) {
        aname=name;
        frame = new JFrame("View Patients");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 500);
        frame.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing for all cells
            }
        };
        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false); // Disable column reordering
        JScrollPane scrollPane = new JScrollPane(table);

        // Add columns to the table
        String[] columns = {"Patient ID", "Name", "Username", "Email", "Phone", "City", "Gender", "Blood Group",
                "Age", "Address", "Date of Birth", "Disease"};
        model.setColumnIdentifiers(columns);

        // Fetch data from database and add to table
        fetchData();

        JPanel optionsPanel = new JPanel(new FlowLayout());
        JLabel usernameLabel = new JLabel("Enter Username:");
        usernameField = new JTextField(15);
        deleteButton = new JButton("Delete Patient");
        editButton = new JButton("Edit Patient");
        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.RED); // Set background color to red

        optionsPanel.add(usernameLabel);
        optionsPanel.add(usernameField);
        optionsPanel.add(deleteButton);
        optionsPanel.add(editButton);
        optionsPanel.add(cancelButton); // Add cancel button

        contentPane.add(optionsPanel, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                deletePatient(username);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a username.");
                } else {
                    frame.setVisible(false);
                    //new Edit_Patient(username).setVisible(true);
                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to go back to AdminHomePage
                frame.dispose(); // Close the current window
                JOptionPane.showMessageDialog(frame, "Action terminated...click here to go home page");
                new Index().setVisible(true); // Open AdminHomePage
            }
        });

        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    private void fetchData() {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patient");

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("patient_id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("username"));
                row.add(rs.getString("email"));
                row.add(rs.getString("phone"));
                row.add(rs.getString("city"));
                row.add(rs.getString("gender"));
                row.add(rs.getString("blood_group"));
                row.add(rs.getString("age"));
                row.add(rs.getString("address"));
                row.add(rs.getString("dob"));
                row.add(rs.getString("disease"));
                model.addRow(row);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while fetching patient data.");
        }
    }

    private void deletePatient(String username) {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            Statement stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate("DELETE FROM patient WHERE username = '" + username + "'");
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Patient deleted successfully.");
                model.setRowCount(0); // Clear the existing rows
                fetchData(); // Refresh the table after deletion
            } else {
                JOptionPane.showMessageDialog(frame, "No patient found with the given username.");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while deleting patient.");
        }
    }

    public static void main(String[] args) {
        new View_Patient("patient");
    }
}
