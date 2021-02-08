package il.ac.hit.costmanager.model;

import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class DerbyDBModel implements IModel{

    public static String driver = "org.apache.derby.jdbc.ClientDriver";
    public static String protocol = "jdbc:derby://localhost:1527/CostManager;create=true";


    /**
     * This function will add item to the list
     * @param item
     * @throws CostManagerException
     */
    @Override
    public void addCostItem(CostItem item, String cateName) throws CostManagerException {
        try
        {
            //Check if item exists already by name
            List<CostItem> newList = getAllItems();
            List<Category> newCateList = getAllCategories();
            for(int i=0;i< newList.size();i++){
                if(newList.get(i).getItemName().equals(item.getItemName()) && newList.get(i).getPurchaseDate().equals(item.getPurchaseDate()))
                    throw new CostManagerException("Item Name already exsists in the same date in system.");
            }
            //search for the categories in database to save the cateID
            int cateID = 1;
            for(int i = 0; i < newCateList.size();i++){
                if(cateName.equals(newCateList.get(i).getCategoryName())){
                    cateID = getAllCategories().get(i).getCategoryID();
                    break;
                }
            }
            Connection connection = DriverManager.getConnection(protocol);
            Statement statement = connection.createStatement();
            statement.execute("insert into ItemDB(ITEMIDCOL,CATEIDCOL," +
                    "ITEMNAMECOL,CURRENCYCOL,PRICECOL,PurchaseDateCol) VALUES (" + generateItemID() + ","
                    + cateID + "," + "'" + item.getItemName() + "','" +
                    item.getEcurrency() + "'," + item.getPrice() + ",'" +
                    item.getPurchaseDate() + "')");
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
            throw new CostManagerException("Couldn't add a new item.");
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
                if(cateID == newItemList.get(i).getCateID()) deleteCostItem(newItemList.get(i));
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
        }
    }

    /**
     * This function is used to create Dataset for the pieChart
     * @return DefaultPieDataset
     * @throws CostManagerException
     */
    @Override
    public DefaultPieDataset createDataset(Date dateTo, Date dateFrom) throws CostManagerException {
        DefaultPieDataset result = new DefaultPieDataset();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        //counting array to count all items by cateID and split to categories via numbers
        int count[] = new int[getAllCategories().size()];
        for (int i = 0; i < getAllCategories().size(); ++i) count[i] = 0;
        for (int i = 0; i < getAllItems().size(); ++i) ++count[getAllItems().get(i).getCateID()];
        for (int i = 0; i < count.length; i++) result.setValue(getAllCategories().get(i).getCategoryName(), count[i]);
        //Set a list to filter dates according to the entered data
        List<CostItem> filteredItems = new LinkedList<CostItem>();
        for(int i=0;i<getAllItems().size();i++) {
            Date currentItemDate = null;
            try {
                currentItemDate = format.parse(getAllItems().get(i).getPurchaseDate());
            } catch (ParseException e) {
                e.printStackTrace();
                throw new CostManagerException("Bad dates have been set");
            }
            if (dateFrom.before(currentItemDate) && dateTo.after(currentItemDate) || dateFrom.equals(currentItemDate) || dateTo.equals(currentItemDate)) {
                filteredItems.add(getAllItems().get(i));
            }
        }
        int countItems[] = new int[getAllCategories().size() + 2];
        List<Category> newCateList = getAllCategories();
        //add values according to cateid in the couting array so later add it to the piechart
        for(int i = 0; i < filteredItems.size();i++)
        {
            countItems[filteredItems.get(i).getCateID()]++;
        }
        //add the items according to order
        for(int i = 0; i < newCateList.size(); i++){
            result.setValue(newCateList.get(i).getCategoryName(),countItems[i + 1]);
        }
        return result;
    }

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
    public List<CostItem> handleReport(Date sDateFrom, Date sDateTo) throws CostManagerException {
        List<CostItem> filteredItems = new LinkedList<>();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        for (int i = 0; i < getAllItems().size(); i++) {
            try {
                Date currentItemDate = format.parse(getAllItems().get(i).getPurchaseDate());
                if (sDateFrom.before(currentItemDate) && sDateTo.after(currentItemDate) || sDateFrom.equals(currentItemDate) || sDateTo.equals(currentItemDate))
                    filteredItems.add(getAllItems().get(i));
                }
                catch(ParseException e){
                    e.printStackTrace();
                    throw new CostManagerException("Bad dates have been set");
                }
            }
        return filteredItems;
    }

    @Override
    public JTable updatePanelCateItem(int queryNum) throws CostManagerException {
        List<CostItem> itemList = getAllItems();
        String columnItems[]={"ItemID","CateID", "Item Name","Price", "Currency", "Purchase Date"};
        String columnCategories[]={"ID","Category Name"};
        JTable table = null;
        String data[][] = null;
        if(queryNum == 1){
            data = new String[itemList.size()][itemList.size()];
            for(int i = 0; i < itemList.size(); i++){
                data[i][0] = String.valueOf((getAllItems().get(i).getItemID()));
                data[i][1] = String.valueOf((getAllItems().get(i).getCateID()));
                data[i][2] = (getAllItems().get(i).getItemName());
                data[i][3] = String.valueOf((getAllItems().get(i).getPrice()));
                data[i][4] = String.valueOf((getAllItems().get(i).getEcurrency()));
                data[i][5] = (getAllItems().get(i).getPurchaseDate());

            }
            table = new JTable(data,columnItems);
        }
        else if(queryNum == 2){
            data = new String[getAllCategories().size()][getAllCategories().size()];
            for(int i = 0; i < getAllCategories().size(); i++){
                data[i][0] = String.valueOf((getAllCategories().get(i).getCategoryID()));
                data[i][1] = getAllCategories().get(i).getCategoryName();
            }
            table = new JTable(data, columnCategories);
        }
            return table;
    }

    @Override
    public JTable updatePanelReport(List<CostItem> listReport) throws CostManagerException {
        String columnItems[]={"ItemID","CateID", "Item Name","Price", "Currency", "Purchase Date"};
        String data[][] = null;
            data = new String[listReport.size()][6];
            for(int i = 0; i < listReport.size(); i++) {
                data[i][0] = String.valueOf((listReport.get(i).getItemID()));
                data[i][1] = String.valueOf((listReport.get(i).getCateID()));
                data[i][2] = listReport.get(i).getItemName();
                data[i][3] = String.valueOf((listReport.get(i).getPrice()));
                data[i][4] = String.valueOf((listReport.get(i).getEcurrency()));
                data[i][5] = listReport.get(i).getPurchaseDate();
            }
        JTable table = new JTable(data, columnItems);
        return table;
    }

    /**
     * Will generate itemID for an item
     * @return
     * @throws CostManagerException
     */
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

    /**
     * Will generate cateID for a category
     * @return
     * @throws CostManagerException
     */
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

}
