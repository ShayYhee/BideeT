package ru.sfedu.bideet.Entities;

import com.opencsv.bean.CsvBindByName;
import jakarta.xml.bind.annotation.*;
import java.io.*;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.*;
import javax.persistence.*;
import ru.sfedu.bideet.Enumerations.*;

/**
 *
 * @author Osebi
 */

@Entity
@XmlRootElement(name="Product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Serializable {
    Seller seller = new Seller();
    
    @Id
    @XmlElement(name="Id")
    @SequenceGenerator(name="user_sequence", sequenceName="user_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @CsvBindByName(column="Id")
    public Integer id;
    
    @XmlElement(name="Name")
    @CsvBindByName(column="Name")
    public String name;
    
    @XmlElement(name="Brand")
    @CsvBindByName(column="Brand")
    public String brand;
    
    @XmlElement(name="Year")
    @CsvBindByName(column="Year")
    public String year;
    
    @XmlElement(name="StartPrice")
    @CsvBindByName(column="StartPrice")
    public String startPrice;
    
    @XmlElement(name="Seller")
    @CsvBindByName(column="Seller")
    //public Seller seller;
    public String sellerName = seller.getName();
    
    @XmlElement(name="ProductState")
    @CsvBindByName(column="ProductState")
    public ProductState productState;
    
    public Product(String name, String brand, String year, String startPrice, String sellerName, ProductState productState){
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.startPrice = startPrice;
        this.sellerName = sellerName;
        this.productState = productState;
        this.id = nextId.getAndIncrement();
    }
    
    public Product(){}
    
    public static long getNum(){
        
        // make a connection to the file
        Path file = Paths.get("src/main/resources/CSVFILES/Product.csv");
        // read all lines of the file
        long count = 0;
        try {
            count = Files.lines(file).count();
        } catch (IOException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getBrand(){
        return brand;
    }
    
    public void setBrand(String brand){
        this.brand = brand;
    }
    
    public String getYear(){
        return year;
    }
    
    public String getStartPrice(){
        return startPrice;
    }
    
    public void setStartPrice(String startPrice){
        this.startPrice = startPrice;
    }
    
    public void setYear(String year){
        this.year = year;
    }
    
    public String getSeller(){
        return sellerName;
    }
    
    public void setSeller(String sellerName){
        this.sellerName = sellerName;
    }
    
    public ProductState getAuctionState(){
        return productState;
    }
    
    public void setAuctionState(ProductState productState){
        this.productState = productState;
    }
    
    @Override
    public String toString(){
        return  id + ", " + name + ", " + brand + ", " + year + ", " + startPrice;// + ", " + sellerName+ ", " + productState;
    }
}
