package il.ac.hit.costmanager.model;

import java.sql.PreparedStatement;


public class Category {

    public String categoryName;          //The name of category
    public int categoryID;               //The category ID

    /**
     * constructor
     * @param cateID
     * @param cateName
     */
    public Category(int cateID, String cateName) {
        setCategoryID(cateID);
        setCategoryName(cateName);
    }

    /**
     * Sets the CateID
     * @param categoryID
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    /**
     *  Returns the CateID
     * @return
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     *  Sets the CateName
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Returns the CateName
     * @return
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Return the category name to string
     * @return String
     */
    @Override
    public String toString() {
        return "Category [ID=" + categoryID + ", CategoryName=" + categoryName + "]";
    }
}
