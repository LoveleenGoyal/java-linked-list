public class StudentRecordManagement {
    class StudentRecord {
        int rollNumber;
        String name;
        int age;
        String grade;
        StudentRecord next;

        public StudentRecord(int rollNumber, String name, int age, String grade){
            this.rollNumber = rollNumber;
            this.name = name;
            this.age = age;
            this.grade = grade;
            this.next = null;
        }
    }
    public StudentRecord head = null;
    public StudentRecord tail = null;

    public void addStudentAtBeginning(int rollNumber, String name, int age, String grade) {
        StudentRecord newStudent = new StudentRecord(rollNumber, name, age, grade);
        if (head == null) {
            head = newStudent;
            tail = newStudent;
        } else {
            newStudent.next = head;
            head = newStudent;
        }
    }

    public void addStudentAtEnd(int rollNumber, String name, int age, String grade) {
        StudentRecord newStudent = new StudentRecord(rollNumber, name, age, grade);
        if (head == null) {
            head = newStudent;
            tail = newStudent;
        } else {
            tail.next = newStudent;
            tail = newStudent;
        }
    }

    public void addStudentAtPosition(int rollNumber, String name, int age, String grade, int position) {
        StudentRecord newStudent = new StudentRecord(rollNumber, name, age, grade);
        if (position == 1) {
            addStudentAtBeginning(rollNumber, name, age, grade);
            return;
        }

        StudentRecord current = head;
        int count = 1;
        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }
        if (current == null) {
            System.out.println("Position out of bounds");
        } else {
            newStudent.next = current.next;
            current.next = newStudent;
        }
    }

    public void deleteStudent(int key) {
        StudentRecord temp = head;
        StudentRecord prev = null;
        if (temp != null && temp.rollNumber == key) {
            head = temp.next;
            return;
        }
        while (temp != null && temp.rollNumber != key) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
           return;
        }
        prev.next = temp.next;
    }

    public void searchStudent(int rollNumber) {
        StudentRecord current = head;
        int i = 1;
        boolean flag = false;
        if ( head == null) {
            System.out.println("List is empty");
        } else {
            while (current != null) {
               if (current.rollNumber == rollNumber) {
                   flag = true;
                   break;
               }
               i++;
               current = current.next;
            }
        }
        if(flag) {
            System.out.println("Student with roll number " + rollNumber + " is present in student records.");
            System.out.println("Name: " + current.name + ", Age: " + current.age + ", Grade: " + current.grade + "\n");
        } else {
            System.out.println("Student with roll number " + rollNumber + " is not present." + "\n");
        }
    }

    public void updateStudentGrade(int rollNumber, String newGrade) {
        StudentRecord current = head;
        while (current != null) {
            if (current.rollNumber == rollNumber) {
                current.grade = newGrade;
                System.out.println("Updated grade for Roll Number: " + rollNumber);
                return;
            }
            current = current.next;
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    public void display() {
        StudentRecord current = head;
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("Student Records:");
        while (current != null) {
            System.out.print("Roll Number: " + current.rollNumber);
            System.out.print(", Name: " + current.name);
            System.out.print(", Age: " + current.age);
            System.out.print(", Grade: " + current.grade);
            System.out.println();
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StudentRecordManagement studentRecord = new StudentRecordManagement();

        studentRecord.addStudentAtEnd(101, "Loveleen", 21, "A+");
        studentRecord.addStudentAtEnd(201, "Loveleen", 23, "B+");
        studentRecord.addStudentAtBeginning(102, "Yagyata", 21, "A");
        studentRecord.addStudentAtPosition(103, "Harsh", 22, "B", 3);
        studentRecord.display();

        studentRecord.deleteStudent(101);
        studentRecord.display();

        studentRecord.searchStudent(101);
        studentRecord.searchStudent(201);

        studentRecord.updateStudentGrade(201, "O");
        studentRecord.display();
    }
}
/*Student Records:
Roll Number: 102, Name: Yagyata, Age: 21, Grade: A
Roll Number: 101, Name: Loveleen, Age: 21, Grade: A+
Roll Number: 103, Name: Harsh, Age: 22, Grade: B
Roll Number: 201, Name: Loveleen, Age: 23, Grade: B+

Student Records:
Roll Number: 102, Name: Yagyata, Age: 21, Grade: A
Roll Number: 103, Name: Harsh, Age: 22, Grade: B
Roll Number: 201, Name: Loveleen, Age: 23, Grade: B+

Student with roll number 101 is not present.

Student with roll number 201 is present in student records.
Name: Loveleen, Age: 23, Grade: B+

Updated grade for Roll Number: 201
Student Records:
Roll Number: 102, Name: Yagyata, Age: 21, Grade: A
Roll Number: 103, Name: Harsh, Age: 22, Grade: B
Roll Number: 201, Name: Loveleen, Age: 23, Grade: O
*/