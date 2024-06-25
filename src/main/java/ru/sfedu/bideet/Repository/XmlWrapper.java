package ru.sfedu.bideet.Repository;

import jakarta.xml.bind.annotation.*;
import java.util.*;
import ru.sfedu.bideet.Entities.*;

/**
 *
 * @author Osebi
 * @param <T>
 */

@XmlRootElement(name="XmlWrapper")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Auction.class, AuctionProduct.class, Buyer.class, Product.class, Seller.class, User.class})
public class XmlWrapper<T> {
    @XmlElementWrapper(name="wrapper")
    @XmlElement(name="List")
    private List<T> list = new ArrayList<>();
    
    public XmlWrapper(){}
    
    public List<T> getList(){
        return list;
    }
    
    public void setList(List<T> list){
        this.list = list;
    }
}
