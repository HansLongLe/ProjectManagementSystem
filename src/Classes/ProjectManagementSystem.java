package Classes;

import Classes.Employee;
import Classes.Project;

import java.util.ArrayList;

public  class ProjectManagementSystem
{
  private ArrayList<Employee> employees;
  private ArrayList<Project> projects;

  public ProjectManagementSystem(){
    employees = new ArrayList<Employee>();
    projects = new ArrayList<Project>();
  }

  /**
   *
   * @param project that is added to the array list projects
   */
  public void addProject(Project project){
    projects.add(project);
  }

  /**
   *
   * @param project that is removed from the array list projects
   */
  public void removeProject(Project project){
    projects.remove(project);
  }

  /**
   *
   * @param employee that is added to the emploees aray list
   */
  public void addEmployee(Employee employee)
  {
    employees.add(employee);
  }

  /**
   *
   * @param employee that is removed from the array list of emploees
   */
  public void removeEmployee(Employee employee){
    employees.remove(employee);
  }

  /**
   *
   * @return array list of projects
   */
  public ArrayList<Project> getProjects(){
    return projects;
  }

  /**
   *
   * @return array list of employees
   */
  public ArrayList<Employee> getEmployees(){
    return employees;
  }

}
