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
@XmlRootElement(name="Buyer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Buyer extends User implements Serializable{
    
    public Buyer(){}
    
    public Buyer(String name, String username, String email, String password) {
        super(name, username, email, password);
        id = nextId.getAndIncrement();
    }
    
    Path file = Paths.get("src/main/resources/CSVFILES/Buyer.csv");
    long i = getNum(file);
    public AtomicInteger nextId = new AtomicInteger((int) (i/2));
    
}
