package lk.ijse.dinamore.service.impl;

import lk.ijse.dinamore.service.ServiceFactory;
import lk.ijse.dinamore.service.SuperService;
import lk.ijse.dinamore.service.custom.impl.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory {

    public static ServiceFactory serviceFactory;
    private ServiceFactoryImpl() throws RemoteException {
    }

    public  static ServiceFactory getInstance()throws Exception{
        if (serviceFactory==null){
            serviceFactory=new ServiceFactoryImpl();
        }
        return serviceFactory;
    }

    @Override
    public SuperService getService(ServiceTypes types) throws Exception {
        switch (types){
            case CHEFF:
                return new CheffServiceImpl();
            case CUSTOMER:
                return new CustomerServiceImpl();
            case DELIVER:
                return new DeliverServiceImpl();
            case FOOD:
                return new FoodServiceImpl();
            case ORDER_DETAILS:
                return new OrderDetailsServiceImpl();
            case ORDER:
                return new OrderServiceImpl();
            case RESEPTION:
                return new ReseptionServiceImpl();
            default:
                return null;
        }
    }
}
