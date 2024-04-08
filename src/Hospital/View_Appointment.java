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

public class View_Appointment {
    JFrame frame;
    JTable table;
    JTextField patientNameField;
    JButton deleteButton;
    JButton editButton;
    JButton cancelButton;
    DefaultTableModel model;
    String adminName;

    View_Appointment(String name) {
        adminName = name;
        frame = new JFrame("View Appointments");
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

        String[] columns = {"Appointment ID", "Patient Name", "Contact", "Doctor ID", "Date", "Time", "Reason"};
        model.setColumnIdentifiers(columns);

        fetchData();

        JPanel optionsPanel = new JPanel(new FlowLayout());
        JLabel patientNameLabel = new JLabel("Enter Patient Name:");
        patientNameField = new JTextField(15);
        deleteButton = new JButton("Delete Appointment");
        editButton = new JButton("Edit Appointment");
        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.RED);

        optionsPanel.add(patientNameLabel);
        optionsPanel.add(patientNameField);
        optionsPanel.add(deleteButton);
        optionsPanel.add(editButton);
        optionsPanel.add(cancelButton);

        contentPane.add(optionsPanel, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        deleteButton.addActionListener(e -> {
            String patientName = patientNameField.getText();
            deleteAppointment(patientName);
        });

        editButton.addActionListener(e -> {
            String patientName = patientNameField.getText();
            if (patientName.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a patient name.");
            } else {
                frame.setVisible(false);
                // Add logic for editing appointment
            }
        });

        cancelButton.addActionListener(e -> {
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Action terminated...click here to go home page");
            new Index().setVisible(true);
        });

        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    private void fetchData() {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM appointment");

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("app_id"));
                row.add(rs.getString("patient_name"));
                row.add(rs.getString("contact"));
                row.add(rs.getString("doctor_id"));
                row.add(rs.getString("date"));
                row.add(rs.getString("time"));
                row.add(rs.getString("reason"));
                model.addRow(row);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while fetching appointment data.");
        }
    }

    private void deleteAppointment(String patientName) {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            Statement stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate("DELETE FROM appointment WHERE patient_name = '" + patientName + "'");
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Appointment deleted successfully.");
                model.setRowCount(0);
                fetchData();
            } else {
                JOptionPane.showMessageDialog(frame, "No appointment found with the given patient name.");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while deleting appointment.");
        }
    }

    public static void main(String[] args) {
        new View_Appointment("admin");
    }
}
