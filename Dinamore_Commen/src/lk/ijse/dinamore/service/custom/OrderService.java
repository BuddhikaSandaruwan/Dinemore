package lk.ijse.dinamore.service.custom;

import lk.ijse.dinamore.dto.OrderDTO;
import lk.ijse.dinamore.service.SuperService;

import java.util.List;

public interface OrderService extends SuperService {
    public boolean addOrder(OrderDTO orderDTO)throws Exception;

    public boolean updateOrder(OrderDTO orderDTO)throws Exception;

    public boolean deleteOrder(String id)throws Exception;

    public OrderDTO searchOrder(int id)throws Exception;

    public List<OrderDTO> getAllOrder()throws Exception;
}
