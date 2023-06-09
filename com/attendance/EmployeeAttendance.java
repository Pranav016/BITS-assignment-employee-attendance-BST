// import java.util.InputMismatchException;
import java.util.Scanner;

// try {
//     System.out.print("Enter an integer: ");
//     input = scanner.nextInt();
// } catch (InputMismatchException e) {
//     System.out.println("Invalid input. Please enter an integer.");
// }

public class EmployeeAttendance {

  private static void displayOptions() {
    System.out.println();
    System.out.println("Menu Options");
    System.out.println("1. Import employee attendance");
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
    int input = scanner.nextInt();
    while (input != 7) {
      switch (input) {
        case 1:
          // assuming the delimiter to be -1
          // we could also have inputted a count of the employees to be entered
          System.out.print("Enter Employee Id:");
          int empId1 = scanner.nextInt();
          while (empId1 != -1) {
            root = bst.readEmployees(root, empId1);
            System.out.print("Enter Employee Id: ");
            empId1 = scanner.nextInt();
          }
          break;
        case 2:
          int headCount = bst.getHeadcount(root);
          System.out.println("Employees visited the office- " + headCount);
          break;
        case 3:
          System.out.print("Enter the Employee ID for search: ");
          int empId2 = scanner.nextInt();
          String res = bst.searchID(root, empId2)
            ? "Visited the Office"
            : "Out of Office";
          System.out.println(res);
          break;
        case 4:
          System.out.print("Enter the Employee ID: ");
          int empId3 = scanner.nextInt();
          int att = bst.howOften(root, empId3);
          System.out.println(
            "Attendance: " + (att == -1 ? "Did not visit the office" : att)
          );
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
          int id1 = scanner.nextInt();
          int id2 = scanner.nextInt();
          bst.printRangePresent(root, id1, id2);
          break;
      }
      displayOptions();
      input = scanner.nextInt();
    }
  }
}
