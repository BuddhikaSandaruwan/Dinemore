package lk.ijse.dinamore.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerDTO implements Serializable {
    @Id
    private String nic;
    private String name;
    private String adress;
    private String tp;
    @OneToMany(mappedBy = "cusNic",cascade = CascadeType.ALL)
    private List<OrderDTO>orderDTOS=new ArrayList<>();

    public List<OrderDTO> getOrderDTOS() {
        return orderDTOS;
    }

    public void setOrderDTOS(List<OrderDTO> orderDTOS) {
        this.orderDTOS = orderDTOS;
    }

    public CustomerDTO() {
    }

    public CustomerDTO(String nic, String name, String adress, String tp) {
        this.nic = nic;
        this.name = name;
        this.adress = adress;
        this.tp = tp;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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
}
