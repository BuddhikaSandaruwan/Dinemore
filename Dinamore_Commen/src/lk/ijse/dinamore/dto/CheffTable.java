package lk.ijse.dinamore.dto;

public class CheffTable {
    private int foodID;
    private String foodName;
    private int qty;

    public CheffTable() {
    }

    public CheffTable(int foodID, String foodName, int qty) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.qty = qty;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
