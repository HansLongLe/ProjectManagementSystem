package MyFile;

import Classes.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FileAdapter
{
  private MyFileIO mfio;
  private String fileName;

  public FileAdapter(String fileName)
  {
    mfio = new MyFileIO();
    this.fileName = fileName;
  }

  /**
   *  This method reads an object from a .bin file.
   * @return an array list of Employees.
   */
  public ArrayList<Employee> getAllEmployees()
  {
    ArrayList<Employee> employees = new ArrayList<Employee>();

    try
    {
        employees = (ArrayList<Employee>)mfio.readObjectFromFile(fileName);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return employees;
  }

  /**
   * This method writes an object of type ProjectManagmentSystem to a .bin file.
   * @param pms - a ProjectManagementSystem object.
   */
  public void saveToPMSFile(ProjectManagementSystem pms){

    try{
      mfio.writeToFile("ProjectManagementSystem.bin", pms);
    }
    catch(FileNotFoundException e){
      System.out.println("File not found");
    }
    catch(IOException e){
      System.out.println("IO Error writing file");
    }
  }

  /**
   * This method reads the information froma .bin file.
   * @return pms - a ProjectManagementSystem object.
   */
  public ProjectManagementSystem loadPMS(){
    ProjectManagementSystem pms = new ProjectManagementSystem();
    try{
      pms = (ProjectManagementSystem)mfio.readObjectFromFile("ProjectManagementSystem.bin");

    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return pms;
  }



}
