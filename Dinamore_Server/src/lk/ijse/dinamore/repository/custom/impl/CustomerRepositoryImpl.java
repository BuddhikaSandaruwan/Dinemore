package lk.ijse.dinamore.repository.custom.impl;

import lk.ijse.dinamore.dto.CustomerDTO;
import lk.ijse.dinamore.repository.SuperRepositoryImpl;
import lk.ijse.dinamore.repository.custom.CustomerRepository;

public class CustomerRepositoryImpl extends SuperRepositoryImpl<CustomerDTO,String> implements CustomerRepository {

    public CustomerRepositoryImpl() {
    }
}
