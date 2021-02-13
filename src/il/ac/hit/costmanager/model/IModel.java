/*
Authors:

First Name: Sagi
Last Name: Vidal
ID: 205657042

First Name: Shlomi
Last Name: Amir
ID: 311403844
 */
package il.ac.hit.costmanager.model;

import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.Date;
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
    public void addCostItem(CostItem item, String cateName) throws CostManagerException;

    /**
     * This function will remove item from the list
     * @param item
     * @throws CostManagerException
     */
    public void deleteCostItem(CostItem item) throws CostManagerException;

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
     * This function is used to create Dataset for the pieChart
     * @return DefaultPieDataset
     * @throws CostManagerException
     */
    public DefaultPieDataset createDataset(Date dateTo, Date dateFrom) throws CostManagerException;

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
    public List<CostItem> handleReport(Date sDateFrom, Date sDateTo) throws CostManagerException;

    /**
     * This method will give a table variable for the items or categories list
     * @param queryNum
     * @return
     * @throws CostManagerException
     */
    public JTable updatePanelCateItem(int queryNum) throws CostManagerException;

    /**
     * This method will give a table variable for the report list
     * @param listReport
     * @return
     * @throws CostManagerException
     */
    public JTable updatePanelReport(List<CostItem> listReport) throws CostManagerException;
}
