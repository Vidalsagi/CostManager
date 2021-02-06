package il.ac.hit.costmanager.model;

import org.jfree.data.general.DefaultPieDataset;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class DerbyDBModel implements IModel{


    public static String driver = "org.apache.derby.jdbc.ClientDriver";
    public static String protocol = "jdbc:derby://localhost:1527/CostManager;create=true";
    //private Connection connection;
    //private Statement statement;
    //private ResultSet rs;

    /**
     * This function will add item to the list
     * @param item
     * @throws CostManagerException
     */
    @Override
    public void addCostItem(CostItem item) throws CostManagerException {
        try
        {
            //Check if item exists already by name
            List<CostItem> newList = getAllItems();
            for(int i=0;i< newList.size();i++){
                if(newList.get(i).getItemName().equals(item.getItemName()) && newList.get(i).getPurchaseDate().equals(item.getPurchaseDate()))
                    throw new CostManagerException("Item Name already exsists in the same date in system.");
            }
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            statement.execute("insert into ItemDB(ITEMIDCOL,CATEIDCOL," +
                    "ITEMNAMECOL,CURRENCYCOL,PRICECOL,PurchaseDateCol) VALUES (" + generateItemID() + ","
                    + generateCateID() + "," + "'" + item.getItemName() + "','" +
                    item.getEcurrency() + "'," + item.getPrice() + ",'" +
                    item.getPurchaseDate() + "')");
            //ResultSet rs = statement.executeQuery(
            //        "SELECT ItemIDCol,ITEMNAMECOL FROM ItemDB ORDER BY ItemIDCol");
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
            throw new CostManagerException("Couldn't add a new item.");
        } finally{
            //closeConnectionAndStatement(connection, statement);
        }
    }

    /**
     * This function will remove item from the list
     * @param item
     * @throws CostManagerException
     */
    @Override
    public void deleteCostItem(CostItem item) throws CostManagerException {
        try {
            //Make sure the name exsists in database
            List<CostItem> newList = getAllItems();
            for(int i = 0; i < newList.size(); i++) {
                if(newList.get(i).getItemName().equals(item.getItemName())){
                    if(item.getPurchaseDate().equals(newList.get(i).getPurchaseDate())) break;
                }
                if(i == newList.size() - 1)
                {
                    if(item.getItemName().equals(newList.get(i).getItemName())){
                        if(item.getPurchaseDate().equals(newList.get(i).getPurchaseDate())) break;
                    }
                    else{
                        throw new CostManagerException("Please check name or date.");
                    }
                }
            }
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM ITEMDB WHERE ItemNameCol = '" + item.getItemName()+"' AND PurchaseDateCol = '" +
                    item.getPurchaseDate() + "'");
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
            throw new CostManagerException("Couldn't delete item.");
        } finally{
            //closeConnectionAndStatement(connection, statement);
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

    /**
     * This function will edit a cost item
     * @param item
     * @param newCateID
     * @throws CostManagerException
     */
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
        } finally{
            //closeConnectionAndStatement(connection, statement);
        }
    }

    /**
     * This function will add category to the list
     * @param category
     * @throws CostManagerException
     */
    @Override
    public void addCategory(Category category) throws CostManagerException {
        try
        {
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            statement.execute("insert into CateDB(CateIDCol,CateNameCol) VALUES (" + generateCateID()
                    + ",'" + category.getCategoryName()  + "')");

        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
            throw new CostManagerException("Couldn't add a new category.");
        }
    }

    /**
     * This function will delete category from the list
     * @param category
     * @throws CostManagerException
     */
    @Override
    public void deleteCategory(Category category) throws CostManagerException {
        List<Category> newList = getAllCategories();
        List<CostItem> newItemList = getAllItems();
        int cateID = 1;
        //add the cateID from the database
        for(int i = 0; i < newList.size();i++){
            if(category.getCategoryName().equals(newList.get(i).getCategoryName())) cateID = newList.get(i).getCategoryID();
        }
        //Validation check
        try {
            //Make sure the name exsists in database
            for(int i = 0; i < getAllCategories().size(); i++) {
                if(category.getCategoryName().equals(newList.get(i).getCategoryName())){
                    cateID = newList.get(i).getCategoryID();
                    break;
                }
                if(i == newList.size() - 1)
                {
                    if(category.getCategoryName().equals(newList.get(i).getCategoryName())) {
                        cateID = newList.get(i).getCategoryID();
                        break;
                    }
                    else{
                        throw new CostManagerException("Category doesn't exsists in system.");
                    }
                }
            }
            //Delete all items with the same cateID from DB
            for(int i = 0; i < newItemList.size(); i++) {
                if(category.getCategoryID() == newItemList.get(i).getCateID()) deleteCostItem(newItemList.get(i));
            }
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
        } finally{
            //closeConnectionAndStatement(connection, statement);
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

    /**
     * This function will edit a category
     * @param category
     * @throws CostManagerException
     */
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
        } finally{
            //closeConnectionAndStatement(connection, statement);
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

    /**
     * This function is used to create Dataset for the pieChart
     * @return DefaultPieDataset
     * @throws CostManagerException
     */
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

    /**
     * This function is used to load the items from database and return the list
     * @return List<CostItem></CostItem>
     * @throws CostManagerException
     */
    @Override
    public List<CostItem> getAllItems() throws CostManagerException {
        //Define a new list called newList of CostItem
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
        } finally{
            //closeConnectionAndStatement(connection, statement);
        }
        return newList;
    }

    /**
     * This function is used to load the categories from database and return the list
     * @return List<Category></Category>
     * @throws CostManagerException
     */
    @Override
    public List<Category> getAllCategories() throws CostManagerException {
        //Define a new list called newList of cagegory
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
        } finally{
            //closeConnectionAndStatement(connection, statement);
        }
        return newList;
    }

    /**
     * This function is used to handle the date ranges from the report/piechart
     * @param sDateFrom
     * @param sDateTo
     * @return List<CostItem></CostItem>
     * @throws CostManagerException
     */
    @Override
    public List<CostItem> handleReport(String sDateFrom, String sDateTo) throws CostManagerException {
        List<CostItem> filteredItems = getAllItems();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date dateFrom = null;
        try {
            dateFrom = format.parse(sDateFrom);
            Date dateTo = format.parse(sDateTo);
            for (int i = 0; i < getAllItems().size(); i++) {
                Date currentItemDate = format.parse(getAllItems().get(i).getPurchaseDate());
                if (dateFrom.before(currentItemDate) && dateTo.after(currentItemDate) || dateFrom.equals(currentItemDate) || dateTo.equals(currentItemDate)) {
                    filteredItems.add(getAllItems().get(i));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new CostManagerException("Couldn't create the report."); //NEED TO ASK LIFEMICHAEL
        }
        return filteredItems;
    }


    public int generateItemID() throws CostManagerException{
        //Define a new list called newList of CostItem
        List<CostItem> newList = getAllItems();
        int itemID = 0;
        for (int i = 1; i < newList.size() + 1; i++){
            if(newList.get(i-1).getItemID() != i) {
                itemID = i;
                break;
            }
            if(i == newList.size())itemID = i + 1;
        }
        return itemID;
    }

    public int generateCateID() throws CostManagerException{
        int cateID = 1;
        List<Category> newList = getAllCategories();
            for (int i = 1; i < newList.size() + 1; i++) {
                if (newList.get(i - 1).getCategoryID() != i) {
                    cateID = i;
                    break;
                }
                if (i == newList.size()) cateID = i + 1;
            }
        return cateID;
    }

    /**
     * Makes sure connection and statement are close
     * @param connection
     * @param statement
     * @throws CostManagerException
     */
    public void closeConnectionAndStatement(Connection connection, Statement statement) throws CostManagerException{
        if(statement != null) try{
            statement.close();
        } catch (Exception e){
            e.printStackTrace();
            throw new CostManagerException("Error connection.");
        }
        if(connection != null) try{
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
            throw new CostManagerException("Error statement.");
        }
    }

}
