package ru.sfedu.bideet.Entities;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import ru.sfedu.bideet.Entities.Seller;
import ru.sfedu.bideet.Enumerations.ProductState;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-25T23:58:04", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Seller> seller;
    public static volatile SingularAttribute<Product, String> startPrice;
    public static volatile SingularAttribute<Product, String> year;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> sellerName;
    public static volatile SingularAttribute<Product, Integer> id;
    public static volatile SingularAttribute<Product, String> brand;
    public static volatile SingularAttribute<Product, ProductState> productState;

}