package lk.ijse.dinamore.observer;

import java.rmi.Remote;

public interface Observer extends Remote {

    public void updateObservers()throws Exception;

}
