/*
Authors:

First Name: Sagi
Last Name: Vidal
ID: 205657042

First Name: Shlomi
Last Name: Amir
ID: 311403844
 */
package il.ac.hit.costmanager.view;

import il.ac.hit.costmanager.model.Category;
import il.ac.hit.costmanager.model.CostItem;
import il.ac.hit.costmanager.viewmodel.IViewModel;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.List;

public interface IView {

    /**
     * This will connect the view with the viewmodel
     * @param vm
     */
    public void setViewModel(IViewModel vm);

    /**
     * This will show the report of items in selected datesv
     * @param itemsReportTable
     */
    public void showReportItems(JTable itemsReportTable);

    /**
     * This func is related to piechart
     * @param dateset
     */
    public void getPieChartDataSet(DefaultPieDataset dateset);

    /**
     * This func will display msgs related to errors
     * @param text
     */
    public void showMessage(String text);

    /**
     * This func will display the items in table at the items tab
     * @param itemTable
     */
    public void showItemsTable(JTable itemTable);

    /**
     * This func will display the categories in database
     * @param cateTable
     */
    public void showCategoriesTable(JTable cateTable);

    /**
     * Will set the combobox of categories in the items panel
     * @param categories
     */
    public void setItemsCateCB(List<Category> categories);

}

