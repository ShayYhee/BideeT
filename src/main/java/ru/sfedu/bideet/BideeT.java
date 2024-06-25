/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package ru.sfedu.bideet;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.bideet.Services.*;

/**
 *
 * @author Osebi
 */
public class BideeT {
    private static Logger logger = LogManager.getLogger(BideeT.class);

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        logger.info("Starting App!");
        Registration reg = new Registration();
        //OngoingBid1 bid = new OngoingBid1();
        //bid.OngoingBid1();
        //Login login = new Login();
        //AddProduct add = new AddProduct();
        //AddAuctionProduct ap = new AddAuctionProduct();
        //Bid bid = new Bid();
        //AuctionWins wins = new AuctionWins();
        //ViewAuctions vAuc = new ViewAuctions();
        
//        List<String> result = new ArrayList<>();
//        DataProviderTXT txt = new DataProviderTXT();
//        List<String> l = txt.getRecords("src/main/resources/TXTFILES/Product.txt");
//        String str = l.toString();
//        logger.info(str);
//        result.add(str);
//        for (String s: result){
//            if(s.contains("Id,")){
//                result.add(s);
//                logger.info(s);
//                logger.info("yes");
//            }
//            logger.info(result);
//        }
        
        
    }
}
