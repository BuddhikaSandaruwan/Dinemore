package lk.ijse.dinamore.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FoodDTO implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int foodID;
    private String foodName;
    private double price;
    @OneToMany(mappedBy = "foodID",cascade = CascadeType.ALL)
    private List<OrderDetailsDTO> orderDetailsDTOS=new ArrayList<>();

    public FoodDTO() {
    }

    public FoodDTO(int foodID, String foodName, double price) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return foodName;
    }
}
