package il.ac.hit.costManager.model;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

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

    //|||||||||||||||||||||||||||||||| This function will add item from the list ||||||||||||||||||||||||||||||||
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

    //|||||||||||||||||||||||||||||||| This function will remove item from the list ||||||||||||||||||||||||||||||||
    @Override
    public void deleteCostItem(CostItem item) throws CostManagerException {
        try {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM ITEMDB WHERE ItemNameCol = '" + item.getItemName()+"' AND PurchaseDateCol = '" +
            item.getPurchaseDate() + "'");
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

    //|||||||||||||||||||||||||||||||| This function will add category to the list ||||||||||||||||||||||||||||||||

    @Override
    public void addCategory(Category category) throws CostManagerException {
        try
        {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            //=========== Run a code to check if CateDB exsists =============
            //statement.execute("create table CateDB(CateIDCol int, CateNameCol varchar(40))");
            statement.execute("insert into CateDB(CateIDCol,CateNameCol) VALUES (" + category.getCategoryID()
                    + ",'" + category.getCategoryName()  + "')");
            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM CateDB ORDER BY CateIDCol");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        categories.add(category);
    }

    //|||||||||||||||||||||||||||||||| This function will delete category from the list ||||||||||||||||||||||||||||||||

    @Override
    public void deleteCategory(Category category) throws CostManagerException {
        try {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            //=========== Run a code to check if CateDB exsists =============
            //statement.execute("create table CateDB(CateIDCol int, CateNameCol varchar(40))");
            statement.execute("DELETE FROM CateDB WHERE CateNameCol = '" + category.getCategoryName()+"'");
            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM ItemDB ORDER BY CateIDCol");
        }
        catch(Exception e) { e.printStackTrace(); }

        for(int i = 0; i < categories.size();i++){
            if(category.getCategoryName().equals(categories.get(i).getCategoryName())){
                categories.remove(i);
                break;
            }
        }
    }

    //|||||||||||||||||||||||||||||||| This function is used for loading items from database ||||||||||||||||||||||||||||||||

    @Override
    public void loadItems()  {
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

    //|||||||||||||||||||||||||||||||| This function is used for loading categories from database ||||||||||||||||||||||||||||||||

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
                newList.add(category);
            }
            categories = newList;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    //|||||||||||||||||||||||||||||||| This function is used to create Dataset for the pieChart ||||||||||||||||||||||||||||||||


    @Override
    public DefaultPieDataset createDataset() throws CostManagerException {
        DefaultPieDataset result = new DefaultPieDataset();
        loadItems(); //make sure list is loaded
        loadCategories(); //make sure categories list is loaded
        //counting array
        int count[] = new int[categories.size()];
        for (int i = 0; i < categories.size(); ++i)
            count[i] = 0;
        for (int i = 0; i < items.size(); ++i)
            ++count[items.get(i).getCateID()];
        for(int i = 0; i < count.length; i++){
            result.setValue(categories.get(i).getCategoryName(), count[i]);
        }
        return result;
    }

    //|||||||||||||||||||||||||||||||| This function is used to add costItems to the list ||||||||||||||||||||||||||||||||


    @Override
    public CostItem[] getCostItems() throws CostManagerException {
        return items.toArray(new CostItem[0]);
    }

    //|||||||||||||||||||||||||||||||| This function is used to add categories to the list ||||||||||||||||||||||||||||||||


    @Override
    public Category[] getCategories() throws CostManagerException {
        return categories.toArray(new Category[0]);
    }

    //|||||||||||||||||||||||||||||||| This function is used to load the items from database and return the list ||||||||||||||||||||||||||||||||


    @Override
    public List<CostItem> getAllItems() throws CostManagerException {
        List<CostItem> newList = new LinkedList<>();
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

                System.out.println("ItemID="+rs.getInt("ItemIDCol")
                        + "CateID= " + rs.getInt("CateIDCol") +
                        " Name= "+rs.getString("ITEMNAMECOL")
                        + "Currency = " + rs.getString("CurrencyCol") +
                        "Price= " + rs.getInt("PriceCol") +
                        "Purchase Date: " + rs.getString("PurchaseDateCol"));
                newList.add(costItem);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return newList;
    }

    //|||||||||||||||||||||||||||||||| This function is used to load the categories from database and return the list ||||||||||||||||||||||||||||||||


    @Override
    public List<Category> getAllCategories() throws CostManagerException {
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
                newList.add(category);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return newList;
    }

    //|||||||||||||||||||||||||||||||| This function is used to handle the date ranges from the report/piechart ||||||||||||||||||||||||||||||||

    @Override
    public List<CostItem> handleReport(String sDateFrom, String sDateTo) throws ParseException, CostManagerException {

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dateFrom = format.parse(sDateFrom);
        Date dateTo = format.parse(sDateTo);
        loadItems();
        List<CostItem> filteredItems = new LinkedList<CostItem>();

        for(int i=0;i<items.size();i++){
            Date currentItemDate = format.parse(items.get(i).getPurchaseDate());
            if(dateFrom.before(currentItemDate)&&dateTo.after(currentItemDate)||dateFrom.equals(currentItemDate)||dateTo.equals(currentItemDate)) {
                filteredItems.add(items.get(i));
            }
        }
        return filteredItems;
    }
}
