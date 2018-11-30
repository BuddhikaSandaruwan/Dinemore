package lk.ijse.dinamore.service.custom;

import lk.ijse.dinamore.dto.CustomerDTO;
import lk.ijse.dinamore.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {
    public boolean addCustomer(CustomerDTO customerDTO)throws Exception;

    public boolean updateCustomer(CustomerDTO customerDTO)throws Exception;

    public boolean deleteCustomer(String id)throws Exception;

    public List<CustomerDTO> getAllCustomer()throws Exception;
}
