import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Process {
    String name;
    int arrivalTime;
    int burstTime;
    int waitingTime;
    int turnaroundTime;

    public Process(String name, int arrivalTime, int burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }
}

class SJFPreemptiveScheduling {
    public static void run(List<Process> processes) {
        List<Process> completedProcesses = new ArrayList<>(); // Added this line
        PriorityQueue<Process> priorityQueue = new PriorityQueue<>(Comparator
                .<Process, Integer>comparing(process -> process.burstTime)
                .thenComparing(process -> process.arrivalTime));
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        while (!priorityQueue.isEmpty() || !processes.isEmpty()) {
            while (!processes.isEmpty() && processes.get(0).arrivalTime <= currentTime) {
                priorityQueue.offer(processes.remove(0));
            }

            if (!priorityQueue.isEmpty()) {
                Process currentProcess = priorityQueue.poll();

                System.out.println("Executing " + currentProcess.name + " at time " + currentTime);

                currentProcess.waitingTime = currentTime - currentProcess.arrivalTime;
                totalWaitingTime += currentProcess.waitingTime;

                if (currentProcess.burstTime > 1) {
                    currentProcess.burstTime -= 1;
                    currentTime += 1;
                    priorityQueue.offer(currentProcess);
                } else {
                    currentTime += currentProcess.burstTime;
                    completedProcesses.add(currentProcess);
                }

                currentProcess.turnaroundTime = currentProcess.waitingTime + currentProcess.burstTime;
                totalTurnaroundTime += currentProcess.turnaroundTime;

                System.out.println("Process " + currentProcess.name + " - Waiting Time: " +
                        currentProcess.waitingTime + ", Turnaround Time: " + currentProcess.turnaroundTime);
            } else {
                currentTime = processes.get(0).arrivalTime;
            }
        }

        double averageWaitingTime = (double) totalWaitingTime / completedProcesses.size();
        double averageTurnaroundTime = (double) totalTurnaroundTime / completedProcesses.size();

        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
    }

    public static void runWithUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        List<Process> processes = new ArrayList<>();
        for (int i = 1; i <= numProcesses; i++) {
            System.out.println("Enter details for process P" + i + ":");
            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();

            processes.add(new Process("P" + i, arrivalTime, burstTime));
        }

        scanner.close();

        run(processes);
    }
}
