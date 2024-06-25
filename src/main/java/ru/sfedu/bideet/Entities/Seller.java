package ru.sfedu.bideet.Entities;

import jakarta.xml.bind.annotation.*;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import static ru.sfedu.bideet.Entities.User.getNum;

/**
 *
 * @author Osebi
 */

@XmlRootElement(name="Seller")
@XmlAccessorType(XmlAccessType.FIELD)
public class Seller extends User implements Serializable{
    
    public Seller(){}
    
    public Seller(String name, String username, String email, String password) {
        super(name, username, email, password);
        id = nextId.getAndIncrement();
    }
    
    Path file = Paths.get("src/main/resources/CSVFILES/Seller.csv");
    long i = getNum(file);
    public AtomicInteger nextId = new AtomicInteger((int) (i/2));
    
}
