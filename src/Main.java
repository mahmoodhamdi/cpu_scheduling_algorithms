import java.util.Scanner;

public class Main {
    // ANSI escape codes for text colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\n" + CYAN + "🌟 Welcome to CPU Scheduling Simulator! 🌟" + RESET);
            displayMenu();

            System.out.print("\nEnter the number of the selected algorithm: ");
            int selectedAlgorithm = scanner.nextInt();

            System.out.println(); // Add an empty line for better separation

            switch (selectedAlgorithm) {
                case 1:
                    runAlgorithm("FCFS", YELLOW, FCFSScheduling::runWithUserInput);
                    break;
                case 2:
                    runAlgorithm("SJF (Non-Preemptive)", YELLOW, SJFNonPreemptiveScheduling::runWithUserInput);
                    break;
                case 3:
                    runAlgorithm("SJF (Preemptive)", YELLOW, SJFPreemptiveScheduling::runWithUserInput);
                    break;
                case 4:
                    runAlgorithm("Round Robin", YELLOW, () -> RoundRobin.main(args));
                    break;
                case 5:
                    runAlgorithm("Priority Scheduling (Preemptive)", YELLOW,
                            () -> PriorityPreemptiveScheduling.main(args));
                    break;
                case 6:
                    runAlgorithm("Priority Scheduling (Non-Preemptive)", YELLOW,
                            () -> NonPreemptivePriorityCPUSchedulingAlgorithm.main(args));
                    break;
                default:
                    System.out.println(RED + "❌ Invalid choice. Please choose a valid algorithm." + RESET);
            }
        } catch (Exception e) {
            System.out.println(RED + "💥 An error occurred: " + e.getMessage() + RESET);
        }
    }

    private static void displayMenu() {
        System.out.println("Choose a scheduling algorithm:");
        System.out.println("1. " + GREEN + "FCFS (First Come First Serve)" + RESET);
        System.out.println("2. " + GREEN + "SJF (Shortest Job First - Non-Preemptive)" + RESET);
        System.out.println("3. " + GREEN + "SJF (Shortest Job First - Preemptive)" + RESET);
        System.out.println("4. " + GREEN + "Round Robin" + RESET);
        System.out.println("5. " + GREEN + "Priority Scheduling (Preemptive)" + RESET);
        System.out.println("6. " + GREEN + "Priority Scheduling (Non-Preemptive)" + RESET);
    }

    private static void runAlgorithm(String algorithmName, String color, Runnable algorithmRunner) {
        System.out.println(color + "Running " + BOLD + algorithmName + BOLD + " algorithm..." + RESET);
        algorithmRunner.run();
    }
}
