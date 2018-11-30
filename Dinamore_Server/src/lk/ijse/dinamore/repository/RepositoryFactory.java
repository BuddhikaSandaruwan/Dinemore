package lk.ijse.dinamore.repository;

import lk.ijse.dinamore.repository.custom.impl.*;

public class RepositoryFactory {
    public enum RepositoryTypes{
        CHEFF,CUSTOMER,DELIVER,FOOD,ORDER,ORDER_DETAILS,RESEPTION
    }
    public static RepositoryFactory repositoryFactory;

    private RepositoryFactory() {
    }

    public static RepositoryFactory getInstance(){
        if (repositoryFactory==null){
            repositoryFactory=new RepositoryFactory();
        }
        return repositoryFactory;
    }
    public SuperRepository getRepository(RepositoryTypes types){
        switch (types){
            case CHEFF:
                return new CheffRepositoryImpl();
            case CUSTOMER:
                return new CustomerRepositoryImpl();
            case DELIVER:
                return new DeliverRepositoryImpl();
            case FOOD:
                return new FoodRepositoryImpl();
            case ORDER_DETAILS:
                return new OrderDetailsRepositoryImpl();
            case ORDER:
                return new OrderRepositoryImpl();
            case RESEPTION:
                return new ReseptionRepositoryImpl();
            default:
                return null;
        }
    }
}
