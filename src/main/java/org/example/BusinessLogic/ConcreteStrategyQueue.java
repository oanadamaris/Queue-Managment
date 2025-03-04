package org.example.BusinessLogic;

import org.example.Model.Server;
import org.example.Model.Task;

import java.util.List;
public class ConcreteStrategyQueue implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t) {
        int minQueue=Integer.MAX_VALUE;
        for (Server iterator : servers) {
            if (iterator.getSize() < minQueue) {
                minQueue=iterator.getSize();
            }
        }
        for (Server iterator : servers) {
            if (iterator.getSize() == minQueue) {
                iterator.addTask(t);
                break;
            }
        }
    }
}
