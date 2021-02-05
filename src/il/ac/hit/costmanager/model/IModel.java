package il.ac.hit.costmanager.model;

import org.jfree.data.general.DefaultPieDataset;

import java.util.List;

/**
 * IModel interface for the application's functionality
 *
 */

public interface IModel {
    /**
     * This function will add item to the list
     * @param item
     * @throws CostManagerException
     */
    public void addCostItem(CostItem item) throws CostManagerException;

    /**
     * This function will remove item from the list
     * @param item
     * @throws CostManagerException
     */
    public void deleteCostItem(CostItem item) throws CostManagerException;

    /**
     * This function will edit a cost item
     * @param item
     * @param newCateID
     * @throws CostManagerException
     */
    public void editCostItem(CostItem item, int newCateID) throws CostManagerException;

    /**
     * This function will add category to the list
     * @param category
     * @throws CostManagerException
     */
    public void addCategory(Category category) throws CostManagerException;

    /**
     * This function will delete category from the list
     * @param category
     * @throws CostManagerException
     */
    public void deleteCategory(Category category) throws CostManagerException;

    /**
     * This function will edit a category
     * @param category
     * @throws CostManagerException
     */
    public void editCategory(Category category) throws CostManagerException;

    /**
     * This function is used to create Dataset for the pieChart
     * @return DefaultPieDataset
     * @throws CostManagerException
     */
    public DefaultPieDataset createDataset() throws CostManagerException;

    /**
     * This function is used to load the items from database and return the list
     * @return List<CostItem></CostItem>
     * @throws CostManagerException
     */
    public List<CostItem> getAllItems() throws CostManagerException;

    /**
     * This function is used to load the categories from database and return the list
     * @return List<Category></Category>
     * @throws CostManagerException
     */
    public List<Category> getAllCategories() throws CostManagerException;

    /**
     * This function is used to handle the date ranges from the report/piechart
     * @param sDateFrom
     * @param sDateTo
     * @return List<CostItem></CostItem>
     * @throws CostManagerException
     */
    public List<CostItem> handleReport(String sDateFrom, String sDateTo) throws CostManagerException;

    //public void loadItems() throws CostManagerException;
    //public void loadCategories() throws CostManagerException;
    //public CostItem[] getCostItems() throws CostManagerException;
    //public Category[] getCategories() throws CostManagerException;



}
