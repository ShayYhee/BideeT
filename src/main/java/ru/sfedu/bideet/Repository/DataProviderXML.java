package ru.sfedu.bideet.Repository;

import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.*;
import java.util.*;
import org.apache.logging.log4j.*;
import ru.sfedu.bideet.ConfigUtils;
import static ru.sfedu.bideet.Constants.*;
import ru.sfedu.bideet.Entities.*;

/**
 *
 * @author Osebi
 */
public class DataProviderXML {
    @XmlTransient
    private static Logger logger = LogManager.getLogger(DataProviderXML.class);
    private JAXBContext context;
    
    public <T> List<T> getAllRecords(String path) throws IOException{
        File file = new File(path);
        file.createNewFile();
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader inputData = new InputStreamReader(fis);
        try{
            context = JAXBContext.newInstance(XmlWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            XmlWrapper wrap = (XmlWrapper) unmarshaller.unmarshal(inputData);
            logger.debug("initializing records: " + wrap.getList());
            return new ArrayList<>(wrap.getList());
        }catch (JAXBException e){
            logger.info("SimpleTest " + e.getMessage());
        }
        return new ArrayList<>();
    }
    
    public void initRecord(List list, String path){
        try{
            File f = new File(path);
            FileOutputStream output = new FileOutputStream(f);
            context = JAXBContext.newInstance(XmlWrapper.class);
            XmlWrapper wrap = new XmlWrapper();
            wrap.setList(list);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(wrap, output);
        }catch(JAXBException | FileNotFoundException e){
            logger.error("Initializing error " + e.getMessage());
        }
    }
    
    public void saveRecordAuction(Auction object) throws Exception{
        List<Auction> list = getAllRecords(ConfigUtils.getConfigurationEntry(AUCTION_XML));
        list.add(object);
        initRecord(list, ConfigUtils.getConfigurationEntry(AUCTION_XML));
    }
    
    public void saveRecordAuctionProduct(AuctionProduct object) throws Exception{
        List<AuctionProduct> list = getAllRecords(ConfigUtils.getConfigurationEntry(AUCTIONPRODUCT_XML));
        list.add(object);
        initRecord(list, ConfigUtils.getConfigurationEntry(AUCTIONPRODUCT_XML));
    }
    
    public void saveRecordBuyer(Buyer object) throws Exception{
        List<Buyer> list = getAllRecords(ConfigUtils.getConfigurationEntry(BUYER_XML));
        list.add(object);
        initRecord(list, ConfigUtils.getConfigurationEntry(BUYER_XML));
    }
    
    public void saveRecordProduct(Product object) throws Exception{
        List<Product> list = getAllRecords(ConfigUtils.getConfigurationEntry(PRODUCT_XML));
        list.add(object);
        initRecord(list, ConfigUtils.getConfigurationEntry(PRODUCT_XML));
    }
    
    public void saveRecordSeller(Seller object) throws Exception{
        List<Seller> list = getAllRecords(ConfigUtils.getConfigurationEntry(SELLER_XML));
        list.add(object);
        initRecord(list, ConfigUtils.getConfigurationEntry(SELLER_XML));
    }
    
    public void deleteRecordAuction(Integer id) throws Exception{
        List<Auction> list = getAllRecords(ConfigUtils.getConfigurationEntry(AUCTION_XML));
        for (Auction auction : list){
            if (auction.getId().equals(id)){
                list.remove(auction);
                logger.info("Auction has been deleted");
            }else throw new Exception("Auction could not be deleted! Check ID");
            initRecord(list, ConfigUtils.getConfigurationEntry(AUCTION_XML));
        }
    }
    
    public void deleteRecordAuctionProduct(Integer id) throws Exception{
        List<AuctionProduct> list = getAllRecords(ConfigUtils.getConfigurationEntry(AUCTIONPRODUCT_XML));
        for (AuctionProduct aucprod : list){
            if (aucprod.getProductId().equals(id)){
                list.remove(aucprod);
                logger.info("AuctionProduct has been deleted");
            }else throw new Exception("AuctionProduct could not be deleted! Check ID");
            initRecord(list, ConfigUtils.getConfigurationEntry(AUCTIONPRODUCT_XML));
        }
    }
    
    public void deleteRecordBuyer(Integer id) throws Exception{
        List<Buyer> list = getAllRecords(ConfigUtils.getConfigurationEntry(BUYER_XML));
        for (Buyer buyer : list){
            if (buyer.getId().equals(id)){
                list.remove(buyer);
                logger.info("Buyer has been deleted");
            }else throw new Exception("Buyer could not be deleted! Check ID");
            initRecord(list, ConfigUtils.getConfigurationEntry(BUYER_XML));
        }
    }
    
    public void deleteRecordProduct(Integer id) throws Exception{
        List<Product> list = getAllRecords(ConfigUtils.getConfigurationEntry(PRODUCT_XML));
        for (Product product : list){
            if (product.getId().equals(id)){
                list.remove(product);
                logger.info("Product has been deleted");
            }else throw new Exception("Product could not be deleted! Check ID");
            initRecord(list, ConfigUtils.getConfigurationEntry(PRODUCT_XML));
        }
    }
    
    public void deleteRecordSeller(Integer id) throws Exception{
        List<Seller> list = getAllRecords(ConfigUtils.getConfigurationEntry(SELLER_XML));
        for (Seller seller : list){
            if (seller.getId().equals(id)){
                list.remove(seller);
                logger.info("Seller has been deleted");
            }else throw new Exception("Seller could not be deleted! Check ID");
            initRecord(list, ConfigUtils.getConfigurationEntry(SELLER_XML));
        }
    }
    
    public Auction getRecordAuction(Integer id) throws Exception{
        List<Auction> list = getAllRecords(ConfigUtils.getConfigurationEntry(AUCTION_XML));
        for (Auction auction : list){
            logger.debug("get auction by id: " + id);
            if(auction.getId().equals(id)){
                return auction;
            }
        }
        throw new Exception("auction not found");
    }
     
    public AuctionProduct getRecordAuctionProduct(Integer id) throws Exception{
        List<AuctionProduct> list = getAllRecords(ConfigUtils.getConfigurationEntry(AUCTIONPRODUCT_XML));
        for (AuctionProduct aucprod : list){
            logger.debug("get auction product by id: " + id);
            if(aucprod.getProductId().equals(id)){
                return aucprod;
            }
        }
        throw new Exception("Auction product not found");
    }
      
    public Buyer getRecordBBuyer(Integer id) throws Exception{
        List<Buyer> list = getAllRecords(ConfigUtils.getConfigurationEntry(BUYER_XML));
        for (Buyer buyer : list){
            logger.debug("get buyer by id: " + id);
            if(buyer.getId().equals(id)){
                return buyer;
            }
        }
        throw new Exception("Buyer not found");
    } 
     
    public Product getRecordProduct(Integer id) throws Exception{
        List<Product> list = getAllRecords(ConfigUtils.getConfigurationEntry(PRODUCT_XML));
        for (Product product : list){
            logger.debug("get product by id: " + id);
            if(product.getId().equals(id)){
                return product;
            }
        }
        throw new Exception("product not found");
    }
    
     public Seller getRecordSeller(Integer id) throws Exception{
        List<Seller> list = getAllRecords(ConfigUtils.getConfigurationEntry(SELLER_XML));
        for (Seller seller : list){
            logger.debug("get seller by id: " + id);
            if(seller.getId().equals(id)){
                return seller;
            }
        }
        throw new Exception("Seller not found");
    }
}
