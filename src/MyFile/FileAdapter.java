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

}
