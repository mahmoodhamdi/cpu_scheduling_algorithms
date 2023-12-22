import java.util.Scanner;

public class Main {
    // ANSI escape codes for colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Print header with colors
        System.out.println(ANSI_BLUE + "Choose a scheduling algorithm:" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "----------------------------------" + ANSI_RESET);
        System.out.println(" " + ANSI_YELLOW + "Algorithm" + "\t\t" + "Description" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "----------------------------------" + ANSI_RESET);
        System.out.println(" " + ANSI_YELLOW + "1." + "\t\t\t" + "FCFS (First Come First Serve)" + ANSI_RESET);
        System.out.println(" " + ANSI_YELLOW + "2." + "\t\t\t" + "SJF (Shortest Job First - Non-Preemptive)" + ANSI_RESET);
        System.out.println(" " + ANSI_YELLOW + "3." + "\t\t\t" + "SJF (Shortest Job First - Preemptive)" + ANSI_RESET);
        System.out.println(" " + ANSI_YELLOW + "4." + "\t\t\t" + "Round Robin" + ANSI_RESET);
        System.out.println(" " + ANSI_YELLOW + "5." + "\t\t\t" + "Priority Scheduling (Preemptive)" + ANSI_RESET);
        System.out.println(" " + ANSI_YELLOW + "6." + "\t\t\t" + "Priority Scheduling (Non-Preemptive)" + ANSI_RESET);

        System.out.print("Enter the number of the selected algorithm: ");
        int selectedAlgorithm = scanner.nextInt();

        switch (selectedAlgorithm) {
            case 1:
                FCFSScheduling.runWithUserInput();
                break;
            case 4:
                RoundRobin.main(args);
                break;
            case 5:
                PriorityPreemptiveScheduling.main(args);
                break;
            case 6:
                NonPreemptivePriorityCPUSchedulingAlgorithm.main(args);
                break;
            case 2:
                SJFNonPreemptiveScheduling.runWithUserInput();
                break;
            case 3:
                SJFPreemptiveScheduling.runWithUserInput();
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid algorithm.");
        }

        scanner.close();
    }
}
