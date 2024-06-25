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
public class AddProduct extends JFrame implements ActionListener{
     
    public String sellerName;
    private Container c;
    private JLabel title;
    private JLabel lname;
    private JTextField name;
    private JLabel lbrand;
    private JTextField brand;
    private JLabel lyear;
    private JTextField year;
    private JLabel lprice;
    private JTextField price;
    private JButton addBtn;
    private Font labelFont = new Font("Serif", Font.BOLD, 15);
    private Font textFont = new Font("Serif", Font.PLAIN, 14);
    private Color labelColor = new Color(255,255,255);
    private Color textColor = new Color(35,35,35);
    private Color textArea = new Color(255,255,255);
    
    public AddProduct(){
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
        
        lbrand = new JLabel("Brand: ");
        lbrand.setFont(labelFont);
        lbrand.setForeground(labelColor);
        lbrand.setLocation(20, 130);
        lbrand.setSize(100, 20);
        c.add(lbrand);
        
        brand = new RoundJTextField(2);
        brand.setFont(textFont);
        brand.setForeground(textColor);
        brand.setBackground(textArea);
        brand.setLocation(130,130);
        brand.setSize(300,25);
        c.add(brand);
        
        lyear = new JLabel("Year: ");
        lyear.setFont(labelFont);
        lyear.setForeground(labelColor);
        lyear.setLocation(20, 170);
        lyear.setSize(100, 20);
        c.add(lyear);
        
        year = new RoundJTextField(2);
        year.setFont(textFont);
        year.setForeground(textColor);
        year.setBackground(textArea);
        year.setLocation(130,170);
        year.setSize(300,25);
        c.add(year);
        
        lprice = new JLabel("Price: ");
        lprice.setFont(labelFont);
        lprice.setForeground(labelColor);
        lprice.setLocation(20, 210);
        lprice.setSize(100, 20);
        c.add(lprice);
        
        price = new RoundJTextField(2);
        price.setFont(textFont);
        price.setForeground(textColor);
        price.setBackground(textArea);
        price.setLocation(130,210);
        price.setSize(300,25);
        c.add(price);
        
        addBtn = new JButton("Add");
        addBtn.setFont(labelFont);
        addBtn.setForeground(labelColor);
        addBtn.setBackground(new Color(174,235,173));
        addBtn.setLocation(200,260);
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
            DataProviderTXT txt = new DataProviderTXT();
//            Seller seller = new Seller();
//            String s = seller.setName(sellerName);
            Product product = new Product(name.getText(), brand.getText(), year.getText(), price.getText(), sellerName, ProductState.LISTED);
            try {
                csv.saveRecordProduct(product);
                txt.saveRecordProduct(product);
            } catch (Exception ex) {
                Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
