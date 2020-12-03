import java.util.ArrayList;

public  class ProjectManagementSystem
{
  private ArrayList<Employee> employees;
  private ArrayList<Project> projects;

  public ProjectManagementSystem(){
    employees = new ArrayList<Employee>();
    projects = new ArrayList<Project>();
  }
  public void addProject(Project project){
    projects.add(project);
  }
  public void removeProject(Project project){
    projects.remove(project);
  }
  public void addEmployee(Employee employee){
    employees.add(employee);
  }
  public void removeEmployee(Employee employee){
    employees.remove(employee);
  }
  public ArrayList<Project> getProjects(){
    return projects;
  }
  public ArrayList<Employee> getEmployees(){
    return employees;
  }
}
