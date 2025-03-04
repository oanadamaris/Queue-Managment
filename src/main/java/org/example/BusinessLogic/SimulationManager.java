package org.example.BusinessLogic;

import org.example.GUI.SimulationFrame;
import org.example.Model.Server;
import org.example.Model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable{
    public int timeLimit=15; /////must be read from UI
    public int maxServiceTime=4;
    public int minServiceTime=2;
    public int maxArrivalTime=8;
    public int minArrivalTime=2;
    public int numberofServers=2;
    public int numberofClients=4;
    public SelectionPolicy selectionPolicy= SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Task> generatedTasks;
    private int peakHour;
    private double avgWaitingTime=0.0;
    private double avgServiceTime=0.0;
    private SimulationFrame show;

    public SimulationManager(SimulationFrame show,int timeLimit, int maxServiceTime, int minServiceTime, int maxArrivalTime, int minArrivalTime, int numberofServers, int numberofClients, SelectionPolicy selectionPolicy) {
        this.timeLimit = timeLimit;
        this.maxServiceTime = maxServiceTime;
        this.minServiceTime = minServiceTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.numberofServers = numberofServers;
        this.numberofClients = numberofClients;
        this.selectionPolicy = selectionPolicy;
        scheduler=new Scheduler(numberofServers,(numberofClients/numberofServers),selectionPolicy);
        generatedTasks=new ArrayList<>();
        generateNRandomTasks();
        this.peakHour=0;
        this.show=show;
    }

    public void generateNRandomTasks(){
        Random rand=new Random();
        for(int i=1; i<=numberofClients; i++){
            int sTime=rand.nextInt(maxServiceTime-minServiceTime+1)+minServiceTime;
            int aTime=rand.nextInt(maxArrivalTime-minArrivalTime+1)+minArrivalTime;
            Task t=new Task(i,aTime,sTime);
            generatedTasks.add(t);
        }
        generatedTasks.sort(new ArrivalSort());
    }
    @Override
    public void run() {
        try(FileWriter writer = new FileWriter("Log.txt", false)){
        int currentTime=0;
        int mostClients=0;
        while(currentTime<= timeLimit){
            System.out.println("Time "+currentTime);
            writer.write("\nTime "+currentTime+"\n\n");
            show.textGUI("\nTime "+currentTime+"\n\n");
            int clientsNow=0;
            Iterator<Task> iterator = generatedTasks.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task.getArrivalTime() == currentTime) {
                    scheduler.dispatchTask(task);
                    avgServiceTime+=task.getServiceTime();
                    iterator.remove();
                }
            }
            writer.write("Waiting clients: ");
            show.textGUI("Waiting clients: ");
            for(Task it: generatedTasks){
                writer.write("("+it.getID()+" "+ it.getArrivalTime()+" "+it.getServiceTime()+")  ");
                show.textGUI("("+it.getID()+" "+ it.getArrivalTime()+" "+it.getServiceTime()+")  ");
            }
            writer.write("\n");
            show.textGUI("\n");
            for(Server it: scheduler.getServers() ){
                clientsNow=clientsNow+it.getTasks().size();
            }
            writer.write(scheduler.printServers());
            show.textGUI(scheduler.printServers());

            if(mostClients<clientsNow){
                mostClients=clientsNow;
                peakHour=currentTime;
            }

            if(currentTime== timeLimit){
                for(Server server: scheduler.getServers()){
                    avgWaitingTime+=server.getTimeWaited();
                }
                scheduler.killServers();
            }
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        avgWaitingTime=avgWaitingTime/numberofClients;
        avgServiceTime=avgServiceTime/numberofClients;
        System.out.println("AVG Service: "+avgServiceTime);
            writer.write("\nAVG Service: "+avgServiceTime+"\n");
            show.textGUI("\nAVG Service: "+avgServiceTime+"\n");
            System.out.println("AVG WAIT: "+avgWaitingTime);
            show.textGUI("AVG WAIT: "+avgWaitingTime+"\n");
            writer.write("AVG WAIT: "+avgWaitingTime+"\n");
        System.out.println("PEAK: "+peakHour);
            show.textGUI("PEAK: "+peakHour+"\n");
            writer.write("PEAK: "+peakHour+"\n");
    } catch (IOException e) {
            e.printStackTrace();
        }
    }
}










