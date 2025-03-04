package org.example.Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int Id;
    public boolean dead=false;
    private double timeWaited ;
    public Server(int Id) {
        this.Id=Id;
        this.tasks = new LinkedBlockingQueue<>();
        this.waitingPeriod = new AtomicInteger(0);
        this.timeWaited=0.0;
    }
    synchronized public void addTask(Task task) {
        tasks.offer(task);
        waitingPeriod.addAndGet(task.getServiceTime());
    }
    public int getId() {
        return Id;
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }
    public String printTasks(){
        StringBuilder sb=new StringBuilder();
        if(!tasks.isEmpty()) {
            for (Task it : tasks) {
                sb.append("("+it.getID()+", "+it.getArrivalTime()+", "+ it.getServiceTime()+") ");
            }
        }
        else{
            sb.append(" closed");
        }
        String result=sb.toString();
        return result;
    }

    public double getTimeWaited() {
        return timeWaited;
    }

    public int getSize(){
        return tasks.size();
    }
    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }
    @Override
    public void run() {
        while(!dead){
            for(Task it: tasks){
                it.incrementWaitingTime();
            }
            Task currentTask = tasks.peek();
            if(currentTask!=null)
            {
                currentTask.decrementServiceTime();
                this.waitingPeriod.getAndAdd(-1);
                if(currentTask.getServiceTime()==0)
                {
                    timeWaited+=currentTask.getiWait();
                    tasks.remove();
                }
            }
            try{
                sleep(1000);
            }catch(Exception e){
                throw new RuntimeException(e);
            }}
    }
}
