// Final assignment 2 ( Student Management System)
package student; 

import java.util.*;

enum Branch {
    CSE, IT
}

enum Year {
    FY, SY, TY
}

class Student {
    private static int prnCounter = 1000;

    int prn;
    String name;
    Year year;
    Branch branch;
    double sgpa;
    double cgpa;
    int attendance;

    // Constructor for FY students
    public Student(String name, Year year, Branch branch) {
        this.prn = prnCounter++;
        this.name = name;
        this.year = year;
        this.branch = branch;
        this.sgpa = 0.0;
        this.cgpa = 0.0;
        this.attendance = 0;
    }

    // Constructor for SY and TY students with SGPA and CGPA
    public Student(String name, Year year, Branch branch, double sgpa, double cgpa) {
        this.prn = prnCounter++;
        this.name = name;
        this.year = year;
        this.branch = branch;
        this.sgpa = sgpa;
        this.cgpa = cgpa;
        this.attendance = 0;
    }

    public void updateAttendance(int daysPresent) {
        this.attendance = daysPresent;
    }

    public void display() {
        System.out.println("PRN: " + prn);
        System.out.println("Name: " + name);
        System.out.println("Year: " + year);
        System.out.println("Branch: " + branch);
        if (year == Year.SY || year == Year.TY) {
            System.out.println("SGPA: " + sgpa);
            System.out.println("CGPA: " + cgpa);
        }
        System.out.println("Attendance: " + attendance + "%\n");
    }
}

public class StudentManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static Map<Integer, Student> studentDB = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Admin Login\n2. Student Login\n3. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: adminPanel(); break;
                case 2: studentPanel(); break;
                case 3: System.exit(0);
                default: System.out.println("Invalid option");
            }
        }
    }

    static void adminPanel() {
        while (true) {
            System.out.println("\n--- Admin Panel ---");
            System.out.println("1. Add Student\n2. View All Students\n3. Search Student by PRN\n4. Remove Student\n5. Update Attendance\n6. Back");
            int option = sc.nextInt();

            switch (option) {
                case 1: addStudent(); break;
                case 2: viewAllStudents(); break;
                case 3: searchStudent(); break;
                case 4: removeStudent(); break;
                case 5: updateAttendance(); break;
                case 6: return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    static void studentPanel() {
        System.out.print("Enter your PRN: ");
        int prn = sc.nextInt();
        Student s = studentDB.get(prn);
        if (s != null) {
            s.display();
        } else {
            System.out.println("Student not found");
        }
    }

    static void addStudent() {
        System.out.print("Enter name: ");
        sc.nextLine(); // consume leftover newline
        String name = sc.nextLine();

        System.out.print("Enter year (FY/SY/TY): ");
        Year year = Year.valueOf(sc.next().toUpperCase());

        System.out.print("Enter branch (CSE/IT): ");
        Branch branch = Branch.valueOf(sc.next().toUpperCase());

        if (year == Year.FY) {
            Student s = new Student(name, year, branch);
            studentDB.put(s.prn, s);
            System.out.println("Student added with PRN: " + s.prn);
        } else {
            System.out.print("Enter SGPA: ");
            double sgpa = sc.nextDouble();

            System.out.print("Enter CGPA: ");
            double cgpa = sc.nextDouble();

            Student s = new Student(name, year, branch, sgpa, cgpa);
            studentDB.put(s.prn, s);
            System.out.println("Student added with PRN: " + s.prn);
        }
    }

    static void viewAllStudents() {
        if (studentDB.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : studentDB.values()) {
                s.display();
            }
        }
    }

    static void searchStudent() {
        System.out.print("Enter PRN to search: ");
        int prn = sc.nextInt();
        Student s = studentDB.get(prn);
        if (s != null) {
            s.display();
        } else {
            System.out.println("Student not found.");
        }
    }

    static void removeStudent() {
        System.out.print("Enter PRN to remove: ");
        int prn = sc.nextInt();
        if (studentDB.containsKey(prn)) {
            studentDB.remove(prn);
            System.out.println("Student removed.");
        } else {
            System.out.println("PRN not found.");
        }
    }

    static void updateAttendance() {
        System.out.print("Enter PRN to update attendance: ");
        int prn = sc.nextInt();
        Student s = studentDB.get(prn);
        if (s != null) {
            System.out.print("Enter attendance percentage: ");
            int att = sc.nextInt();
            s.updateAttendance(att);
            System.out.println("Attendance updated.");
        } else {
            System.out.println("Student not found.");
        }
    }
}