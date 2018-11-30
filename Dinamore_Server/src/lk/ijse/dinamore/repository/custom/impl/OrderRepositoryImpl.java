package lk.ijse.dinamore.repository.custom.impl;

import lk.ijse.dinamore.dto.OrderDTO;
import lk.ijse.dinamore.repository.SuperRepositoryImpl;
import lk.ijse.dinamore.repository.custom.OrderRepository;

public class OrderRepositoryImpl extends SuperRepositoryImpl<OrderDTO,String> implements OrderRepository {

    public OrderRepositoryImpl() {
    }
}
