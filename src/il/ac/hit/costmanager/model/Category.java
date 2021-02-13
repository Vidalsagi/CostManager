/*
Authors:

First Name: Sagi
Last Name: Vidal
ID: 205657042

First Name: Shlomi
Last Name: Amir
ID: 311403844
 */
package il.ac.hit.costmanager.model;
/**
 * This is a model class to hold a Category information
 *
 */
public class Category {

    public String categoryName;          //The name of category
    public int categoryID;               //The category ID

    /**
     * constructor
     * @param cateID
     * @param cateName
     */
    public Category(int cateID, String cateName) throws CostManagerException {
        setCategoryID(cateID);
        setCategoryName(cateName);
    }

    /**
     * Sets the CateID
     * @param categoryID
     */
    public void setCategoryID(int categoryID) throws CostManagerException {
        if(categoryID < 0) throw(new CostManagerException("Category ID cannot be negative!"));
        this.categoryID = categoryID;
    }

    /**
     *  Returns the CateID
     * @return
     */
    public int getCategoryID() throws CostManagerException {
        if(categoryID < 0) throw(new CostManagerException("Category ID cannot be negative!"));
        return categoryID;
    }

    /**
     *  Sets the CateName
     * @param categoryName
     */
    public void setCategoryName(String categoryName) throws CostManagerException {
        if(categoryName.isEmpty()) throw(new CostManagerException("Item name cannot be empty!"));
        this.categoryName = categoryName;
    }

    /**
     * Returns the CateName
     * @return
     */
    public String getCategoryName() throws CostManagerException {
        if(categoryName.isEmpty()) throw(new CostManagerException("Item name cannot be empty!"));
        return categoryName;
    }

    /**
     * Return the category name to string, this method is used only for combobox at the items panel
     * @return String
     */
    @Override
    public String toString() {
        return categoryName;
    }
}
