package lk.ijse.dinamore.dto;

public class toOrderTableDTO {
    private int itemID;
    private String itemName;
    private int qty;
    private double price;

    public toOrderTableDTO() {
    }

    public toOrderTableDTO(int itemID, String itemName, int qty, double price) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.qty = qty;
        this.price = price;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "toOrderTableDTO{" +
                "itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
