package lk.ijse.dinamore.memoryID;

public class MemoryID {
    private static int reseptionID;
    private static int deliverID;
    private static int cheffID;

    public MemoryID() {
    }

    public static int getReseptionID() {
        return reseptionID;
    }

    public static void setReseptionID(int reseptionID) {
        MemoryID.reseptionID = reseptionID;
    }

    public static int getDeliverID() {
        return deliverID;
    }

    public static void setDeliverID(int deliverID) {
        MemoryID.deliverID = deliverID;
    }

    public static int getCheffID() {
        return cheffID;
    }

    public static void setCheffID(int cheffID) {
        MemoryID.cheffID = cheffID;
    }
}
