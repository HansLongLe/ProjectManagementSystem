package MyFile;
import Classes.*;

import java.io.FileNotFoundException;

public class LoadInitialData
{
  public static void main(String[] args)
  {
    ProjectManagementSystem projectManagementSystem = new ProjectManagementSystem();

    MyTextFileIO mtfio = new MyTextFileIO();
    String[] employeeArray = null;
    try
    {
      employeeArray = mtfio.readArrayFromFile("employees.txt");

      for (int i = 0; i < employeeArray.length; i++)
      {
        String temp = employeeArray[i];
        String[] tempArr = temp.split(" ");
        String firstName = tempArr[0];
        String lastName = tempArr[1];
        String position = tempArr[2];
        String salary = tempArr[3];
        String employeeID = tempArr[4];

        projectManagementSystem.addEmployee(
            new TeamMember(firstName, lastName, position, Double.parseDouble(salary),
            Integer.parseInt(employeeID)));
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File was not found, or could not be opened");
    }

    System.out.println(projectManagementSystem.getEmployees());
  }
}
