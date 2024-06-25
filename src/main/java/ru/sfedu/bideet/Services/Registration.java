package ru.sfedu.bideet.Services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.*;
import org.bson.Document;
import ru.sfedu.bideet.Entities.*;
import ru.sfedu.bideet.Repository.*;

/**
 *
 * @author Osebi
 */
public class Registration extends JFrame implements ActionListener{
    
    private Container c;
    private JLabel title;
    private JLabel lname;
    private JTextField name;
    private JLabel lemail;
    private JTextField email;
    private JLabel lpassword;
    private JTextField password;
    private JLabel lusername;
    private JTextField username;
    private JLabel lrole;
    private JRadioButton buyer;
    private JRadioButton seller;
    private ButtonGroup btn;
    private JButton submit;
    private JLabel loginMsg;
    private JLabel login;
    
    public Registration(){
        setTitle("Registration Form");
        setBounds(300, 100, 1000, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(Color.pink);
        
       title = new JLabel("Create your account!");
       title.setFont(new Font("Garamond", Font.ITALIC|Font.BOLD, 36));
       title.setSize(900, 40);
       title.setLocation(300, 20);
       c.add(title);
       
       lname = new JLabel("Name: ");
       lname.setFont(new Font("Garamond", Font.BOLD, 20));
       lname.setSize(170, 20);
       lname.setLocation(300, 100);
       c.add(lname);
       
       name = new JTextField();
       name.setFont(new Font("Garamond", Font.PLAIN, 14));
       name.setSize(300, 30);
       name.setLocation(420, 100);
       //name.setBorder(new RoundTextField());
       c.add(name);
       
       lemail = new JLabel("Email: ");
       lemail.setFont(new Font("Garamond", Font.BOLD, 20));
       lemail.setSize(170,20);
       lemail.setLocation(300, 150);
       c.add(lemail);
       
       email = new JTextField();
       email.setFont(new Font("Garamond", Font.PLAIN, 14));
       email.setSize(300,30);
       email.setLocation(420, 150);
       c.add(email);
       
       lusername = new JLabel("Username: ");
       lusername.setFont(new Font("Garamond", Font.BOLD, 20));
       lusername.setSize(170,20);
       lusername.setLocation(300, 200);
       c.add(lusername);
       
       username = new JTextField();
       username.setFont(new Font("Garamond", Font.PLAIN, 14));
       username.setSize(300,30);
       username.setLocation(420, 200);
       c.add(username);
       
       lpassword = new JLabel("Password: ");
       lpassword.setFont(new Font("Garamond", Font.BOLD, 20));
       lpassword.setSize(170,20);
       lpassword.setLocation(300, 250);
       c.add(lpassword);
       
       password = new JTextField();
       password.setFont(new Font("Garamond", Font.PLAIN, 14));
       password.setSize(300,30);
       password.setLocation(420, 250);
       c.add(password);
       
       lrole = new JLabel("Role: ");
       lrole.setFont(new Font("Garamond", Font.BOLD, 20));
       lrole.setSize(170,20);
       lrole.setLocation(300, 300);
       c.add(lrole);
       
       buyer = new JRadioButton("Buyer");
       buyer.setFont(new Font("Garamond", Font.PLAIN,14));
       buyer.setSize(70,20);
       buyer.setLocation(420, 300);
       buyer.setSelected(true);
       c.add(buyer);
       
       seller = new JRadioButton("Seller");
       seller.setFont(new Font("Garamond", Font.PLAIN,14));
       seller.setSize(70,20);
       seller.setLocation(490,300);
       seller.setSelected(false);
       c.add(seller);
       
       btn = new ButtonGroup();
       btn.add(buyer);
       btn.add(seller);
       
       submit = new JButton("Submit");
       submit.setFont(new Font("Garamond", Font.BOLD, 15));
       submit.setSize(100, 20);
       submit.setLocation(430, 350);
       submit.addActionListener(this);
       c.add(submit);
       
       loginMsg = new JLabel("If you already have an account, ");
       loginMsg.setFont(new Font("Garamond", Font.BOLD, 14));
       loginMsg.setSize(380,20);
       loginMsg.setLocation(350, 390);
       c.add(loginMsg);
       
       login = new JLabel("Login!");
       login.setFont(new Font("Garamond", Font.PLAIN, 15));
       login.setForeground(Color.BLUE.darker());
       login.setCursor(new Cursor(Cursor.HAND_CURSOR));
       login.setSize(50,20);
       login.setLocation(540,390);
       login.addMouseListener(new MouseAdapter(){
           @Override
           public void mouseClicked(MouseEvent e) {
               Login log = new Login();
               log.show();
               dispose();
           }
       });
       c.add(login);
       
       setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = null;
        Document newDoc = new Document();
        if (e.getSource() == submit){
            if (buyer.isSelected()){
                Buyer buy = new Buyer(name.getText(), username.getText(), email.getText(), password.getText());
                DataProviderCSV csv = new DataProviderCSV();
                DataProviderXML xml = new DataProviderXML();
                DataProviderTXT txt = new DataProviderTXT();
                try {
                    csv.saveRecordBuyer(buy);
                    xml.saveRecordBuyer(buy);
                    txt.saveRecordBuyer(buy);
                    System.out.println(buy);
                    Login login = new Login();
                } catch (Exception ex) {
                    Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(seller.isSelected()){
                Seller sell = new Seller(name.getText(),username.getText(), email.getText(), password.getText());
                DataProviderCSV csv = new DataProviderCSV();
                DataProviderXML xml = new DataProviderXML();
                DataProviderTXT txt = new DataProviderTXT();
                try {
                    csv.saveRecordSeller(sell);
                    xml.saveRecordSeller(sell);
                    txt.saveRecordSeller(sell);
                    Login login = new Login();
                } catch (Exception ex) {
                    Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else{
                    JOptionPane.showMessageDialog(c, "Choose a role, please!");
            }
        }
    }
    
}
