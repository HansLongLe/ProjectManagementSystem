public class TeamMember extends Employee
{
  private static ProjectManagementSystem pms;
  public TeamMember(String firstName, String lastName, String position, double salary, int employeeID){
    super(firstName, lastName, position, salary, employeeID);
  }

   public Project viewProjectInfo(int ID)
  {
    for(int i=0; i<pms.getProjects().size(); i++){
      if(pms.getProjects().get(i).getID()==ID){
        return pms.getProjects().get(i);
      }
    }
    return null;
  }

   public Project viewProjectInfo(String name)
  {
    for(int i=0; i<pms.getProjects().size(); i++){
      if(pms.getProjects().get(i).getName().equals(name)){
        return pms.getProjects().get(i);
      }
    }
    return null;
  }

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
