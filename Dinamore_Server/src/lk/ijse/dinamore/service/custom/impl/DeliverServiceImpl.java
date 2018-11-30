package lk.ijse.dinamore.service.custom.impl;

import lk.ijse.dinamore.busness.BOFactory;
import lk.ijse.dinamore.busness.custom.CheffBO;
import lk.ijse.dinamore.busness.custom.DeliverBO;
import lk.ijse.dinamore.dto.DeliverDTO;
import lk.ijse.dinamore.observer.Observer;
import lk.ijse.dinamore.observer.Subject;
import lk.ijse.dinamore.reservation.impl.ReservationImpl;
import lk.ijse.dinamore.service.custom.CheffService;
import lk.ijse.dinamore.service.custom.DeliverService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DeliverServiceImpl extends UnicastRemoteObject implements DeliverService, Subject {

    private DeliverBO deliverBO;
    private static ArrayList<Observer> allObservers=new ArrayList<>();
    private static ReservationImpl<DeliverService> deliverResBook=new ReservationImpl<>();

    public DeliverServiceImpl() throws RemoteException {
        deliverBO=(DeliverBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DELIVER);
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
    public boolean addDeliver(DeliverDTO deliverDTO) throws Exception {
        try {
            boolean result=deliverBO.addDeliver(deliverDTO);
            notifyObservers();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public boolean updateDeliver(DeliverDTO deliverDTO) throws Exception {
        boolean result=false;
        if (deliverResBook.reserve(deliverDTO.getDrivingLisionID(),this,true)){
            result=deliverBO.updateDeliver(deliverDTO);
            notifyObservers();
            if (deliverResBook.isInternalReservation(deliverDTO.getDrivingLisionID())){
                relese(deliverDTO.getDrivingLisionID());
            }
        }
        return result;
    }

    @Override
    public boolean deleteDeliver(String id) throws Exception {
        boolean result=false;
        if (deliverResBook.reserve(id,this,true)){
            result=deliverBO.deleteDeliver(id);
            notifyObservers();
            if (deliverResBook.isInternalReservation(id)){
                relese(id);
            }
        }
        return result;
    }

    @Override
    public DeliverDTO searchDeliver(int id) throws Exception {
        DeliverDTO result=null;
        if (deliverResBook.reserve(id,this,true)){
            result=deliverBO.searchDelivers(id);
            notifyObservers();
            if (deliverResBook.isInternalReservation(id)){
                relese(id);
            }
        }
        return result;
    }

    @Override
    public List<DeliverDTO> getAllDelivers() throws Exception {
        return deliverBO.getAllDelivers();
    }

    @Override
    public boolean reseve(Object id) throws Exception {
        return deliverResBook.reserve(id,this,false);
    }

    @Override
    public boolean relese(Object id) throws Exception {
        return deliverResBook.release(id);
    }
}
