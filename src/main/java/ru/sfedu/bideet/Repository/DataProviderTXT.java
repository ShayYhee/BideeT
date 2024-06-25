package ru.sfedu.bideet.Repository;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.apache.logging.log4j.*;
import ru.sfedu.bideet.Entities.*;

/**
 *
 * @author Osebi
 */
public class DataProviderTXT {
    private static Logger logger = LogManager.getLogger(DataProviderTXT.class);
    
    public String[] auctionHeader = {"Id", "Products", "AuctionState"};
    public String[] buyerHeader = {"Id", "Name", "Username", "Email", "Password"};
    public String[] productHeader = {"Id", "Name", "Brand", "Year", "StartPrice", "Seller", "ProductState"};
    public String[] sellerHeader = {"Id", "Name", "Username", "Email", "Auctions"};
    public String[] auctionProductHeader = {"ProductId", "ProductName", "StartPrice", "CurrentBid", "WinningBid", "Date"};
    
    public <T> List<T> getRecords(String path) throws IOException, ClassNotFoundException{
       List<T> list = new ArrayList<>();
       //BufferedReader br = null;
       try{

            Path p = Paths.get(path);
            long l = Files.lines(p).count();
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line = br.readLine();
                for(int i=1; i<l; i++){
                    if(line.length()!= -1){}
                    list.add((T) line);
                    line = br.readLine();
                    logger.info(line);
                }
                    br.close();
            }
        }catch(IOException e){
           logger.info("No records in this file");
	}
       System.out.println(list);
        //Files.readAllLines(new File(path).toPath(), Charset.defaultCharset());
       return new ArrayList<>();
    }
    
    public void saveRecord(Object object, String path, String[] columns) throws IOException{
       
        File file = new File(path);
        FileWriter fw = new FileWriter(file, true);
        try (PrintWriter pw = new PrintWriter(fw)) {
            pw.println(object);
        }
        
    }
    
    public void saveRecords(Object object, String path) throws IOException{
       
        File file = new File(path);
        FileWriter fw = new FileWriter(file, true);
        try (PrintWriter pw = new PrintWriter(fw)) {
            pw.println(object);
        }
        
    }
    
    public void saveRecordAuction(Auction auction)throws Exception{
        String path = "src/main/resources/TXTFILES/Auction.txt";
        String[] header = auctionHeader;
        List<Auction> list = getRecords(path);
        list.add(auction);
        logger.debug(list);
        saveRecord(auction, path, header);
    }
    
    public void saveRecordAuctionProduct(AuctionProduct auctionProd)throws Exception{
        String path = "src/main/resources/TXTFILES/AuctionProduct.txt";
        String[] header = auctionProductHeader;
        List<AuctionProduct> list = getRecords(path);
        list.add(auctionProd);
        logger.debug(list);
        saveRecord(auctionProd, path, header);
    }
    
    public void saveRecordBuyer(Buyer buyer)throws Exception{
        String path = "src/main/resources/TXTFILES/Buyer.txt";
        String[] header = buyerHeader;
        List<Buyer> list = getRecords(path);
        list.add(buyer);
        logger.debug(list);
        saveRecord(buyer, path, header);
    }
    
    public void saveRecordProduct(Product product)throws Exception{
        String path = "src/main/resources/TXTFILES/Product.txt";
        String[] header = auctionHeader;
        List<Product> list = getRecords(path);
        list.add(product);
        logger.debug(list);
        saveRecord(product, path, header);
    }
    
    public void saveRecordSeller(Seller seller)throws Exception{
        String path = "src/main/resources/TXTFILES/Seller.txt";
        String[] header = auctionHeader;
        List<Seller> list = getRecords(path);
        list.add(seller);
        logger.debug(list);
        saveRecord(seller, path, header);
    }
}
