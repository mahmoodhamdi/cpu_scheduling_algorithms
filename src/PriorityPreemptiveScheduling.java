import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MyTask implements Runnable {
    String name;
    private int priority;
    private int waitingTime;
    private int turnaroundTime;

    public MyTask(String name, int priority) {
        this.name = name;
        this.priority = priority;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            System.out.println(name + " is running");
        }

        long endTime = System.currentTimeMillis();

        waitingTime = (int) (endTime - startTime) - 5; // Subtracting 5 because the loop runs for 5 iterations
        turnaroundTime = waitingTime + 5;
    }

    public int getPriority() {
        return priority;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }
}

public class PriorityPreemptiveScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<MyTask> tasks = new ArrayList<>();

        System.out.print("Enter the number of tasks: ");
        int numTasks = scanner.nextInt();

        for (int i = 1; i <= numTasks; i++) {
            System.out.print("Enter name for Task " + i + ": ");
            String taskName = scanner.next();
            System.out.print("Enter priority for Task " + i + " (1-10): ");
            int taskPriority = scanner.nextInt();

            if (taskPriority < 1 || taskPriority > 10) {
                System.out.println("Invalid priority. Priority should be between 1 and 10.");
                return;
            }

            tasks.add(new MyTask(taskName, taskPriority));
        }

        scanner.close();

        tasks.sort((t1, t2) -> Integer.compare(t2.getPriority(), t1.getPriority()));

        for (MyTask task : tasks) {
            Thread thread = new Thread(task);
            thread.setPriority(task.getPriority());
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Task: " + task.name +
                    ", Priority: " + task.getPriority() +
                    ", Waiting Time: " + task.getWaitingTime() +
                    ", Turnaround Time: " + task.getTurnaroundTime());
        }

        int totalWaitingTime = tasks.stream().mapToInt(MyTask::getWaitingTime).sum();
        int totalTurnaroundTime = tasks.stream().mapToInt(MyTask::getTurnaroundTime).sum();

        double averageWaitingTime = (double) totalWaitingTime / numTasks;
        double averageTurnaroundTime = (double) totalTurnaroundTime / numTasks;

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
    }
}
