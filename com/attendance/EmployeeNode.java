public class EmployeeNode {

  public int id;
  public int attendance_counter;
  EmployeeNode left;
  EmployeeNode right;

  EmployeeNode(int id, int attendance_counter) {
    this.id = id;
    this.attendance_counter = attendance_counter;
    this.left = null;
    this.right = null;
  }
}
