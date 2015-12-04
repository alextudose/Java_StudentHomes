package ro.ubbcluj.cs.model;

public class StudentHome implements HasId<Integer>
{
    private String name;
    private int roomSize;
    private int numberOfRooms;
    private double tax;
    private int id;

    public StudentHome(String name, int roomSize, int numberOfRooms, double tax )
    {
        this.name = name;
        this.roomSize = roomSize;
        this.numberOfRooms = numberOfRooms;
        this.tax = tax;
        id = -1;
    }

    public StudentHome()
    {

    }

    @Override
    public boolean equals(Object o)
    {
        StudentHome studentHome = (StudentHome)o;
        if (studentHome ==null)
            return false;
        if ((studentHome.id == this.id) && (this.id != -1))
            return true;
        if (studentHome.name == this.name)
            return true;
        return false;
    }

    @Override
    public String toString()
    {
        return String.format("Name : %s RoomSize : %s NumberOfRooms : %s Tax : %s RON", name,
                roomSize, numberOfRooms, tax);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getRoomSize()
    {
        return roomSize;
    }

    public void setRoomSize(int roomSize)
    {
        this.roomSize = roomSize;
    }

    public int getNumberOfRooms()
    {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms)
    {
        this.numberOfRooms = numberOfRooms;
    }

    public double getTax()
    {
        return tax;
    }

    public void setTax(double tax)
    {
        this.tax = tax;
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
}
