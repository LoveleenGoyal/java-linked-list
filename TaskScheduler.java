public class TaskScheduler {
    class Task {
        String id;
        String name;
        int priority;
        String date;
        Task next;

        public Task(String id, String name, int priority, String date) {
            this.id = id;
            this.name = name;
            this.priority = priority;
            this.date = date;
            this.next = null;
        }
    }

    public Task head = null, tail = null;

    public void addTaskAtBeginning(String id, String name, int priority, String date) {
        Task newTask = new Task(id, name, priority, date);

        if (head == null) {
            head = newTask;
            tail = newTask;
            newTask.next = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
    }

    public void addTaskAtEnd(String id, String name, int priority, String date) {
        Task newTask = new Task(id, name, priority, date);

        if (head == null) {
            head = newTask;
            tail = newTask;
            newTask.next = head;
        } else {
            newTask.next = head;
            tail.next = newTask;
            tail = newTask;
        }
    }

    public void addTaskAtPosition(String id, String name, int priority, String date, int postion) {
        Task newTask = new Task(id, name, priority, date);
        if(postion == 1) {
            addTaskAtBeginning(id, name, priority, date);
            return;
        }
        Task current = head;
        int count = 1;
        while (count < postion - 1 && current.next != head) {
            current = current.next;
            count++;
        }
        newTask.next = current.next;
        current.next = newTask;

        if (current == tail) {
            tail = newTask;
        }
    }

    public void removeTask(String id) {
        if (head ==  null) {
            return;
        }
        Task current = head;
        Task prev = null;
        // If head is to be deleted
        if (head.id.equals(id)) {
            if (head == tail) { // Only one element in the list
                head = tail = null;
            } else {
                head = head.next;
                tail.next = head;
            }
            return;
        }

        // Traverse to find the task
        do {
            prev = current;
            current = current.next;
            if (current.id.equals(id)) {
                prev.next = current.next;
                if (current == tail) {
                    tail = prev;
                }
                return;
            }
        } while (current != head);
    }

    public void searchTask(int priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }

        Task temp = head;
        boolean found = false;
        System.out.println("Tasks with Priority " + priority + ":");
        do {
            if (temp.priority == priority) {
                System.out.println("ID: " + temp.id + ", Name: " + temp.name + ", Date: " + temp.date);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No tasks found with priority " + priority);
        }
    }

    public void displayTasks() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        System.out.println("Task list:");
        Task temp = head;
        do {
            System.out.println("ID: " + temp.id + ", Name: " + temp.name + ", Priority: " + temp.priority + ", Due Date: " + temp.date);
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        scheduler.addTaskAtEnd("T1", "Design UI", 1, "2025-03-20");
        scheduler.addTaskAtEnd("T2", "Develop Backend", 2, "2025-03-22");
        scheduler.addTaskAtEnd("T3", "Test Application", 3, "2025-03-25");

        System.out.println("Initial Task List:");
        scheduler.displayTasks();

        System.out.println("Adding a task at position 2:");
        scheduler.addTaskAtPosition("T4", "Write Documentation", 2, "2025-03-30", 2);
        scheduler.displayTasks();

        System.out.println("Removing Task with id: T2");
        scheduler.removeTask("T2");
        scheduler.displayTasks();

        System.out.println("\nSearching for Tasks with Priority 2:");
        scheduler.searchTask(2);
    }
}
