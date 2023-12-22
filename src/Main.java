import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a scheduling algorithm:");
        System.out.println("1. FCFS (First Come First Serve)");
        System.out.println("2. SJF (Shortest Job First - Non-Preemptive)");
        System.out.println("3. SJF (Shortest Job First - Preemptive)");
        System.out.println("4. Round Robin");
        System.out.println("5. Priority Scheduling (Preemptive)");
        System.out.println("6. Priority Scheduling (Non-Preemptive)");

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
