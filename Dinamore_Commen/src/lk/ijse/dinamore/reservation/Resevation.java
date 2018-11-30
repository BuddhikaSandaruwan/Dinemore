package lk.ijse.dinamore.reservation;

import java.rmi.Remote;

public interface Resevation extends Remote {
    public abstract boolean reseve(Object id)throws Exception;

    public abstract boolean relese(Object id)throws Exception;

}
