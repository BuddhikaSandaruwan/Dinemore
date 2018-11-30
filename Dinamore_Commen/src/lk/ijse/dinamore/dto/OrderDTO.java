package lk.ijse.dinamore.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class OrderDTO implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int orderID;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne(cascade = CascadeType.ALL)
    private CustomerDTO cusNic;
    @ManyToOne(cascade = CascadeType.ALL)
    private ReseptionDTO reseNIC;
    private double totalPrice;
    private double discount;
    private String state;
    @ManyToOne(cascade = CascadeType.ALL)
    private CheffDTO cheffNIC;
    @ManyToOne(cascade = CascadeType.ALL)
    private DeliverDTO deliverNIC;
    @OneToMany(mappedBy = "orderID",cascade = CascadeType.ALL)
    private List<OrderDetailsDTO>orderDetailsDTOS=new ArrayList<>();


    public OrderDTO() {
    }

    public OrderDTO(int id,Date date, CustomerDTO cusNic, ReseptionDTO reseNIC, double totalPrice, double discount, String state, CheffDTO cheffNIC, DeliverDTO deliverNIC) {
        this.orderID=id;
        this.date = date;
        this.cusNic = cusNic;
        this.reseNIC = reseNIC;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.state = state;
        this.cheffNIC = cheffNIC;
        this.deliverNIC = deliverNIC;

    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CustomerDTO getCusNic() {
        return cusNic;
    }

    public void setCusNic(CustomerDTO cusNic) {
        this.cusNic = cusNic;
    }

    public ReseptionDTO getReseNIC() {
        return reseNIC;
    }

    public void setReseNIC(ReseptionDTO reseNIC) {
        this.reseNIC = reseNIC;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CheffDTO getCheffNIC() {
        return cheffNIC;
    }

    public void setCheffNIC(CheffDTO cheffNIC) {
        this.cheffNIC = cheffNIC;
    }

    public DeliverDTO getDeliverNIC() {
        return deliverNIC;
    }

    public void setDeliverNIC(DeliverDTO deliverNIC) {
        this.deliverNIC = deliverNIC;
    }

    public List<OrderDetailsDTO> getOrderDetailsDTOS() {
        return orderDetailsDTOS;
    }

    public void setOrderDetailsDTOS(List<OrderDetailsDTO> orderDetailsDTOS) {
        this.orderDetailsDTOS = orderDetailsDTOS;
    }
}
