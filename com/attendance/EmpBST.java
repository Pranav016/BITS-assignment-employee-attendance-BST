public class EmpBST {

  // only for testing
  public void inorder(EmployeeNode root) {
    if (root != null) {
      inorder(root.left);
      System.out.print(root.id + " ");
      inorder(root.right);
    }
  }

  // insert employees in BST
  public EmployeeNode readEmployees(EmployeeNode root, int id) {
    if (root == null) {
      EmployeeNode newEmp = new EmployeeNode(id, 1);
      return newEmp;
    }
    if (root.id == id) {
      ++root.attendance_counter;
      return root;
    }
    if (root.id > id) {
      root.left = readEmployees(root.left, id);
    } else {
      root.right = readEmployees(root.right, id);
    }
    return root;
  }

  // total count of all employees who entered
  public int getHeadcount(EmployeeNode root) {
    if (root == null) {
      return 0;
    }

    int leftCount = getHeadcount(root.left);
    int rightCount = getHeadcount(root.right);

    return leftCount + rightCount + 1;
  }

  // searching an employee
  public boolean searchID(EmployeeNode root, int id) {
    if (root == null) {
      return false;
    }
    if (root.id == id) {
      return true;
    }
    boolean leftResponse = searchID(root.left, id);
    boolean rightResponse = searchID(root.right, id);

    if (leftResponse || rightResponse) {
      return true;
    } else {
      return false;
    }
  }

  // attendance of an employee
  public int howOften(EmployeeNode root, int id) {
    if (root == null) {
      return -1;
    }
    if (root.id == id) {
      return root.attendance_counter;
    }
    int leftResponse = howOften(root.left, id);
    int rightResponse = howOften(root.right, id);
    if (leftResponse != -1) {
      return leftResponse;
    } else {
      return rightResponse;
    }
  }

  // most frequent visitor
  public EmployeeNode frequentVisitor(EmployeeNode root) {
    if (root == null) {
      return root;
    }
    EmployeeNode leftResponse = frequentVisitor(root.left);
    EmployeeNode rightResponse = frequentVisitor(root.right);
    EmployeeNode maxNode = root;
    if (
      leftResponse != null &&
      leftResponse.attendance_counter > maxNode.attendance_counter
    ) {
      maxNode = leftResponse;
    }
    if (
      rightResponse != null &&
      rightResponse.attendance_counter > maxNode.attendance_counter
    ) {
      maxNode = rightResponse;
    }
    return maxNode;
  }

  public void printRangePresent(EmployeeNode root, int id1, int id2) {
    if (root == null) {
      return;
    }

    // traverse the left side first so that we can get all the employee ids in ascending order
    if (root.id > id1) {
      printRangePresent(root.left, id1, id2);
    }
    // print if within range
    // assuming inclusive of extreme limit
    if (root.id >= id1 && root.id <= id2) {
      System.out.println(
        "Employee ID: " + root.id + ", Attendance: " + root.attendance_counter
      );
    }
    // traverse to right if root id is less than id2 (root id has room to increase)
    if (root.id < id2) {
      printRangePresent(root.right, id1, id2);
    }
  }
}
