package Hospital;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class represents the Doctor's home page.
 */
public class LaboratoristHomePage extends JFrame implements ActionListener {
    JLabel l1;
    Font f, f1, f2;
    String name;
    LaboratoristHomePage(String adminName) {
        super(adminName + " Home Page");
        name = adminName;
        setLocation(0, 0);
        setSize(1920, 1080);

        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("Hospital/Icons/admin_home.jpg"));
        Image img = ic.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon ic1 = new ImageIcon(img);
        l1 = new JLabel(ic1);

        f = new Font("Lucida Fax", Font.BOLD, 20);
        f1 = new Font("Gadugi", Font.BOLD, 15);
        f2 = new Font("MS UI Gothic", Font.BOLD, 18);

        JMenuBar m1 = new JMenuBar();
        JMenu men1 = new JMenu("Doctor");
        JMenuItem ment2 = new JMenuItem("View Doctors");

        JMenu men2 = new JMenu("Patient");
        JMenuItem ment4 = new JMenuItem("View Patients");

        JMenu prescriptionMenu = new JMenu("Prescription");
        JMenuItem viewPrescriptionMenuItem = new JMenuItem("View Prescription");

//        JMenu men4 = new JMenu("Appointment");
//        JMenuItem ment8 = new JMenuItem("View Appointments");
//
//        JMenu men5 = new JMenu("Nurse");
//        JMenuItem ment10 = new JMenuItem("View Nurses");

//        JMenu men6 = new JMenu("Pharmacist");
//        JMenuItem ment12 = new JMenuItem("View Pharmacists");

        JMenu men7 = new JMenu("Laboratorist");
        JMenuItem ment14 = new JMenuItem("View Laboratorists");

        JMenu men8 = new JMenu("Hi, Laboratorist(" + adminName + ")");
        JMenuItem ment15 = new JMenuItem("View Profile");
        JMenuItem ment16 = new JMenuItem("Change Password");

        JMenu men9 = new JMenu("Exit");
        JMenuItem ment17 = new JMenuItem("Exit");

        men1.add(ment2);

        men2.add(ment4);

        prescriptionMenu.add(viewPrescriptionMenuItem);

//        men4.add(ment8);
//
//        men5.add(ment10);
//
//        men6.add(ment12);

        men7.add(ment14);

        men8.add(ment15);
        men8.add(ment16);

        men9.add(ment17);

        JMenuItem[] allMenuItems = {ment2, ment4, viewPrescriptionMenuItem, ment14, ment15, ment16, ment17};

        for (JMenuItem menuItem : allMenuItems) {
            menuItem.setFont(f1);
            menuItem.addActionListener(this);
        }

        m1.add(men1);
        m1.add(Box.createHorizontalStrut(10)); 
        m1.add(men2);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(prescriptionMenu);
        m1.add(Box.createHorizontalStrut(10));
//        m1.add(men4);
//        m1.add(Box.createHorizontalStrut(10));
//        m1.add(men5);
//        m1.add(Box.createHorizontalStrut(10));
//        m1.add(men6);
//        m1.add(Box.createHorizontalStrut(10));
        m1.add(men7);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men8);
        m1.add(Box.createHorizontalStrut(10));
        m1.add(men9);

        men1.setFont(f);
        men2.setFont(f);
        prescriptionMenu.setFont(f);
//        men4.setFont(f);
//        men5.setFont(f);
//        men6.setFont(f);
        men7.setFont(f);
        men8.setFont(f);
        men9.setFont(f);

        men1.setForeground(Color.WHITE);
        men2.setForeground(Color.WHITE);
        prescriptionMenu.setForeground(Color.WHITE);
//        men4.setForeground(Color.WHITE);
//        men5.setForeground(Color.WHITE);
//        men6.setForeground(Color.WHITE);
        men7.setForeground(Color.WHITE);
        men8.setForeground(Color.WHITE);
        men9.setForeground(Color.RED);

        m1.setBackground(Color.BLACK);
        setJMenuBar(m1);
        getContentPane().add(l1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "View Doctors":
                setVisible(false);
                new View_Doctor(name);
                break;
            case "View Patients":
                setVisible(false);
                new View_Patient(name);
                break;
//            case "View Appointments":
//                setVisible(false);
//                new View_Appointment(name);
//                break;
//            case "View Nurses":
//                setVisible(false);
//                new View_Nurse(name);
//                break;
//            case "View Pharmacists":
//                setVisible(false);
//                new View_Pharmacist(name);
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
            case "View Prescription":
                setVisible(false);
                new View_Prescription(name);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        new LaboratoristHomePage("laboratorist").setVisible(true);
    }
}
