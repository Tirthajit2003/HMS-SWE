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

public class View_Doctor {
    JFrame frame;
    JTable table;
    JTextField usernameField;
    JButton deleteButton;
    JButton editButton;
    JButton cancelButton; // Added cancel button
    DefaultTableModel model;
    String adminname;

    View_Doctor(String name) {
        this.adminname=name;
        System.out.println(adminname);
        frame = new JFrame("View Doctors");
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
        String[] columns = {"Doctor ID", "Name", "Username", "Password", "DOB", "Address", "Phone", "City",
                "Gender", "Blood Group", "Joining Date", "Age", "Specialization", "Clinic Number", "Availability"};
        model.setColumnIdentifiers(columns);

        // Fetch data from database and add to table
        fetchData();

        JPanel optionsPanel = new JPanel(new FlowLayout());
        JLabel usernameLabel = new JLabel("Enter Username:");
        usernameField = new JTextField(15);
        deleteButton = new JButton("Delete Doctor");
        editButton = new JButton("Edit Doctor");
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
                deleteDoctor(username);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a username.");
                }else {
                    frame.setVisible(false);
                    new Edit_Doctor(username).setVisible(true);
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM doctor");

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("doc_id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("username"));
                row.add(rs.getString("password"));
                row.add(rs.getString("dob"));
                row.add(rs.getString("address"));
                row.add(rs.getString("phone"));
                row.add(rs.getString("city"));
                row.add(rs.getString("gender"));
                row.add(rs.getString("blood_group"));
                row.add(rs.getString("joining_date"));
                row.add(rs.getString("age"));
                row.add(rs.getString("specialization"));
                row.add(rs.getString("clinic_number"));
                row.add(rs.getString("availability"));
                model.addRow(row);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while fetching doctor data.");
        }
    }

    private void deleteDoctor(String username) {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            Statement stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate("DELETE FROM doctor WHERE username = '" + username + "'");
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Doctor deleted successfully.");
                model.setRowCount(0); // Clear the existing rows
                fetchData(); // Refresh the table after deletion
            } else {
                JOptionPane.showMessageDialog(frame, "No doctor found with the given username.");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while deleting doctor.");
        }
    }

    public static void main(String[] args) {
        new View_Doctor("doctor");
    }
}
