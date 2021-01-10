package il.ac.hit.costManager.viewmodel;

import il.ac.hit.costManager.model.Category;
import il.ac.hit.costManager.model.CostItem;
import il.ac.hit.costManager.model.CostManagerException;
import il.ac.hit.costManager.view.IView;
import il.ac.hit.costManager.model.IModel;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.text.ParseException;
import java.util.List;

public interface IViewModel {

    public void setView(IView view);
    public void setModel(IModel model);
    public void addCostItem(CostItem item);
    public void loadItems();
    public void loadCategories();
    public void deleteCostItem(CostItem item);
    public void addCategory(Category category);
    public void deleteCategory(Category category);
    public void getDataSetPie();
    public List<CostItem> checkItemList() throws CostManagerException;
    public List<Category> checkCateList() throws CostManagerException;
    public void handleReport(String dateFrom , String dateTo) throws ParseException, CostManagerException;

}
