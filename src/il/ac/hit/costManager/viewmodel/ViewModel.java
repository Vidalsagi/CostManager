package il.ac.hit.costManager.viewmodel;

import il.ac.hit.costManager.model.Category;
import il.ac.hit.costManager.model.CostItem;
import il.ac.hit.costManager.model.CostManagerException;
import il.ac.hit.costManager.model.IModel;
import il.ac.hit.costManager.view.IView;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.text.ParseException;
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

    //Connect between model and view to add a new cost item
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

    //Connect between model and view to add a new cost item to the list
    @Override
    public List<CostItem> checkItemList() throws CostManagerException {
        return model.getAllItems();
    }

    //Connect to the model and get the categories list
    @Override
    public List<Category> checkCateList() throws CostManagerException {
        return model.getAllCategories();
    }

    //Connect between model and view to load items and display them
    @Override
    public void loadItems() { //load items from database
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.loadItems();
                    view.showMessage("Cost items were loaded from model.");
                    CostItem[] items = model.getCostItems();
                    view.showItems(items);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    //Connect between model and view to load categories and display them
    @Override
    public void loadCategories() {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.loadCategories();
                    view.showMessage("Categories were loaded from model.");
                    Category[] categories = model.getCategories();
                    view.showCategories(categories);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    //Connect between model and view to delete a cost item
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

    //Connect between model and view to add a new category
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

    //Connect between model and view to delete a category
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

    //Connect between model and view to get the data for the pie chart
    @Override
    public void getDataSetPie() {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    DefaultPieDataset dataset1 = model.createDataset();
                    view.showMessageCate("Data was added successfully to pie chart!");
                    view.getPieChartDataSet(dataset1);
                } catch (CostManagerException e) {
                    view.showMessageCate(e.getMessage());
                }
            }
        });
    }

    //Connect between model and view to display the report
    @Override
    public void handleReport(String DateFrom, String DateTo) throws ParseException, CostManagerException {
        List<CostItem> itemsReport = model.handleReport(DateFrom,DateTo);
        view.showReportItems(itemsReport);
    }
}


