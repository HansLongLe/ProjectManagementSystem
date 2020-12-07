package Classes;

public class Deadline
{
    private int day;
    private int month;
    private int year;

    public Deadline (int day, int month, int year)
    {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    /**
     * Changes the day month and year with given argument.
     * @param obj this is the deadline that we are replacing the old one with.
     */

    public void changeDeadline (Deadline obj)
    {
        this.day = obj.day;
        this.month = obj.month;
        this.year = obj.year;
    }

    public boolean equals (Object obj)
    {
        if (!(obj instanceof Deadline))
        {
            return false;
        }

        Deadline newDeadline = (Deadline)obj;

        return day == newDeadline.day &&
                month == newDeadline.month &&
                year == newDeadline.year;
    }

    public String toString()
    {
        return "The deadline is: " + day + ":" + month + ":" + year;
    }
}
