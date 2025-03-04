package org.example.GUI;
import org.example.BusinessLogic.SelectionPolicy;
import org.example.BusinessLogic.SimulationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;


public class SimulationFrame extends JFrame {
    private JTextField numOfClientsField;
    private JTextField numOfQueuesField;
    private JTextField simulationIntervalField;
    private JTextField minArrivalTimeField;
    private JTextField maxArrivalTimeField;
    private JTextField minServiceTimeField;
    private JTextField maxServiceTimeField;
    private JComboBox strategyDropdown;
    private JButton startSimulationButton;
    private JTextArea showArea;



    public SimulationFrame() {
        setTitle("Queue Manager");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(9, 2, 5, 5));
        add(inputPanel, BorderLayout.CENTER);

        JLabel numOfClientsLabel = new JLabel("Number of clients:");
        inputPanel.add(numOfClientsLabel);
        numOfClientsField = new JTextField();
        inputPanel.add(numOfClientsField);

        JLabel numOfQueuesLabel = new JLabel("Number of queues:");
        inputPanel.add(numOfQueuesLabel);
        numOfQueuesField = new JTextField();
        inputPanel.add(numOfQueuesField);

        JLabel simulationIntervalLabel = new JLabel("Simulation interval:");
        inputPanel.add(simulationIntervalLabel);
        simulationIntervalField = new JTextField();
        inputPanel.add(simulationIntervalField);

        JLabel minArrivalTimeLabel = new JLabel("Minimum arrival time:");
        inputPanel.add(minArrivalTimeLabel);
        minArrivalTimeField = new JTextField();
        inputPanel.add(minArrivalTimeField);

        JLabel maxArrivalTimeLabel = new JLabel("Maximum arrival time:");
        inputPanel.add(maxArrivalTimeLabel);
        maxArrivalTimeField = new JTextField();
        inputPanel.add(maxArrivalTimeField);

        JLabel minServiceTimeLabel = new JLabel("Minimum service time:");
        inputPanel.add(minServiceTimeLabel);
        minServiceTimeField = new JTextField();
        inputPanel.add(minServiceTimeField);

        JLabel maxServiceTimeLabel = new JLabel("Maximum service time:");
        inputPanel.add(maxServiceTimeLabel);
        maxServiceTimeField = new JTextField();
        inputPanel.add(maxServiceTimeField);

        String[] strategies = {"Shortest Queue", "Shortest Time"};
        strategyDropdown = new JComboBox<>(strategies);
        inputPanel.add(strategyDropdown);

        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        startSimulationButton = new JButton("Start Simulation");
        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSimulation();
                //test1();
                //test2();
                //test3();
            }
        });
        buttonPanel.add(startSimulationButton);

        showArea = new JTextArea(10, 30);
        showArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(showArea);
        add(scrollPane, BorderLayout.NORTH);

    }
    public int parse(String text){
        int result=0;
        if(text.isEmpty()) {
            return -1;}
        else{
            result = Integer.parseInt(text);
        }
        return result;
    }
    public void textGUI(String text){
        showArea.append(text);
    }
    private void startSimulation() {
        int clients=parse(numOfClientsField.getText());
        int queues=parse(numOfQueuesField.getText());
        int time=parse(simulationIntervalField.getText());
        int minA=parse(minArrivalTimeField.getText());
        int maxA=parse((maxArrivalTimeField.getText()));
        int minS=parse(minServiceTimeField.getText());
        int maxS=parse(maxServiceTimeField.getText());
        SelectionPolicy select;
        if(strategyDropdown.getSelectedItem().equals("Shortest Queue")){
            select=SelectionPolicy.SHORTEST_QUEUE;
        }
        else{
            select=SelectionPolicy.SHORTEST_TIME;
        }
        if(minA>maxA || minS>maxS || clients<0 || queues<0 || time<0 || minS<0 || minA<0 || maxS<0 || maxA<0 ){
            JOptionPane.showMessageDialog(null, "INVALID INPUT >:(", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else {
            SimulationManager gen = new SimulationManager(this, time, maxS, minS, maxA, minA, queues, clients, select);
            Thread t = new Thread(gen);
            t.start();
        }
    }
    private void test1() {
        SimulationManager gen=new SimulationManager(this,60,4,2,30,2,2,4,SelectionPolicy.SHORTEST_TIME);
        Thread t=new Thread(gen);
        t.start();
    }
    private void test2() {
        SimulationManager gen=new SimulationManager(this,60,7,1,40,2,5,50,SelectionPolicy.SHORTEST_TIME);
        Thread t=new Thread(gen);
        t.start();
    }
    private void test3() {
        SimulationManager gen=new SimulationManager(this,200,9,3,100,10,20,1000,SelectionPolicy.SHORTEST_TIME);
        Thread t=new Thread(gen);
        t.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimulationFrame().setVisible(true);
            }
        });
    }
}
