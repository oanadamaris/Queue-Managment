package org.example.BusinessLogic;

import org.example.Model.Server;
import org.example.Model.Task;
import org.mortbay.thread.ThreadPool;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;
    private Thread thread;
    public Scheduler(int maxNoServers, int maxTasksPerServer,SelectionPolicy select) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.servers = Collections.synchronizedList(new ArrayList<>());
        changeStrategy(select);
            for (int i = 0; i < maxNoServers; i++) {
                Server server = new Server(i + 1);
                servers.add(server);
                Thread thread = new Thread(server);
                thread.start();
            }
    }
    synchronized public void dispatchTask(Task t){
        strategy.addTask(servers, t);
    }

    public List<Server> getServers() {
        return servers;
    }

     public String printServers(){
        StringBuilder sb=new StringBuilder();
        for(Server iterator: servers){
            sb.append("Queue "+iterator.getId()+": ");
            sb.append(iterator.printTasks());
            sb.append("\n");
        }
        String result=sb.toString();
        return result;
    }
    public void changeStrategy(SelectionPolicy policy){
        if(policy==SelectionPolicy.SHORTEST_QUEUE){
            strategy= new ConcreteStrategyQueue();
        }
        if(policy==SelectionPolicy.SHORTEST_TIME){
            strategy= new ConcreteStrategyTime();
        }
    }
    public void killServers() {
        for(Server iterator: servers){
           iterator.dead = true;
        }
    }
}
