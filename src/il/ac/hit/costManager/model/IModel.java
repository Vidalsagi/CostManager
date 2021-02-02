package il.ac.hit.costManager.model;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IModel {
    //This function will add item from the list
    public void addCostItem(CostItem item) throws CostManagerException;

    //This function will remove item from the list
    public void deleteCostItem(CostItem item) throws CostManagerException;

    //This function will edit a cost item
    public void editCostItem(CostItem item, int newCateID) throws CostManagerException;

    //This function will add category to the list
    public void addCategory(Category category) throws CostManagerException;

    //This function will delete category from the list
    public void deleteCategory(Category category) throws CostManagerException;

    //This function will edit a category
    public void editCategory(Category category) throws CostManagerException;

    //This function is used to create Dataset for the pieChart
    public DefaultPieDataset createDataset() throws CostManagerException;

    //This function is used to load the items from database and return the list
    public List<CostItem> getAllItems() throws CostManagerException;

    //This function is used to load the categories from database and return the list
    public List<Category> getAllCategories() throws CostManagerException;

    //This function is used to handle the date ranges from the report/piechart
    public List<CostItem> handleReport(String sDateFrom, String sDateTo) throws CostManagerException;

    //public void loadItems() throws CostManagerException;
    //public void loadCategories() throws CostManagerException;
    //public CostItem[] getCostItems() throws CostManagerException;
    //public Category[] getCategories() throws CostManagerException;



}
