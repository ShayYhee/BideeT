package ru.sfedu.bideet.Services;

import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.*;
import org.apache.logging.log4j.*;

import ru.sfedu.bideet.Repository.*;

/**
 *
 * @author Osebi
 */
public class AuctionWins extends JFrame{
    private static Logger logger = LogManager.getLogger(AuctionWins.class);
    DataProviderTXT txt = new DataProviderTXT();
    Container c;
    JTable table;
    JScrollPane scroll;
    private Color textArea = new Color(255,255,255);
    
    public AuctionWins(){
        setTitle("Placed Bids");
        setBounds(300,100,600,700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(textArea);
        
        table = new JTable();
        table.setLocation(10,60);
        table.setSize(550,600);
        table.getColumnModel();
        table.setDefaultEditor(Object.class, null);
        c.add(table);
        
        scroll = new JScrollPane();
        //scroll.add(table);
        c.add(scroll);
        
        setVisible(true);
    }
    
    public void auctionWins(String fileName) throws IOException{
        String path = "src/main/resources/TXTFILES/Auctions/" +fileName+ ".txt";
        
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
        
        TableModel model1 = table.getModel();
        int rowCount = model1.getRowCount();
        float winBid = 0;
        String winner = null;
        for(int row=0; row<rowCount; row++){
            String line2 = model1.getValueAt(row, 4).toString();
            String line3 = model1.getValueAt(row, 3).toString();
            float bidPrice = Float.parseFloat(line2.replaceAll("\\s", ""));
            
            if (bidPrice>winBid){
                winBid = bidPrice;
                winner = line3;
            }
        }
        String msg = "Winning Bid is: " + winBid + ", won by " + winner + "!!!";
        JDialog d = new JDialog(new JFrame(), fileName, true);
        d.setLayout(new FlowLayout());
        d.add(new JLabel(msg));
        d.setSize(500,100);
        d.setLocation(300, 400);
        d.setVisible(true);
        logger.info(winBid);
        show();
        dispose();
    }
}
