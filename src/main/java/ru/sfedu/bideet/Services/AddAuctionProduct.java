package ru.sfedu.bideet.Services;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import ru.sfedu.bideet.*;
import ru.sfedu.bideet.Entities.*;
import ru.sfedu.bideet.Enumerations.*;
import ru.sfedu.bideet.Repository.*;

/**
 *
 * @author Osebi
 */
public class AddAuctionProduct extends JFrame implements ActionListener{
    private Container c;
    private JLabel title;
    private JLabel lname;
    private JTextField name;
    private JLabel lStartPrice;
    private JTextField startPrice;
    private JLabel lDate;
    private JTextField date;
    private JButton addBtn;
    private Font labelFont = new Font("Serif", Font.BOLD, 15);
    private Font textFont = new Font("Serif", Font.PLAIN, 14);
    private Color labelColor = new Color(255,255,255);
    private Color textColor = new Color(35,35,35);
    private Color textArea = new Color(255,255,255);
    
    public AddAuctionProduct(){
        setTitle("Add Product");
        setBounds(300,100,500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(35,35,35));
        
        title = new JLabel("Add a new Product", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setForeground(labelColor);
        title.setSize(300, 40);
        title.setLocation(100,20);
        c.add(title);
        
        lname = new JLabel("Product name: ");
        lname.setFont(labelFont);
        lname.setForeground(labelColor);
        lname.setLocation(20, 90);
        lname.setSize(100, 20);
        c.add(lname);
        
        name = new RoundJTextField(2);
        name.setFont(textFont);
        name.setForeground(textColor);
        name.setBackground(textArea);
        name.setLocation(130,90);
        name.setSize(300,25);
        c.add(name);
        
        lStartPrice = new JLabel("StartPrice: ");
        lStartPrice.setFont(labelFont);
        lStartPrice.setForeground(labelColor);
        lStartPrice.setLocation(20, 130);
        lStartPrice.setSize(100, 20);
        c.add(lStartPrice);
        
        startPrice = new RoundJTextField(2);
        startPrice.setFont(textFont);
        startPrice.setForeground(textColor);
        startPrice.setBackground(textArea);
        startPrice.setLocation(130,130);
        startPrice.setSize(300,25);
        c.add(startPrice);
        
//        lDate = new JLabel("Date: ");
//        lDate.setFont(labelFont);
//        lDate.setForeground(labelColor);
//        lDate.setLocation(20, 170);
//        lDate.setSize(100, 20);
//        c.add(lDate);
//        
//        date = new RoundJTextField(2);
//        date.setFont(textFont);
//        date.setForeground(textColor);
//        date.setBackground(textArea);
//        date.setLocation(130,170);
//        date.setSize(300,25);
//        c.add(date);
        
        addBtn = new JButton("Add");
        addBtn.setFont(labelFont);
        addBtn.setForeground(labelColor);
        addBtn.setBackground(new Color(174,235,173));
        addBtn.setLocation(200,210);
        addBtn.setSize(70,30);
        addBtn.setBorder(new RoundedBorder(7));
        addBtn.addActionListener(this);
        c.add(addBtn);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addBtn){
            DataProviderCSV csv = new DataProviderCSV();
            AuctionProduct product = new AuctionProduct(name.getText(), startPrice.getText());
            try {
                csv.saveRecordAuctionProduct(product);
            } catch (Exception ex) {
                Logger.getLogger(AddAuctionProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
