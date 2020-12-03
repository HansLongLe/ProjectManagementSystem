import java.util.ArrayList;

public  class ProjectManagementSystem
{
  private ArrayList<Employee> employees;
  private ArrayList<Project> projects;

  public ProjectManagementSystem(){
    employees = new ArrayList<Employee>();
    projects = new ArrayList<Project>();
  }
  /*
  adding projects to project managemnt system
   */
  public void addProject(Project project){
    projects.add(project);
  }
  /*
  removing proh=jects from project management system
   */
  public void removeProject(Project project){
    projects.remove(project);
  }
  /*
  adding employees to project management system
   */
  public void addEmployee(Employee employee){
    employees.add(employee);
  }
  /*
  removing emplpoyees from project management system
   */
  public void removeEmployee(Employee employee){
    employees.remove(employee);
  }
  /*
  returning an array list of projects
   */
  public ArrayList<Project> getProjects(){
    return projects;
  }
  /*
  returning an array list of employees
   */
  public ArrayList<Employee> getEmployees(){
    return employees;
  }
}
