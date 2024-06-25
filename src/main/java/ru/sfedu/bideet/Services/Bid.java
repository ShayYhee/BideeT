package ru.sfedu.bideet.Services;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import ru.sfedu.bideet.Entities.AuctionProduct;
import ru.sfedu.bideet.RoundJTextField;
import ru.sfedu.bideet.RoundedBorder;

/**
 *
 * @author Osebi
 */
public class Bid extends JFrame implements ActionListener{
    //OngoingBid pane;
    //String bidPrice;
    private Container c;
    private JLabel title;
    private JLabel lprice;
    public JTextField price;
    private JButton bid;
    private Font labelFont = new Font("Serif", Font.BOLD, 15);
    private Font textFont = new Font("Serif", Font.PLAIN, 14);
    private Color labelColor = new Color(255,255,255);
    private Color textColor = new Color(35,35,35);
    private Color textArea = new Color(255,255,255);
    
    public Bid(){
        setTitle("Place Bid");
        setBounds(300,100,460,200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(35,35,35));
        
        title = new JLabel("Place bid!", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setForeground(labelColor);
        title.setSize(300, 30);
        title.setLocation(80,10);
        c.add(title);
        
        lprice = new JLabel("Price: ");
        lprice.setFont(labelFont);
        lprice.setForeground(labelColor);
        //lprice.setBackground(new Color(235,232,150));
        //lprice.setBorder((Border) new RoundJLabel(7));
        //lprice.setOpaque(true);
        lprice.setLocation(20, 50);
        lprice.setSize(70, 20);
        c.add(lprice);
        
        price = new RoundJTextField(2);
        price.setFont(textFont);
        price.setForeground(textColor);
        price.setBackground(textArea);
        price.setLocation(100,50);
        price.setSize(300,25);
        c.add(price);
        
        bid = new JButton("Add");
        bid.setFont(labelFont);
        bid.setForeground(labelColor);
        bid.setBackground(new Color(174,235,173));
        bid.setLocation(200,80);
        bid.setSize(70,20);
        bid.setBorder(new RoundedBorder(7));
        bid.addActionListener(this);
        c.add(bid);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== bid){
            String bidPrice = price.getText();
            System.out.println("Bid Class: " + bidPrice);
            //pane = new OngoingBid();
            //pane.bid = bidPrice;
            dispose();
//            AuctionProduct auctProd = new AuctionProduct();
//            float bidPrice = Float.parseFloat(bid.getText());
//            float winBid = 0;
//            if (bidPrice > winBid){
//                winBid = bidPrice;
//            }
//            auctProd.currentBid = bidPrice;
//            if(auctProd.currentBid>auctProd.winningBid){
//                auctProd.winningBid = auctProd.currentBid;
//            }
//            
        }
    }
}
