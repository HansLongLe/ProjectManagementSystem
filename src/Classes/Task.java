package Classes;

import java.io.Serializable;

public class Task implements Serializable
{

    private String name;
    private int ID;
    private String description;
    private int estimatedTime;
    private String status;
    private int hoursWorked;
    private Deadline deadline;
    private Employee employee;



    public Task (String name, int ID, String description, int estimatedTime,
        String status, int hoursWorked, Deadline deadline, Employee employee)
    {
        this.name = name;
        this.ID = ID;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.status = status;
        this.hoursWorked = hoursWorked;
        this.deadline = deadline;
        this.employee = employee;
    }

    /**
     * This constructor will set the status to a default one.
     */

    public Task (String name, int ID, String description, int estimatedTime, int hoursWorked, Deadline deadline, Employee employee)
    {
        this.name = name;
        this.ID = ID;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.hoursWorked = hoursWorked;
        this.deadline = deadline;
        this.employee = null;
        status = "Not Started";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public int getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public boolean equals (Object obj)
    {
        if (!(obj instanceof Task))
        {
            return false;
        }

        Task newTask = (Task)obj;

        return name == newTask.name &&
                ID == newTask.ID &&
                description == newTask.description &&
                estimatedTime == newTask.estimatedTime &&
                status == newTask.status &&
                hoursWorked == newTask.hoursWorked &&
                deadline == newTask.deadline;
    }

    public void assignEmployee(Employee employee)
    {
        if (!(this.employee.equals(employee) ))
        {
            this.employee = employee;
        }
    }

    public String toString()
    {
        return ID + "\t\t\t" + name + "\t\t" + status + "\t\t\tAssigned to: " + employee;
    }
}
