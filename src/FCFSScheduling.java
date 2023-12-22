import java.util.ArrayList;
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

class FCFSScheduling {
    public static void run(ArrayList<Process> processes) {
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        for (Process process : processes) {
            if (process.arrivalTime > currentTime) {
                currentTime = process.arrivalTime;
            }

            process.waitingTime = currentTime - process.arrivalTime;
            totalWaitingTime += process.waitingTime;

            System.out.println("Executing " + process.name + " at time " + currentTime);
            currentTime += process.burstTime;

            process.turnaroundTime = process.waitingTime + process.burstTime;
            totalTurnaroundTime += process.turnaroundTime;

            System.out.println("Process " + process.name + " - Waiting Time: " + process.waitingTime +
                    ", Turnaround Time: " + process.turnaroundTime);
        }

        double averageWaitingTime = (double) totalWaitingTime / processes.size();
        double averageTurnaroundTime = (double) totalTurnaroundTime / processes.size();

        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
    }

    public static void runWithUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        ArrayList<Process> processes = new ArrayList<>();
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
