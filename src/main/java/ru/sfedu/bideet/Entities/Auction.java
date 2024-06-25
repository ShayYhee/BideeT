package ru.sfedu.bideet.Entities;

import ru.sfedu.bideet.Enumerations.*;
import com.opencsv.bean.CsvBindByName;
import jakarta.xml.bind.annotation.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.persistence.*;
import org.apache.logging.log4j.*;

/**
 *
 * @author Osebi
 */
@Entity
@XmlRootElement(name="Auction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Auction implements Serializable {
    
    @Id
    @XmlElement(name="Id")
    @CsvBindByName(column="Id")
    @SequenceGenerator(name="user_sequence", sequenceName="user_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    public Integer id;
    
    @XmlElement(name="Product")
    @CsvBindByName(column="Product")
    public List<Product> products;
    
    @XmlElement(name="AuctionState")
    @CsvBindByName(column="AuctionState")
    public AuctionState auctionState;
    
    @XmlTransient
    Logger log = LogManager.getLogger(Auction.class);
    
    public Auction(List products, AuctionState auctionState){
        this.id = nextId.getAndIncrement();
        this.products = products;
        this.auctionState = auctionState;
    }
    
    public Auction(){}
    
    public static long getNum(){
        
        // make a connection to the file
        Path file = Paths.get("src/main/resources/CSVFILES/Auction.csv");
        // read all lines of the file
        long count = 0;
        try {
            count = Files.lines(file).count();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AuctionProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return (long) count;
    }
    
    static long i = getNum();
    public static AtomicInteger nextId = new AtomicInteger((int) (i/2));
    
    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public List<Product> getProducts(){
        return products;
    }
    
    public void setProducts(List<Product> products){
        this.products = products;
    }
    
    public AuctionState getAuctionState(){
        return auctionState;
    }
    
    public void setAuctionState(AuctionState auctionState){
        this.auctionState = auctionState;
    }
    
    @Override
    public String toString(){
        return id + ", " + products;
    }
}
