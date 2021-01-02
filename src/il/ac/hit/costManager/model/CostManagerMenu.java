package il.ac.hit.costManager.model;

import java.util.List;
import java.util.Scanner;


public class CostManagerMenu {


    public static void main(String[] args) throws Exception {
        DerbyDBModel itemDao = new DerbyDBModel();
        Scanner scan = new Scanner(System.in);
        Currency currency = Currency.ILS;

        //Add a new Item
       System.out.println("Add a new item to the database:");
        //CostItem item = new ConsoleItemReader().readItem();
        //int i = itemDao.generateItemID();
        CostItem item = new CostItem(12,1,"Tomato",currency,13,"12.01.2020");
        itemDao.addCostItem(item);
        System.out.println("Added a new item to the database");

        //Remove an item via name
        //System.out.println("The Item you have added will be deleted");
        //itemDao.deleteCostItem(item);

        //Adding items to list
        List<CostItem> newList = itemDao.getAllItems();
        for(int i = 0; i < newList.size(); i++){
            CostItem Item = newList.get(i);
            System.out.println("ItemID="+Item.getItemID()
                    + "CateID= " + Item.getCateID() +
                    " Name= "+Item.getItemName()
                    + "Currency = " + Item.getEcurrency() +
                    "Price= " + Item.getPrice() +
                    "Purchase Date: " + Item.getPurchaseDate());
        }
        //add a new category
        //Category category = new Category(2,"Vegtables");
        //ItemDao.addCategory(category);
        //System.out.println("Added a new fruit!");

        //Remove an category via name
        //System.out.println("The Category you have added will be deleted");
        //ItemDao.deleteCategory(category);

        //Show all the categories in database
        //System.out.println("Your categories list look like this:");
        //ItemDao.getCategories();

        }
}