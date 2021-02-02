package il.ac.hit.costManager.model;

public class CostItem {
    private int itemID;
    public int cateID;
    private String itemName;
    private Currency ecurrency;
    private double price;
    public String purchaseDate;



    public CostItem(int ItemID, int CateID, String ItemName, Currency eCurrency,
         double Price, String PurchaseDate) {
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

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Currency getEcurrency() {
        return ecurrency;
    }

    public void setEcurrency(Currency ecurrency) {
        this.ecurrency = ecurrency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "CostItem [ID=" + itemID + ", CateID=" + cateID + ", Name=" + itemName + ", Price=" + price
                + ", Currency=" + ecurrency + ", PurchaseDate=" + purchaseDate + "]";
    }

}

