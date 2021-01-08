package il.ac.hit.costManager.model;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class InMemoryModel implements IModel{

    private List<CostItem> items = new LinkedList<CostItem>();
    private List<Category> categories = new LinkedList<Category>();

    public static String driver = "org.apache.derby.jdbc.ClientDriver";
    public static String protocol = "jdbc:derby://localhost:1527/CostManager;create=true";

    @Override
    public void addCostItem(CostItem item) throws CostManagerException {
        try
        {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            statement.execute("insert into ItemDB(ITEMIDCOL,CATEIDCOL," +
                    "ITEMNAMECOL,CURRENCYCOL,PRICECOL,PurchaseDateCol) VALUES (" + item.getItemID() + ","
                    + item.getCateID() + "," + "'" + item.getItemName() + "','" +
                    item.getEcurrency() + "'," + item.getPrice() + ",'" +
                    item.getPurchaseDate() + "')");
            ResultSet rs = statement.executeQuery(
                    "SELECT ItemIDCol,ITEMNAMECOL FROM ItemDB ORDER BY ItemIDCol");
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
            throw new CostManagerException("Couldn't add a new item.");
        }
        items.add(item);
    }

    @Override
    public void deleteCostItem(CostItem item) throws CostManagerException {
        try {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM ITEMDB WHERE ItemNameCol = '" + item.getItemName()+"'");
            ResultSet rs = statement.executeQuery(
                    "SELECT ItemIDCol,ITEMNAMECOL FROM ItemDB ORDER BY ItemIDCol");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        for(int i = 0; i < items.size();i++){
                if(item.getItemName().equals(items.get(i).getItemName())){
                items.remove(i);
                break;
            }
        }
    }

    @Override
    public void addCategory(Category category) throws CostManagerException {
        categories.add(category);
    }

    @Override
    public void deleteCategory(Category category) throws CostManagerException {
        for(int i = 0; i < categories.size();i++){
            if(category.getCategoryName().equals(categories.get(i).getCategoryName())){
                categories.remove(i);
                break;
            }
        }
    }

    @Override
    public int generateItemID() throws CostManagerException {
        return 0;
    }

    @Override
    public void loadItems()  { //this function is used for loading items from database
        List<CostItem> itemsNew = new LinkedList<CostItem>();
        try
        {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM ItemDB ORDER BY ItemIDCol");
            while(rs.next())
            {
                Currency currency = Currency.valueOf(rs.getString("CurrencyCol").toUpperCase());
                CostItem costItem = new CostItem(rs.getInt("ItemIDCol"),
                        rs.getInt("CateIDCol"), rs.getString("ITEMNAMECOL"),
                        currency, rs.getInt("PriceCol"),
                        rs.getString("PurchaseDateCol"));

                //String cateName = getNameOfCate(rs.getInt("CateIDCol"));

                System.out.println("ItemID="+rs.getInt("ItemIDCol")
                        + "CateID= " + rs.getInt("CateIDCol") +
                        " Name= "+rs.getString("ITEMNAMECOL")
                        + "CategoryName = " + "cateName" +
                        "Currency = " + rs.getString("CurrencyCol") +
                        "Price= " + rs.getInt("PriceCol") +
                        "Purchase Date: " + rs.getString("PurchaseDateCol"));
                itemsNew.add(costItem);
            }
            items = itemsNew;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadCategories() throws CostManagerException {
        List<Category> newList = new LinkedList<>();
        try
        {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM CateDB ORDER BY CateIDCol");
            while(rs.next())
            {
                Category category = new Category(rs.getInt("CateIDCol"),
                        rs.getString("CateNameCol"));

                System.out.println("CateID="+rs.getInt("CateIDCol")
                        + "Category Name= " + rs.getString("CateNameCol"));
                categories.add(category);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCateList() throws CostManagerException {

    }

    @Override
    public void updateItemsList() throws CostManagerException {

    }

    @Override
    public CostItem[] getCostItems() throws CostManagerException {
        return items.toArray(new CostItem[0]);
    }

    @Override
    public Category[] getCategories() throws CostManagerException {
        return categories.toArray(new Category[0]);
    }

    @Override
    public List<CostItem> getAllItems() throws CostManagerException {
        return null;
    }

    @Override
    public List<Category> getAllCategories() throws CostManagerException {
        return null;
    }
    @Override
    public List<CostItem> handleReport(String sDateFrom, String sDateTo) throws ParseException, CostManagerException {

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dateFrom = format.parse(sDateFrom);
        Date dateTo = format.parse(sDateTo);
        loadItems();
        List<CostItem> filteredItems = new LinkedList<CostItem>();

        for(int i=0;i<items.size();i++){
            Date currentItemDate = format.parse(items.get(i).getPurchaseDate());
            if(dateFrom.before(currentItemDate)&&dateTo.after(currentItemDate)) {
                filteredItems.add(items.get(i));
            }
        }
        return filteredItems;
    }
}
