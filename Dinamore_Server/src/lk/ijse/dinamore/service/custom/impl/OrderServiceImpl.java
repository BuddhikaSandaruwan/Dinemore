package lk.ijse.dinamore.service.custom.impl;

import lk.ijse.dinamore.busness.BOFactory;
import lk.ijse.dinamore.busness.custom.CheffBO;
import lk.ijse.dinamore.busness.custom.OrderBO;
import lk.ijse.dinamore.dto.OrderDTO;
import lk.ijse.dinamore.observer.Observer;
import lk.ijse.dinamore.observer.Subject;
import lk.ijse.dinamore.reservation.impl.ReservationImpl;
import lk.ijse.dinamore.service.custom.CheffService;
import lk.ijse.dinamore.service.custom.OrderService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl extends UnicastRemoteObject implements OrderService, Subject {

    private OrderBO orderBO;
    private static ArrayList<Observer> allObservers=new ArrayList<>();
    private static ReservationImpl<OrderService> orderResBook=new ReservationImpl<>();

    public OrderServiceImpl() throws RemoteException {
        orderBO=(OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
    }

    @Override
    public void registerObserver(Observer observer) throws Exception {
        allObservers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) throws Exception {
        allObservers.remove(observer);
    }

    @Override
    public void notifyObservers() throws Exception {
        new Thread(() ->{
            for (Observer observer : allObservers){
                try {
                    observer.updateObservers();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public boolean addOrder(OrderDTO orderDTO) throws Exception {
        try {
            boolean result=orderBO.addOrder(orderDTO);
            notifyObservers();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public boolean updateOrder(OrderDTO orderDTO) throws Exception {
        boolean result=false;
        if (orderResBook.reserve(orderDTO.getOrderID(),this,true)){
            result=orderBO.updateOrder(orderDTO);
            notifyObservers();
            if (orderResBook.isInternalReservation(orderDTO.getOrderID())){
                relese(orderDTO.getOrderID());
            }
        }
        return result;
    }

    @Override
    public boolean deleteOrder(String id) throws Exception {
        boolean result=false;
        if (orderResBook.reserve(id,this,true)){
            result=orderBO.deleteOrder(id);
            notifyObservers();
            if (orderResBook.isInternalReservation(id)){
                relese(id);
            }
        }
        return result;
    }

    @Override
    public OrderDTO searchOrder(int id) throws Exception {
        OrderDTO result=null;
        if (orderResBook.reserve(id,this,true)){
            result=orderBO.searchOrder(id);
            notifyObservers();
            if (orderResBook.isInternalReservation(id)){
                relese(id);
            }
        }
        return result;
    }

    @Override
    public List<OrderDTO> getAllOrder() throws Exception {
        return orderBO.getAllOrders();
    }

    @Override
    public boolean reseve(Object id) throws Exception {
        return orderResBook.reserve(id,this,false);
    }

    @Override
    public boolean relese(Object id) throws Exception {
        return orderResBook.release(id);
    }
}
