package courseV;

public class Person implements Comparable<Person>
{
    public String nume;
    public int age;

    public Person(String nume, int age)
    {
        this.nume = nume;
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "Nume='" + nume + '\'' +
                ", Age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o)
    {
        return 0;
    }

    public static Object getId(Person person)
    {
        return person.age;
    }
}
