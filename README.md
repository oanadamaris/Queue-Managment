# 🕹️ Queue Management Simulator  

A GUI-based tool for simulating and optimizing client queues using dynamic strategies to minimize waiting times. 

---

## 📖 Table of Contents  
- [📌 Description](#-description)  
  - [💛 Motivation](#-motivation)  
  - [🎯 Why this project?](#-why-this-project)  
  - [✅ What problem does it solve?](#-what-problem-does-it-solve)  
  - [💡 What I learned](#-what-i-learned)  
- [🚀 Usage](#-usage)  
  - [⚙️ How to Configure](#%EF%B8%8F-how-to-configure)  
  - [♟️ Strategies](#%EF%B8%8F-strategies)  
  - [🖼️ Preview](#%EF%B8%8F-preview)  
- [✨ Features](#-features)  
- [🛠️ Built With](#%EF%B8%8F-built-with)  
- [📊 Test Results](#-test-results)  

## 📌 Description  

This project simulates queue management systems using two strategies: Shortest Queue (fewest clients) and Shortest Time (lowest total service time). Clients are dynamically assigned to queues for optimal efficiency.

### 💛 Motivation  
Manual queue management often results in inefficiencies and long wait times. This tool automates the process and provides insights to optimize real-world systems like banks and supermarkets.

### 🎯 Why this project?  
I aimed to explore **multi-threading**, **synchronization**, and the **Strategy Pattern** while building a practical solution for service optimization.  

### ✅ What problem does it solve?  
It minimizes client waiting time through intelligent queue distribution and real-time monitoring.  

### 💡 What I learned  
- How to use **multi-threading** for efficient concurrent task processing  
- How to apply the **Strategy Pattern** for dynamic queue allocation logic. 
- How to work with **Java Swing** to enable real-time GUI updates.
- How to utilize **BlockingQueue** for thread-safe task management.

---

## 🚀 Usage  

### ⚙️ How to Configure  
1. **Set Input Parameters**:  
   - Number of clients (`N`), queues (`Q`), simulation time.  
   - Min/Max arrival and service times (e.g., `[2, 30]`).  
2. **Select a Strategy**:  
   - Choose **Shortest Queue** or **Shortest Time** via dropdown.  
3. **Start Simulation**:  
   - Click "Start Simulation" to run the scenario.  

**Example Configuration**:  
```plaintext
Clients (N): 50  
Queues (Q): 5  
Simulation Time: 60s  
Arrival Time Range: [2, 40]  
Service Time Range: [1, 7]  
Strategy: Shortest Queue  
```

### ♟️ Strategies  
| Strategy          | Description                                  |  
|-------------------|----------------------------------------------|  
| **Shortest Queue**| Assigns clients to the queue with the fewest tasks. |  
| **Shortest Time** | Assigns clients to the queue with the shortest total service time. |  

---

### 🖼️ Preview

<img width="400" src="https://github.com/user-attachments/assets/e5d64438-6b26-4bf7-a3ea-123032454124" />  

---

## ✨ Features  
✅ **Real-Time Monitoring**  
- Live updates of queue statuses and client distribution.

<img width="400" src="https://github.com/user-attachments/assets/bce36a6b-ec01-4226-ba34-58f438c8ca40" />


✅ **Dynamic Strategy Switching**  
- Toggle between strategies during configuration.

<img width="400" src="https://github.com/user-attachments/assets/4a9cb6c5-7742-4e76-b0c2-cf7d52dde36c" />



✅ **Input Validation**  
- Ensures valid parameters (e.g., `Min < Max` for time ranges).

<img width="400" src="https://github.com/user-attachments/assets/85db0972-de0c-4459-8914-edd3c8ccf9b7" />


✅ **Thread-Safe Design**  
- Uses `BlockingQueue` and synchronized methods for concurrency.
  

✅ **Performance Metrics**  
- Computes average waiting time and service time.  

<img width="400" src="https://github.com/user-attachments/assets/f3d81af7-e2e0-48ba-b42a-658308779c06" />


---

## 🛠️ Built With  
| Technology       | Description                          |  
|------------------|--------------------------------------|  
| **Java**         | Core programming language            |  
| **Swing**        | GUI framework                        |  
| **JUnit**        | Testing framework                    |  

---

## 📊 Test Results  

| Test Case | Clients (N) | Queues (Q) | Simulation Time | Strategy          | Avg Wait Time |  
|-----------|-------------|------------|-----------------|-------------------|---------------|  
| Test 1    | 4           | 2          | 60s             | Shortest Time     | 8s            |  
| Test 2    | 50          | 5          | 60s             | Shortest Queue    | 12s           |  
| Test 3    | 1000        | 20         | 200s            | Shortest Time     | 15s           |  

*Detailed logs: `TEST1.txt`, `TEST2.txt`, `TEST3.txt`.*  
