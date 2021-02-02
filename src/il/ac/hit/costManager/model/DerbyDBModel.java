package il.ac.hit.costManager.model;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class DerbyDBModel implements IModel{


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
    }

    //|||||||||||||||||||||||||||||||| This function will remove item from the list ||||||||||||||||||||||||||||||||
    @Override
    public void deleteCostItem(CostItem item) throws CostManagerException {
        try {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM ITEMDB WHERE ItemNameCol = '" + item.getItemName()+"' AND PurchaseDateCol = '" +
                    item.getPurchaseDate() + "'");
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
            throw new CostManagerException("Couldn't delete item.");
        }
        /*
        for(int i = 0; i < items.size();i++){
            if(item.getItemName().equals(items.get(i).getItemName())){
                items.remove(i);
                break;
            }
        }

         */
    }
    //|||||||||||||||||||||||||||||||| This function will edit a cost item via id and name ||||||||||||||||||||||||||||||||

    @Override
    public void editCostItem(CostItem item, int newCateID) throws CostManagerException {
        try
        {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            String query = "update ItemDB set CateIDCol = ? where ItemNameCol = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt   (1, newCateID);
            preparedStmt.setString(2, item.getItemName());

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            connection.close();
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
            throw new CostManagerException("Couldn't edit item.");
        }
    }

    //|||||||||||||||||||||||||||||||| This function will add category to the list ||||||||||||||||||||||||||||||||

    @Override
    public void addCategory(Category category) throws CostManagerException {
        try
        {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            statement.execute("insert into CateDB(CateIDCol,CateNameCol) VALUES (" + category.getCategoryID()
                    + ",'" + category.getCategoryName()  + "')");

        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
            throw new CostManagerException("Couldn't add a new category.");
        }
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
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
            throw new CostManagerException("Couldn't delete category.");
        }

        //Remove the category also from list
        /*
        for(int i = 0; i < categories.size();i++){
            if(category.getCategoryName().equals(categories.get(i).getCategoryName())){//find the category via name
                categories.remove(i);
                //Save the index of the deleted category
                for(int j = i; j < categories.size(); j++){
                    Category category1 = categories.get(i);
                    for(int x = 0; x < items.size();x++){
                        if(items.get(x).getCateID() == category1.getCategoryID()){
                            editCostItem(items.get(x),j + 1);
                            items.get(x).setCateID(j + 1);
                        }
                    }
                    editCategory(category1);
                    categories.get(j).setCategoryID(j + 1);
                }
                break;
            }
        }


         */
    }

    //|||||||||||||||||||||||||||||||| This function will edit a category ||||||||||||||||||||||||||||||||

    @Override
    public void editCategory(Category category) throws CostManagerException {
        try
        {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            String query = "update CateDB set CateIDCol = ? where CateNameCol = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt   (1, category.getCategoryID());
            preparedStmt.setString(2, category.getCategoryName());

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            connection.close();
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
            throw new CostManagerException("Couldn't edit category.");
        }
    }

    //|||||||||||||||||||||||||||||||| This function is used for loading items from database ||||||||||||||||||||||||||||||||
/*
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


 */
    //|||||||||||||||||||||||||||||||| This function is used for loading categories from database ||||||||||||||||||||||||||||||||
/*
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


 */
    //|||||||||||||||||||||||||||||||| This function is used to create Dataset for the pieChart ||||||||||||||||||||||||||||||||


    @Override
    public DefaultPieDataset createDataset() throws CostManagerException {
        DefaultPieDataset result = new DefaultPieDataset();
            /*
            loadItems(); //make sure list is loaded
            loadCategories(); //make sure categories list is loaded
            //counting array
            int count[] = new int[categories.size()];
            for (int i = 0; i < categories.size(); ++i)
                count[i] = 0;
            for (int i = 0; i < items.size(); ++i)
                ++count[items.get(i).getCateID()];
            for (int i = 0; i < count.length; i++) {
                result.setValue(categories.get(i).getCategoryName(), count[i]);
            }
             */
        return result;
    }

    //|||||||||||||||||||||||||||||||| This function is used to add costItems to the list ||||||||||||||||||||||||||||||||

/*
    @Override
    public CostItem[] getCostItems() throws CostManagerException {
        return items.toArray(new CostItem[0]);
    }

    //|||||||||||||||||||||||||||||||| This function is used to add categories to the list ||||||||||||||||||||||||||||||||


    @Override
    public Category[] getCategories() throws CostManagerException {
        return categories.toArray(new Category[0]);
    }


 */
    //|||||||||||||||||||||||||||||||| This function is used to load the items from database and return the list ||||||||||||||||||||||||||||||||


    @Override
    public List<CostItem> getAllItems() throws CostManagerException {
        List<CostItem> newList = new LinkedList<>();
        try
        {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            int itemID = 1;
            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM ItemDB ORDER BY ItemIDCol");
            while(rs.next())
            {
                Currency currency = Currency.valueOf(rs.getString("CurrencyCol").toUpperCase());
                CostItem costItem = new CostItem(rs.getInt("ItemIDCol"),
                        rs.getInt("CateIDCol"), rs.getString("ITEMNAMECOL"),
                        currency, rs.getInt("PriceCol"),
                        rs.getString("PurchaseDateCol"));
                //Print for debug
                System.out.println("ItemID="+rs.getInt("ItemIDCol")
                        + "CateID= " + rs.getInt("CateIDCol") +
                        " Name= "+rs.getString("ITEMNAMECOL")
                        + "Currency = " + rs.getString("CurrencyCol") +
                        "Price= " + rs.getInt("PriceCol") +
                        "Purchase Date: " + rs.getString("PurchaseDateCol"));
                //Add to list
                newList.add(costItem);
            }
        }
        //reset ItemID to make sure order still exists
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
            throw new CostManagerException("Couldn't load item list.");
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
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
            throw new CostManagerException("Couldn't load categories list.");
        }
        return newList;
    }

    //|||||||||||||||||||||||||||||||| This function is used to handle the date ranges from the report/piechart ||||||||||||||||||||||||||||||||

    @Override
    public List<CostItem> handleReport(String sDateFrom, String sDateTo) throws CostManagerException {
        List<CostItem> filteredItems = getAllItems();
        /*
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date dateFrom = format.parse(sDateFrom);
            Date dateTo = format.parse(sDateTo);
            //loadItems();
            for (int i = 0; i < getAllItems().size(); i++) {
                Date currentItemDate = format.parse(getAllItems().get(i).getPurchaseDate());
                if (dateFrom.before(currentItemDate) && dateTo.after(currentItemDate) || dateFrom.equals(currentItemDate) || dateTo.equals(currentItemDate)) {
                    filteredItems.add(getAllItems().get(i));
                }
            }


         */
        return filteredItems;
    }
}
