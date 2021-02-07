//CostItem class with get and set methods
package il.ac.hit.costmanager.model;


/**
 * This is a model class to hold an item information
 *
 */

public class CostItem {
    private int itemID;              // The id of the item
    public int cateID;               // The id of the category
    private String itemName;         // The item name
    private Currency ecurrency;      // The currency of the item
    private double price;            // The Price
    public String purchaseDate;      // The Purchase Date

    /**
     * CostItem constructor
     * setItem(ItemID)
     * setCateID(CateID)
     * setItemName(ItemName)
     * setEcurrency(eCurrency)
     * setPrice(Price)
     * setPurchaseDate(PurchaseDate)
     */

    public CostItem(int ItemID, int CateID, String ItemName, Currency eCurrency,
        double Price, String PurchaseDate) throws CostManagerException {
        this.itemID = ItemID ;
        this.cateID = CateID;
        this.itemName = ItemName;
        this.ecurrency = eCurrency;
        this.price = Price;
        this.purchaseDate = PurchaseDate;
        setItemID(ItemID);
        setCateID(CateID);
        setItemName(ItemName);
        setEcurrency(eCurrency);
        setPrice(Price);
        setPurchaseDate(PurchaseDate);
    }

    /**
     Gets the itemID
     * @return a int
     */

    public int getItemID() {
        return itemID;
    }

    /**
     * Sets the itemID code
     * @param itemID the itemID code
     * @throws Exception in case of invalid item ID code
     */

    public void setItemID(int itemID) throws CostManagerException {
        //Make sure the itemID isn't zero
        if(itemID < 0) throw(new CostManagerException("Item ID cannot be negative!"));
        this.itemID = itemID;
    }

    /**
     Gets the cateID
     * @return an int
     */

    public int getCateID() {
        return cateID;
    }

    /**
     * Sets the cateID code
     * @param cateID the cateID code
     * @throws Exception in case of invalid category ID code
     */

    public void setCateID(int cateID) throws CostManagerException {
        //Make sure the cateID isn't zero
        if(cateID < 0) throw(new CostManagerException("Category ID cannot be negative!"));
        this.cateID = cateID;
    }

    /**
     Gets the ItemName
     * @return an int
     */

    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the itemName code
     * @param itemName the itemName code
     * @throws Exception in case of invalid item name code
     */

    public void setItemName(String itemName) throws CostManagerException {
        //Make sure itemName isn't empty
        if(itemName.isEmpty()) throw(new CostManagerException("Item name cannot be empty!"));
        this.itemName = itemName;
    }

    /**
     Gets the ecurrency
     * @return an ecurrency
     */

    public Currency getEcurrency() {
        return ecurrency;
    }

    /**
     * Sets the Currency code
     * @param ecurrency the currency code
     * @throws Exception in case of invalid item name code
     */

    public void setEcurrency(Currency ecurrency) throws CostManagerException {
        //Make sure the curency isn't an empty string
        if(ecurrency.toString().isEmpty()) throw(new CostManagerException("Item currency cannot be empty!"));
        this.ecurrency = ecurrency;
    }

    /**
     Gets the price
     * @return an int
     */

    public double getPrice() {

        return price;
    }

    /**
     * Sets the Price code
     * @param price the price code
     * @throws Exception in case of invalid price code
     */

    public void setPrice(double price) throws CostManagerException {
        //Item prices cannot be negative
        if(price < 0) throw(new CostManagerException("Item price cannot be negative!"));
        this.price = price;
    }


    /**
     Gets the purchase date
     * @return an string
     */


    public String getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the purchadeDate code
     * @param purchaseDate the purchaseDate code
     * @throws Exception in case of invalid purchase date code
     */

    public void setPurchaseDate(String purchaseDate) throws CostManagerException {
        if(purchaseDate.isEmpty()) throw(new CostManagerException("Please enter a date."));
        this.purchaseDate = purchaseDate;
    }

    /**
     * Convert to standard string format
     * @return a <code> string </code> representing
     * Cost item in standard format
     */


    @Override
    public String toString() {
        return "CostItem [ID=" + itemID + ", CateID=" + cateID + ", Name=" + itemName + ", Price=" + price
                + ", Currency=" + ecurrency + ", PurchaseDate=" + purchaseDate + "]";
    }

}

