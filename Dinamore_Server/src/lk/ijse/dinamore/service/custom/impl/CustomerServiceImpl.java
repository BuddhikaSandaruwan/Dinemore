package lk.ijse.dinamore.service.custom.impl;

import lk.ijse.dinamore.busness.BOFactory;
import lk.ijse.dinamore.busness.custom.CheffBO;
import lk.ijse.dinamore.busness.custom.CustomerBO;
import lk.ijse.dinamore.dto.CustomerDTO;
import lk.ijse.dinamore.observer.Observer;
import lk.ijse.dinamore.observer.Subject;
import lk.ijse.dinamore.reservation.impl.ReservationImpl;
import lk.ijse.dinamore.service.custom.CheffService;
import lk.ijse.dinamore.service.custom.CustomerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl extends UnicastRemoteObject implements CustomerService, Subject {

    private CustomerBO customerBO;
    private static ArrayList<Observer> allObservers=new ArrayList<>();
    private static ReservationImpl<CustomerService> customerResBook=new ReservationImpl<>();

    public CustomerServiceImpl() throws RemoteException {
        customerBO=(CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
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
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception {
        try {
            boolean result=customerBO.addCustomer(customerDTO);
            notifyObservers();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return false;

        }

    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        boolean result=false;
        if (customerResBook.reserve(customerDTO.getNic(),this,true)){
            result=customerBO.updateCustomer(customerDTO);
            notifyObservers();
            if (customerResBook.isInternalReservation(customerDTO.getNic())){
                relese(customerDTO.getNic());
            }
        }
        return result;
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        boolean result=false;
        if (customerResBook.reserve(id,this,true)){
            result=customerBO.deleteCustomer(id);
            notifyObservers();
            if (customerResBook.isInternalReservation(id)){
                relese(id);
            }
        }
        return result;
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws Exception {
        return customerBO.getAllCustomers();
    }

    @Override
    public boolean reseve(Object id) throws Exception {
        return customerResBook.reserve(id,this,false);
    }

    @Override
    public boolean relese(Object id) throws Exception {
        return customerResBook.release(id);
    }
}
