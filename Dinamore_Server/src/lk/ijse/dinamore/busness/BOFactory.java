package lk.ijse.dinamore.busness;

import lk.ijse.dinamore.busness.custom.impl.*;

public class BOFactory {
    public enum BOTypes{
        CHEFF,CUSTOMER,DELIVER,FOOD,ORDER_DETAILS,ORDER,RESEPTION
    }

    private static BOFactory boFactory;

    public BOFactory() {
    }

    public static BOFactory getInstance(){
        if (boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CHEFF:
                return new CheffBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case DELIVER:
                return new DeliverBOImpl();
            case FOOD:
                return new FoodBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsBOImpl();
            case RESEPTION:
                return  new ReseptionBOImpl();
            default:
                return null;
        }
    }
}
