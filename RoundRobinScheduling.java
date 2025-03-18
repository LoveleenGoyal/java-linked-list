import java.util.ArrayList;
import java.util.List;

class RoundRobinScheduling {
    class Process {
        int id;
        int burstTime;
        int remainingTime;
        int priority;
        Process next;

        public Process(int id, int burstTime, int priority) {
            this.id = id;
            this.burstTime = burstTime;
            this.remainingTime = burstTime; // To track execution progress
            this.priority = priority;
            this.next = null;
        }
    }

    private Process head = null;
    private Process tail = null;
    private int totalProcesses = 0;

    // Add a process at the end of the circular queue
    public void addProcess(int id, int burstTime, int priority) {
        Process newProcess = new Process(id, burstTime, priority);

        if (head == null) {
            head = newProcess;
            tail = newProcess;
            newProcess.next = head;  // Circular link
        } else {
            newProcess.next = head;
            tail.next = newProcess;
            tail = newProcess;
        }
        totalProcesses++;
    }

    // Remove a process after execution
    public void removeProcess(int id) {
        if (head == null) return;

        Process current = head;
        Process prev = null;

        do {
            if (current.id == id) {
                if (current == head && current == tail) {
                    head = null;
                    tail = null;
                } else if (current == head) {
                    head = head.next;
                    tail.next = head;
                } else if (current == tail) {
                    prev.next = head;
                    tail = prev;
                } else {
                    prev.next = current.next;
                }
                totalProcesses--;
                System.out.println("Process " + id + " completed and removed.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    public void schedulingProcess(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }

        int totalWaitingTime = 0, totalTurnAroundTime = 0, processCount = 0;
        Process current = head;

        System.out.println("\nStarting Round Robin Scheduling with Time Quantum = " + timeQuantum);

        while (head != null) {
            displayProcesses();

            if (current.remainingTime > timeQuantum) {
                System.out.println("Process " + current.id + " executes for " + timeQuantum + " units.");
                current.remainingTime -= timeQuantum;
            } else {
                System.out.println("Process " + current.id + " executes for " + current.remainingTime + " units and completes.");
                totalTurnAroundTime += current.burstTime;  // Turnaround time = completion time
                totalWaitingTime += (totalTurnAroundTime - current.burstTime);  // Waiting Time = Turnaround - Burst
                processCount++;
                Process temp = current;
                current = current.next;
                removeProcess(temp.id);
                continue;
            }
            current = current.next;
        }

        // Calculate and display average waiting & turnaround time
        double avgWaitingTime = (double) totalWaitingTime / processCount;
        double avgTurnAroundTime = (double) totalTurnAroundTime / processCount;
        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnAroundTime);
    }

    // Display the list of processes in the circular queue
    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes left.");
            return;
        }

        System.out.print("Processes in queue: ");
        Process temp = head;
        do {
            System.out.print("[P" + temp.id + " (" + temp.remainingTime + ")] -> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("(Back to start)");
    }

    public static void main(String[] args) {
        RoundRobinScheduling processQueue = new RoundRobinScheduling();

        processQueue.addProcess(1, 10, 2);
        processQueue.addProcess(2, 8, 3);
        processQueue.addProcess(3, 5, 1);

        int timeQuantum = 4;
        processQueue.schedulingProcess(timeQuantum);
    }
}

/*Starting Round Robin Scheduling with Time Quantum = 4
Processes in queue: [P1 (10)] -> [P2 (8)] -> [P3 (5)] -> (Back to start)
Process 1 executes for 4 units.
Processes in queue: [P1 (6)] -> [P2 (8)] -> [P3 (5)] -> (Back to start)
Process 2 executes for 4 units.
Processes in queue: [P1 (6)] -> [P2 (4)] -> [P3 (5)] -> (Back to start)
Process 3 executes for 4 units.
Processes in queue: [P1 (6)] -> [P2 (4)] -> [P3 (1)] -> (Back to start)
Process 1 executes for 4 units.
Processes in queue: [P1 (2)] -> [P2 (4)] -> [P3 (1)] -> (Back to start)
Process 2 executes for 4 units and completes.
Process 2 completed and removed.
Processes in queue: [P1 (2)] -> [P3 (1)] -> (Back to start)
Process 3 executes for 1 units and completes.
Process 3 completed and removed.
Processes in queue: [P1 (2)] -> (Back to start)
Process 1 executes for 2 units and completes.
Process 1 completed and removed.

Average Waiting Time: 7.0
Average Turnaround Time: 7.666666666666667
*/