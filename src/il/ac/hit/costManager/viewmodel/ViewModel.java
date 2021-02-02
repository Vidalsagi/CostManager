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

    //Connect the vm with the view
    @Override
    public void setView(IView view) {
        this.view = view;
    }

    //Connect the vm with the model
    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    //Add item from the view to the model
    @Override
    public void addCostItem(CostItem item) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addCostItem(item); //Add to the DB the new CostItem
                    view.showMessage("Cost item was added successfully");
                    //get the new list of items
                    List<CostItem> items = model.getAllItems();
                    //show the new updated list
                    view.showItems(items);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });

    }


    //Delete an item
    @Override
    public void deleteCostItem(CostItem item) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //delete the item according to name and id from database
                    model.deleteCostItem(item);
                    view.showMessage("Cost item was deleted successfully");
                    //CostItem[] items = model.getCostItems();
                    //send a list from the model to the view for a new print on screen
                    List<CostItem> items = model.getAllItems();
                    view.showItems(items);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });

    }

    @Override
    public void editCostItem(CostItem item, int newCateID) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.editCostItem(item, newCateID);
                    //Get the list of items from the DB to view and print it
                    List<CostItem> items = model.getAllItems();
                    view.showItems(items);
                } catch (CostManagerException e) {
                    view.showMessageCate(e.getMessage());
                }
            }
        });

    }

    //Load the items from database to screen
    @Override
    public void loadItems() { //load items from database
        pool.submit(new Runnable() {
            @Override
            public void run() {
                view.showMessage("Cost items were loaded from model.");
                try {
                    List<CostItem> items = model.getAllItems();
                    view.showItems(items);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }


    //Add a category
    @Override
    public void addCategory(Category category) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addCategory(category);
                    view.showMessageCate("Category was added successfully");
                    //Category[] categories = model.getCategories();
                    List<Category> categories = model.getAllCategories();
                    view.showCategories(categories);
                } catch (CostManagerException e) {
                    view.showMessageCate(e.getMessage());
                }
            }
        });
    }

    //Delete a category
    @Override
    public void deleteCategory(Category category) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //Delete a category from DB
                    model.deleteCategory(category);
                    view.showMessageCate("Category was deleted successfully");
                    //Get the list of categories from the DB to view and print it
                    List<Category> categories = model.getAllCategories();
                    view.showCategories(categories);
                } catch (CostManagerException e) {
                    view.showMessageCate(e.getMessage());
                }
            }
        });

    }

    //Edit a category
    @Override
    public void editCategory(Category category) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.editCategory(category);
                    //Get the list of categories from the DB to view and print it
                    List<Category> categories = model.getAllCategories();
                    view.showCategories(categories);
                } catch (CostManagerException e) {
                    view.showMessageCate(e.getMessage());
                }
            }
        });

    }

    //Load the categories from DB to screen
    @Override
    public void loadCategories() {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                List<Category> categories = null;
                try {
                    categories = model.getAllCategories();
                    view.showCategories(categories);
                    view.showMessage("Categories were loaded from model.");
                } catch (CostManagerException e) {
                    view.showMessageCate(e.getMessage());
                }
            }
        });
    }

    //Add data to the piechart
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

    //Will Give a list of items to print on screen
    @Override
    public List<CostItem> checkItemList() throws CostManagerException {
        return model.getAllItems();
    }

    //Will Give a list of Categories to print on screen
    @Override
    public List<Category> checkCateList() throws CostManagerException {
        return model.getAllCategories();
    }


    //Set dates on the report and bring back data from the DB
    @Override
    public void handleReport(String DateFrom, String DateTo) throws CostManagerException {
        List<CostItem> itemsReport = model.handleReport(DateFrom,DateTo);
        view.showReportItems(itemsReport);
    }
}


