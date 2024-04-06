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

public class Change_Password extends JFrame implements ActionListener {
    JFrame frame;
    JLabel currentPasswordLabel, newPasswordLabel, confirmPasswordLabel;
    JPasswordField currentPasswordField, newPasswordField, confirmPasswordField;
    JButton confirmButton, backButton;
    String adminUsername;

    Change_Password(String username) {
        adminUsername = username;
        frame = new JFrame("Change Password");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Increased height
        frame.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        currentPasswordLabel = new JLabel("Current Password:");
        newPasswordLabel = new JLabel("New Password:");
        confirmPasswordLabel = new JLabel("Confirm New Password:");

        currentPasswordField = new JPasswordField(15);
        newPasswordField = new JPasswordField(15);
        confirmPasswordField = new JPasswordField(15);

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.RED);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        contentPane.add(currentPasswordLabel, gbc);

        gbc.gridy = 1;
        contentPane.add(newPasswordLabel, gbc);

        gbc.gridy = 2;
        contentPane.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(currentPasswordField, gbc);

        gbc.gridy = 1;
        contentPane.add(newPasswordField, gbc);

        gbc.gridy = 2;
        contentPane.add(confirmPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 5, 5, 5);
        contentPane.add(confirmButton, gbc);

        gbc.gridy = 4;
        contentPane.add(backButton, gbc);

        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == confirmButton) {
            String currentPassword = new String(currentPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            // Perform validation
            if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "New Password and Confirm Password do not match.");
                return;
            }

            try {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection conn = connectionClass.conn;

                // Verify the current password
                PreparedStatement verifyStatement = conn.prepareStatement("SELECT password FROM admin WHERE username=?");
                verifyStatement.setString(1, adminUsername);
                ResultSet rs = verifyStatement.executeQuery();

                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    if (!currentPassword.equals(storedPassword)) {
                        JOptionPane.showMessageDialog(frame, "Incorrect current password.");
                        return;
                    }
                }

                // Update the password
                PreparedStatement updateStatement = conn.prepareStatement("UPDATE admin SET password=? WHERE username=?");
                updateStatement.setString(1, newPassword);
                updateStatement.setString(2, adminUsername);
                updateStatement.executeUpdate();

                JOptionPane.showMessageDialog(frame, "Password updated successfully.");

                conn.close();
                frame.dispose(); // Close the ChangePassword window after successful password change
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error occurred while updating password.");
            }
        } else if (ae.getSource() == backButton) {
            frame.dispose();
            new AdminHomePage(adminUsername).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Change_Password("admin");
    }
}
