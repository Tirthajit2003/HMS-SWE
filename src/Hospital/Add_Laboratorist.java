package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Add_Laboratorist extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3, l4, l5, l6, l7;
    JTextField t1, t4,  t6;
    JPasswordField t5;
    JComboBox<String> testNameComboBox;
    JButton submit, cancel;
    String adminName;

    Add_Laboratorist(String name) {
        adminName = name;
        f = new JFrame("Add Laboratorist");
        f.setBackground(Color.white);
        f.setLayout(null);

        l1 = new JLabel();
        l1.setBounds(0, 0, 750, 550);
        l1.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Hospital/Icons/add_doc.jpg"));
        Image i1 = img.getImage().getScaledInstance(750, 550, Image.SCALE_SMOOTH);
        ImageIcon img1 = new ImageIcon(i1);
        l1.setIcon(img1);

        l2 = new JLabel("Add Laboratorist Details");
        l2.setBounds(200, 20, 500, 50);
        l2.setFont(new Font("Arial", Font.BOLD, 25));
        l2.setForeground(Color.BLACK);
        l1.add(l2);

        l3 = new JLabel("Name");
        l3.setBounds(50, 100, 100, 30);
        l3.setFont(new Font("Arial", Font.BOLD, 20));
        l3.setForeground(Color.BLACK);
        l1.add(l3);
        t1 = new JTextField();
        t1.setBounds(200, 100, 150, 30);
        l1.add(t1);

        l4 = new JLabel("Test Name");
        l4.setBounds(50, 150, 100, 30);
        l4.setFont(new Font("Arial", Font.BOLD, 20));
        l4.setForeground(Color.BLACK);
        l1.add(l4);

        // Create a JComboBox for selecting test names with multiple selections
        String[] testNames = {"Blood Test", "Urine Test", "X-ray", "MRI", "CT Scan"};
        testNameComboBox = new JComboBox<>(testNames);
        testNameComboBox.setBounds(200, 150, 150, 30);
        l1.add(testNameComboBox);

        l5 = new JLabel("Username");
        l5.setBounds(400, 100, 100, 30);
        l5.setFont(new Font("Arial", Font.BOLD, 20));
        l5.setForeground(Color.BLACK);
        l1.add(l5);
        t4 = new JTextField();
        t4.setBounds(550, 100, 150, 30);
        l1.add(t4);

        l6 = new JLabel("Password");
        l6.setBounds(400, 150, 100, 30);
        l6.setFont(new Font("Arial", Font.BOLD, 20));
        l6.setForeground(Color.BLACK);
        l1.add(l6);
        t5 = new JPasswordField();
        t5.setBounds(550, 150, 150, 30);
        l1.add(t5);

        l7 = new JLabel("Phone Number");
        l7.setBounds(50, 200, 150, 30);
        l7.setFont(new Font("Arial", Font.BOLD, 20));
        l7.setForeground(Color.BLACK);
        l1.add(l7);
        t6 = new JTextField();
        t6.setBounds(200, 200, 150, 30);
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

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String name = t1.getText();
            String testName = (String) testNameComboBox.getSelectedItem();
            String username = t4.getText();
            String password = t5.getText();
            String phone = t6.getText();

            // Perform validation
            if (name.isEmpty() || testName == null || username.isEmpty() || password.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(f, "All fields are required.");
            } else {
                try {
                    // Establish connection using ConnectionClass
                    ConnectionClass connectionClass = new ConnectionClass();
                    Connection conn = connectionClass.conn;

                    // Generate a random Laboratorist ID
                    String laboratoristID = generateLaboratoristID();

                    // Create SQL query to insert data into the laboratorist table
                    String query = "INSERT INTO laboratorist (laboratorist_id, name, test_name, username, password, phone) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement pst = conn.prepareStatement(query);

                    // Set parameter values for the prepared statement
                    pst.setString(1, laboratoristID);
                    pst.setString(2, name);
                    pst.setString(3, testName);
                    pst.setString(4, username);
                    pst.setString(5, password);
                    pst.setString(6, phone);

                    // Execute the query
                    pst.executeUpdate();

                    // Close the prepared statement
                    pst.close();

                    // Display success message or perform any further actions
                    JOptionPane.showMessageDialog(null, "Laboratorist details added successfully.");
                } catch (SQLException e) {
                    // Handle any SQL exceptions
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(f, "Error occurred while adding laboratorist details.");
                }
            }
        } else if (ae.getSource() == cancel) {
            f.setVisible(false);
            new AdminHomePage(adminName).setVisible(true);
        }
    }

    // Method to generate a random Laboratorist ID
    private String generateLaboratoristID() {
        // Generate a random number between 100000 and 999999
        int randomNumber = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(randomNumber);
    }

    public static void main(String[] args) {
        new Add_Laboratorist("admin");
    }
}
