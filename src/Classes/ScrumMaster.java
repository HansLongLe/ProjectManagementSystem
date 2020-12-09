package Classes;

import Classes.Employee;
import Classes.Project;
import Classes.ProjectManagementSystem;
import Classes.Requirement;

import java.io.Serializable;

public class ScrumMaster extends Employee implements Serializable
{
  private static ProjectManagementSystem projectManagementSystem;

    public ScrumMaster(String firstName, String lastName, String position, double salary, int employeeID)
    {
      super(firstName, lastName, position, salary, employeeID);
    }

  /**
   * Changing the project from Classes.Project Management System.
   * @param newProject Classes.Project we change(replace) with.
   * @param name Name of the project we would like to replace.
   */
  public void changeProject(Project newProject, String name)
  {
    for (int i=0; i<projectManagementSystem.getProjects().size(); i++)
    {
      if (projectManagementSystem.getProjects().get(i).getName().equals(name))
      {
        projectManagementSystem.getProjects().set(i, newProject);
      }
    }
  }

  /**
   * Changing a requirement from a project.
   * @param newRequirement Classes.Requirement we change(replace) with.
   * @param name Name of the requirement we change.
   */
  public void changeRequirement(Requirement newRequirement, String name)
  {
    for (int i=0; i<projectManagementSystem.getProjects().get(i).getRequirements().size(); i++)
    {
      if (projectManagementSystem.getProjects().get(i).getRequirements().get(i).getName().equals(name))
      {
        projectManagementSystem.getProjects().get(i).getRequirements().set(i, newRequirement);
      }
    }
  }

  /**
   * Changing a task from a requirement.
   * @param newTask Classes.Task we change(replace) with.
   * @param name Name of the task we change.
   */
  public void changeTask(Task newTask, String name)
  {
    for (int i=0; i<projectManagementSystem.getProjects().get(i).getRequirements().get(i).getTask().size(); i++)
    {
      if (projectManagementSystem.getProjects().get(i).getRequirements().get(i).getTask().get(i).getName().equals(name))
      {
        projectManagementSystem.getProjects().get(i).getRequirements().get(i).getTask().set(i, newTask);
      }
    }
  }

  /**
   * Find employee by first and last name.
   * @param firstNameEmployee
   * @param lastNameEmployee
   * @return Classes.Employee with given last and first name.
   */
  public Employee showEmployeeInfo(String firstNameEmployee, String lastNameEmployee)
  {
    for (int i=0; i<projectManagementSystem.getEmployees().size(); i++)
    {
      if (projectManagementSystem.getEmployees().get(i).getFirstName().equals(firstNameEmployee) && projectManagementSystem.getEmployees()
          .get(i).getLastName().equals(lastNameEmployee))
      {
        return projectManagementSystem.getEmployees().get(i);
      }
    }
    return null;
  }

  /**
   * @param name of the project we are looking for.
   * @return searched project
   */
  public Project viewProjectInfo(String name)
  {
    Project project = null;
    for (int i = 0; i<projectManagementSystem.getProjects().size(); i++)
    {
      if (name.equals(projectManagementSystem.getProjects().get(i).getName()))
      {
        project = projectManagementSystem.getProjects().get(i);
      }
    }
    return project;
  }

  /**
   * @param ID of the project we are looking for.
   * @return searched project
   */
  public Project viewProjectInfo(int ID)
  {
    Project project = null;
    for (int i = 0; i<projectManagementSystem.getProjects().size(); i++)
    {
      if (ID == projectManagementSystem.getProjects().get(i).getID())
      {
        project = projectManagementSystem.getProjects().get(i);
      }
    }
    return project;
  }
}
