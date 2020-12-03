public class ScrumMaster extends Employee
{
  private static ProjectManagementSystem projectManagementSystem;

    public ScrumMaster(String firstName, String lastName, String position, double salary, int employeeID)
    {
      super(firstName, lastName, position, salary, employeeID);
    }

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
