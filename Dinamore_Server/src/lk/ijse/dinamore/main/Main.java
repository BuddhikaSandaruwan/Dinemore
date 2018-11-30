package lk.ijse.dinamore.main;

import lk.ijse.dinamore.dto.CustomerDTO;
import lk.ijse.dinamore.dto.FoodDTO;
import lk.ijse.dinamore.dto.OrderDTO;
import lk.ijse.dinamore.dto.OrderDetailsDTO;
import lk.ijse.dinamore.service.impl.ServiceFactoryImpl;
import lk.ijse.dinamore.util.HibanateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
//        SessionFactory sessionFactory= HibanateUtil.getSessionFactory();
//        try (Session session=sessionFactory.openSession()){
//            session.beginTransaction();
//
//
//
//
//            session.getTransaction().commit();
//        }
//        sessionFactory.close();



        Registry registry = LocateRegistry.createRegistry(6060);
        registry.rebind("Dinamore", ServiceFactoryImpl.getInstance());
        System.out.println("Server has been started successfully");

    }
}
