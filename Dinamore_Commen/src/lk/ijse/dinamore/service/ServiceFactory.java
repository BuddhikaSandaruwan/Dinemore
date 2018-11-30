package lk.ijse.dinamore.service;

import java.rmi.Remote;

public interface ServiceFactory extends Remote {
    public enum ServiceTypes{
        CHEFF,CUSTOMER,DELIVER,FOOD,ORDER_DETAILS,ORDER,RESEPTION
    }

    public SuperService getService(ServiceTypes types)throws Exception;

}
