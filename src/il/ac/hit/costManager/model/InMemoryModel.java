package il.ac.hit.costManager.model;

import java.util.*;

public class InMemoryModel implements IModel {

    private List<CostItem> items = new LinkedList<CostItem>();

    @Override
    public void addCostItem(CostItem item) throws CostManagerException {
        items.add(item);
    }

    @Override
    public void deleteCostItem(CostItem item) throws CostManagerException {

    }

    @Override
    public void addCategory(Category category) throws CostManagerException {

    }

    @Override
    public void deleteCategory(Category category) throws CostManagerException {

    }

    @Override
    public int generateItemID() throws CostManagerException {
        return 0;
    }

    @Override
    public CostItem[] getCostItems() throws CostManagerException {
        return items.toArray(new CostItem[0]);
    }

    @Override
    public List<CostItem> getAllItems() throws CostManagerException {
        return null;
    }

    @Override
    public List<Category> getAllCategories() throws CostManagerException {
        return null;
    }
}
