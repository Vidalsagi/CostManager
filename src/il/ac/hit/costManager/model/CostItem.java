package il.ac.hit.costManager.model;

public class CostItem {
    private int itemID; //ID will be generated
    public int cateID;
    private String itemName;
    private Currency ecurrency;
    private int price;
    public String purchaseDate;



    public CostItem(int ItemID, int CateID, String ItemName, Currency eCurrency,
                    int Price, String PurchaseDate) {
        this.itemID = ItemID ;
        this.cateID = CateID;
        this.itemName = ItemName;
        this.ecurrency = eCurrency;
        this.price = Price;
        this.purchaseDate = PurchaseDate;
        setItemID(ItemID);        //assigning id with a unique value
        setCateID(CateID);
        setItemName(ItemName);
        setEcurrency(eCurrency);
        setPrice(Price);
        setPurchaseDate(PurchaseDate);
        //..
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}

