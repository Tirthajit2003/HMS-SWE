/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hospital;


 /*
 *
 * @author hp
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {
    JFrame f;
    JPanel panel;
    JLabel l1,l2,l3,l4;
    JTextField tf1;
    JPasswordField pf1;
    JButton bt1,bt2;
    String tname;
    LoginPage(String tname)
    {
        this.tname=tname;
        System.out.println(tname);
        f=new JFrame("Login Page");
        f.setBackground(Color.WHITE);
        f.setLayout(null);
        
        l1=new JLabel();
        l1.setBounds(0,0,580,350);
        l1.setLayout(null);
        
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("Hospital/Icons/login.jpg"));
        Image i1=img.getImage().getScaledInstance(580, 350, Image.SCALE_SMOOTH);
        ImageIcon img1=new ImageIcon(i1);
        l1.setIcon(img1);
        
        l2=new JLabel("Login Page");
        l2.setBounds(200,100,400,30);
        l2.setFont(new Font("Arial",Font.BOLD,30));
        l2.setForeground(Color.BLACK);
        
        l3=new JLabel("Username :");
        l3.setBounds(150,150,400,30);
        l3.setFont(new Font("Arial",Font.BOLD,15));
        l3.setForeground(Color.BLACK);
        
        l4=new JLabel("Password :");
        l4.setBounds(150,200,400,30);
        l4.setFont(new Font("Arial",Font.BOLD,15));
        l4.setForeground(Color.BLACK);
        
        l1.add(l2);
        l1.add(l3);
        l1.add(l4);
        f.add(l1);
        
        tf1=new JTextField();
        tf1.setBounds(250,150,200,30);
        l1.add(tf1);
        
        pf1=new JPasswordField();
        pf1.setBounds(250,200,200,30);
        l1.add(pf1);
        
        bt1=new JButton("Login");
        bt1.setBounds(180, 250, 100, 30);
        bt1.setForeground(Color.WHITE);
        bt1.setBackground(Color.BLACK);
        l1.add(bt1);
        
        
        bt2=new JButton("Back");
        bt2.setBounds(300, 250, 100, 30);
        bt2.setForeground(Color.WHITE);
        bt2.setBackground(Color.BLACK);
        l1.add(bt2);
        
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        
        
        f.setSize(580,350);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
        
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==bt1)
        {
            try{
                ConnectionClass obj=new ConnectionClass();
                String name=tf1.getText();
                String pass=pf1.getText();
                String q="Select * from "+tname+" where username='"+name+"' and password='"+pass+"'";
                ResultSet rs=obj.stm.executeQuery(q);
                
                if (rs.next())
                {
//                    JOptionPane.showMessageDialog(null,"Login Successful");
                    String adminName=rs.getString("name");
                    if (tname.equalsIgnoreCase("admin"))
                    {
                        new AdminHomePage(adminName).setVisible(true);
                        f.setVisible(false); 
                    }
                    else if (tname.equalsIgnoreCase("doctor"))
                    {
                        new DoctorHomePage(adminName).setVisible(true);
                        f.setVisible(false);
                    }
                    //To do
                    else if (tname.equalsIgnoreCase("patient"))
                    {
                        new PatientHomePage(adminName).setVisible(true);
                        f.setVisible(false);
                    }
                    else if (tname.equalsIgnoreCase("nurse"))
                    {
                        new NurseHomePage(adminName).setVisible(true);
                        f.setVisible(false);
                    }
                    else if (tname.equalsIgnoreCase("pharmacist"))
                    {
                        new PharmacistHomePage(adminName).setVisible(true);
                        f.setVisible(false);
                    }
                    else if (tname.equalsIgnoreCase("laboratorist"))
                    {
            //            new LaboratoristHomePage(adminName).setVisible(true);
                        f.setVisible(false);
                    }
                    else if (tname.equalsIgnoreCase("accountant"))
                    {
            //            new AccountantHomePage(adminName).setVisible(true);
                        f.setVisible(false);
                    }
                    
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"You have entered wrong username or password");
                    f.setVisible(false);
//                    f.setVisible(true);
                    new LoginPage(tname);
                }
                
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
                
        }
        else
        {
            this.f.setVisible(false);
            new Index();
        }
    }

//    public static void main(String[] args)
//    {
//        new LoginPage();
//    }
}
