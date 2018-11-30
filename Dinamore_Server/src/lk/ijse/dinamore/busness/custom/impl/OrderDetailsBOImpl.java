package lk.ijse.dinamore.busness.custom.impl;

import lk.ijse.dinamore.busness.custom.OrderDetailsBO;
import lk.ijse.dinamore.dto.OrderDTO;
import lk.ijse.dinamore.dto.OrderDetailsDTO;
import lk.ijse.dinamore.repository.RepositoryFactory;
import lk.ijse.dinamore.repository.custom.OrderDetailsRepository;
import lk.ijse.dinamore.repository.custom.OrderRepository;
import lk.ijse.dinamore.service.custom.OrderDetailsService;
import lk.ijse.dinamore.util.HibanateUtil;
import org.hibernate.Session;

import java.util.List;

public class OrderDetailsBOImpl implements OrderDetailsBO {
        private OrderDetailsRepository orderDetailsRepository;
    public OrderDetailsBOImpl() {
        this.orderDetailsRepository = (OrderDetailsRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ORDER_DETAILS);

    }

    @Override
    public boolean addOrderDetails(OrderDetailsDTO orderDetailsDTO) throws Exception {

        Session session= HibanateUtil.getSessionFactory().openSession();

            orderDetailsRepository.setSession(session);
            System.out.println("order details is running");
            session.beginTransaction();

            OrderDetailsDTO orderDetailsDTO1 = new OrderDetailsDTO(
                    0,
                    orderDetailsDTO.getQty(),
                    orderDetailsDTO.getOrderID(),
                    orderDetailsDTO.getFoodID()
            );
            boolean result = orderDetailsRepository.save(orderDetailsDTO1);
            session.getTransaction().commit();
            return result;

    }

    @Override
    public boolean updateOrderDetails(OrderDetailsDTO orderDetailsDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteOrderDetails(String id) throws Exception {
        return false;
    }

    @Override
    public List<OrderDetailsDTO> getAaaOrderDetails() throws Exception {
        Session session= HibanateUtil.getSessionFactory().openSession();

        orderDetailsRepository.setSession(session);
        System.out.println("order details is running");
        session.beginTransaction();

        List<OrderDetailsDTO> all = orderDetailsRepository.findAll();
        session.getTransaction().commit();
        return all;
    }
}
