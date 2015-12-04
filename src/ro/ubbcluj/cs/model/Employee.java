package ro.ubbcluj.cs.model;

public class Employee implements IPerson, HasId<Integer>
{
    private String pid;
    private String name;
    private String surname;
    private String job;
    private int id;

    public Employee()
    {
        
    }

    public Employee(String pid, String name, String surname, String job)
    {
        this.pid = pid;
        this.name = name;
        this.surname = surname;
        this.job = job;
        id = -1;
    }

    @Override
    public boolean equals(Object obj)
    {
        Employee employee = (Employee)obj;
        if (employee == null)
            return false;
        if (employee.pid == this.pid)
            return true;
        if ((employee.id == this.id) && (this.id != -1))
            return true;
        return false;
    }

    @Override
    public String toString()
    {
        return String.format("PID : %s Name : %s Surname : %s Job : %s", pid,
                name, surname, job);
    }

    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public void setId(Integer integer)
    {
        this.id = integer;
    }

    @Override
    public String getPID()
    {
        return pid;
    }

    @Override
    public void setPID(String pid)
    {
        this.pid = pid;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getSurname()
    {
        return surname;
    }

    @Override
    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getJob()
    {
        return job;
    }

    public void setJob(String jobGiven)
    {
        job = jobGiven;
    }
}
