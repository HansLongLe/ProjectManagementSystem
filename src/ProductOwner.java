public class ProductOwner
{
  private String firstName;
  private String lastName;

  public ProductOwner(String firstName, String lastName){
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  public String toString(){
    return "The Product owner is " + firstName + " " + lastName;
  }
  public boolean equals(Object obj){
    if(!(obj instanceof ProductOwner)){
      return false;
    }
    ProductOwner other = (ProductOwner) obj;
    return firstName.equals(other.firstName) &&
          lastName.equals(other.lastName);
  }
}
