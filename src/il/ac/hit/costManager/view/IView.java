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

    public void setViewModel(IViewModel vm);
    public void showMessage(String text);
    public void showMessageCate(String text);
    public void showItems(CostItem[] items);
    public void showCategories(Category[] categories);
    public void showMessageItemsLoaded(String message);
    public void showReportItems(List<CostItem> itemsReport);
    public void getPieChartDataSet(DefaultPieDataset dateset);

}

