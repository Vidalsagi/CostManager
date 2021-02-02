package il.ac.hit.costManager.viewmodel;

import il.ac.hit.costManager.model.Category;
import il.ac.hit.costManager.model.CostItem;
import il.ac.hit.costManager.model.CostManagerException;
import il.ac.hit.costManager.view.IView;
import il.ac.hit.costManager.model.IModel;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.text.ParseException;
import java.util.List;

public interface IViewModel {

    //Connect the vm with the view
    public void setView(IView view);

    //Connect the vm with the model
    public void setModel(IModel model);

    //Add item from the view to the model
    public void addCostItem(CostItem item);

    //Delete an item
    public void deleteCostItem(CostItem item);

    //Edit an existing item and replace the cateID
    public void editCostItem(CostItem item, int newCateID);

    //Load the items from database to screen
    public void loadItems();

    //Add a category
    public void addCategory(Category category);

    //Delete a category
    public void deleteCategory(Category category);

    //Edit an existing category
    public void editCategory(Category category);

    //Load the categories from DB to screen
    public void loadCategories();

    //Add data to the piechart
    public void getDataSetPie();

    //Will Give a list of items to print on screen
    public List<CostItem> checkItemList() throws CostManagerException;

    //Will Give a list of Categories to print on screen
    public List<Category> checkCateList() throws CostManagerException;

    //Set dates on the report and bring back data from the DB
    public void handleReport(String dateFrom , String dateTo) throws CostManagerException;

}
