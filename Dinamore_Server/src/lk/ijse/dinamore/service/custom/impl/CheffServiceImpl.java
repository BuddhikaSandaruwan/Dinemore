package lk.ijse.dinamore.service.custom.impl;

import lk.ijse.dinamore.busness.BOFactory;
import lk.ijse.dinamore.busness.custom.CheffBO;
import lk.ijse.dinamore.dto.CheffDTO;
import lk.ijse.dinamore.observer.Observer;
import lk.ijse.dinamore.observer.Subject;
import lk.ijse.dinamore.reservation.impl.ReservationImpl;
import lk.ijse.dinamore.service.custom.CheffService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class CheffServiceImpl extends UnicastRemoteObject implements CheffService, Subject {

    private CheffBO cheffBO;
    private static ArrayList<Observer>allObservers=new ArrayList<>();
    private static ReservationImpl<CheffService> cheffResBook=new ReservationImpl<>();

    public CheffServiceImpl() throws RemoteException {
       cheffBO=(CheffBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CHEFF);
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
    public boolean addCheff(CheffDTO cheffDTO)   {

        try {
            boolean result=cheffBO.addCheff(cheffDTO);
            notifyObservers();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return false;

        }

    }

    @Override
    public boolean updatecheff(CheffDTO cheffDTO) throws Exception {
        boolean result=false;
        if (cheffResBook.reserve(cheffDTO.getCheffID(),this,true)){
            result=cheffBO.updateCheff(cheffDTO);
            notifyObservers();
            if (cheffResBook.isInternalReservation(cheffDTO.getCheffID())){
                relese(cheffDTO.getCheffID());
            }
        }
        return result;
    }

    @Override
    public boolean deleteCheff(String id) throws Exception {
        boolean result=false;
        if (cheffResBook.reserve(id,this,true)){
            result=cheffBO.deleteCheff(id);
            notifyObservers();
            if (cheffResBook.isInternalReservation(id)){
                relese(id);
            }
        }
        return result;
    }

    @Override
    public CheffDTO searchCheff(int id) throws Exception {
        CheffDTO result=null;
        if (cheffResBook.reserve(id,this,true)){
            result=cheffBO.searchCheff(id);
            notifyObservers();
            if (cheffResBook.isInternalReservation(id)){
                relese(id);
            }
        }
        return result;
    }

    @Override
    public List<CheffDTO> getAllCheffs() throws Exception {
        return cheffBO.getAllCheffs();
    }

    @Override
    public boolean reseve(Object id) throws Exception {
        return cheffResBook.reserve(id,this,false);
    }

    @Override
    public boolean relese(Object id) throws Exception {
        return cheffResBook.release(id);
    }
}
