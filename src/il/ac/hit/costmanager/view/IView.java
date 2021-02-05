package il.ac.hit.costmanager.view;

import il.ac.hit.costmanager.model.Category;
import il.ac.hit.costmanager.model.CostItem;
import il.ac.hit.costmanager.model.CostManagerException;
import il.ac.hit.costmanager.viewmodel.IViewModel;
import org.jfree.data.general.DefaultPieDataset;

import java.util.List;

public interface IView {

    /**
     * This will connect the view with the viewmodel
     * @param vm
     */
    public void setViewModel(IViewModel vm);

    /**
     * This will show the report of items in selected datesv
     * @param itemsReport
     */
    public void showReportItems(List<CostItem> itemsReport);

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
     * This func will display the items in database
     * @param items
     */
    public void showItems(List<CostItem> items);

    /**
     * This func will display the categories in database
     * @param categories
     */
    public void showCategories(List<Category>  categories);

    /**
     * This func will display a msg when all items are loaded
     * @param message
     */
    public void showMessageItemsLoaded(String message);

}

