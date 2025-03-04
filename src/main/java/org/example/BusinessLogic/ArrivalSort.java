package org.example.BusinessLogic;

import org.example.Model.Task;
import java.util.Comparator;
public class ArrivalSort implements Comparator<Task> {
    public int compare(Task a, Task b){
        return a.getArrivalTime() - b.getArrivalTime();
    }
}