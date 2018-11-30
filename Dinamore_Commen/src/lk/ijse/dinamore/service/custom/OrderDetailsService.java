package lk.ijse.dinamore.service.custom;

import lk.ijse.dinamore.dto.OrderDetailsDTO;
import lk.ijse.dinamore.service.SuperService;

import java.util.List;

public interface OrderDetailsService extends SuperService {
    public boolean addOrderDetails(OrderDetailsDTO orderDetailsDTO)throws Exception;

    public boolean updateOrderDetails(OrderDetailsDTO orderDetailsDTO)throws Exception;

    public boolean deleteOrderDetails(String id)throws Exception;

    public List<OrderDetailsDTO> getAllOrderDetails()throws Exception;
}
