package lk.ijse.dinamore.reservation.impl;



import lk.ijse.dinamore.service.SuperService;

import java.util.HashMap;
import java.util.Map;

public class ReservationImpl<T extends SuperService> {

    private static class ResPage<T>{

        private T service;
        private boolean interally;

        public ResPage(T service, boolean interally) {
            this.service = service;
            this.interally = interally;
        }

        public T getService() {
            return service;
        }

        public void setService(T service) {
            this.service = service;
        }

        public boolean isInterally() {
            return interally;
        }

        public void setInterally(boolean interally) {
            this.interally = interally;
        }

    }

    private Map<Object,ResPage<T>> resBook=new HashMap<>();

    public boolean reserve(Object key,T service,boolean interally){
        if (resBook.containsKey(key)){
            if (resBook.get(key).service==service){
                return true;
            }else {
                return false;
            }
        }else {
            resBook.put(key,new ResPage<>(service,interally));
            return true;
        }
    }

    public boolean isInternalReservation(Object key){
        if (resBook.containsKey(key)){
            return resBook.get(key).interally;
        }else {
            return false;
        }
    }

    public boolean release(Object key){
        if (resBook.containsKey(key)){
            resBook.remove(key);
            return true;
        }else {
            return false;
        }
    }
}
