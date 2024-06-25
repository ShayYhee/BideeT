package ru.sfedu.bideet.Entities;

import com.opencsv.bean.CsvBindByName;
import jakarta.xml.bind.annotation.*;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Osebi
 */
@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {
    @Id
    @SequenceGenerator(name="user_sequence", sequenceName="user_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @XmlElement(name="Id")
    @CsvBindByName(column="Id")
    public Integer id;
    
    @XmlElement(name="Name")
    @CsvBindByName(column="Name")
    private String name;
    
    @XmlElement(name="Username")
    @CsvBindByName(column="Username")
    private String username;
    
    @XmlElement(name="Email")
    @CsvBindByName(column="Email")
    private String email;
    
    @XmlElement(name="Password")
    @CsvBindByName(column="password")
    private String password; 
    
    public User(){}
    public User(String name, String username, String email, String password){
        //this.id = nextId.getAndIncrement();
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    public static long getNum(Path file){
        
        long count = 0;
        try {
            count = Files.lines(file).count();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (long) count;
    }

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
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    @Override
    public String toString(){
        return id + ", " + name+ ", " + username + ", " + email + ", " + password;
    }
}
