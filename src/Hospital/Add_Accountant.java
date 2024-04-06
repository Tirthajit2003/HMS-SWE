package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Add_Accountant extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10,l11;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9;
    JPasswordField passwordField;
    JButton submit, cancel;
    String aname;

    Add_Accountant(String name) {
        aname = name;
        f = new JFrame("Add Accountant");
        f.setBackground(Color.white);
        f.setLayout(null);

        l1 = new JLabel();
        l1.setBounds(0, 0, 900, 600);
        l1.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Hospital/Icons/add_doc.jpg"));
        Image i1 = img.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        ImageIcon img1 = new ImageIcon(i1);
        l1.setIcon(img1);

        l2 = new JLabel("Add Accountant Details");
        l2.setBounds(350, 30, 500, 50);
        l2.setFont(new Font("Arial", Font.BOLD, 30));
        l2.setForeground(Color.BLACK);
        l1.add(l2);

        l3 = new JLabel("Name:");
        l3.setBounds(50, 150, 150, 30);
        l3.setFont(new Font("Arial", Font.BOLD, 20));
        l3.setForeground(Color.BLACK);
        l1.add(l3);
        t1 = new JTextField();
        t1.setBounds(200, 150, 150, 30);
        l1.add(t1);

        l4 = new JLabel("Username:");
        l4.setBounds(50, 200, 150, 30);
        l4.setFont(new Font("Arial", Font.BOLD, 20));
        l4.setForeground(Color.BLACK);
        l1.add(l4);
        t2 = new JTextField();
        t2.setBounds(200, 200, 150, 30);
        l1.add(t2);

        l5 = new JLabel("Password:");
        l5.setBounds(50, 250, 150, 30);
        l5.setFont(new Font("Arial", Font.BOLD, 20));
        l5.setForeground(Color.BLACK);
        l1.add(l5);
        passwordField = new JPasswordField();
        passwordField.setBounds(200, 250, 150, 30);
        l1.add(passwordField);

        l6 = new JLabel("Phone:");
        l6.setBounds(50, 300, 150, 30);
        l6.setFont(new Font("Arial", Font.BOLD, 20));
        l6.setForeground(Color.BLACK);
        l1.add(l6);
        t3 = new JTextField();
        t3.setBounds(200, 300, 150, 30);
        l1.add(t3);

        l7 = new JLabel("Address:");
        l7.setBounds(50, 350, 150, 30);
        l7.setFont(new Font("Arial", Font.BOLD, 20));
        l7.setForeground(Color.BLACK);
        l1.add(l7);
        t4 = new JTextField();
        t4.setBounds(200, 350, 150, 30);
        l1.add(t4);

        l8 = new JLabel("City:");
        l8.setBounds(50, 400, 150, 30);
        l8.setFont(new Font("Arial", Font.BOLD, 20));
        l8.setForeground(Color.BLACK);
        l1.add(l8);
        t5 = new JTextField();
        t5.setBounds(200, 400, 150, 30);
        l1.add(t5);

        l9 = new JLabel("Gender:");
        l9.setBounds(50, 450, 150, 30);
        l9.setFont(new Font("Arial", Font.BOLD, 20));
        l9.setForeground(Color.BLACK);
        l1.add(l9);
        t6 = new JTextField();
        t6.setBounds(200, 450, 150, 30);
        l1.add(t6);

        l10 = new JLabel("Date of Birth:");
        l10.setBounds(450, 150, 150, 30);
        l10.setFont(new Font("Arial", Font.BOLD, 20));
        l10.setForeground(Color.BLACK);
        l1.add(l10);
        t7 = new JTextField();
        t7.setBounds(600, 150, 150, 30);
        l1.add(t7);

        l11 = new JLabel("Age:");
        l11.setBounds(450, 200, 150, 30);
        l11.setFont(new Font("Arial", Font.BOLD, 20));
        l11.setForeground(Color.BLACK);
        l1.add(l11);
        t8 = new JTextField();
        t8.setBounds(600, 200, 150, 30);
        l1.add(t8);

        submit = new JButton("Submit");
        submit.setBounds(250, 500, 100, 30);
        submit.setFont(new Font("Arial", Font.BOLD, 15));
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLACK);
        submit.addActionListener(this);
        l1.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(550, 500, 100, 30);
        cancel.setFont(new Font("Arial", Font.BOLD, 15));
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.RED);
        cancel.addActionListener(this);
        l1.add(cancel);

        f.add(l1);
        f.setSize(900, 600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String name = t1.getText();
            String username = t2.getText();
            String password = passwordField.getText();
            String phone = t3.getText();
            String address = t4.getText();
            String city = t5.getText();
            String gender = t6.getText();
            String dob = t7.getText();
            String age = t8.getText();

            try {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection conn = connectionClass.conn;
                String query = "INSERT INTO accountant (name, username, password, phone, address, city, gender, dob, age) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, name);
                pst.setString(2, username);
                pst.setString(3, password);
                pst.setString(4, phone);
                pst.setString(5, address);
                pst.setString(6, city);
                pst.setString(7, gender);
                pst.setString(8, dob);
                pst.setString(9, age);
                pst.executeUpdate();
                pst.close();
                JOptionPane.showMessageDialog(null, "Details successfully inserted");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == cancel) {
            f.setVisible(false);
            new AdminHomePage(aname).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Add_Accountant("admin");
    }
}
