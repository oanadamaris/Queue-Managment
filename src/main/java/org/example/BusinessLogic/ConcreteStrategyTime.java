package org.example.BusinessLogic;
import org.example.Model.Server;
import org.example.Model.Task;
import java.util.List;

public class ConcreteStrategyTime implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t) {
        int minTime=Integer.MAX_VALUE;
        for (Server iterator : servers) {
            if (iterator.getWaitingPeriod().get() < minTime) {
                minTime=iterator.getWaitingPeriod().get();
            }
        }
        for (Server iterator : servers) {
            if (iterator.getWaitingPeriod().get() == minTime) {
                iterator.addTask(t);
                break;
            }
        }
    }
}
