package lk.ijse.dinamore.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ReseptionDTO implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int reseptionID;
    private String ReseptionNIC;
    private String name;
    private String adress;
    private String tp;
    private String userName;
    private String password;
    private String decriptKey;
    @OneToMany(mappedBy = "deliverNIC",cascade = CascadeType.ALL)
    private List<OrderDTO>orderDTOS=new ArrayList<>();

    public ReseptionDTO() {
    }

    public ReseptionDTO(String reseptionNIC, String name, String adress, String tp, String userName, String password, String decriptKey) {
        ReseptionNIC = reseptionNIC;
        this.name = name;
        this.adress = adress;
        this.tp = tp;
        this.userName = userName;
        this.password = password;
        this.decriptKey = decriptKey;
        this.orderDTOS = orderDTOS;
    }

    public int getReseptionID() {
        return reseptionID;
    }

    public void setReseptionID(int reseptionID) {
        this.reseptionID = reseptionID;
    }

    public String getReseptionNIC() {
        return ReseptionNIC;
    }

    public void setReseptionNIC(String reseptionNIC) {
        ReseptionNIC = reseptionNIC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDecriptKey() {
        return decriptKey;
    }

    public void setDecriptKey(String decriptKey) {
        this.decriptKey = decriptKey;
    }

    public List<OrderDTO> getOrderDTOS() {
        return orderDTOS;
    }

    public void setOrderDTOS(List<OrderDTO> orderDTOS) {
        this.orderDTOS = orderDTOS;
    }

    @Override
    public String toString() {
        return "ReseptionDTO{" +
                "reseptionID=" + reseptionID +
                ", ReseptionNIC='" + ReseptionNIC + '\'' +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", tp='" + tp + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", decriptKey='" + decriptKey + '\'' +
                ", orderDTOS=" + orderDTOS +
                '}';
    }
}
