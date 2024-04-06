/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hospital;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Random;

public class Add_Doctor extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21, l22, l23, l24, l25, l26;
    JTextField t1,t2,t5, t4, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15;
    JPasswordField t3;
    JButton submit,cancel;
    String aname;
    
    Add_Doctor(String name) {
        aname=name;
        f = new JFrame("Add Doctor");
        f.setBackground(Color.white);
        f.setLayout(null);
        
        l1 = new JLabel();
        l1.setBounds(0, 0, 900, 600);
        l1.setLayout(null);
        
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Hospital/Icons/add_doc.jpg"));
        Image i1 = img.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        ImageIcon img1 = new ImageIcon(i1);
        l1.setIcon(img1);
        
        l2 = new JLabel("Add Doctor Details");
        l2.setBounds(350, 30, 500, 50);
        l2.setFont(new Font("Arial", Font.BOLD, 30));
        l2.setForeground(Color.BLACK);
        
        l1.add(l2);
        f.add(l1);
        
        // Labels for various details
        l3 = new JLabel("Name");
        l3.setBounds(50, 150, 150, 30);
        l3.setFont(new Font("Arial", Font.BOLD, 20));
        l3.setForeground(Color.BLACK);
        l1.add(l3);
        t1 = new JTextField();
        t1.setBounds(200, 150, 150, 30);
        l1.add(t1);
        
        l4 = new JLabel("Username");
        l4.setBounds(50, 200, 150, 30);
        l4.setFont(new Font("Arial", Font.BOLD, 20));
        l4.setForeground(Color.BLACK);
        l1.add(l4);
        t2 = new JTextField();
        t2.setBounds(200, 200, 150, 30);
        l1.add(t2);
        
        l5 = new JLabel("Password");
        l5.setBounds(50, 250, 150, 30);
        l5.setFont(new Font("Arial", Font.BOLD, 20));
        l5.setForeground(Color.BLACK);
        l1.add(l5);
        t3 = new JPasswordField();
        t3.setBounds(200, 250, 150, 30);
        l1.add(t3);
        
        l6 = new JLabel("Date of Birth");
        l6.setBounds(50, 300, 150, 30);
        l6.setFont(new Font("Arial", Font.BOLD, 20));
        l6.setForeground(Color.BLACK);
        l1.add(l6);
        t4 = new JTextField();
        t4.setBounds(200, 300, 150, 30);
        l1.add(t4);
        
        l7 = new JLabel("Address");
        l7.setBounds(50, 350, 150, 30);
        l7.setFont(new Font("Arial", Font.BOLD, 20));
        l7.setForeground(Color.BLACK);
        l1.add(l7);
        t5 = new JTextField();
        t5.setBounds(200, 350, 150, 30);
        l1.add(t5);
        
        l8 = new JLabel("Phone");
        l8.setBounds(50, 400, 150, 30);
        l8.setFont(new Font("Arial", Font.BOLD, 20));
        l8.setForeground(Color.BLACK);
        l1.add(l8);
        t6 = new JTextField();
        t6.setBounds(200, 400, 150, 30);
        l1.add(t6);
        
        l9 = new JLabel("City");
        l9.setBounds(50, 450, 150, 30);
        l9.setFont(new Font("Arial", Font.BOLD, 20));
        l9.setForeground(Color.BLACK);
        l1.add(l9);
        t7 = new JTextField();
        t7.setBounds(200, 450, 150, 30);
        l1.add(t7);
        
        // Gender label moved to the next column
        l10 = new JLabel("Gender");
        l10.setBounds(450, 150, 150, 30);
        l10.setFont(new Font("Arial", Font.BOLD, 20));
        l10.setForeground(Color.BLACK);
        l1.add(l10);
        t8 = new JTextField();
        t8.setBounds(600, 150, 150, 30);
        l1.add(t8);
        
        l11 = new JLabel("Blood Group");
        l11.setBounds(450, 200, 150, 30);
        l11.setFont(new Font("Arial", Font.BOLD, 20));
        l11.setForeground(Color.BLACK);
        l1.add(l11);
        t9 = new JTextField();
        t9.setBounds(600, 200, 150, 30);
        l1.add(t9);
        
        l12 = new JLabel("Joining Date");
        l12.setBounds(450, 250, 150, 30);
        l12.setFont(new Font("Arial", Font.BOLD, 20));
        l12.setForeground(Color.BLACK);
        l1.add(l12);
        t10 = new JTextField();
        t10.setBounds(600, 250, 150, 30);
        l1.add(t10);
        
        l13 = new JLabel("Age");
        l13.setBounds(450, 300, 150, 30);
        l13.setFont(new Font("Arial", Font.BOLD, 20));
        l13.setForeground(Color.BLACK);
        l1.add(l13);
        t11 = new JTextField();
        t11.setBounds(600, 300, 150, 30);
        l1.add(t11);
        
        l14 = new JLabel("Specialization");
        l14.setBounds(450, 350, 150, 30);
        l14.setFont(new Font("Arial", Font.BOLD, 20));
        l14.setForeground(Color.BLACK);
        l1.add(l14);
        t12 = new JTextField();
        t12.setBounds(600, 350, 150, 30);
        l1.add(t12);
        
        l15 = new JLabel("Clinic Number");
        l15.setBounds(450, 400, 150, 30);
        l15.setFont(new Font("Arial", Font.BOLD, 20));
        l15.setForeground(Color.BLACK);
        l1.add(l15);
        t13 = new JTextField();
        t13.setBounds(600, 400, 150, 30);
        l1.add(t13);
        
        submit = new JButton("Submit");
        submit.setBounds(250, 500, 100, 30);
        submit.setFont(new Font("Arial", Font.BOLD, 15));
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLACK);
        submit.addActionListener(this);
        l1.add(submit);

        // Cancel Button
        cancel = new JButton("Cancel");
        cancel.setBounds(550, 500, 100, 30);
        cancel.setFont(new Font("Arial", Font.BOLD, 15));
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.RED);
        cancel.addActionListener(this);
        l1.add(cancel);
        
        f.setSize(900, 600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) 
        {
            String name = t1.getText();
            String username = t2.getText();
            String password = t3.getText();
            String dob = t4.getText();
            String address = t5.getText();
            String phone = t6.getText();
            String city = t7.getText();
            String gender = t8.getText();
            String bloodGroup = t9.getText();
            String joiningDate = t10.getText();
            String age = t11.getText();
            String specialization = t12.getText();
            String clinicNumber = t13.getText();
            String avl="Yes";
            Random r=new Random();
            String doc_id=""+Math.abs(r.nextInt()%100000);
            
             try {
            // Establish connection using ConnectionClass
            ConnectionClass connectionClass = new ConnectionClass();
            Connection conn = connectionClass.conn;
            
            // Create SQL query to insert data into the doctor table
            String query = "INSERT INTO doctor (doc_id, name, username, password, dob, address, phone, city, gender, blood_group, joining_date, age, specialization, clinic_number, availability) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            
            // Set parameter values for the prepared statement
            pst.setString(1, doc_id);
            pst.setString(2, name);
            pst.setString(3, username);
            pst.setString(4, password);
            pst.setString(5, dob);
            pst.setString(6, address);
            pst.setString(7, phone);
            pst.setString(8, city);
            pst.setString(9, gender);
            pst.setString(10, bloodGroup);
            pst.setString(11, joiningDate);
            pst.setString(12, age);
            pst.setString(13, specialization);
            pst.setString(14, clinicNumber);
            pst.setString(15, avl);
            
            // Execute the query
            pst.executeUpdate();
            
            // Close the prepared statement
            pst.close();
            
            // Display success message or perform any further actions
            System.out.println("Doctor details added successfully.");
            JOptionPane.showMessageDialog(null,"Details successfully inserted");
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        }
            
        }   
        else if (ae.getSource() == cancel) 
        {
                f.setVisible(false);
                new AdminHomePage(aname).setVisible(true);

        }
    }
    
//    public static void main(String[] args) {
//        new Add_Doctor();
//    }
}
