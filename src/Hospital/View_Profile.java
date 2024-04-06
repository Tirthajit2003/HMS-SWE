package Hospital;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class View_Profile extends JFrame {
    JFrame frame;
    JLabel nameLabel, usernameLabel, ageLabel, phoneLabel, cityLabel, emailLabel, genderLabel;
    String adminname;

    View_Profile(String name) {
        adminname = name;
        frame = new JFrame("View Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new GridLayout(8, 2, 10, 10));

        nameLabel = new JLabel("Name:");
        usernameLabel = new JLabel("Username:");
        ageLabel = new JLabel("Age:");
        phoneLabel = new JLabel("Phone:");
        cityLabel = new JLabel("City:");
        emailLabel = new JLabel("Email:");
        genderLabel = new JLabel("Gender:");

        JLabel nameValueLabel = new JLabel();
        JLabel usernameValueLabel = new JLabel();
        JLabel ageValueLabel = new JLabel();
        JLabel phoneValueLabel = new JLabel();
        JLabel cityValueLabel = new JLabel();
        JLabel emailValueLabel = new JLabel();
        JLabel genderValueLabel = new JLabel();

        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM admin WHERE name='" + adminname + "'");

            if (rs.next()) {
                nameValueLabel.setText(rs.getString("name"));
                usernameValueLabel.setText(rs.getString("username"));
                ageValueLabel.setText(rs.getString("age"));
                phoneValueLabel.setText(rs.getString("phone"));
                cityValueLabel.setText(rs.getString("city"));
                emailValueLabel.setText(rs.getString("email"));
                genderValueLabel.setText(rs.getString("gender"));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error occurred while fetching admin data.");
        }

        addRow(contentPane, nameLabel, nameValueLabel);
        addRow(contentPane, usernameLabel, usernameValueLabel);
        addRow(contentPane, ageLabel, ageValueLabel);
        addRow(contentPane, phoneLabel, phoneValueLabel);
        addRow(contentPane, cityLabel, cityValueLabel);
        addRow(contentPane, emailLabel, emailValueLabel);
        addRow(contentPane, genderLabel, genderValueLabel);

        // Adding the back button
        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.RED);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminHomePage(adminname).setVisible(true);
            }
        });
        contentPane.add(backButton);
        
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    private void addRow(JPanel panel, JLabel label, JLabel valueLabel) {
        panel.add(label);
        panel.add(valueLabel);
    }

    public static void main(String[] args) {
        new View_Profile("Tirthajit");
    }
}
