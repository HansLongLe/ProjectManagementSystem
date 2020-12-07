import java.util.Objects;

public abstract class Employee
{
  private String firstName;
  private String lastName;
  private String position;
  private double salary;
  private int employeeID;

  public Employee(String firstName, String lastName, String position, double salary, int employeeID){
    this.firstName = firstName;
    this.lastName = lastName;
    this.position = position;
    this.salary = salary;
    this.employeeID = employeeID;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getPosition()
  {
    return position;
  }

  public void setPosition(String position)
  {
    this.position = position;
  }

  public double getSalary()
  {
    return salary;
  }

  public void setSalary(double salary)
  {
    this.salary = salary;
  }

  public int getEmployeeID()
  {
    return employeeID;
  }

  public void setEmployeeID(int employeeID)
  {
    this.employeeID = employeeID;
  }

  public String toString()
  {
    return "The employee " + firstName + " " + lastName + " is a " + position + ". They are payed " + salary + "$\n ID: " + employeeID;
  }

  public boolean equals(Object obj)
  {
    if(!(obj instanceof Employee)){
      return false;
    }
    Employee other = (Employee)obj;
    return firstName.equals(other.firstName) &&
            lastName.equals(other.lastName) &&
            position.equals(other.position) &&
          salary==other.salary &&
          employeeID==other.employeeID;
  }

  /**
   * This is an abstract method that will show all the employees' information.
   * @param firstName for the name of an Employee.
   * @param lastName for the name of an Employee.
   * @return returns the information about an Employee.
   */
  public abstract Employee showEmployeeInfo(String firstName, String lastName);



  /**
   * This is an abstract method that will the project's info.
   * @param ID of the project we are looking for.
   * @return a project with all its info.
   */
  public abstract Project viewProjectInfo(int ID);


  /**
   * This is an abstract method that will the project's info.
   * @param name of the project we are looking for.
   * @return a project with all its info.
   */
  public abstract Project viewProjectInfo(String name);

}
