import java.util.ArrayList;

public class Project
{
  private String name;
  private int ID;
  private String description;
  private int estimatedTime;
  private String status;
  private int hoursWorked;
  private ArrayList<Requirement> requirements;
  private Deadline deadline;
  private ProductOwner productOwner;
  /*
  Constructor for the Project objects
   */
  public Project (String name, int ID, String description, int estimatedTime, String status, int hoursWorked, Deadline deadline, ProductOwner productOwner){
    this.name = name;
    this.ID = ID;
    this.description = description;
    this.estimatedTime = estimatedTime;
    this.status = status;
    this.hoursWorked = hoursWorked;
    this.deadline = deadline;
    requirements = new ArrayList<Requirement>();
    this.productOwner = productOwner;
  }

  public void addRequirement(Requirement requirement){
    requirements.add(requirement);
  }
  public void removeRequirement(Requirement requirement){
    requirements.remove(requirement);
  }
  public ProductOwner getProductOwner(){
    return productOwner;
  }
  public void setProductOwner(ProductOwner productOwner){
    this.productOwner = productOwner;
  }
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getID()
  {
    return ID;
  }

  public void setID(int ID)
  {
    this.ID = ID;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public int getEstimatedTime()
  {
    return estimatedTime;
  }

  public void setEstimatedTime(int estimatedTime)
  {
    this.estimatedTime = estimatedTime;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }


/*
The hours worked on a project are a summ of the hours worked on the requirements
 */
 public int getHoursWorked(){

  for(int i=0; i<requirements.size(); i++){
    hoursWorked+= requirements.get(i).hoursWorkedOnRequirement();
  }
  return hoursWorked;
 }

  public void setHoursWorked(int hoursWorked)
  {
    this.hoursWorked = hoursWorked;
  }

  public ArrayList<Requirement> getRequirements()
  {
    return requirements;
  }

  public void setRequirements(ArrayList<Requirement> requirements)
  {
    this.requirements = requirements;
  }

  public Deadline getDeadline()
  {
    return deadline;
  }

  public void setDeadline(Deadline deadline)
  {
    this.deadline = deadline;
  }



/*
the equals method for the Project objects
 */
  public boolean equals(Object obj){
    if(!(obj instanceof Project)){
      return false;
    }
    Project other = (Project)obj;
    return name.equals(other.name) &&
            ID==other.ID &&
        description.equals(other.description) &&
        estimatedTime==other.estimatedTime &&
        status.equals(other.status) &&
        hoursWorked==other.hoursWorked &&
        requirements.equals(other.requirements) &&
        productOwner.equals(other.productOwner)&&
        deadline.equals(other.deadline);

  }
  public String toString(){
    return "The project info:\n" + name + "\nID:" + ID +"\nDescription: " + description +"\nEstimated time: " + estimatedTime +"\nStatus: " + status +"\nHours worked " + getHoursWorked() +"\nDeadline: " + deadline + "\nOwned by: " + productOwner;
  }
}
