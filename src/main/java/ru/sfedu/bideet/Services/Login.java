package ru.sfedu.bideet.Services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import org.apache.logging.log4j.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import ru.sfedu.bideet.Entities.*;
import ru.sfedu.bideet.Repository.*;
import ru.sfedu.bideet.Constants;

/**
 *
 * @author Osebi
 */
public class Login extends JFrame implements ActionListener{
    
    private static Logger logger = LogManager.getLogger(Login.class);
    private Container c;
    private JLabel avatar;
    private JLabel lemail;
    private JTextField email;
    private JLabel lpassword;
    private JTextField password;
    private JRadioButton buyer;
    private JRadioButton seller;
    private JButton login;
    private JLabel regMsg;
    private JLabel reg;
    
    public Login(){
        setTitle("Login Form");
        setBounds(300, 100, 800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(237,145,192));
        
        avatar = new JLabel();
        avatar.setIcon(new ImageIcon(new ImageIcon("src/main/resources/Images/avatarBid3.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        avatar.setSize(200, 200);
        avatar.setLocation(350,20);
        c.add(avatar);
        
        lemail = new JLabel("Email: ");
        lemail.setFont(new Font("Garamond", Font.BOLD, 20));
        lemail.setSize(120,20);
        lemail.setLocation(150, 200);
        c.add(lemail);
       
        email = new JTextField();
        email.setFont(new Font("Garamond", Font.BOLD, 14));
        email.setSize(300,30);
        email.setLocation(270, 200);
        c.add(email);
       
        lpassword = new JLabel("Password: ");
        lpassword.setFont(new Font("Garamond", Font.BOLD, 20));
        lpassword.setSize(120,20);
        lpassword.setLocation(150, 250);
        c.add(lpassword);
        
        password = new JTextField();
        password.setFont(new Font("Garamond", Font.PLAIN, 14));
        password.setSize(300,30);
        password.setLocation(270, 250);
        c.add(password);
        
        buyer = new JRadioButton("Buyer");
        buyer.setFont(new Font("Garamond", Font.PLAIN,14));
        buyer.setSize(70,20);
        buyer.setLocation(310, 300);
        buyer.setSelected(true);
        c.add(buyer);

        seller = new JRadioButton("Seller");
        seller.setFont(new Font("Garamond", Font.PLAIN,14));
        seller.setSize(70,20);
        seller.setLocation(380,300);
        seller.setSelected(false);
        c.add(seller);
        
        ButtonGroup btn = new ButtonGroup();
        btn.add(buyer);
        btn.add(seller);

        login = new JButton("Login");
        login.setFont(new Font("Garamond", Font.BOLD, 15));
        login.setSize(100, 20);
        login.setLocation(350, 350);
        login.addActionListener(this);
        c.add(login);
        
        regMsg = new JLabel("You don't have an account?");
        regMsg.setFont(new Font("Garamond", Font.PLAIN, 14));
        regMsg.setSize(300,20);
        regMsg.setLocation(300,380);
        c.add(regMsg);
        
        reg = new JLabel("Register");
        reg.setFont(new Font("Garamond", Font.PLAIN, 14));
        reg.setSize(70,20);
        reg.setLocation(459,380);
        reg.setForeground(Color.BLUE.darker());
        reg.setCursor(new Cursor(Cursor.HAND_CURSOR));
        reg.addMouseListener(new MouseAdapter(){
           @Override
           public void mouseClicked(MouseEvent e) {
               Registration register = new Registration();
               register.show();
               dispose();
           }
        });
        c.add(reg);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login){
            if(buyer.isSelected()){
                try {
                    DataProviderTXT txt = new DataProviderTXT();
                    List<Buyer> tlist = txt.getRecords("src/main/resources/TXTFILES/Buyer.txt");
                    System.out.println(tlist);
                    for(Buyer b:tlist){
                        System.out.println(b);
                        if(email.getText().equals(b.getEmail())&&password.getText().equals( b.getPassword())){
                            OngoingBid1 ongoingBid = new OngoingBid1();
                            ongoingBid.show();
                            dispose();
                        }
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            } else if(seller.isSelected()){
                try {
                    DataProviderXML xml1 = new DataProviderXML();
                    List<Seller> xlist1 = xml1.getAllRecords("src/main/resources/XMLFILES/Seller.xml");
                    for(Seller s : xlist1){
                        if(s.getEmail().equals(email.getText()) && s.getPassword().equals(password.getText())){
                            AddProduct addProduct = new AddProduct();
                            addProduct.show();
                            dispose();
                        }
                    }
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
            
        }
    }
    
    public void loginTry(){
        try {
                String[] buyerHeader = {"Id", "Name", "Username", "Email", "Password"};
                String path = Constants.BUYER_CSV;
                Class cl = Buyer.class;
                String[] header = buyerHeader;
                
                //csv
                DataProviderCSV csv = new DataProviderCSV();
                List<Buyer> list = csv.getAllRecords(path, cl, header);
                logger.info(list);
                
//                //xml
//                DataProviderXML xml = new DataProviderXML();
//                List<Buyer> xlist = xml.getAllRecords(path);
                
                //txt
                DataProviderTXT txt = new DataProviderTXT();
                List<Buyer> tlist = txt.getRecords("src/main/resources/TXTFILES/Buyer.txt");
                System.out.println(tlist);
                //if(tlist.contains(email.getText())){
                        //OngoingBidPane1 ongoingBid = new OngoingBidPane1();
                        OngoingBid1 ongoingBid = new OngoingBid1();
                        ongoingBid.show();
                        dispose();
                //}
                for(Buyer b:tlist){
                    System.out.println(b);
                    if(email.getText().equals(b.getEmail())&&password.getText().equals( b.getPassword())){
                        //OngoingBid1 ongoingBid = new OngoingBid1();
                        ongoingBid.show();
                        dispose();
                    }
                }
                String[] sellerHeader = {"Id", "Name", "Username", "Email", "Auctions"};
                String path1 = Constants.SELLER_CSV;
                Class cl1 = Seller.class;
                String[] header1 = sellerHeader;
                
                //csv
                //DataProviderCSV csv1 = new DataProviderCSV();
                //List<Seller> list1 = csv1.getAllRecords(path1, cl1, header1);
                
                //xml
                DataProviderXML xml1 = new DataProviderXML();
                List<Seller> xlist1 = xml1.getAllRecords(path);
                for(Seller s : xlist1){
                    if(s.getEmail().equals(email.getText()) && s.getPassword().equals(password.getText())){
                        String sellerName = s.getName();
                        AddProduct addProduct = new AddProduct();
                        //addProduct.sellerName = sellerName;
                    }
                }
            } catch (IOException ex) {
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
    }
}
