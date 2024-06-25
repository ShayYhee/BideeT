package ru.sfedu.bideet.Repository;

import com.opencsv.CSVWriter;
import ru.sfedu.bideet.Constants;
import ru.sfedu.bideet.ConfigUtils;
import ru.sfedu.bideet.Entities.*;
import com.opencsv.bean.*;
import com.opencsv.exceptions.*;
import org.apache.logging.log4j.*;
import java.io.*;
import java.util.*;


/**
 *
 * @author Osebi
 */
public class DataProviderCSV {
    private static Logger logger = LogManager.getLogger(DataProviderCSV.class);
    
    private String[] auctionHeader = {"Id", "Products", "AuctionState"};
    private String[] buyerHeader = {"Id", "Name", "Username", "Email", "Password"};
    private String[] productHeader = {"Id", "Name", "Brand", "Year", "StartPrice", "Seller", "ProductState"};
    private String[] sellerHeader = {"Id", "Name", "Username", "Email", "Auctions"};
    public String[] auctionProductHeader = {"ProductId", "ProductName", "StartPrice", "CurrentBid", "WinningBid", "Date"};
    
    public DataProviderCSV(){
       
    }
    
    public <T> List<T> getAllRecords (String path, Class<T> tClass, String[] columns){
        try{
            logger.debug("Retrieving records...");
            FileReader reader = new FileReader(path);
            ColumnPositionMappingStrategy<T> strat = new ColumnPositionMappingStrategyBuilder<T>().build();
            strat.setType(tClass);
            strat.setColumnMapping(columns);
            CsvToBean csv = new CsvToBeanBuilder(reader).withMappingStrategy(strat).build();
            List list = csv.parse();
            logger.debug("Available Records: " + list);
            return list;
        }catch(IOException e){
            logger.info("No records in file. File is empty!");
        }
        return new ArrayList<>();
    }
    
