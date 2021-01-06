package il.ac.hit.costManager.viewmodel;

import il.ac.hit.costManager.model.Category;
import il.ac.hit.costManager.model.CostItem;
import il.ac.hit.costManager.model.CostManagerException;
import il.ac.hit.costManager.view.IView;
import il.ac.hit.costManager.model.IModel;

import java.util.List;

public interface IViewModel {

    public void setView(IView view);
    public void setModel(IModel model);
    public void addCostItem(CostItem item);
    public void deleteCostItem(CostItem item);
    public void addCategory(Category category);
    public void deleteCategory(Category category);

}
