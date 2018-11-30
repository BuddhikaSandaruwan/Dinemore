package lk.ijse.dinamore.observer;

import java.rmi.Remote;

public interface Subject extends Remote {
    public void registerObserver(Observer observer)throws Exception;

    public void unregisterObserver(Observer observer)throws Exception;

    public void notifyObservers()throws Exception;
}
