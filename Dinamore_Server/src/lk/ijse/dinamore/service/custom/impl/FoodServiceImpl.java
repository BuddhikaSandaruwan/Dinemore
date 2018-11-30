package lk.ijse.dinamore.service.custom.impl;

import lk.ijse.dinamore.busness.BOFactory;
import lk.ijse.dinamore.busness.custom.FoodBO;
import lk.ijse.dinamore.dto.FoodDTO;
import lk.ijse.dinamore.observer.Observer;
import lk.ijse.dinamore.observer.Subject;
import lk.ijse.dinamore.reservation.impl.ReservationImpl;
import lk.ijse.dinamore.service.custom.FoodService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class FoodServiceImpl extends UnicastRemoteObject implements FoodService, Subject {

    private FoodBO foodBO;
    private static ArrayList<Observer>allObservers=new ArrayList<>();
    private static ReservationImpl<FoodService> foodResBook=new ReservationImpl<>();

    public FoodServiceImpl() throws RemoteException {
        foodBO=(FoodBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.FOOD);
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
    public boolean addFood(FoodDTO foodDTO) throws Exception {
        try {
            boolean result=foodBO.addFood(foodDTO);
            notifyObservers();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public boolean updateFood(FoodDTO foodDTO) throws Exception {
        boolean result=false;
        if (foodResBook.reserve(foodDTO.getFoodID(),this,true)){
            result=foodBO.updateFood(foodDTO);
            notifyObservers();
            if (foodResBook.isInternalReservation(foodDTO.getFoodID())){
                relese(foodDTO.getFoodID());
            }
        }
        return result;
    }

    @Override
    public boolean deleteFood(String id) throws Exception {
        boolean result=false;
        if (foodResBook.reserve(id,this,true)){
            result=foodBO.deleteFood(id);
            notifyObservers();
            if (foodResBook.isInternalReservation(id)){
                relese(id);
            }
        }
        return result;
    }

    @Override
    public FoodDTO searchFood(int id) throws Exception {
        FoodDTO foodDTO = null;
        if (foodResBook.reserve(id,this,true)){
            foodDTO = foodBO.searchFood(id);
            notifyObservers();
            if (foodResBook.isInternalReservation(id)){
                relese(id);
            }
        }
        return foodDTO;
    }

    @Override
    public List<FoodDTO> getAllFoods() throws Exception {
        return foodBO.getAllFoods();
    }

    @Override
    public boolean reseve(Object id) throws Exception {
        return foodResBook.reserve(id,this,false);
    }

    @Override
    public boolean relese(Object id) throws Exception {
        return foodResBook.release(id);
    }
}
