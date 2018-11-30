package lk.ijse.dinamore.busness.custom.impl;

import lk.ijse.dinamore.busness.custom.OrderBO;
import lk.ijse.dinamore.dto.CustomerDTO;
import lk.ijse.dinamore.dto.FoodDTO;
import lk.ijse.dinamore.dto.OrderDTO;
import lk.ijse.dinamore.dto.OrderDetailsDTO;
import lk.ijse.dinamore.repository.RepositoryFactory;
import lk.ijse.dinamore.repository.custom.CustomerRepository;
import lk.ijse.dinamore.repository.custom.OrderRepository;
import lk.ijse.dinamore.util.HibanateUtil;
import org.hibernate.Session;

import java.util.List;

public class OrderBOImpl implements OrderBO {
    private OrderRepository orderRepository;
    public OrderBOImpl() {
        this.orderRepository = (OrderRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.ORDER);
    }

    @Override
    public boolean addOrder(OrderDTO orderDTO) throws Exception {
        Session session= HibanateUtil.getSessionFactory().openSession();

        orderRepository.setSession(session);

        session.beginTransaction();

        OrderDTO orderDTO1=new OrderDTO(
                orderDTO.getOrderID(),
                orderDTO.getDate(),
                orderDTO.getCusNic(),
                orderDTO.getReseNIC(),
                orderDTO.getTotalPrice(),
                orderDTO.getDiscount(),
                orderDTO.getState(),
                orderDTO.getCheffNIC(),
                orderDTO.getDeliverNIC()
        );
        System.out.println("rmi"+orderDTO1);
        boolean result=orderRepository.save(orderDTO1);

        session.getTransaction().commit();
        return result;
    }



    @Override
    public boolean updateOrder(OrderDTO orderDTO) throws Exception {
        Session session= HibanateUtil.getSessionFactory().openSession();

        orderRepository.setSession(session);

        session.beginTransaction();

        OrderDTO orderDTO1=new OrderDTO(
                orderDTO.getOrderID(),
                orderDTO.getDate(),
                orderDTO.getCusNic(),
                orderDTO.getReseNIC(),
                orderDTO.getTotalPrice(),
                orderDTO.getDiscount(),
                orderDTO.getState(),
                orderDTO.getCheffNIC(),
                orderDTO.getDeliverNIC()
        );
        System.out.println("rmi"+orderDTO1);
        boolean result=orderRepository.update(orderDTO1);

        session.getTransaction().commit();
        return result;
    }

    @Override
    public boolean deleteOrder(String id) throws Exception {
        return false;
    }

    @Override
    public OrderDTO searchOrder(int id) throws Exception {
        Session session = HibanateUtil.getSessionFactory().openSession();

        orderRepository.setSession(session);

        session.beginTransaction();

        OrderDTO byId = orderRepository.findById(id);

        session.getTransaction().commit();

        return byId;
    }

    @Override
    public List<OrderDTO> getAllOrders() throws Exception {
        Session session = HibanateUtil.getSessionFactory().openSession();

        orderRepository.setSession(session);

        session.beginTransaction();

        List<OrderDTO> foods = orderRepository.findAll();

        session.getTransaction().commit();

        if (foods != null) {
            return foods;

        } else {

            return null;
        }
    }
}
