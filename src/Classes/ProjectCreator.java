package Classes;

import Classes.Employee;
import Classes.Project;

import java.io.Serializable;

public class ProjectCreator extends Employee implements Serializable
{
    private static ProjectManagementSystem projectManagementSystem;
    private Project project;
    private Requirement requirement;

    /**
     *
     * all parameters to the super class constructor
     */
    public ProjectCreator(String firstName, String lastName, String position, double salary, int employeeID)
    {
        super(firstName, lastName, position, salary, employeeID);
    }
    public void createProject(Project project)
    {
        this.project = project;
        projectManagementSystem.addProject(project);
    }
    public void createRequirements(Requirement requirement)
    {
        project.addRequirement(requirement);
        this.requirement = requirement;
    }
    public void createTask(Task task)
    {
        requirement.addTask(task);
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @return Classes.Employee information (if he/she is in the system)
     */
    public Employee showEmployeeInfo(String firstName, String lastName)
    {
        Employee employee =null;
        for (int i = 0; i<projectManagementSystem.getEmployees().size(); i++)
        {
            if(firstName.equals(projectManagementSystem.getEmployees().get(i).getFirstName()) && lastName.equals(projectManagementSystem.getEmployees().get(i).getLastName()))
            {
                employee = projectManagementSystem.getEmployees().get(i);
            }
        }
        return employee;
    }

    /**
     *
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
     *
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
