package MyFile;

import Classes.*;

import java.io.FileNotFoundException;
import java.io.IOException;

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

        Employee employee = null;

        if (position.equals("ScrumMaster"))
        {
         employee = new ScrumMaster(firstName, lastName, position, Double.parseDouble(salary),
                  Integer.parseInt(employeeID));
        }
        else if (position.equals("TeamMember"))
        {
          employee = new TeamMember(firstName, lastName, position, Double.parseDouble(salary),
              Integer.parseInt(employeeID));
        }
        else if(position.equals("ProjectCreator"))
        {
          employee = new ProjectCreator(firstName, lastName, position, Double.parseDouble(salary),
              Integer.parseInt(employeeID));
        }

        projectManagementSystem.getEmployees().add(employee);
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File was not found, or could not be opened");
    }

    MyFileIO mfio = new MyFileIO();

    try
    {
      mfio.writeToFile("employees.bin", projectManagementSystem.getEmployees());
    }
    catch (FileNotFoundException e)
    {
      System.out.println("Error opening file ");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file ");
    }

    System.out.println("Done");
  }


}

