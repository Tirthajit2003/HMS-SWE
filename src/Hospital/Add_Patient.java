/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hospital;

/**
 *
 * @author hp
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Random;


public class Add_Patient extends JFrame implements ActionListener {
    JFrame f;
    JLabel l0, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14;
    JTextField t1, t2, t4, t5, t6, t7, t9, t10, t11, t12, t13, t14;
    JPasswordField t3;
    JButton submit, cancel;
    String aname;

    Add_Patient(String name) {
        f = new JFrame("Add Patient");
        aname=name;
        f.setLayout(null);

        l0 = new JLabel();
        l0.setBounds(0, 0, 900, 600);
        l0.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Hospital/Icons/add_doc.jpg"));
        Image i1 = img.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        ImageIcon img1 = new ImageIcon(i1);
        l0.setIcon(img1);

        l1 = new JLabel("Add Patient Details");
        l1.setBounds(350, 30, 500, 50);
        l1.setFont(new Font("Arial", Font.BOLD, 30));
        l1.setForeground(Color.BLACK);
        l0.add(l1);

        l2 = new JLabel("Name");
        l2.setBounds(50, 150, 150, 30);
        l2.setFont(new Font("Arial", Font.BOLD, 20));
        l2.setForeground(Color.BLACK);
        l0.add(l2);

        t1 = new JTextField();
        t1.setBounds(200, 150, 150, 30);
        l0.add(t1);

        l3 = new JLabel("Username");
        l3.setBounds(450, 150, 150, 30);
        l3.setFont(new Font("Arial", Font.BOLD, 20));
        l3.setForeground(Color.BLACK);
        l0.add(l3);

        t2 = new JTextField();
        t2.setBounds(600, 150, 150, 30);
        l0.add(t2);

        l4 = new JLabel("Password");
        l4.setBounds(50, 200, 150, 30);
        l4.setFont(new Font("Arial", Font.BOLD, 20));
        l4.setForeground(Color.BLACK);
        l0.add(l4);

        t3 = new JPasswordField();
        t3.setBounds(200, 200, 150, 30);
        l0.add(t3);

        l5 = new JLabel("Email");
        l5.setBounds(450, 200, 150, 30);
        l5.setFont(new Font("Arial", Font.BOLD, 20));
        l5.setForeground(Color.BLACK);
        l0.add(l5);

        t4 = new JTextField();
        t4.setBounds(600, 200, 150, 30);
        l0.add(t4);

        l6 = new JLabel("Father's Name");
        l6.setBounds(50, 250, 150, 30);
        l6.setFont(new Font("Arial", Font.BOLD, 20));
        l6.setForeground(Color.BLACK);
        l0.add(l6);

        t5 = new JTextField();
        t5.setBounds(200, 250, 150, 30);
        l0.add(t5);

        l7 = new JLabel("Phone");
        l7.setBounds(450, 250, 150, 30);
        l7.setFont(new Font("Arial", Font.BOLD, 20));
        l7.setForeground(Color.BLACK);
        l0.add(l7);

        t6 = new JTextField();
        t6.setBounds(600, 250, 150, 30);
        l0.add(t6);

        l8 = new JLabel("City");
        l8.setBounds(50, 300, 150, 30);
        l8.setFont(new Font("Arial", Font.BOLD, 20));
        l8.setForeground(Color.BLACK);
        l0.add(l8);

        t7 = new JTextField();
        t7.setBounds(200, 300, 150, 30);
        l0.add(t7);

        l9 = new JLabel("Gender");
        l9.setBounds(450, 300, 150, 30);
        l9.setFont(new Font("Arial", Font.BOLD, 20));
        l9.setForeground(Color.BLACK);
        l0.add(l9);

        t9 = new JTextField();
        t9.setBounds(600, 300, 150, 30);
        l0.add(t9);

        l10 = new JLabel("Blood Group");
        l10.setBounds(50, 350, 150, 30);
        l10.setFont(new Font("Arial", Font.BOLD, 20));
        l10.setForeground(Color.BLACK);
        l0.add(l10);

        t10 = new JTextField();
        t10.setBounds(200, 350, 150, 30);
        l0.add(t10);

        l11 = new JLabel("Age");
        l11.setBounds(450, 350, 150, 30);
        l11.setFont(new Font("Arial", Font.BOLD, 20));
        l11.setForeground(Color.BLACK);
        l0.add(l11);

        t11 = new JTextField();
        t11.setBounds(600, 350, 150, 30);
        l0.add(t11);

        l12 = new JLabel("Address");
        l12.setBounds(50, 400, 150, 30);
        l12.setFont(new Font("Arial", Font.BOLD, 20));
        l12.setForeground(Color.BLACK);
        l0.add(l12);

        t12 = new JTextField();
        t12.setBounds(200, 400, 150, 30);
        l0.add(t12);

        l13 = new JLabel("Date of Birth");
        l13.setBounds(450, 400, 150, 30);
        l13.setFont(new Font("Arial", Font.BOLD, 20));
        l13.setForeground(Color.BLACK);
        l0.add(l13);

        t13 = new JTextField();
        t13.setBounds(600, 400, 150, 30);
        l0.add(t13);

        l14 = new JLabel("Disease");
        l14.setBounds(50, 450, 150, 30);
        l14.setFont(new Font("Arial", Font.BOLD, 20));
        l14.setForeground(Color.BLACK);
        l0.add(l14);

        t14 = new JTextField();
        t14.setBounds(200, 450, 150, 30);
        l0.add(t14);

        submit = new JButton("Submit");
        submit.setBounds(250, 500, 100, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        l0.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 500, 100, 30);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        l0.add(cancel);

        submit.addActionListener(this);
        cancel.addActionListener(this);

        f.setSize(900, 650);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(l0); // Add the label with image and components to the frame
        f.setVisible(true);
        f.setResizable(false);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String name = t1.getText();
            String username = t2.getText();
            String password = t3.getText();
            String email = t4.getText();
            String fatherName = t5.getText();
            String phone = t6.getText();
            String city = t7.getText();
            String gender = t9.getText();
                        String bloodGroup = t10.getText();
            String age = t11.getText();
            String address = t12.getText();
            String dob = t13.getText();
            String disease = t14.getText();

            try {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection conn = connectionClass.conn;
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO patient (name, username, password, email, father_name, phone, city, gender, blood_group, age, address, dob, disease) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, name);
                stmt.setString(2, username);
                stmt.setString(3, password);
                stmt.setString(4, email);
                stmt.setString(5, fatherName);
                stmt.setString(6, phone);
                stmt.setString(7, city);
                stmt.setString(8, gender);
                stmt.setString(9, bloodGroup);
                stmt.setString(10, age);
                stmt.setString(11, address);
                stmt.setString(12, dob);
                stmt.setString(13, disease);
                
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(f, "Patient added successfully.");
                    f.dispose(); // Close the window after adding
                    
                } else {
                    JOptionPane.showMessageDialog(f, "Failed to add patient.");
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(f, "Error occurred while adding patient.");
            }
        } else if (ae.getSource() == cancel) {
            f.setVisible(false);
            new AdminHomePage(aname).setVisible(true);
        }
    }

//    public static void main(String[] args) {
//        new Add_Patient();
//    }
}

            
           

