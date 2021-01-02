package il.ac.hit.costManager.model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DerbyDBModel implements IModel {
    public static String driver = "org.apache.derby.jdbc.ClientDriver";
    public static String protocol = "jdbc:derby://localhost:1527/CostManager;create=true";

    public DerbyDBModel() throws CostManagerException {
        try {
            Class.forName(driver);
        }catch(ClassNotFoundException e){
            throw new CostManagerException("Excpetion");
        }
    }

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
        throw new CostManagerException("Exception");
        }
    }

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

    @Override
    public void deleteCostItem(CostItem item) throws CostManagerException {
        try {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            //=========== Run a code to check if ITEMDB exsists =============
            //create itemDB if not exsists
            //statement.execute("create table ItemDB(ItemIDCol int, CateIDCol int," +
            //        " ItemNameCol varchar(40), CurrencyCol varchar(40), PriceCol double, " +
            //        "PurchaseDateCol varchar(40))");
            statement.execute("DELETE FROM ITEMDB WHERE ItemNameCol = '" + item.getItemName()+"'");
            ResultSet rs = statement.executeQuery(
                    "SELECT ItemIDCol,ITEMNAMECOL FROM ItemDB ORDER BY ItemIDCol");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


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
    }

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
    }

    @Override
    public int generateItemID() throws CostManagerException {
        int incID = 1;
        int minID = 1;
        int prevID = 1;
        try
        {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT ItemIDCol FROM ItemDB ORDER BY ItemIDCol");
            while(rs.next())
            {
                incID = rs.getInt("ItemIDCol");
                System.out.println("Incoming ID is: " + incID + "and minID is: " + minID);
                if (incID - prevID == 2){
                    minID = incID - 1;
                    return minID;
                }
                prevID = incID;
            }
        }
        catch(Exception e) { e.printStackTrace(); }
        return minID;
    }

    @Override
    public CostItem[] getCostItems() throws CostManagerException {
        //action performed will execute this command
        return new CostItem[0];
    }

}
