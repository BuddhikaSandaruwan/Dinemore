package lk.ijse.dinamore.service.custom.impl;

import lk.ijse.dinamore.busness.BOFactory;
import lk.ijse.dinamore.busness.custom.ReseptionBO;
import lk.ijse.dinamore.dto.ReseptionDTO;
import lk.ijse.dinamore.observer.Observer;
import lk.ijse.dinamore.observer.Subject;
import lk.ijse.dinamore.reservation.impl.ReservationImpl;
import lk.ijse.dinamore.service.custom.ReseptionService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ReseptionServiceImpl extends UnicastRemoteObject implements ReseptionService, Subject {

    private ReseptionBO reseptionBO;
    private static ArrayList<Observer> allObservers=new ArrayList<>();
    private static ReservationImpl<ReseptionService> reseptionResBook = new ReservationImpl<>();

    public ReseptionServiceImpl() throws RemoteException {
        reseptionBO=(ReseptionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RESEPTION);
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
        new Thread(() -> {
            for (Observer observer : allObservers) {
                try {
                    observer.updateObservers();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public boolean addReseption(ReseptionDTO reseptionDTO) throws Exception {
        boolean result=reseptionBO.addReseption(reseptionDTO);
        notifyObservers();
        return result;
    }

    @Override
    public boolean updateReseption(ReseptionDTO reseptionDTO) throws Exception {
        boolean result = false;
        if (reseptionResBook.reserve(reseptionDTO.getReseptionID(), this, true)) {
            result = reseptionBO.updateReseption(reseptionDTO);
            notifyObservers();
            if (reseptionResBook.isInternalReservation(reseptionDTO.getReseptionID())){
                relese(reseptionDTO.getReseptionID());
            }
        }
        return result;
    }

    @Override
    public boolean deleteReseption(String id) throws Exception {
        boolean result = false;
        if (reseptionResBook.reserve(id, this, true)) {
            result = reseptionBO.deleteReseption(id);
            notifyObservers();
            if (reseptionResBook.isInternalReservation(id)){
                relese(id);
            }
        }
        return result;
    }

    @Override
    public List<ReseptionDTO> getAllReseptionS() throws Exception {
        return reseptionBO.getAllReseptions();
    }

    @Override
    public ReseptionDTO searchReseption(int id) throws Exception {
        ReseptionDTO result = null;
        if (reseptionResBook.reserve(id, this, true)) {
            result = reseptionBO.searchReseption(id);
            notifyObservers();
            if (reseptionResBook.isInternalReservation(id)){
                relese(id);
            }
        }
        return result;
    }


    @Override
    public boolean reseve(Object id) throws Exception {
        return reseptionResBook.reserve(id, this, false);
    }

    @Override
    public boolean relese(Object id) throws Exception {
        return reseptionResBook.release(id);
    }
}
