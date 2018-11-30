package lk.ijse.dinamore.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderDetailsDTO implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int orderDetailsID;
    private int qty;
    @ManyToOne(cascade = CascadeType.ALL,targetEntity = OrderDTO.class)
    private OrderDTO orderID;
    @ManyToOne(cascade = CascadeType.ALL,targetEntity = FoodDTO.class)
    private FoodDTO foodID;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(int orderDetailsID, int qty, OrderDTO orderID, FoodDTO foodID) {
        this.orderDetailsID = orderDetailsID;
        this.qty = qty;
        this.orderID = orderID;
        this.foodID = foodID;
    }

    public OrderDetailsDTO(int qty, OrderDTO orderID, FoodDTO foodID) {
        this.qty = qty;
        this.orderID = orderID;
        this.foodID = foodID;
    }

    public int getOrderDetailsID() {
        return orderDetailsID;
    }

    public void setOrderDetailsID(int orderDetailsID) {
        this.orderDetailsID = orderDetailsID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public OrderDTO getOrderID() {
        return orderID;
    }

    public void setOrderID(OrderDTO orderID) {
        this.orderID = orderID;
    }

    public FoodDTO getFoodID() {
        return foodID;
    }

    public void setFoodID(FoodDTO foodID) {
        this.foodID = foodID;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "orderDetailsID=" + orderDetailsID +
                ", qty=" + qty +
                ", orderID=" + orderID +
                ", foodID=" + foodID +
                '}';
    }
}
