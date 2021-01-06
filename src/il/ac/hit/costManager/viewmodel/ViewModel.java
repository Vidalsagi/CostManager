package il.ac.hit.costManager.viewmodel;

import il.ac.hit.costManager.model.Category;
import il.ac.hit.costManager.model.CostItem;
import il.ac.hit.costManager.model.CostManagerException;
import il.ac.hit.costManager.model.IModel;
import il.ac.hit.costManager.view.IView;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel implements IViewModel {

    private IModel model;
    private IView view;
    private ExecutorService pool;

    public ViewModel() {
        pool = Executors.newFixedThreadPool(10);
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    @Override
    public void addCostItem(CostItem item) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addCostItem(item);
                    view.showMessage("Cost item was added successfully");
                    CostItem[] items = model.getCostItems();
                    view.showItems(items);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });

    }

    @Override
    public void deleteCostItem(CostItem item) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.deleteCostItem(item);
                    view.showMessage("Cost item was deleted successfully");
                    CostItem[] items = model.getCostItems();
                    view.showItems(items);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });

    }

    @Override
    public void addCategory(Category category) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addCategory(category);
                    view.showMessageCate("Category was added successfully");
                    Category[] categories = model.getCategories();
                    view.showCategories(categories);
                } catch (CostManagerException e) {
                    view.showMessageCate(e.getMessage());
                }
            }
        });
    }

    @Override
    public void deleteCategory(Category category) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.deleteCategory(category);
                    view.showMessageCate("Category was deleted successfully");
                    Category[] categories = model.getCategories();
                    view.showCategories(categories);
                } catch (CostManagerException e) {
                    view.showMessageCate(e.getMessage());
                }
            }
        });

    }
}


