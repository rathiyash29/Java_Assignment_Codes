// Assignment 3 (Employee management system)

// HR ID: hr1
// Password: pass123

//EMP001
// Password: passEMP001
package employee;

import java.util.*;

interface Payroll {
    double calculateBonus();
}

interface Attendance {
    void markAttendance();
}

class Person {
    String name, email, phone;
    int age;

    public Person(String name, int age, String email, String phone) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age + ", Email: " + email + ", Phone: " + phone);
    }
}

class Employee extends Person implements Payroll, Attendance {
    static int idCounter = 1;
    String employeeID, department, designation, password;
    double salary;
    int leaveBalance = 12;
    boolean leaveRequested = false;
    String leaveStatus = "None";
    int attendanceDays = 0;

    public Employee(String name, int age, String email, String phone, String department, String designation, double salary) {
        super(name, age, email, phone);
        this.employeeID = String.format("EMP%03d", idCounter++);
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.password = "pass" + this.employeeID;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("ID: " + employeeID + ", Dept: " + department + ", Post: " + designation);
    }

    public void viewSalary() {
        System.out.println("Your salary is: ₹" + salary);
    }

    public void applyLeave() {
        if (leaveRequested) {
            System.out.println("You already have a pending leave request.");
        } else if (leaveBalance <= 0) {
            System.out.println("No leave balance left.");
        } else {
            leaveRequested = true;
            leaveStatus = "Pending";
            System.out.println("Leave request submitted.");
        }
    }

    public void checkLeaveStatus() {
        System.out.println("Leave Status: " + leaveStatus);
    }

    @Override
    public double calculateBonus() {
        return salary * 0.10;
    }

    @Override
    public void markAttendance() {
        attendanceDays++;
        System.out.println("Attendance marked. Total days: " + attendanceDays);
    }
}

class Manager extends Employee {
    public Manager(String name, int age, String email, String phone, String department, String designation, double salary) {
        super(name, age, email, phone, department, designation, salary);
    }

    @Override
    public double calculateBonus() {
        return salary * 0.15;
    }
}

class HR extends Person implements Payroll {
    String hrID, password;

    public HR(String name, int age, String email, String phone, String hrID, String password) {
        super(name, age, email, phone);
        this.hrID = hrID;
        this.password = password;
    }

    public void addEmployee(ArrayList<Employee> list, Scanner sc) {
        System.out.print("Enter name: "); String name = sc.nextLine();
        System.out.print("Enter age: "); int age = Integer.parseInt(sc.nextLine());
        System.out.print("Enter email: "); String email = sc.nextLine();
        System.out.print("Enter phone: "); String phone = sc.nextLine();
        System.out.print("Enter department: "); String dept = sc.nextLine();
        System.out.print("Enter designation: "); String desg = sc.nextLine();
        System.out.print("Enter salary: "); double sal = Double.parseDouble(sc.nextLine());
        Employee emp = new Employee(name, age, email, phone, dept, desg, sal);
        list.add(emp);
        System.out.println("Employee added with ID: " + emp.employeeID + " Password: " + emp.password);
    }

    public void removeEmployee(ArrayList<Employee> list, Scanner sc) {
        System.out.print("Enter Employee ID to remove: "); String id = sc.nextLine();
        list.removeIf(e -> e.employeeID.equals(id));
        System.out.println("If ID was valid, employee removed.");
    }

