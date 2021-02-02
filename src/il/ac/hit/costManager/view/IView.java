package il.ac.hit.costManager.view;

import il.ac.hit.costManager.model.Category;
import il.ac.hit.costManager.model.CostItem;
import il.ac.hit.costManager.model.CostManagerException;
import il.ac.hit.costManager.viewmodel.IViewModel;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.text.ParseException;
import java.util.List;

public interface IView {

    //This will connect the view with the viewmodel
    public void setViewModel(IViewModel vm);

    //This will show the report of items in selected dates
    public void showReportItems(List<CostItem> itemsReport);

    //This func is related to piechart
    public void getPieChartDataSet(DefaultPieDataset dateset);

    //This func will display msgs related to categories
    public void showMessageCate(String text);

    //This func will display msgs related to items
    public void showMessage(String text);

    //This func will display the items in database
    public void showItems(List<CostItem> items) throws CostManagerException;

    //This func will display the categories in database
    public void showCategories(List<Category>  categories);

    //This func will display a msg when all items are loaded
    public void showMessageItemsLoaded(String message);

}

