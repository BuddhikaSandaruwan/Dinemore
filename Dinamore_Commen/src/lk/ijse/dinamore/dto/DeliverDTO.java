package lk.ijse.dinamore.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DeliverDTO implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int deliverID;
    private String drivingLisionID;
    private String name;
    private String adress;
    private String tp;
    private String vehicalNo;
    private String userName;
    private String password;
    private String decriptKey;
    @OneToMany(mappedBy = "reseNIC",cascade = CascadeType.ALL)
    private List<OrderDTO> orderDTOS=new ArrayList<>();

    public DeliverDTO() {
    }

    public DeliverDTO(String drivingLisionID, String name, String adress, String tp, String vehicalNo, String userName, String password, String decriptKey) {
        this.drivingLisionID = drivingLisionID;
        this.name = name;
        this.adress = adress;
        this.tp = tp;
        this.vehicalNo = vehicalNo;
        this.userName = userName;
        this.password = password;
        this.decriptKey = decriptKey;
    }

    public int getDeliverID() {
        return deliverID;
    }

    public void setDeliverID(int deliverID) {
        this.deliverID = deliverID;
    }

    public String getDrivingLisionID() {
        return drivingLisionID;
    }

    public void setDrivingLisionID(String drivingLisionID) {
        this.drivingLisionID = drivingLisionID;
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

    public String getVehicalNo() {
        return vehicalNo;
    }

    public void setVehicalNo(String vehicalNo) {
        this.vehicalNo = vehicalNo;
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

    @Override
    public String toString() {
        return "DeliverDTO{" +
                "drivingLisionID='" + drivingLisionID + '\'' +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", tp='" + tp + '\'' +
                ", vehicalNo='" + vehicalNo + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", decriptKey='" + decriptKey + '\'' +
                ", orderDTOS=" + orderDTOS +
                '}';
    }
}
