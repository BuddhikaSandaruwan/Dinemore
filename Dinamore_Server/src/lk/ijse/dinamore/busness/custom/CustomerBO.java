package lk.ijse.dinamore.busness.custom;

import lk.ijse.dinamore.busness.SuperBO;
import lk.ijse.dinamore.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {
    public boolean addCustomer(CustomerDTO customerDTO)throws Exception;

    public boolean updateCustomer(CustomerDTO customerDTO)throws Exception;

    public boolean deleteCustomer(String id)throws Exception;

    public List<CustomerDTO>getAllCustomers()throws Exception;
}
