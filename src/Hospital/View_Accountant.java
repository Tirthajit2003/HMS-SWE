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

public class View_Accountant {
    JFrame frame;
    JTable table;
    JTextField usernameField;
    JButton deleteButton;
    JButton editButton;
    JButton cancelButton;
    DefaultTableModel model;
    String adminName;

    View_Accountant(String name) {
        adminName = name;
        frame = new JFrame("View Accountants");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 500);
        frame.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);

        String[] columns = {"Accountant ID", "Name", "Username", "Password", "Phone", "Address", "City", "Gender", "DOB", "Age"};
        model.setColumnIdentifiers(columns);

        fetchData();

        JPanel optionsPanel = new JPanel(new FlowLayout());
        JLabel usernameLabel = new JLabel("Enter Username:");
        usernameField = new JTextField(15);
        deleteButton = new JButton("Delete Accountant");
        editButton = new JButton("Edit Accountant");
        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.RED);

        optionsPanel.add(usernameLabel);
        optionsPanel.add(usernameField);
        optionsPanel.add(deleteButton);
        optionsPanel.add(editButton);
        optionsPanel.add(cancelButton);

        contentPane.add(optionsPanel, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                deleteAccountant(username);
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
                    // Add logic for editing accountant
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JOptionPane.showMessageDialog(frame, "Action terminated...click here to go home page");
                new Index().setVisible(true);
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM accountant");

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("acc_id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("username"));
                row.add(rs.getString("password"));
                row.add(rs.getString("phone"));
                row.add(rs.getString("address"));
                row.add(rs.getString("city"));
                row.add(rs.getString("gender"));
                row.add(rs.getString("dob"));
                row.add(rs.getString("age"));
                model.addRow(row);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while fetching accountant data.");
        }
    }

    private void deleteAccountant(String username) {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            Statement stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate("DELETE FROM accountant WHERE username = '" + username + "'");
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Accountant deleted successfully.");
                model.setRowCount(0);
                fetchData();
            } else {
                JOptionPane.showMessageDialog(frame, "No accountant found with the given username.");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while deleting accountant.");
        }
    }

    public static void main(String[] args) {
        new View_Accountant("admin");
    }
}
