package il.ac.hit.costmanager.viewmodel;

import il.ac.hit.costmanager.model.Category;
import il.ac.hit.costmanager.model.CostItem;
import il.ac.hit.costmanager.model.CostManagerException;
import il.ac.hit.costmanager.model.IModel;
import il.ac.hit.costmanager.view.IView;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class will be the implementation of the Interface - viewmodel
 */
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
    public void addCostItem(CostItem item, String cateName) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //Add to the DB the new CostItem
                    model.addCostItem(item, cateName);
                    view.showMessage("Cost item was added successfully");
                    //Update the list of costitems on screen as well
                    JTable itemsTable = model.updatePanelCateItem(1);
                    view.showItemsTable(itemsTable);
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
                    //Update the list of costitems on screen as well
                    JTable itemsTable = model.updatePanelCateItem(1);
                    view.showItemsTable(itemsTable);
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
                    JTable cateTable =  cateTable = model.updatePanelCateItem(2);
                    view.showCategoriesTable(cateTable);
                    //Update categories combobox in items panel
                    List<Category> categories = model.getAllCategories();
                    view.setItemsCateCB(categories);
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
                    JTable cateTable =  cateTable = model.updatePanelCateItem(2);
                    view.showCategoriesTable(cateTable);
                    //Update the list of costitems on screen as well
                    JTable itemsTable = model.updatePanelCateItem(1);
                    view.showItemsTable(itemsTable);
                    //Update categories combobox in items panel
                    List<Category> categories = model.getAllCategories();
                    view.setItemsCateCB(categories);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    //Load the categories from DB to screen
    @Override
    public void loadCategoriesTable() {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                JTable cateTable = null;
                try {
                    cateTable = model.updatePanelCateItem(2);
                    view.showCategoriesTable(cateTable);
                    view.showMessage("Categories table were loaded from the DB.");
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    //Load the categories from DB to screen
    @Override
    public void loadItemTable() {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                JTable itemsTable = null;
                try {
                    itemsTable = model.updatePanelCateItem(1);
                    view.showItemsTable(itemsTable);
                    view.showMessage("Items table were loaded from the DB.");
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    //Add data to the piechart
    @Override
    public void getDataSetPie(Date dateTo, Date dateFrom) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    DefaultPieDataset dataset1 = model.createDataset(dateTo,dateFrom);
                    view.showMessage("Data was added successfully to pie chart!");
                    view.getPieChartDataSet(dataset1);
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }

    @Override
    public void setCbItems() {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                List<Category> categories = null;
                try {
                    categories = model.getAllCategories();
                    view.setItemsCateCB(categories);
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
    public void handleReport(Date dateFrom, Date dateTo) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //Define a list of items, take it from the model and add the strings DateFrom DateTo
                    List<CostItem> itemsReport = model.handleReport(dateFrom,dateTo);
                    //Display the report in the panel
                    JTable itemsTable = model.updatePanelReport(itemsReport);
                    view.showReportItems(itemsTable);
                    view.showMessage("Displaying the report.");
                } catch (CostManagerException e) {
                    view.showMessage(e.getMessage());
                }
            }
        });
    }
}