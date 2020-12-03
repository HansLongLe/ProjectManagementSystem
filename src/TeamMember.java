public class TeamMember extends Employee
{
  private static ProjectManagementSystem pms;
  public TeamMember(String firstName, String lastName, String position, double salary, int employeeID){
    super(firstName, lastName, position, salary, employeeID);
  }

  /**
   *
   * @param ID of the project we are looking for.
   * @return an object of type Project
   */
   public Project viewProjectInfo(int ID)
  {
    for(int i=0; i<pms.getProjects().size(); i++){
      if(pms.getProjects().get(i).getID()==ID){
        return pms.getProjects().get(i);
      }
    }
    return null;
  }

  /**
   *
   * @param name of the project we are looking for.
   * @return an object of type Project
   */
   public Project viewProjectInfo(String name)
  {
    for(int i=0; i<pms.getProjects().size(); i++){
      if(pms.getProjects().get(i).getName().equals(name)){
        return pms.getProjects().get(i);
      }
    }
    return null;
  }

  /**
   *
   * @param firstName to check the fisrt name of an employee int the array list
   * @param lastName to check the last name of an employee in the array list
   * @return an object of type Employee
   */
  public Employee showEmployeeInfo(String firstName, String lastName)
  {
   for(int i=0; i<pms.getEmployees().size(); i++){
     if(pms.getEmployees().get(i).getFirstName().equals(firstName) && pms.getEmployees().get(i).getLastName().equals(lastName)){
       return pms.getEmployees().get(i);
     }
   }
   return null;
  }
}
