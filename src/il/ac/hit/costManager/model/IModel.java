package il.ac.hit.costManager.model;

import java.sql.SQLException;
import java.util.List;

public interface IModel {

    public void addCostItem(CostItem item) throws CostManagerException;
    public void deleteCostItem(CostItem item) throws CostManagerException;
    public void addCategory(Category category) throws CostManagerException;
    public void deleteCategory(Category category) throws CostManagerException;
    public int generateItemID() throws CostManagerException;
    public void loadItems() throws CostManagerException;
    public void loadCategories() throws CostManagerException;
    public void updateCateList() throws CostManagerException;
    public void updateItemsList() throws CostManagerException;
    public CostItem[] getCostItems() throws CostManagerException;
    public Category[] getCategories() throws CostManagerException;
    public List<CostItem> getAllItems() throws CostManagerException;
    public List<Category> getAllCategories() throws CostManagerException;

}
