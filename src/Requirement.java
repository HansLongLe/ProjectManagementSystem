import java.util.ArrayList;

public class Requirement
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
     * @param task The requirement has to have at least one task.
     */
    public Requirement(String name, int ID,String description, int estimatedTime, int priority, Deadline deadline, Task task)
    {
        this.name = name;
        this.ID = ID;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.priority = priority;
        this.deadline = deadline;
        tasks = new ArrayList<Task>();
        tasks.add(task);
        status = "Started";
        hoursWorked = 0;
    }
    public void addTask(Task task)
    {
        tasks.add(task);
    }
    public void removeTask(Task task)
    {
        tasks.remove(task);
    }

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
        return "Requirement{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                ", description='" + description + '\'' +
                ", estimatedTime=" + estimatedTime +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", hoursWorked=" + hoursWorked +
                ", deadline=" + deadline +
                ", tasks=" + tasks +
                '}';
    }

    public void setDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    public Deadline getDeadline() {
        return deadline;
    }

}
