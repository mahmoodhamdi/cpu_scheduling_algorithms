import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class FCFSScheduling {
    // ANSI escape codes for text colors
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String BOLD = "\u001B[1m";

    public static void run(ArrayList<Process> processes) {
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        System.out.println("\n" + BOLD + "Process Execution Table" + RESET);
        System.out.println("+-----------+--------------+-------------+-----------------+");

        System.out.printf("| %-9s | %-12s | %-11s | %-15s |\n", "Process", "Arrival Time", "Burst Time", "Completion Time");
        System.out.println("+-----------+--------------+-------------+-----------------+");

        for (Process process : processes) {
            if (process.arrivalTime > currentTime) {
                currentTime = process.arrivalTime;
            }

            process.waitingTime = currentTime - process.arrivalTime;
            totalWaitingTime += process.waitingTime;

            System.out.printf("| %-9s | %-12d | %-11d | %-15d |\n", process.name, process.arrivalTime, process.burstTime, currentTime + process.burstTime);
            currentTime += process.burstTime;

            process.turnaroundTime = process.waitingTime + process.burstTime;
            totalTurnaroundTime += process.turnaroundTime;
        }

        System.out.println("+-----------+--------------+-------------+-----------------+");

        double averageWaitingTime = (double) totalWaitingTime / processes.size();
        double averageTurnaroundTime = (double) totalTurnaroundTime / processes.size();

        System.out.println("\n" + BOLD + "Summary" + RESET);
        System.out.println("Average Waiting Time: " + CYAN + averageWaitingTime + RESET);
        System.out.println("Average Turnaround Time: " + CYAN + averageTurnaroundTime + RESET);
    }

    public static void runWithUserInput() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the number of processes: ");
            int numProcesses = scanner.nextInt();

            if (numProcesses <= 0) {
                System.out.println(RED + "Number of processes must be greater than 0." + RESET);
                return;
            }

            ArrayList<Process> processes = new ArrayList<>();
            for (int i = 1; i <= numProcesses; i++) {
                System.out.println("Enter details for process P" + i + ":");

                int arrivalTime = getValidInput(scanner, "Arrival Time: ");
                int burstTime = getValidInput(scanner, "Burst Time: ");

                processes.add(new Process("P" + i, arrivalTime, burstTime));
            }

            run(processes);
        } catch (InputMismatchException e) {
            System.out.println(RED + "Invalid input. Please enter a valid integer." + RESET);
        } finally {
            scanner.close();
        }
    }

    private static int getValidInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(RED + "Invalid input. Please enter a valid integer." + RESET);
                scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
            }
        }
    }
}
