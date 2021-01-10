package il.ac.hit.costManager.model;

import java.util.Scanner;

public class ConsoleItemReader {

//this class is used for testing the model and entering data manually
    public CostItem readItem() throws Exception {
        int itemID, cateID, price;
        String itemName, dateString, getCurrency = null;
        String purchaseDate;
        Currency currency = null;
        DerbyDBModel ItemDao = new DerbyDBModel();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter itemID:");
        itemID = scan.nextInt();
        //itemID = ItemDao.generateItemID();
        //System.out.println("Current new ItemID and CateID are: " + itemID + 0);
        scan.nextLine();//Enter Data
        System.out.println("Please enter CategoryID:");
        cateID = scan.nextInt();
        scan.nextLine();//Enter Data
        System.out.println("Please enter ItemName:");
        itemName = scan.nextLine();
        System.out.println("Please choose Currency: (ILS,GBP,USD,EUO)");
        currency = Currency.valueOf(getCurrency.toUpperCase());
        System.out.println("Please enter Price:");
        price = scan.nextInt();
        scan.nextLine();//Enter Data
        System.out.println("Please enter Purchase Date in the format of DD.MM.YYYY :");
        purchaseDate = scan.nextLine(); //Enter Data

        return new CostItem(itemID ,cateID ,itemName ,currency ,price , purchaseDate);
    }

}