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

public class View_Laboratorist {
    JFrame frame;
    JTable table;
    JTextField usernameField;
    JButton deleteButton;
    JButton editButton;
    JButton cancelButton;
    DefaultTableModel model;
    String adminName;

    View_Laboratorist(String name) {
        adminName = name;
        frame = new JFrame("View Laboratorists");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 500);
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

        String[] columns = {"Laboratorist ID", "Name", "Test Name", "Username", "Password", "Phone"};
        model.setColumnIdentifiers(columns);

        fetchData();

        JPanel optionsPanel = new JPanel(new FlowLayout());
        JLabel usernameLabel = new JLabel("Enter Username:");
        usernameField = new JTextField(15);
        deleteButton = new JButton("Delete Laboratorist");
        editButton = new JButton("Edit Laboratorist");
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
                deleteLaboratorist(username);
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
                    // Add logic for editing laboratorist
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM laboratorist");

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("laboratorist_id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("test_name"));
                row.add(rs.getString("username"));
                row.add(rs.getString("password"));
                row.add(rs.getString("phone"));
                model.addRow(row);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while fetching laboratorist data.");
        }
    }

    private void deleteLaboratorist(String username) {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            Statement stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate("DELETE FROM laboratorist WHERE username = '" + username + "'");
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Laboratorist deleted successfully.");
                model.setRowCount(0);
                fetchData();
            } else {
                JOptionPane.showMessageDialog(frame, "No laboratorist found with the given username.");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while deleting laboratorist.");
        }
    }

    public static void main(String[] args) {
        new View_Laboratorist("admin");
    }
}
