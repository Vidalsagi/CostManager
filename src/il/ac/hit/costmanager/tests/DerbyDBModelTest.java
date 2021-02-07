package il.ac.hit.costmanager.tests;

import il.ac.hit.costmanager.model.Currency;
import il.ac.hit.costmanager.model.DerbyDBModel;
import org.junit.After;
import org.junit.Before;
import il.ac.hit.costmanager.model.CostItem;
import il.ac.hit.costmanager.model.CostManagerException;

import static org.junit.Assert.assertEquals;

class DerbyDBModelTest {

    @Before //Will print this before each test
    public void setUp() throws Exception {
        System.out.println("Entering Data..");
    }

    @After //Will print this after each test
    public void tearDown() throws Exception {
        System.out.println("Test Completed!");
    }

    @org.junit.jupiter.api.Test
    void addCostItem() throws CostManagerException {
        Currency currency = Currency.ILS;
        String cateName = "Accessories";
        CostItem item = new CostItem(15 ,13 ,"Sponge" ,currency ,15 , "15.1.2020");
        DerbyDBModel ItemDao = new DerbyDBModel();
        ItemDao.addCostItem(item, cateName);
        System.out.println("Item was added succesfully");
    }

    @org.junit.jupiter.api.Test
    void deleteCostItem() throws CostManagerException {
        Currency currency = Currency.EURO;
        CostItem item = new CostItem(15 ,13 ,"Sponge" , currency ,15 , "15.1.2020");
        DerbyDBModel ItemDao = new DerbyDBModel();
        ItemDao.deleteCostItem(item);
        System.out.println("Item was removed succesfully");
    }

    @org.junit.jupiter.api.Test
    void addItemUnsuccessfully() throws CostManagerException {
        Currency currency = Currency.EURO;
        CostItem item = new CostItem(15 ,13 ,"ThisNameIsTooLongForAName" , currency ,15 , "15.1.2020");
        DerbyDBModel ItemDao = new DerbyDBModel();
        ItemDao.deleteCostItem(item);
        assertEquals(10, item.getItemName());
        //expected the items name will be 10, got the size of 25
    }
}