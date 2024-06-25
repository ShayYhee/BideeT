package ru.sfedu.bideet.Entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.apache.logging.log4j.Logger;
import ru.sfedu.bideet.Entities.Product;
import ru.sfedu.bideet.Enumerations.AuctionState;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-25T23:58:04", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Auction.class)
public class Auction_ { 

    public static volatile SingularAttribute<Auction, AuctionState> auctionState;
    public static volatile SingularAttribute<Auction, Logger> log;
    public static volatile SingularAttribute<Auction, Integer> id;
    public static volatile ListAttribute<Auction, Product> products;

}