package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Requirement implements Serializable
{
    private String name;
    private int ID;
    private String description;
    private int estimatedTime;
    private String status;
    private int priority;
    private int hoursWorked;
    private Deadline deadline;
    ArrayList<Task> tasks;

    /**
     * This is the constructor.
     */
    public Requirement(String name, int ID, String description, int estimatedTime, int priority, Deadline deadline, String status)
    {
        this.name = name;
        this.ID = ID;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.priority = priority;
        this.deadline = deadline;
        tasks = new ArrayList<Task>();
        this.status = status;
        hoursWorked = 0;
    }
    public void addTask(Task task)
    {
        tasks.add(task);
    }


    public ArrayList<Task> getTasks()
    {
        return tasks;
    }

    /**
     * Getting the hours from each task to see the time worked on the requirement.
     * @return
     */
    public int hoursWorkedOnRequirement()
    {
        int hoursWorkedInTotal = 0;
        for (int i =0; i<tasks.size(); i++)
        {
            hoursWorkedInTotal += tasks.get(i).getHoursWorked();
        }
        return hoursWorkedInTotal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * @param ID Checking if the ID is not less or more then 4 digits.
     */
    public void setID(int ID) {
        if (ID > 999 && ID <=9999 )
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * @param estimatedTime Checking if the time isn't less then 0.
     */
    public void setEstimatedTime(int estimatedTime) {
        if (estimatedTime >0)
        {
            this.estimatedTime = estimatedTime;
        }
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setPriority(int priority) {
            this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Requirement))
        {
            return false;
        }
        else {
            Requirement requirement = (Requirement)obj;
            return (requirement.tasks.equals(tasks) && requirement.name == name && requirement.ID == ID && requirement.description == description) || requirement.name == name || requirement.ID == ID || requirement.description == description || requirement.tasks.equals(tasks);
        }
    }
    public String toString() {
        return priority + "\t\t" + ID + "\t\t" + name + "\t\t" + status;
    }

    public void setDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }
}
