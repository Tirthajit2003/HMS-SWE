package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Add_Prescription extends JFrame implements ActionListener {
    JFrame frame;
    JLabel patientLabel, diseaseLabel, medicineLabel, testLabel, bmiLabel, pastIllnessLabel, doctorLabel;
    JComboBox<String> medicineComboBox, testComboBox, patientComboBox;
    JTextField bmiTextField, pastIllnessTextField;
    JButton submitButton, cancelButton;
    String doctorName;

    Add_Prescription(String doctorName) {
        this.doctorName = doctorName;
        frame = new JFrame("Add Prescription");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new GridLayout(9, 2, 10, 10));

        patientLabel = new JLabel("Patient Name:");
        contentPane.add(patientLabel);

        patientComboBox = new JComboBox<>(fetchPatientNames());
        patientComboBox.addActionListener(this);
        contentPane.add(patientComboBox);

        diseaseLabel = new JLabel("Disease:");
        contentPane.add(diseaseLabel);

        // Fetch disease for selected patient
        String selectedPatient = (String) patientComboBox.getSelectedItem();
        String disease = fetchDiseaseForPatient(selectedPatient);
        JLabel diseaseValueLabel = new JLabel(disease);
        contentPane.add(diseaseValueLabel);

        medicineLabel = new JLabel("Medicine:");
        contentPane.add(medicineLabel);

        medicineComboBox = new JComboBox<>(fetchMedicinesFromPharmacistSpecialization());
        contentPane.add(medicineComboBox);

        testLabel = new JLabel("Lab Test:");
        contentPane.add(testLabel);

        testComboBox = new JComboBox<>(fetchLabTestsFromLaboratoristSpecialization());
        contentPane.add(testComboBox);

        bmiLabel = new JLabel("BMI:");
        contentPane.add(bmiLabel);

        bmiTextField = new JTextField();
        contentPane.add(bmiTextField);

        pastIllnessLabel = new JLabel("Past Illnesses:");
        contentPane.add(pastIllnessLabel);

        pastIllnessTextField = new JTextField();
        contentPane.add(pastIllnessTextField);

        doctorLabel = new JLabel("Verified By: " + doctorName);
        contentPane.add(doctorLabel);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        contentPane.add(submitButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.RED);
        cancelButton.addActionListener(this);
        contentPane.add(cancelButton);
       

        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    // Method to fetch patient names from the patient table
    private String[] fetchPatientNames() {
        List<String> patientNames = new ArrayList<>();
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            String query = "SELECT name FROM patient";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                patientNames.add(rs.getString("name"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while fetching patient names.");
        }
        return patientNames.toArray(new String[0]);
    }

    // Method to fetch disease for a specific patient
    private String fetchDiseaseForPatient(String patientName) {
        String disease = "";
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            String query = "SELECT disease FROM patient WHERE name = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, patientName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                disease = rs.getString("disease");
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while fetching disease for the patient.");
        }
        return disease;
    }

    // Method to fetch medicines from pharmacist specialization column
    private String[] fetchMedicinesFromPharmacistSpecialization() {
        List<String> medicines = new ArrayList<>();
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            String query = "SELECT specialization FROM pharmacist";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                medicines.add(rs.getString("specialization"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while fetching medicines.");
        }
        return medicines.toArray(new String[0]);
    }

    // Method to fetch lab tests from laboratorist specialization column
    private String[] fetchLabTestsFromLaboratoristSpecialization() {
        List<String> labTests = new ArrayList<>();
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            String query = "SELECT test_name FROM laboratorist";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                labTests.add(rs.getString("test_name"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while fetching lab tests.");
        }
        return labTests.toArray(new String[0]);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            String patientName = (String) patientComboBox.getSelectedItem();
            String medicine = (String) medicineComboBox.getSelectedItem();
            String labTest = (String) testComboBox.getSelectedItem();
            String bmi = bmiTextField.getText();
            String pastIllnesses = pastIllnessTextField.getText();

            addPrescriptionToDatabase(patientName, medicine, labTest, bmi, pastIllnesses, doctorName);
        } else if (ae.getSource() == cancelButton) {
            frame.dispose();
            new DoctorHomePage(doctorName).setVisible(true);
        } else if (ae.getSource() == patientComboBox) {
            // Fetch and update disease label for the selected patient
            String selectedPatient = (String) patientComboBox.getSelectedItem();
            String disease = fetchDiseaseForPatient(selectedPatient);
            JLabel diseaseValueLabel = new JLabel(disease);
            frame.getContentPane().remove(3); // Remove old disease label
            frame.getContentPane().add(diseaseValueLabel, 3); // Add new disease label
            frame.revalidate();
            frame.repaint();
        }
    }

    private void addPrescriptionToDatabase(String patientName, String medicine, String labTest, String bmi, String pastIllnesses, String doctorName) {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            String query = "INSERT INTO prescriptions (prescription_id, patient_name, medicine, lab_test, bmi, past_illnesses, verified_by) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, generatePrescriptionID());
            pstmt.setString(2, patientName);
            pstmt.setString(3, medicine);
            pstmt.setString(4, labTest);
            pstmt.setString(5, bmi);
            pstmt.setString(6, pastIllnesses);
            pstmt.setString(7, doctorName);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            JOptionPane.showMessageDialog(frame, "Prescription added successfully.");
            frame.dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while adding prescription.");
        }
    }

    private String generatePrescriptionID() {
        // Generate a random number between 1000 and 9999
        Random random = new Random();
        return String.valueOf(1000 + random.nextInt(9000));
    }

    public static void main(String[] args) {
        new Add_Prescription("Dr. Smith");
    }
}
