package ru.sfedu.bideet.Services;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.*;
import org.apache.logging.log4j.*;
import ru.sfedu.bideet.Entities.*;
import ru.sfedu.bideet.*;
import ru.sfedu.bideet.Repository.DataProviderTXT;

/**
 *
 * @author Osebi
 */
public class ViewAuctions extends JFrame implements ActionListener{
    private static Logger logger = LogManager.getLogger(ViewAuctions.class);
    DataProviderTXT txt = new DataProviderTXT();
    
    JTable table;
    JScrollPane scroll;
    Container c;
    private Color textArea = new Color(255,255,255);
    
    public ViewAuctions() throws IOException{
        setTitle("Placed Bids");
        setBounds(300,100,600,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(textArea);
        
        scroll = new JScrollPane();
        c.add(scroll);
        c.add(scroll);
        
        table = new JTable();
        table.setLocation(10,60);
        table.setSize(550,600);
        table.getColumnModel();
        table.setDefaultEditor(Object.class, null);
        table.add(scroll);
        c.add(table);
        
        String path = "src/main/resources/TXTFILES/AuctionProduct.txt";
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
        
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt){
            
                int index = table.getSelectedRow();
                TableModel model1 = table.getModel();
                String line1 = model1.getValueAt(index, 1).toString();
                //int rowCount = model1.getRowCount();
                
                String init = line1.replaceAll("\\s", "");
                String file = "src/main/resources/TXTFILES/Auctions/" + init + ".txt";
                AuctionWins wins = new AuctionWins();
                try {
                    wins.auctionWins(init);
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(ViewAuctions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                wins.show();
            
            }});
           
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    public void auctionWins(String path) throws IOException{
        //String path = "src/main/resources/TXTFILES/AuctionProduct.txt";
        ArrayList<String> arrl = new ArrayList<>();
        Path p = Paths.get(path);
        long l = Files.lines(p).count();
        
        try{
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line = br.readLine();
                for(int i=1; i<l; i++){
                    if(line.length()!= -1){}
                    line = br.readLine();
                   
                    logger.info(line);
                    arrl.addAll(Arrays.asList(line));
                    
                }
                br.close();
                logger.info(arrl);
            }
            
        }catch(IOException e){
           logger.info("No records in this file");
	}
    }
}
