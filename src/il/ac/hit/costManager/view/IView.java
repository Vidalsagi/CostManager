package il.ac.hit.costManager.view;

import il.ac.hit.costManager.model.CostItem;
import il.ac.hit.costManager.viewmodel.IViewModel;

public interface IView {

    //public void displayPieChart(Map map);
    public void setViewModel(IViewModel vm);
    public void showMessage(String text);
    public void showItems(CostItem[] vec);
    //..
}

