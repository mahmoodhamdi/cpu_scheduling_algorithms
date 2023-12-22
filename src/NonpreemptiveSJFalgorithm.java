import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

class SJFNonPreemptiveScheduling {
    public static void run(List<Process> processes) {
        List<Process> completedProcesses = new ArrayList<>();
        List<Process> waitingProcesses = new ArrayList<>(processes);
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        // Sort processes by burst time (shortest first)
        Collections.sort(waitingProcesses, (p1, p2) -> Integer.compare(p1.burstTime, p2.burstTime));

        for (Process currentProcess : waitingProcesses) {
            System.out.println("Executing " + currentProcess.name + " at time " + currentTime);

            currentProcess.waitingTime = currentTime - currentProcess.arrivalTime;
            totalWaitingTime += currentProcess.waitingTime;

            currentTime += currentProcess.burstTime;

            currentProcess.turnaroundTime = currentProcess.waitingTime + currentProcess.burstTime;
            totalTurnaroundTime += currentProcess.turnaroundTime;

            completedProcesses.add(currentProcess);

            System.out.println("Process " + currentProcess.name + " - Waiting Time: " +
                    currentProcess.waitingTime + ", Turnaround Time: " + currentProcess.turnaroundTime);
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