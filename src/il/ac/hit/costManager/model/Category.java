package il.ac.hit.costManager.model;

import java.sql.PreparedStatement;

public class Category {

    public String categoryName;
    public int categoryID;
    private PreparedStatement insertNewCategory;

    // constructor
    public Category(int cateID, String cateName) {
        setCategoryID(cateID);
        setCategoryName(cateName);
    }

    // sets the CateID
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    // returns the CateID
    public int getCategoryID() {
        return categoryID;
    }

    // sets the CateName
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // returns the CateName
    public String getCategoryName() {
        return categoryName;
    }

    @Override//in DBConnector
    public String toString() {
        return "Category [ID=" + categoryID + ", CategoryName=" + categoryName + "]";
    }
}