    public void updateEmployee(ArrayList<Employee> list, Scanner sc) {
        System.out.print("Enter Employee ID to update: "); String id = sc.nextLine();
        for (Employee e : list) {
            if (e.employeeID.equals(id)) {
                System.out.print("Enter new salary: "); e.salary = Double.parseDouble(sc.nextLine());
                System.out.print("Enter new designation: "); e.designation = sc.nextLine();
                System.out.println("Updated successfully.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public void viewAllEmployees(ArrayList<Employee> list) {
        for (Employee e : list) {
            e.displayInfo();
            System.out.println("Salary: ₹" + e.salary + ", Leave Balance: " + e.leaveBalance);
            System.out.println("------");
        }
    }

    public void manageLeaves(ArrayList<Employee> list, Scanner sc) {
        for (Employee e : list) {
            if (e.leaveRequested && e.leaveStatus.equals("Pending")) {
                System.out.println(e.employeeID + " - " + e.name + " has requested leave.");
                System.out.print("Approve? (y/n): ");
                String choice = sc.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    e.leaveStatus = "Approved";
                    e.leaveBalance--;
                } else {
                    e.leaveStatus = "Rejected";
                }
                e.leaveRequested = false;
            }
        }
    }

    @Override
    public double calculateBonus() {
        return 5000; // Fixed HR bonus
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Employee> employees = new ArrayList<>();

        HR hr = new HR("Ramesh", 35, "hr@company.com", "9876543210", "hr1", "pass123");

        employees.add(new Employee("Amit", 28, "amit@company.com", "9999999999", "IT", "Developer", 50000));
        employees.add(new Employee("Priya", 30, "priya@company.com", "8888888888", "Finance", "Accountant", 40000));

        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. HR Login");
            System.out.println("2. Employee Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter HR ID: "); String id = sc.nextLine();
                System.out.print("Enter Password: "); String pass = sc.nextLine();
                if (id.equals(hr.hrID) && pass.equals(hr.password)) {
                    while (true) {
                        System.out.println("\n--- HR Menu ---");
                        System.out.println("1. Add Employee");
                        System.out.println("2. Remove Employee");
                        System.out.println("3. Update Employee");
                        System.out.println("4. View All Employees");
                        System.out.println("5. Manage Leaves");
                        System.out.println("6. Logout");
                        System.out.print("Enter choice: ");
                        String ch = sc.nextLine();
                        switch (ch) {
                            case "1": hr.addEmployee(employees, sc); break;
                            case "2": hr.removeEmployee(employees, sc); break;
                            case "3": hr.updateEmployee(employees, sc); break;
                            case "4": hr.viewAllEmployees(employees); break;
                            case "5": hr.manageLeaves(employees, sc); break;
                            case "6": System.out.println("Logging out..."); break;
                            default: System.out.println("Invalid choice."); 
                        }
                        if (ch.equals("6")) break;
                    }
                } else {
                    System.out.println("Invalid HR credentials.");
                }
            } 
            else if (choice.equals("2")) {
                System.out.print("Enter Employee ID: "); String id = sc.nextLine();
                System.out.print("Enter Password: "); String pass = sc.nextLine();
                Employee emp = null;
                for (Employee e : employees) {
                    if (e.employeeID.equals(id) && e.password.equals(pass)) {
                        emp = e; break;
                    }
                }
                if (emp != null) {
                    while (true) {
                        System.out.println("\n--- Employee Menu ---");
                        System.out.println("1. View Profile");
                        System.out.println("2. View Salary");
                        System.out.println("3. Apply Leave");
                        System.out.println("4. Check Leave Status");
                        System.out.println("5. Mark Attendance");
                        System.out.println("6. Logout");
                        System.out.print("Enter choice: ");
                        String ch = sc.nextLine();
                        switch (ch) {
                            case "1": emp.displayInfo(); break;
                            case "2": emp.viewSalary(); break;
                            case "3": emp.applyLeave(); break;
                            case "4": emp.checkLeaveStatus(); break;
                            case "5": emp.markAttendance(); break;
                            case "6": System.out.println("Logging out..."); break;
                            default: System.out.println("Invalid choice.");
                        }
                        if (ch.equals("6")) break;
                    }
                } else {
                    System.out.println("Invalid Employee credentials.");
                }
            } 
            else if (choice.equals("3")) {
                System.out.println("Exiting...");
                break;
            } 
            else {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}