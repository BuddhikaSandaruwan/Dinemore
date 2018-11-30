package lk.ijse.dinamore.busness.custom;

import lk.ijse.dinamore.busness.SuperBO;
import lk.ijse.dinamore.dto.OrderDetailsDTO;

import java.util.List;

public interface OrderDetailsBO extends SuperBO {
    public boolean addOrderDetails(OrderDetailsDTO orderDetailsDTO)throws Exception;

    public boolean updateOrderDetails(OrderDetailsDTO orderDetailsDTO)throws Exception;

    public boolean deleteOrderDetails(String id)throws Exception;

    public List<OrderDetailsDTO> getAaaOrderDetails()throws Exception;
}
