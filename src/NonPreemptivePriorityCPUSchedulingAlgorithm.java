import java.util.Scanner;

public class NonPreemptivePriorityCPUSchedulingAlgorithm {

    // ANSI escape codes for text colors
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String BOLD = "\u001B[1m";

    int burstTime[];
    int priority[];
    int arrivalTime[];
    String[] processId;
    int numberOfProcess;

    void getProcessData(Scanner input) {
        System.out.print("Enter the number of Processes for Scheduling: ");
        int inputNumberOfProcess = input.nextInt();
        numberOfProcess = inputNumberOfProcess;
        burstTime = new int[numberOfProcess];
        priority = new int[numberOfProcess];
        arrivalTime = new int[numberOfProcess];
        processId = new String[numberOfProcess];
        String st = "P";
        for (int i = 0; i < numberOfProcess; i++) {
            processId[i] = st.concat(Integer.toString(i));
            System.out.print("Enter the burst time for Process " + processId[i] + ": ");
            burstTime[i] = input.nextInt();
            System.out.print("Enter the arrival time for Process " + processId[i] + ": ");
            arrivalTime[i] = input.nextInt();
            System.out.print("Enter the priority for Process " + processId[i] + ": ");
            priority[i] = input.nextInt();
        }
    }

    void sortAccordingArrivalTimeAndPriority(int[] at, int[] bt, int[] prt, String[] pid) {
        int temp;
        String stemp;
        for (int i = 0; i < numberOfProcess; i++) {
            for (int j = 0; j < numberOfProcess - i - 1; j++) {
                if (at[j] > at[j + 1] || (at[j] == at[j + 1] && prt[j] > prt[j + 1])) {
                    // swapping arrival time
                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    // swapping burst time
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    // swapping priority
                    temp = prt[j];
                    prt[j] = prt[j + 1];
                    prt[j + 1] = temp;

                    // swapping process identity
                    stemp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = stemp;
                }
            }
        }
    }

    void priorityNonPreemptiveAlgorithm() {
        int finishTime[] = new int[numberOfProcess];
        int bt[] = burstTime.clone();
        int at[] = arrivalTime.clone();
        int prt[] = priority.clone();
        String pid[] = processId.clone();
        int waitingTime[] = new int[numberOfProcess];
        int turnAroundTime[] = new int[numberOfProcess];

        sortAccordingArrivalTimeAndPriority(at, bt, prt, pid);

        // calculating waiting & turn-around time for each process
        finishTime[0] = at[0] + bt[0];
        turnAroundTime[0] = finishTime[0] - at[0];
        waitingTime[0] = turnAroundTime[0] - bt[0];

        for (int i = 1; i < numberOfProcess; i++) {
            finishTime[i] = bt[i] + finishTime[i - 1];
            turnAroundTime[i] = finishTime[i] - at[i];
            waitingTime[i] = turnAroundTime[i] - bt[i];
        }

        float sumWaitingTime = 0;
        float sumTurnAroundTime = 0;

        // calculate sums for averages
        for (int n : waitingTime) {
            sumWaitingTime += n;
        }
        for (int n : turnAroundTime) {
            sumTurnAroundTime += n;
        }

        float averageWaitingTime = sumWaitingTime / numberOfProcess;
        float averageTurnAroundTime = sumTurnAroundTime / numberOfProcess;

        // print on console the order of processes along with their finish time & turn around time
        System.out.println(CYAN + BOLD + "Priority Scheduling Algorithm:" + RESET);
        System.out.format("%-12s%-12s%-12s%-12s%-15s%-15s%-15s\n",
                "ProcessId", "BurstTime", "ArrivalTime", "Priority", "FinishTime", "WaitingTime", "TurnAroundTime");

        for (int i = 0; i < numberOfProcess; i++) {
            System.out.format("%-12s%-12d%-12d%-12d%-15d%-15d%-15d\n",
                    pid[i], bt[i], at[i], prt[i], finishTime[i], waitingTime[i], turnAroundTime[i]);
        }

        System.out.format("%-51s%-15.2f%-15.2f\n",
                "Average", averageWaitingTime, averageTurnAroundTime);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        NonPreemptivePriorityCPUSchedulingAlgorithm obj = new NonPreemptivePriorityCPUSchedulingAlgorithm();
        obj.getProcessData(input);
        obj.priorityNonPreemptiveAlgorithm();
    }
}
