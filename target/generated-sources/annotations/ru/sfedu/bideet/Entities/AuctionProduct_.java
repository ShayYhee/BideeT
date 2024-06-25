package ru.sfedu.bideet.Entities;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ru.sfedu.bideet.Entities.Product;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-25T23:58:04", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(AuctionProduct.class)
public class AuctionProduct_ { 

    public static volatile SingularAttribute<AuctionProduct, String> startPrice;
    public static volatile SingularAttribute<AuctionProduct, Date> date;
    public static volatile SingularAttribute<AuctionProduct, Product> product;
    public static volatile SingularAttribute<AuctionProduct, Float> currentBid;
    public static volatile SingularAttribute<AuctionProduct, Float> winningBid;
    public static volatile SingularAttribute<AuctionProduct, Integer> productId;
    public static volatile SingularAttribute<AuctionProduct, String> productName;
    public static volatile SingularAttribute<AuctionProduct, String> buyer;

}