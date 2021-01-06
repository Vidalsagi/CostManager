package il.ac.hit.costManager.model;

import java.util.*;

public class InMemoryModel implements IModel {

    private List<CostItem> items = new LinkedList<CostItem>();
    private List<Category> categories = new LinkedList<Category>();

    @Override
    public void addCostItem(CostItem item) throws CostManagerException {
        items.add(item);
    }

    @Override
    public void deleteCostItem(CostItem item) throws CostManagerException {
        for(int i = 0; i < items.size();i++){
                if(item.getItemName().equals(items.get(i).getItemName())){
                items.remove(i);
                break;
            }
        }
    }

    @Override
    public void addCategory(Category category) throws CostManagerException {
        categories.add(category);
    }

    @Override
    public void deleteCategory(Category category) throws CostManagerException {
        for(int i = 0; i < categories.size();i++){
            if(category.getCategoryName().equals(categories.get(i).getCategoryName())){
                categories.remove(i);
                break;
            }
        }
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
    public Category[] getCategories() throws CostManagerException {
        return categories.toArray(new Category[0]);
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
