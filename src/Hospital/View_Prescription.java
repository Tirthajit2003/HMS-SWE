package Hospital;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class View_Prescription {
    JFrame frame;
    JTable table;
    JTextField patientNameField;
    JButton deleteButton;
    JButton editButton;
    JButton cancelButton;
    DefaultTableModel model;
    String doctorName;

    View_Prescription(String doctorName) {
        this.doctorName = doctorName;
        frame = new JFrame("View Prescriptions");
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

        String[] columns = {"Prescription ID", "Patient Name", "Medicine", "Lab Test", "BMI", "Past Illnesses", "Verified By"};
        model.setColumnIdentifiers(columns);

        fetchData();

        JPanel optionsPanel = new JPanel(new FlowLayout());
        JLabel patientNameLabel = new JLabel("Enter Patient Name:");
        patientNameField = new JTextField(15);
        deleteButton = new JButton("Delete Prescription");
        editButton = new JButton("Edit Prescription");
        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.RED);

        optionsPanel.add(patientNameLabel);
        optionsPanel.add(patientNameField);
        optionsPanel.add(deleteButton);
        optionsPanel.add(editButton);
        optionsPanel.add(cancelButton);

        contentPane.add(optionsPanel, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = patientNameField.getText();
                deletePrescription(patientName);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = patientNameField.getText();
                if (patientName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a patient name.");
                } else {
                    frame.setVisible(false);
                    // Add logic for editing prescription
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
            String query = "SELECT * FROM prescriptions";
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("prescription_id"));
                row.add(rs.getString("patient_name"));
                row.add(rs.getString("medicine"));
                row.add(rs.getString("lab_test"));
                row.add(rs.getString("bmi"));
                row.add(rs.getString("past_illnesses"));
                row.add(rs.getString("verified_by"));
                model.addRow(row);
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while fetching prescriptions.");
        }
    }

    private void deletePrescription(String patientName) {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            String query = "DELETE FROM prescriptions WHERE patient_name = ? AND verified_by = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, patientName);
            pstmt.setString(2, doctorName);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Prescription deleted successfully.");
                model.setRowCount(0);
                fetchData();
            } else {
                JOptionPane.showMessageDialog(frame, "No prescription found for the given patient name.");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while deleting prescription.");
        }
    }

    public static void main(String[] args) {
        new View_Prescription("Dr. Smith");
    }
}
