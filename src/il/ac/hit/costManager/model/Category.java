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

    // Sets the CateID
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    // Returns the CateID
    public int getCategoryID() {
        return categoryID;
    }

    // Sets the CateName
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // Returns the CateName
    public String getCategoryName() {
        return categoryName;
    }

    //Return the category name to string
    @Override
    public String toString() {
        return categoryName;
    }
}
