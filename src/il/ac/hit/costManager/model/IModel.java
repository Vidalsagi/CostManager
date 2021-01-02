package il.ac.hit.costManager.model;

import java.util.List;

public interface IModel {
    public void addCostItem(CostItem item) throws CostManagerException;
    public void deleteCostItem(CostItem item) throws CostManagerException;
    public void addCategory(Category category) throws CostManagerException;
    public void deleteCategory(Category category) throws CostManagerException;
    public int generateItemID() throws CostManagerException;
    public List<CostItem> getAllItems() throws CostManagerException;
    public List<Category> getAllCategories() throws CostManagerException;

}
