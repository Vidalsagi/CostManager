package il.ac.hit.costManager.model;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IModel {

    public void addCostItem(CostItem item) throws CostManagerException;
    public void deleteCostItem(CostItem item) throws CostManagerException;
    public void editCostItem(CostItem item, int newCateID) throws CostManagerException;
    public void addCategory(Category category) throws CostManagerException;
    public void deleteCategory(Category category) throws CostManagerException;
    public void editCategory(Category category) throws CostManagerException;
    public void loadItems() throws CostManagerException;
    public void loadCategories() throws CostManagerException;
    public DefaultPieDataset createDataset() throws CostManagerException;
    public CostItem[] getCostItems() throws CostManagerException;
    public Category[] getCategories() throws CostManagerException;
    public List<CostItem> getAllItems() throws CostManagerException;
    public List<Category> getAllCategories() throws CostManagerException;
    public List<CostItem> handleReport(String sDateFrom, String sDateTo) throws ParseException, CostManagerException;


}
