package il.ac.hit.costmanager.viewmodel;

import il.ac.hit.costmanager.model.Category;
import il.ac.hit.costmanager.model.CostItem;
import il.ac.hit.costmanager.model.CostManagerException;
import il.ac.hit.costmanager.view.IView;
import il.ac.hit.costmanager.model.IModel;

import java.util.List;

public interface IViewModel {

    /**
     * Connect the vm with the view
     * @param view
     */
    public void setView(IView view);

    /**
     * Connect the vm with the model
     * @param model
     */
    public void setModel(IModel model);

    /**
     * Add item from the view to the model
     * @param item
     */
    public void addCostItem(CostItem item);

    /**
     * Delete an item
     * @param item
     */
    public void deleteCostItem(CostItem item);

    /**
     * Edit an existing item and replace the cateID
     * @param item
     * @param newCateID
     */
    public void editCostItem(CostItem item, int newCateID);

    /**
     * Load the items from database to screen
     */
    public void loadItems();

    /**
     * Add a category
     * @param category
     */
    public void addCategory(Category category);

    /**
     * Delete a category
     * @param category
     */
    public void deleteCategory(Category category);

    /**
     * Edit an existing category
     * @param category
     */
    public void editCategory(Category category);

    /**
     * Load the categories from DB to screen
     */
    public void loadCategories();

    /**
     * Add data to the piechart
     */
    public void getDataSetPie();

    /**
     * Will Give a list of items to print on screen
     */
    public void checkItemList();

    /**
     * Will Give a list of Categories to print on screen
     */
    public void checkCateList();

    /**
     * Set dates on the report and bring back data from the DB
     * @param dateFrom
     * @param dateTo
     */
    public void handleReport(String dateFrom , String dateTo);

}
