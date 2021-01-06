package il.ac.hit.costManager.view;

import il.ac.hit.costManager.model.Category;
import il.ac.hit.costManager.model.CostItem;
import il.ac.hit.costManager.viewmodel.IViewModel;

import java.util.List;

public interface IView {

    //public void displayPieChart(Map map);
    public void setViewModel(IViewModel vm);
    public void showMessage(String text);
    public void showMessageCate(String text);
    public void showItems(CostItem[] items);
    public void showCategories(Category[] categories);
    void showMessageItemsLoaded(String message);

    //..
}

