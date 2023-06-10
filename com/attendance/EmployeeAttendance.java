import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeAttendance {

  private static void displayOptions() {
    System.out.println();
    System.out.println("Menu Options");
    System.out.println("1. Import employee attendance (delimiter -1)");
    System.out.println("2. How many employees came?");
    System.out.println("3. Did a particular employee come?");
    System.out.println("4. How often did an employee enter?");
    System.out.println("5. Who came in the most number of times?");
    System.out.println(
      "6. Who all came within a range of IDs, and how often they entered"
    );
    System.out.println("7. Exit");
    System.out.println();
    System.out.print("Enter your choice: ");
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    EmpBST bst = new EmpBST();
    EmployeeNode root = null;
    displayOptions();
    int input = 7;
    try {
      input = scanner.nextInt();
    } catch (InputMismatchException e) {
      System.out.println("Invalid input. Please enter an integer.");
    }
    while (input != 7) {
      if (input < 1 || input > 7) {
        System.out.println("Invalid option");
      }
      switch (input) {
        case 1:
          // assuming the delimiter to be -1
          // we could also have inputted a count of the employees to be entered
          System.out.print("Enter Employee Id:");
          int empId1 = -1;
          try {
            empId1 = scanner.nextInt();
          } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
            break;
          }
          while (empId1 != -1) {
            if (empId1 <= 0) {
              System.out.println("Please enter a valid ID");
            } else {
              root = bst.readEmployees(root, empId1);
            }
            System.out.print("Enter Employee Id: ");
            try {
              empId1 = scanner.nextInt();
            } catch (InputMismatchException e) {
              System.out.println("Invalid input. Please enter an integer.");
              break;
            }
          }
          break;
        case 2:
          int headCount = bst.getHeadcount(root);
          System.out.println("Employees visited the office: " + headCount);
          break;
        case 3:
          System.out.print("Enter the Employee ID for search: ");
          int empId2 = -1;
          try {
            empId2 = scanner.nextInt();
          } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
            break;
          }
          if (empId2 >= 0) {
            String res = bst.searchID(root, empId2)
              ? "Visited the Office"
              : "Did not visit the Office";
            System.out.println(res);
          } else {
            System.out.println("Please enter a valid ID");
          }
          break;
        case 4:
          System.out.print("Enter the Employee ID: ");
          int empId3 = -1;
          try {
            empId3 = scanner.nextInt();
          } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
            break;
          }
          if (empId3 >= 0) {
            int att = bst.howOften(root, empId3);
            System.out.println(
              "Attendance: " + (att == -1 ? "Did not visit the office" : att)
            );
          } else {
            System.out.println("Please enter a valid ID");
          }
          break;
        case 5:
          EmployeeNode node = bst.frequentVisitor(root);
          if (node == null) {
            System.out.println("Nobody visited the office.");
          } else {
            System.out.println(
              "Employee Id: " +
              node.id +
              ", visited: " +
              node.attendance_counter
            );
          }
          break;
        case 6:
          System.out.println("Enter the Employee id range- ");
          int id1 = -1;
          int id2 = -1;
          try {
            id1 = scanner.nextInt();
            id2 = scanner.nextInt();
          } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
            break;
          }
          if (id1 < 0 || id2 < 0) {
            System.out.println("Please enter a valid range");
          } else if (id1 > id2) {
            int temp = id2;
            id2 = id1;
            id1 = temp;
          }
          bst.printRangePresent(root, id1, id2);
          break;
      }
      displayOptions();
      try {
        input = scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter an integer.");
      }
    }
  }
}
