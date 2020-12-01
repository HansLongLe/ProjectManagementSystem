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

  @Override public String toString()
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

  public abstract Employee showEmployeeInfo(String name);

  // abstract methods that will be created
  public abstract Project viewProjectInfo(int ID);
  public abstract Project viewProjectInfo(String name);

}
