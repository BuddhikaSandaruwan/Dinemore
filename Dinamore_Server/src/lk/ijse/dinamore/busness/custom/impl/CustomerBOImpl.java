package lk.ijse.dinamore.busness.custom.impl;

import lk.ijse.dinamore.busness.custom.CustomerBO;
import lk.ijse.dinamore.dto.CheffDTO;
import lk.ijse.dinamore.dto.CustomerDTO;
import lk.ijse.dinamore.password.PasswordUtil;
import lk.ijse.dinamore.repository.RepositoryFactory;
import lk.ijse.dinamore.repository.custom.CheffRepository;
import lk.ijse.dinamore.repository.custom.CustomerRepository;
import lk.ijse.dinamore.util.HibanateUtil;
import org.hibernate.Session;

import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private CustomerRepository customerRepository;

    public CustomerBOImpl() {
        this.customerRepository = (CustomerRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.CUSTOMER);
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception {
        Session session= HibanateUtil.getSessionFactory().openSession();

        customerRepository.setSession(session);

        session.beginTransaction();

        CustomerDTO customerDTO1=new CustomerDTO(
                customerDTO.getNic(),
                customerDTO.getName(),
                customerDTO.getAdress(),
                customerDTO.getTp()
                );
        boolean result=customerRepository.save(customerDTO1);

        session.getTransaction().commit();
        return result;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return false;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws Exception {
        return null;
    }
}
