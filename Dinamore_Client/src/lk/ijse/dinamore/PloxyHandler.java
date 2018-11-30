package lk.ijse.dinamore;


import com.sun.deploy.net.proxy.ProxyHandler;
import lk.ijse.dinamore.service.ServiceFactory;
import lk.ijse.dinamore.service.SuperService;
import lk.ijse.dinamore.service.custom.*;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PloxyHandler implements ServiceFactory {

    private static PloxyHandler ploxyHandler;
    private ServiceFactory serviceFactory;
    private CustomerService customerService;
    private CheffService cheffService;
    private DeliverService deliverService;
    private FoodService foodService;
    private OrderDetailsService orderDetailsService;
    private OrderService orderService;
    private ReseptionService reseptionService;

    private PloxyHandler() {
        try {
            this.serviceFactory = (ServiceFactory) Naming.lookup("rmi://localhost:6060/Dinamore");
            this.customerService = (CustomerService) this.serviceFactory.getService(ServiceTypes.CUSTOMER);
            this.cheffService = (CheffService) this.serviceFactory.getService(ServiceTypes.CHEFF);
            this.deliverService = (DeliverService) this.serviceFactory.getService(ServiceTypes.DELIVER);
            this.foodService = (FoodService) this.serviceFactory.getService(ServiceTypes.FOOD);
            this.orderDetailsService = (OrderDetailsService) this.serviceFactory.getService(ServiceTypes.ORDER_DETAILS);
            this.orderService = (OrderService) this.serviceFactory.getService(ServiceTypes.ORDER);
            this.reseptionService = (ReseptionService) this.serviceFactory.getService(ServiceTypes.RESEPTION);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PloxyHandler getInstance() {
        if (ploxyHandler == null) {
            ploxyHandler = new PloxyHandler();
        }
        return ploxyHandler;
    }

    @Override
    public SuperService getService(ServiceTypes types) throws Exception {
        switch (types) {
            case CHEFF:
                return this.cheffService;
            case CUSTOMER:
                return this.customerService;
            case DELIVER:
                return this.deliverService;
            case FOOD:
                return this.foodService;
            case ORDER_DETAILS:
                return this.orderDetailsService;
            case ORDER:
                return this.orderService;
            case RESEPTION:
                return this.reseptionService;
            default:
                return null;
        }
    }
}
