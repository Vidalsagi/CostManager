package il.ac.hit.costManager.view;

import il.ac.hit.costManager.viewmodel.IViewModel;

public interface IView {

    public void displayPieChart(PieChart pieChart);
    public void setViewModel(IViewModel vm);
    //..
}
