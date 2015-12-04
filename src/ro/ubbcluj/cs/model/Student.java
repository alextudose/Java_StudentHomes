package ro.ubbcluj.cs.model;

public class Student implements IPerson, HasId<Integer>
{
    private String pid;
    private String name;
    private String surname;
    private Double annualGrade;
    private int id;

    public Student(String pid, String name, String surname, Double annualGrade)
    {
        this.pid = pid;
        this.name = name;
        this.surname = surname;
        this.annualGrade = annualGrade;
        id = -1;
    }

    public Student()
    {

    }

    @Override
    public boolean equals(Object obj)
    {
        Student other = (Student)obj;
        if (other == null)
            return false;
        if (other.pid == this.pid)
            return true;
        if ((other.id == this.id) && (this.id != -1))
            return true;
        return false;
    }

    @Override
    public String toString()
    {
        return String.format("PID : %s Name : %s Surname : %s AnnualGrade : %s", pid,
                name, surname, annualGrade);
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

    public Double getAnnualGrade()
    {
        return annualGrade;
    }

    public void setAnnualGrade(double valueToSet)
    {
        this.annualGrade = valueToSet;
    }
}
