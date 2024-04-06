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
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;

public class Index extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1,l2,l3;
    JButton b1,b2,b3,b4,b5,b6,b7;
    Index(){
        f=new JFrame("Index Page");
        f.setBackground(Color.WHITE);
        f.setLayout(null);
        
        l1=new JLabel();
        l1.setBounds(0,0,1000,570);
        l1.setLayout(null);
        
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("Hospital/Icons/hospital_new.jpg"));
        Image i1=img.getImage().getScaledInstance(1000, 570, Image.SCALE_SMOOTH);
        ImageIcon img1=new ImageIcon(i1);
        l1.setIcon(img1);
        
        
        l2=new JLabel("SMST Group of Hospitals");
        l2.setBounds(320,115,400,30);
        l2.setFont(new Font("Arial",Font.BOLD,30));
        l2.setForeground(Color.WHITE);
        
        l1.add(l2);
        
        
        l3=new JLabel("Healing with Heart, Leading Healthcare Innovation...");
        l3.setBounds(330,145,400,30);
        l3.setFont(new Font("Arial",Font.BOLD,14));
        l3.setForeground(Color.YELLOW);
        l1.add(l3);
        f.add(l1);
        
        b1=new JButton("Doctor");
        b1.setBounds(320,200,120,30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        l1.add(b1);
        
        b2=new JButton("Patient");
        b2.setBounds(460,200,120,30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        l1.add(b2);
        
        
        b3=new JButton("Accountant");
        b3.setBounds(600,200,120,30);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        l1.add(b3);
        
        
        b4=new JButton("Nurse");
        b4.setBounds(320,250,120,30);
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        l1.add(b4);
        
        b5=new JButton("Pharmacist");
        b5.setBounds(460,250,120,30);
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.WHITE);
        l1.add(b5);
        
        b6=new JButton("Laboratorist");
        b6.setBounds(600,250,120,30);
        b6.setBackground(Color.BLACK);
        b6.setForeground(Color.WHITE);
        l1.add(b6);
        
        b7=new JButton("Admin");
        b7.setBounds(460,300,120,30);
        b7.setBackground(Color.BLACK);
        b7.setForeground(Color.WHITE);
        l1.add(b7);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);  
        b3.addActionListener(this);  
        b4.addActionListener(this);  
        b5.addActionListener(this);  
        b6.addActionListener(this);
        b7.addActionListener(this);  
        
                        
        
        f.setSize(1000,570);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
    }
    public void actionPerformed(ActionEvent ae)
    {   
        String src=ae.getActionCommand().toLowerCase();
        if(ae.getSource()==b1){
            f.setVisible(false);
            new LoginPage(src);
        }
        else if(ae.getSource()==b2){
            f.setVisible(false);
            new LoginPage(src);
        }
        else if(ae.getSource()==b3){
            f.setVisible(false);
            new LoginPage(src);
        }
        else if(ae.getSource()==b4){
            f.setVisible(false);
            new LoginPage(src);
        }
        else if(ae.getSource()==b5){
            f.setVisible(false);
            new LoginPage(src);
        }
        else if(ae.getSource()==b6){
            f.setVisible(false);
            new LoginPage(src);
        }
        else{
            f.setVisible(false);
            new LoginPage(src);
        }
        
    }
    public static void main(String[] args)
    {
        new Index();
    }
}
