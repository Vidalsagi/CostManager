/*
Authors:

First Name: Sagi
Last Name: Vidal
ID: 205657042

First Name: Shlomi
Last Name: Amir
ID: 311403844
 */
package il.ac.hit.costmanager.viewmodel;

import il.ac.hit.costmanager.model.Category;
import il.ac.hit.costmanager.model.CostItem;
import il.ac.hit.costmanager.view.IView;
import il.ac.hit.costmanager.model.IModel;

import java.util.Date;


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
    public void addCostItem(CostItem item, String cateName);

    /**
     * Delete an item
     * @param item
     */
    public void deleteCostItem(CostItem item);

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
     * Load the items from DB to table on screen
     */
    public void loadItemTable();

    /**
     * Load the categories from DB to table on screen
     */
    public void loadCategoriesTable();

    /**
     * Add data to the piechart
     */
    public void getDataSetPie(Date dateTo, Date dateFrom);

    /**
     * This method will add the list of categories to the combobox at the items panel
     */
    public void setCbItems();

    /**
     * Set dates on the report and bring back data from the DB
     * @param dateFrom
     * @param dateTo
     */
    public void handleReport(Date dateFrom , Date dateTo);


    }
