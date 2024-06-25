package ru.sfedu.bideet.Entities;

import com.opencsv.bean.CsvBindByName;
import jakarta.xml.bind.annotation.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.*;
import javax.persistence.*;
/**
 *
 * @author Osebi
 */

@Entity
@XmlRootElement(name="Auction Product")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuctionProduct implements Serializable {
    Product product;
    
    @Id
    @XmlElement(name="ProductId")
    @SequenceGenerator(name="user_sequence", sequenceName="user_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @CsvBindByName(column="ProductId")
    public Integer productId;
    
    @XmlElement(name="ProductName")
    @CsvBindByName(column="ProductName")
    public String productName;
    
    @XmlElement(name="StartPrice")
    @CsvBindByName(column="StartPrice")
    public String startPrice;
    
    @XmlElement(name="Buyer")
    @CsvBindByName(column="Buyer")
    public String buyer;
    
    @XmlElement(name="CurrentBid")
    @CsvBindByName(column="CurrentBid")
    public float currentBid;
    
    @XmlElement(name="Date")
    @CsvBindByName(column="Date")
    public Date date;
    
    @XmlElement(name="WinningBid")
    @CsvBindByName(column="WininngBid")
    public float winningBid;
    
    
    public AuctionProduct(){}
    
    public AuctionProduct(String productName, String startPrice){
        this.productName = productName;
        this.startPrice = startPrice;
        this.productId = nextId.getAndIncrement();
    }
    
    public static long getNum(){
        
        // make a connection to the file
        Path file = Paths.get("src/main/resources/CSVFILES/AuctionProduct.csv");
        // read all lines of the file
        long count = 0;
        try {
            count = Files.lines(file).count();
        } catch (IOException ex) {
            Logger.getLogger(AuctionProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (long) count;
    }

    static long i = getNum();
    public static AtomicInteger nextId = new AtomicInteger((int) (i/2));
    
    public Integer getProductId(){
        return productId;
    }
    
    public void setProductId(Integer productId){
        this.productId = productId;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public void setProductName(String productName){
        this.productName = productName;
    }
    
    public String getStartPrice(){
        return startPrice;
    }
    
    public void setStartPrice(String startPrice){
        this.startPrice = startPrice;
    }
    
    public String getBuyer(){
        return buyer;
    }
    
    public void setBuyer(String buyer){
        this.buyer = buyer;
    }
    
    public float getCurrentBid(){
        return currentBid;
    }
    
    public void setCurrentBid(float currentBid){
        this.currentBid = currentBid;
    }
    
    public float getWinningBid(){
        return winningBid;
    }
    
    public void setWinningBid(float winningBid){
        this.winningBid = winningBid;
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    @Override
    public String toString(){
        return productId + ", " + productName+ ", " + startPrice + ", " + buyer + ", " + currentBid;
    }
}
