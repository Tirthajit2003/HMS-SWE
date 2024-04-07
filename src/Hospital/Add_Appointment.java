package Hospital;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Add_Appointment extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JTextField t1, t3, t4, t5, t6;
    JComboBox<String> doctorDropdown,timeDropdown;
    JButton submit, cancel;
    String adminName;

    Add_Appointment(String name) {
        adminName = name;
        f = new JFrame("Add Appointment");
        f.setBackground(Color.white);
        f.setLayout(null);
        String[] timeOptions = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};

        l1 = new JLabel();
        l1.setBounds(0, 0, 750, 550);
        l1.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Hospital/Icons/add_doc.jpg"));
        Image i1 = img.getImage().getScaledInstance(750, 550, Image.SCALE_SMOOTH);
        ImageIcon img1 = new ImageIcon(i1);
        l1.setIcon(img1);

        l2 = new JLabel("Add Appointment Details");
        l2.setBounds(200, 20, 500, 50);
        l2.setFont(new Font("Arial", Font.BOLD, 25));
        l2.setForeground(Color.BLACK);
        l1.add(l2);

        l3 = new JLabel("Patient Name");
        l3.setBounds(50, 100, 150, 30);
        l3.setFont(new Font("Arial", Font.BOLD, 20));
        l3.setForeground(Color.BLACK);
        l1.add(l3);
        t1 = new JTextField();
        t1.setBounds(200, 100, 150, 30);
        l1.add(t1);

        l4 = new JLabel("Contact");
        l4.setBounds(50, 150, 100, 30);
        l4.setFont(new Font("Arial", Font.BOLD, 20));
        l4.setForeground(Color.BLACK);
        l1.add(l4);
        t5 = new JTextField();
        t5.setBounds(200, 150, 150, 30);
        l1.add(t5);

        l5 = new JLabel("Doctor");
        l5.setBounds(50, 200, 100, 30);
        l5.setFont(new Font("Arial", Font.BOLD, 20));
        l5.setForeground(Color.BLACK);
        l1.add(l5);
        doctorDropdown = new JComboBox<>();
        doctorDropdown.setBounds(200, 200, 150, 30);
        populateDoctorDropdown(); // Populate dropdown with doctor names
        l1.add(doctorDropdown);

        l6 = new JLabel("Date");
        l6.setBounds(400, 100, 100, 30);
        l6.setFont(new Font("Arial", Font.BOLD, 20));
        l6.setForeground(Color.BLACK);
        l1.add(l6);
        t3 = new JTextField();
        t3.setBounds(550, 100, 150, 30);
        l1.add(t3);

        l7 = new JLabel("Time");
        l7.setBounds(400, 150, 100, 30);
        l7.setFont(new Font("Arial", Font.BOLD, 20));
        l7.setForeground(Color.BLACK);
        l1.add(l7);
        timeDropdown = new JComboBox<>(timeOptions); // Use time options array to populate dropdown
        timeDropdown.setBounds(550, 150, 150, 30);
        l1.add(timeDropdown);

        l8 = new JLabel("Reason");
        l8.setBounds(400, 200, 100, 30);
        l8.setFont(new Font("Arial", Font.BOLD, 20));
        l8.setForeground(Color.BLACK);
        l1.add(l8);
        t6 = new JTextField();
        t6.setBounds(550, 200, 150, 30);
        l1.add(t6);

        submit = new JButton("Submit");
        submit.setBounds(200, 250, 100, 30);
        submit.setFont(new Font("Arial", Font.BOLD, 15));
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLACK);
        submit.addActionListener(this);
        l1.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(400, 250, 100, 30);
        cancel.setFont(new Font("Arial", Font.BOLD, 15));
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.RED);
        cancel.addActionListener(this);
        l1.add(cancel);

        f.add(l1);

        f.setSize(750, 350);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
    }

    private void populateDoctorDropdown() {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            String query = "SELECT name FROM doctor";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                doctorDropdown.addItem(rs.getString("name"));
            }
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(f, "Error occurred while fetching doctor data.");
        }
    }
    private String getDoctorIDByName(String doctorName) {
    String doctorID = "";
    try {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection conn = connectionClass.conn;
        PreparedStatement stmt = conn.prepareStatement("SELECT doc_id FROM doctor WHERE name = ?");
        stmt.setString(1, doctorName);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            doctorID = rs.getString("doc_id");
        }
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return doctorID;
}

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String patientName = t1.getText();
            String contact = t5.getText();
            String doctorName = (String) doctorDropdown.getSelectedItem();
            String date = t3.getText();
            String time = (String)timeDropdown.getSelectedItem();
            String reason = t6.getText();

            // Perform validation
            if (patientName.isEmpty() || contact.isEmpty() || doctorName.isEmpty() || date.isEmpty() || time.isEmpty() || reason.isEmpty()) {
                JOptionPane.showMessageDialog(f, "All fields are required.");
            } else {
                try {
                    // Establish connection using ConnectionClass
                    ConnectionClass connectionClass = new ConnectionClass();
                    Connection conn = connectionClass.conn;

                    // Get doctor ID based on the selected doctor name
                    String doctorID = getDoctorIDByName(doctorName);

                    // Create SQL query to insert data into the appointment table
                    String query = "INSERT INTO appointment (patient_name, contact, doctor_id, date, time, reason) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement pst = conn.prepareStatement(query);

                    // Set parameter values for the prepared statement
                    pst.setString(1, patientName);
                    pst.setString(2, contact);
                    pst.setString(3, doctorID);
                    pst.setString(4, date);
                    pst.setString(5, time);
                    pst.setString(6, reason);

                    // Execute the query
                    pst.executeUpdate();

                    // Close the prepared statement
                    pst.close();

                    // Display success message or perform any further actions
                    JOptionPane.showMessageDialog(null, "Appointment details added successfully.");
                } catch (SQLException e) {
                    // Handle any SQL exceptions
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(f, "Error occurred while adding appointment details.");
                }
            }
        } else if (ae.getSource() == cancel) {
            f.setVisible(false);
            new AdminHomePage(adminName).setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Add_Appointment("admin");
    }
}

