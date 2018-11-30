package lk.ijse.dinamore.service.custom.impl;

import lk.ijse.dinamore.busness.BOFactory;
import lk.ijse.dinamore.busness.custom.CheffBO;
import lk.ijse.dinamore.busness.custom.OrderDetailsBO;
import lk.ijse.dinamore.dto.OrderDetailsDTO;
import lk.ijse.dinamore.observer.Observer;
import lk.ijse.dinamore.observer.Subject;
import lk.ijse.dinamore.reservation.impl.ReservationImpl;
import lk.ijse.dinamore.service.custom.CheffService;
import lk.ijse.dinamore.service.custom.OrderDetailsService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsServiceImpl extends UnicastRemoteObject implements OrderDetailsService, Subject {

    private OrderDetailsBO orderDetailsBO;
    private static ArrayList<Observer> allObservers=new ArrayList<>();
    private static ReservationImpl<OrderDetailsService> orderDetailsResBook=new ReservationImpl<>();

    public OrderDetailsServiceImpl() throws RemoteException {
        orderDetailsBO=(OrderDetailsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER_DETAILS);
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
    public boolean addOrderDetails(OrderDetailsDTO orderDetailsDTO) throws Exception {
        try {
            boolean result=orderDetailsBO.addOrderDetails(orderDetailsDTO);
            notifyObservers();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public boolean updateOrderDetails(OrderDetailsDTO orderDetailsDTO) throws Exception {
        boolean result=false;
        if (orderDetailsResBook.reserve(orderDetailsDTO.getOrderDetailsID(),this,true)){
            result=orderDetailsBO.updateOrderDetails(orderDetailsDTO);
            notifyObservers();
            if (orderDetailsResBook.isInternalReservation(orderDetailsDTO.getOrderDetailsID())){
                relese(orderDetailsDTO.getOrderDetailsID());
            }
        }
        return result;
    }

    @Override
    public boolean deleteOrderDetails(String id) throws Exception {
        boolean result=false;
        if (orderDetailsResBook.reserve(id,this,true)){
            result=orderDetailsBO.deleteOrderDetails(id);
            notifyObservers();
            if (orderDetailsResBook.isInternalReservation(id)){
                relese(id);
            }
        }
        return result;
    }

    @Override
    public List<OrderDetailsDTO> getAllOrderDetails() throws Exception {
        return orderDetailsBO.getAaaOrderDetails();
    }

    @Override
    public boolean reseve(Object id) throws Exception {
        return orderDetailsResBook.reserve(id,this,false);
    }

    @Override
    public boolean relese(Object id) throws Exception {
        return orderDetailsResBook.release(id);
    }
}
