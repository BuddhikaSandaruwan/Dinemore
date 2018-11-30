package lk.ijse.dinamore.busness.custom;

import lk.ijse.dinamore.busness.SuperBO;
import lk.ijse.dinamore.dto.OrderDTO;

import java.util.List;

public interface OrderBO extends SuperBO {
    public boolean addOrder(OrderDTO orderDTO)throws Exception;

    public boolean updateOrder(OrderDTO orderDTO)throws Exception;

    public boolean deleteOrder(String id)throws Exception;

    public OrderDTO searchOrder(int id)throws Exception;

    public List<OrderDTO> getAllOrders()throws Exception;
}
