package org.example.Model;
import java.util.concurrent.atomic.AtomicInteger;
public class Task {
    private int ID;
    private int arrivalTime;
    private int serviceTime;
    private int iWait;
    public Task(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.iWait=0;
    }

    public int getID() {
        return ID;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public int getServiceTime() {
        return serviceTime;
    }
    public void setServiceTime(int sTime) {
        this.serviceTime = sTime;
    }

    public void incrementWaitingTime() {
        iWait++;
    }

    public int getiWait() {
        return iWait;
    }

    public void decrementServiceTime() {
        this.serviceTime--;
    }
}
