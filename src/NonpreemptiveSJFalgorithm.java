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
    // ANSI escape codes for text colors
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String BOLD = "\u001B[1m";

    public static void run(List<Process> processes) {
        List<Process> completedProcesses = new ArrayList<>();
        List<Process> waitingProcesses = new ArrayList<>(processes);
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        // Sort processes by burst time (shortest first)
        Collections.sort(waitingProcesses, (p1, p2) -> Integer.compare(p1.burstTime, p2.burstTime));

        System.out.println(CYAN + BOLD + "Shortest Job First (SJF) - Non-Preemptive Scheduling" + RESET);

        // Print table header
        System.out.format("%-12s%-12s%-12s%-15s%-15s%-15s\n",
                "Process", "Arrival Time", "Burst Time", "Waiting Time", "Turnaround Time", "Completion Time");

        for (Process currentProcess : waitingProcesses) {
            System.out.format("%-12s%-12d%-12d", currentProcess.name, currentProcess.arrivalTime,
                    currentProcess.burstTime);

            currentProcess.waitingTime = currentTime - currentProcess.arrivalTime;
            totalWaitingTime += currentProcess.waitingTime;

            currentTime += currentProcess.burstTime;

            currentProcess.turnaroundTime = currentProcess.waitingTime + currentProcess.burstTime;
            totalTurnaroundTime += currentProcess.turnaroundTime;

            completedProcesses.add(currentProcess);

            // Print process details
            System.out.format("%-15d%-15d%-15d\n",
                    currentProcess.waitingTime, currentProcess.turnaroundTime, currentTime);
        }

        double averageWaitingTime = (double) totalWaitingTime / completedProcesses.size();
        double averageTurnaroundTime = (double) totalTurnaroundTime / completedProcesses.size();

        // Print average times
        System.out.println("\n" + BOLD + "Average Waiting Time: " + RESET + CYAN + averageWaitingTime + RESET);
        System.out.println(BOLD + "Average Turnaround Time: " + RESET + CYAN + averageTurnaroundTime + RESET);
    }

    public static void runWithUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter the number of processes: ");
        int numProcesses = scanner.nextInt();

        List<Process> processes = new ArrayList<>();
        for (int i = 1; i <= numProcesses; i++) {
            System.out.println("\nEnter details for process P" + i + ":");
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
