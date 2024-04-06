package Hospital;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Add_Nurse extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JTextField t1, t2, t3, t4, t6, t7;
    JPasswordField passwordField;
    JComboBox<String> genderDropdown;
    JButton submit, cancel;
    String adminName;

    Add_Nurse(String name) {
        adminName = name;
        f = new JFrame("Add Nurse");
        f.setBackground(Color.white);
        f.setLayout(null);

        l1 = new JLabel();
        l1.setBounds(0, 0, 750,500);
        l1.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Hospital/Icons/add_doc.jpg"));
        Image i1 = img.getImage().getScaledInstance(750, 500, Image.SCALE_SMOOTH);
        ImageIcon img1 = new ImageIcon(i1);
        l1.setIcon(img1);

        l2 = new JLabel("Add Nurse Details");
        l2.setBounds(250, 20, 500, 50);
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

        l4 = new JLabel("Phone");
        l4.setBounds(50, 150, 100, 30);
        l4.setFont(new Font("Arial", Font.BOLD, 20));
        l4.setForeground(Color.BLACK);
        l1.add(l4);
        t2 = new JTextField();
        t2.setBounds(200, 150, 150, 30);
        l1.add(t2);

        l5 = new JLabel("Address");
        l5.setBounds(50, 200, 100, 30);
        l5.setFont(new Font("Arial", Font.BOLD, 20));
        l5.setForeground(Color.BLACK);
        l1.add(l5);
        t3 = new JTextField();
        t3.setBounds(200, 200, 150, 30);
        l1.add(t3);

        l6 = new JLabel("Gender");
        l6.setBounds(50, 250, 100, 30);
        l6.setFont(new Font("Arial", Font.BOLD, 20));
        l6.setForeground(Color.BLACK);
        l1.add(l6);
        genderDropdown = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderDropdown.setBounds(200, 250, 150, 30);
        l1.add(genderDropdown);

        l7 = new JLabel("Date of Birth");
        l7.setBounds(50, 300, 150, 30);
        l7.setFont(new Font("Arial", Font.BOLD, 20));
        l7.setForeground(Color.BLACK);
        l1.add(l7);
        t4 = new JTextField();
        t4.setBounds(200, 300, 150, 30);
        l1.add(t4);

        l8 = new JLabel("Username");
        l8.setBounds(400, 100, 100, 30);
        l8.setFont(new Font("Arial", Font.BOLD, 20));
        l8.setForeground(Color.BLACK);
        l1.add(l8);
        t6 = new JTextField();
        t6.setBounds(550, 100, 150, 30);
        l1.add(t6);

        l8 = new JLabel("Password");
        l8.setBounds(400, 150, 100, 30);
        l8.setFont(new Font("Arial", Font.BOLD, 20));
        l8.setForeground(Color.BLACK);
        l1.add(l8);
        passwordField = new JPasswordField();
        passwordField.setBounds(550, 150, 150, 30);
        l1.add(passwordField);

        submit = new JButton("Submit");
        submit.setBounds(200, 350, 100, 30);
        submit.setFont(new Font("Arial", Font.BOLD, 15));
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLACK);
        submit.addActionListener(this);
        l1.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(400, 350, 100, 30);
        cancel.setFont(new Font("Arial", Font.BOLD, 15));
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.RED);
        cancel.addActionListener(this);
        l1.add(cancel);

        f.add(l1);

        f.setSize(750, 500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
    }

    private String generateNurseID() {
        // Generate a random 6-digit nurse ID
        Random rand = new Random();
        int nurseID = 100000 + rand.nextInt(900000);
        return String.valueOf(nurseID);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String nurseID = generateNurseID();
            String name = t1.getText();
            String phone = t2.getText();
            String address = t3.getText();
            String gender = (String) genderDropdown.getSelectedItem();
            String dob = t4.getText();
            String username = t6.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || dob.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(f, "All fields are required.");
            } else {
                try {
                    ConnectionClass connectionClass = new ConnectionClass();
                    Connection conn = connectionClass.conn;

                    String query = "INSERT INTO nurse (nurse_id, name, phone, address, gender, dob, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pst = conn.prepareStatement(query);

                    pst.setString(1, nurseID);
                    pst.setString(2, name);
                    pst.setString(3, phone);
                    pst.setString(4, address);
                    pst.setString(5, gender);
                    pst.setString(6, dob);
                    pst.setString(7, username);
                    pst.setString(8, password);

                    pst.executeUpdate();
                    pst.close();

                    JOptionPane.showMessageDialog(null, "Nurse added successfully. Nurse ID: " + nurseID);
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(f, "Error occurred while adding nurse.");
                }
            }
        } else if (ae.getSource() == cancel) {
            f.setVisible(false);
            new AdminHomePage(adminName).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Add_Nurse("admin");
    }
}
