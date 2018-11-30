package lk.ijse.dinamore.util;

import lk.ijse.dinamore.dto.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;

public class HibanateUtil {
    public static SessionFactory sf=buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
        StandardServiceRegistry registry=new StandardServiceRegistryBuilder().loadProperties("resorses.properties").build();
        Metadata metadata=new MetadataSources(registry)
                .addAnnotatedClass(CheffDTO.class)
                .addAnnotatedClass(CustomerDTO.class)
                .addAnnotatedClass(DeliverDTO.class)
                .addAnnotatedClass(FoodDTO.class)
                .addAnnotatedClass(OrderDetailsDTO.class)
                .addAnnotatedClass(OrderDTO.class)
                .addAnnotatedClass(ReseptionDTO.class)

                .buildMetadata();
        return metadata.getSessionFactoryBuilder().build();
    }
    public static SessionFactory getSessionFactory(){
        return sf;
    }
}
