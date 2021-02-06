package il.ac.hit.costmanager.viewmodel;

import il.ac.hit.costmanager.model.Category;
import il.ac.hit.costmanager.model.CostItem;
import il.ac.hit.costmanager.model.CostManagerException;
import il.ac.hit.costmanager.model.IModel;
import il.ac.hit.costmanager.view.IView;
import org.jfree.data.general.DefaultPieDataset;

import java.sql.SQLException;
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
                    //Add to the DB the new CostItem
                    model.addCostItem(item);
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
                    //send a list from the model to the view for a new print on screen
                    List<CostItem> items = model.getAllItems();
                    view.showItems(items);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
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
                try {
                    List<CostItem> items = model.getAllItems();
                    view.showItems(items);
                    view.showMessage("Cost items were loaded from model.");
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
                    view.showMessage("Category was added successfully");
                    //Category[] categories = model.getCategories();
                    List<Category> categories = model.getAllCategories();
                    view.showCategories(categories);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
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
                    view.showMessage("Category was deleted successfully");
                    //Get the list of categories from the DB to view and print it
                    List<Category> categories = model.getAllCategories();
                    view.showCategories(categories);
                    //Update the list of costitems on screen as well
                    List<CostItem> items = model.getAllItems();
                    view.showItems(items);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
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
                    view.showMessage(e.getMessage());
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
                    view.showMessage("Data was added successfully to pie chart!");
                    view.getPieChartDataSet(dataset1);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }


    /**
     * Set dates on the report and bring back data from the DB
     * @param DateFrom is String of the date from
     * @param DateTo is String of the date from
     */
    @Override
    public void handleReport(String DateFrom, String DateTo) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //Define a list of items, take it from the model and add the strings DateFrom DateTo
                    List<CostItem> itemsReport = model.handleReport(DateFrom,DateTo);
                    //Display the report in the panel
                    view.showReportItems(itemsReport);
                    view.showMessage("Displaying the report.");
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }
}


