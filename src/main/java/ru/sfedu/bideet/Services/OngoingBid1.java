package ru.sfedu.bideet.Services;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.*;
import ru.sfedu.bideet.Entities.*;
import ru.sfedu.bideet.*;
import ru.sfedu.bideet.Repository.*;

/**
 *
 * @author Osebi
 */
public class OngoingBid1 extends JFrame implements ActionListener{
    
    JTable table;
    JTable table1;
    JTable table2;
    JButton todayBid;
    JButton viewAuction;
    JButton addProduct;
    JScrollPane scroll;
    JFrame frame;
    Container cb;
    private Container c;
    private Color textArea = new Color(255,255,255);
    
    public OngoingBid1(){
        setTitle("Today's Auctions");
        setBounds(300,100,600,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(textArea);
        
        cb = getContentPane();
        cb.setLayout(null);
        cb.setBackground(textArea);
        
        scroll = new JScrollPane();
        c.add(scroll);
        cb.add(scroll);
        
        todayBid = new JButton("Today's Auctions");
        todayBid.setLocation(30,10);
        todayBid.setSize(150, 30);
        todayBid.setBackground(new Color(255, 153, 153));
        todayBid.addActionListener((ActionEvent e) -> {
            todayBidActionPerformed(e);
        });
        c.add(todayBid);
        
        viewAuction = new JButton("View Bids");
        viewAuction.setLocation(200,10);
        viewAuction.setSize(150, 30);
        viewAuction.setBackground(new Color(255, 153, 153));
        viewAuction.addActionListener((ActionEvent e) -> {
            try {
                auctionActionPerformed(e);
            } catch (IOException ex) {
                Logger.getLogger(OngoingBid1.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        c.add(viewAuction);
        
        addProduct = new JButton("Add Product");
        addProduct.setLocation(370,10);
        addProduct.setSize(150, 30);
        addProduct.setBackground(new Color(255, 153, 153));
        addProduct.addActionListener((ActionEvent e) -> {
            try {
                addProdPerformed(e);
            } catch (IOException ex) {
                Logger.getLogger(OngoingBid1.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        c.add(addProduct);
        
        setVisible(true);
        
        table = new JTable();
        table.setLocation(10,60);
        table.setSize(550,600);
        table.getColumnModel();
        table.setDefaultEditor(Object.class, null);
        c.add(table);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                
                int index = table.getSelectedRow();
                TableModel model1 = table.getModel();
                String line1 = model1.getValueAt(index, 0).toString();
                String line2 = model1.getValueAt(index, 1).toString();
                String line3 = model1.getValueAt(index, 4).toString();
                System.out.println("OGB:" + line1 + ", " + line2 + ", " + line3);
                
                String buyer = JOptionPane.showInputDialog(c,"Enter your username");
                String bid = JOptionPane.showInputDialog(c, "Place Bid");
                
                System.out.println("BidPrice:" + bid);
                float bidPrice = Float.parseFloat(bid);
                System.out.println("BidPrice(float):" + bidPrice);
                float winBid = 0;
                if (bidPrice > winBid){
                    winBid = bidPrice;
                }

                AuctionProduct aucprod = new AuctionProduct();
                aucprod.setProductId(Integer.valueOf(line1));
                aucprod.setProductName(line2);
                aucprod.setStartPrice(line3);
                aucprod.setBuyer(buyer);
                aucprod.setDate(new Date());
                aucprod.setCurrentBid(bidPrice);
                
                String init = line2.replaceAll("\\s", "");
                String file = "src/main/resources/TXTFILES/Auctions/" + init + ".txt";

                try {
                    DataProviderCSV csv = new DataProviderCSV();
                    csv.saveRecordAuctionProduct(aucprod);
                    DataProviderTXT txt = new DataProviderTXT();
                    txt.saveRecordAuctionProduct(aucprod);
                    txt.saveRecords(aucprod, file);
                } catch (Exception ex) {
                    Logger.getLogger(OngoingBidPane1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
       
        
    }
    
    public void todayBidActionPerformed(ActionEvent evt){
        
        
        String path = "src/main/resources/TXTFILES/Product.txt";
        File file = new File(path);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String firstLine = br.readLine().trim();
            String[] columns = firstLine.split(",");
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            model.setColumnIdentifiers(columns);

            Object[] tableLines = br.lines().toArray();

            for (int i = 0; i < (tableLines.length); i++) {
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split(",");
                model.addRow(dataRow);
            }
            table.getColumnModel();
            table.setModel(model);
            
        }catch(IOException e){}
    }
    
    public void auctionActionPerformed(ActionEvent evt) throws IOException{
        ViewAuctions vAuc = new ViewAuctions();
        vAuc.show();
    }
    
    public void addProdPerformed(ActionEvent evt) throws IOException{
        AddProduct add = new AddProduct();
        add.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
}
