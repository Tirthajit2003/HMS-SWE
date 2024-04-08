/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hospital;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author hp
 */
public class AccountantHomePage extends JFrame implements ActionListener {
    JLabel l1;
    Font f,f1,f2;
    String name;
    AccountantHomePage(String adminName){
        super(adminName +" Home Page");
        name=adminName;
        setLocation(0,0);
        setSize(1920,1080);
        
        ImageIcon ic=new ImageIcon(ClassLoader.getSystemResource("Hospital/Icons/admin_home.jpg"));
        Image img=ic.getImage().getScaledInstance(1920,1080, Image.SCALE_SMOOTH);
        ImageIcon ic1=new ImageIcon(img);
        l1=new JLabel(ic1);
        
        f=new Font("Lucida Fax",Font.BOLD,20);
        f1=new Font("Gadugi",Font.BOLD,15);
        f2=new Font("MS UI Gothic",Font.BOLD,18);
        
        JMenuBar m1=new JMenuBar();
        JMenu men1=new JMenu("Doctor");
//        JMenuItem ment1=new JMenuItem("Add Doctor");
        JMenuItem ment2=new JMenuItem("View Doctors");
        
        JMenu men2=new JMenu("Patient");
        JMenuItem ment3=new JMenuItem("Add Patient");
        JMenuItem ment4=new JMenuItem("View Patients");
         
        JMenu men3=new JMenu("Accountant");
//        JMenuItem ment5=new JMenuItem("Add Accountant");
        JMenuItem ment6=new JMenuItem("View Accountants");
        
        JMenu men4=new JMenu("Appointment");
        JMenuItem ment7=new JMenuItem("Add Appointment");
        JMenuItem ment8=new JMenuItem("View Appointments");
        
        JMenu men5=new JMenu("Nurse");
//        JMenuItem ment9=new JMenuItem("Add Nurse");
        JMenuItem ment10=new JMenuItem("View Nurses");
        
        JMenu men6=new JMenu("Pharmacist");
//        JMenuItem ment11=new JMenuItem("Add Pharmacist");
        JMenuItem ment12=new JMenuItem("View Pharmacists");
        
        JMenu men7=new JMenu("Laboratorist");
//        JMenuItem ment13=new JMenuItem("Add Laboratorist");
        JMenuItem ment14=new JMenuItem("View Laboratorists");
        
        JMenu men8=new JMenu("Hi, Accountant("+ adminName+")");
        JMenuItem ment15=new JMenuItem("View Profile");
        JMenuItem ment16=new JMenuItem("Change Password");
        
        JMenu men9=new JMenu("Exit");
        JMenuItem ment17=new JMenuItem("Exit");
        
//        men1.add(ment1);
        men1.add(ment2);
        
        men2.add(ment3);
        men2.add(ment4);
        
//        men3.add(ment5);
        men3.add(ment6);
        
        men4.add(ment7);
        men4.add(ment8);
        
//        men5.add(ment9);
        men5.add(ment10);
        
//        men6.add(ment11);
        men6.add(ment12);
        
//        men7.add(ment13);
        men7.add(ment14);
        
        men8.add(ment15);
        men8.add(ment16);
        
        men9.add(ment17);
        
       
        m1.add(men1);
        m1.add(Box.createHorizontalStrut(10)); // Add space of 10 pixels
        m1.add(men2);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men3);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men4);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men5);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men6);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men7);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men8);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men9);

        
        men1.setFont(f);
        men2.setFont(f);
        men3.setFont(f);
        men4.setFont(f);
        men5.setFont(f);
        men6.setFont(f);
        men7.setFont(f);
        men8.setFont(f);
        men9.setFont(f);
        
        
//        ment1.setFont(f1);
        ment2.setFont(f1);
        ment3.setFont(f1);
        ment4.setFont(f1);
//        ment5.setFont(f1);
        ment6.setFont(f1);
        ment7.setFont(f1);
        ment8.setFont(f1);
//        ment9.setFont(f1);
        ment10.setFont(f1);
//        ment11.setFont(f1);
        ment12.setFont(f1);
//        ment13.setFont(f1);
        ment14.setFont(f1);
        ment15.setFont(f1);
        ment16.setFont(f1);
        ment17.setFont(f1);
        
        men1.setForeground(Color.WHITE);
        men2.setForeground(Color.WHITE);
        men3.setForeground(Color.WHITE);
        men4.setForeground(Color.WHITE);
        men5.setForeground(Color.WHITE);
        men6.setForeground(Color.WHITE);
        men7.setForeground(Color.WHITE);
        men8.setForeground(Color.WHITE);
        men9.setForeground(Color.RED);
        
        
        JMenuItem[] allMenuItems = {ment2, ment3, ment4, ment6, ment7, ment8,ment10, ment12, ment14, ment15, ment16, ment17};
        
        
        for (JMenuItem menuItem : allMenuItems) {
            menuItem.addActionListener(this);
        }
        
        m1.setBackground(Color.BLACK);
        setJMenuBar(m1);
        getContentPane().add(l1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
        
        
    }
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
    
        switch (actionCommand) {
        
//            case "Add Doctor":
//                setVisible(false);
//                new Add_Doctor(name);
//                break;
        
            case "View Doctors":
                setVisible(false);
                new View_Doctor(name);
                break;
        
            case "Add Patient":
                setVisible(false);
                new Add_Patient(name);
                break;
        
            case "View Patients":
                setVisible(false);
                new View_Patient(name);
                break;
        
//            case "Add Accountant":
//                setVisible(false);
//                new Add_Accountant(name);
//                break;
        
            case "View Accountants":
                setVisible(false);
                new View_Accountant(name);
                break;
        
            case "Add Appointment":
                setVisible(false);
                new Add_Appointment(name);
                break;
        
            case "View Appointments":
                setVisible(false);
                new View_Appointment(name);
                break;
        
//            case "Add Nurse":
//                setVisible(false);
//                new Add_Nurse(name);
//                break;
        
            case "View Nurses":
                setVisible(false);
                new View_Nurse(name);
                break;
       
//            case "Add Pharmacist":
//                setVisible(false);
//                new Add_Pharmacist(name);
//                break;
        
            case "View Pharmacists":
                setVisible(false);
                new View_Pharmacist(name);
                break;
        
//            case "Add Laboratorist":
//                setVisible(false);
//                new Add_Laboratorist(name);
//                break;
       
            case "View Laboratorists":
                setVisible(false);
                new View_Laboratorist(name);
                break;
        
            case "View Profile":
                setVisible(false);
                new View_Profile(name);
                break;

            case "Change Password":
                setVisible(false);
                new Change_Password(name);
                break;
            case "Exit":
                this.setVisible(false);
                new Index();
                break;
            default:
                break;
        }
} 
    public static void main(String[] args)
    {
        new AccountantHomePage("Accountant").setVisible(true);
    }
}