    public <T> void initRecords(String path, Class<T> tClass, String[] columns, List list) throws IOException{
        
        new FileOutputStream(path).close();
        FileWriter writer = new FileWriter(path, true);
        CSVWriter cwriter = new CSVWriter(writer);
            logger.info("Initializing records... ");
            //new FileOutputStream(path).close();
            ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategyBuilder<T>().build();
            strategy.setType(tClass);
            strategy.setColumnMapping(columns);
            StatefulBeanToCsvBuilder<T> builder = new StatefulBeanToCsvBuilder<>(cwriter);
            StatefulBeanToCsv<T> beanWriter = builder.build();
            try {
                beanWriter.write(list);
            } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException ex) {
                java.util.logging.Logger.getLogger(DataProviderCSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            cwriter.flush();
            writer.close();

    }
    
    public <T> void appendRecord(String path, Class<T> tClass, List<T> list) throws IOException{
        
        FileOutputStream fos = new FileOutputStream(new File(path), true);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        CSVWriter cwriter = new CSVWriter(osw);
        cwriter.writeAll((Iterable<String[]>) list);
    }
    
    public <T> void appendRecord1(String path, Class<T> tClass, List<T> list) throws IOException{
        //FileWriter writer = new FileWriter(path, true);
        //Object[] str = list.toArray(new String[0]);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,true), "UTF-8"));
        StatefulBeanToCsv<T> builder = new StatefulBeanToCsvBuilder<T>(bw).build();
        try {
            builder.write(list);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException ex) {
            java.util.logging.Logger.getLogger(DataProviderCSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        bw.flush();
        bw.close();
    }
    
    public void saveRecordAuction(Auction object)throws Exception{
        String path = Constants.AUCTION_CSV;
        Class cl = Auction.class;
        String[] header = auctionHeader;
        
        List<Auction> list = getAllRecords(path, cl, header);
        list.add(object);
        logger.debug(list);
        initRecords(ConfigUtils.getConfigurationEntry(path), cl, header, list);
    }
    
    public void saveRecordBuyer(Buyer object) throws Exception{
        String path = Constants.BUYER_CSV;
        Class cl = Buyer.class;
        String[] header = buyerHeader;
        
        List<Buyer> list = getAllRecords(path, cl, header);
        list.add(object);
        logger.debug(list);
        //initRecords(ConfigUtils.getConfigurationEntry(path), cl, header, list);
        appendRecord1(ConfigUtils.getConfigurationEntry(path), cl, list);
    }
    
    public void saveRecordProduct(Product object) throws Exception{
        String path = Constants.PRODUCT_CSV;
        Class cl = AuctionProduct.class;
        String[] header = productHeader;
        
        List<Product> list = new ArrayList<>();
        list.add(object);
        logger.debug(list);
        
        appendRecord1(ConfigUtils.getConfigurationEntry(path), cl, list);
        
    }
    
    public void saveRecordSeller (Seller object) throws Exception{
        String path = Constants.SELLER_CSV;
        Class cl = Seller.class;
        String[] header = sellerHeader;
        
        List<Seller> list = getAllRecords(path, cl, header);
        list.add(object);
        logger.debug(list);
        //initRecords(ConfigUtils.getConfigurationEntry(path), cl, header, list);
        appendRecord1(ConfigUtils.getConfigurationEntry(path), cl, list);
    }
    
    public void saveRecordAuctionProduct (AuctionProduct object) throws Exception{
        String path = Constants.AUCTIONPRODUCT_CSV;
        Class cl = AuctionProduct.class;
        String[] header = auctionProductHeader;
        
        List<AuctionProduct> list = new ArrayList<>();
        list.add(object);
        logger.debug(list);
        //initRecords(ConfigUtils.getConfigurationEntry(path), cl, header, list);
        appendRecord1(ConfigUtils.getConfigurationEntry(path), cl, list);
    }
    
    public void deleteRecordAuction(String id)throws Exception{
        List<Auction> list = getAllRecords(ConfigUtils.getConfigurationEntry(Constants.AUCTION_CSV), Auction.class, auctionHeader);
        for (Auction auction: list){
            if (auction.getId().equals(id)){
                list.remove(auction);
                logger.debug("Auction " + id + " has been deleted");
            }
        }
    }
    
    public void deleteRecordBuyer(String id)throws Exception{
        List<Buyer> list = getAllRecords(ConfigUtils.getConfigurationEntry(Constants.BUYER_CSV), Buyer.class, buyerHeader);
        for (Buyer buyer: list){
            if (buyer.getId().equals(id)){
                list.remove(buyer);
                logger.debug("Buyer " + id + " has been deleted");
            }
        }
    }
    
    public void deleteRecordProduct(String id)throws Exception{
        List<Product> list = getAllRecords(ConfigUtils.getConfigurationEntry(Constants.PRODUCT_CSV), Product.class, productHeader);
        for (Product product: list){
            if (product.getId().equals(id)){
                list.remove(product);
                logger.debug("Product " + id + " has been deleted");
            }
        }
    }
    
    public void deleteRecordSeller(String id)throws Exception{
        List<Seller> list = getAllRecords(ConfigUtils.getConfigurationEntry(Constants.SELLER_CSV), Seller.class, sellerHeader);
        for (Seller seller: list){
            if (seller.getId().equals(id)){
                list.remove(seller);
                logger.debug("Seller " + id + " has been deleted");
            }
        }
    }
    
    public void deleteRecordAuctionProduct(String id)throws Exception{
        List<AuctionProduct> list = getAllRecords(ConfigUtils.getConfigurationEntry(Constants.AUCTIONPRODUCT_CSV), AuctionProduct.class, sellerHeader);
        for (AuctionProduct auctionProduct: list){
            if (auctionProduct.getProductId().equals(id)){
                list.remove(auctionProduct);
                logger.debug("auctionProduct " + id + " has been deleted");
            }
        }
    }
    
    public Auction getRecordByIdAuction(String id) throws Exception{
        List<Auction> list = getAllRecords(ConfigUtils.getConfigurationEntry(Constants.AUCTION_CSV), Auction.class, auctionHeader);
        for(Auction auction : list){
            logger.debug("searching for auction: " + id);
            if(auction.getId().equals(id)){
                return auction;
            }
        }
        throw new Exception("Auction with Id: " + id + "not found");
    }
    
    public Buyer getRecordByIdBuyer(String id) throws Exception{
        List<Buyer> list = getAllRecords(ConfigUtils.getConfigurationEntry(Constants.BUYER_CSV), Buyer.class, buyerHeader);
        for(Buyer buyer : list){
            logger.debug("searching for buyer: " + id + "...");
            if(buyer.getId().equals(id)){
                return buyer;
            }
        }
        throw new Exception("Buyer with Id: " + id + "not found");
    }
    
    public Product getRecordByIdProduct(String id) throws Exception{
        List<Product> list = getAllRecords(ConfigUtils.getConfigurationEntry(Constants.PRODUCT_CSV), Product.class, productHeader);
        for(Product product : list){
            logger.debug("searching for product: " + id + "...");
            if(product.getId().equals(id)){
                return product;
            }
        }
        throw new Exception("Product with Id: " + id + "not found");
    }
    
    public Seller getRecordByIdSeller(String id) throws Exception{
        List<Seller> list = getAllRecords(ConfigUtils.getConfigurationEntry(Constants.SELLER_CSV), Seller.class, sellerHeader);
        for(Seller seller : list){
            logger.debug("searching for seller: " + id + "...");
            if(seller.getId().equals(id)){
                return seller;
            }
        }
        throw new Exception("Seller with Id: " + id + "not found");
    }
    
    public AuctionProduct getRecordByIdAuctionProduct(String id) throws Exception{
        List<AuctionProduct> list = getAllRecords(ConfigUtils.getConfigurationEntry(Constants.AUCTIONPRODUCT_CSV), AuctionProduct.class, auctionProductHeader);
        for(AuctionProduct auctionProduct : list){
            logger.debug("searching for AuctionProduct: " + id + "...");
            if(auctionProduct.getProductId().equals(id)){
                return auctionProduct;
            }
        }
        throw new Exception("AuctionProduct with Id: " + id + "not found");
    }
    
}
