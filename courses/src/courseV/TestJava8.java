package courseV;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class TestJava8
{
    public static void main(String[] args)
    {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Doina nume de cal",21));
        persons.add(new Person("Marinela Puscalau",19));
//        for (Person p : persons)
//        {
//            if (p.nume.startsWith("D"))
//            {
//                System.out.println(p);
//            }
//        }

        persons.stream()
                .filter((p) -> p.nume.startsWith("D"))
                .forEach(System.out::println);

        persons.parallelStream()
                .filter((p) -> p.nume.startsWith("D"))
                .sorted( (p1, p2) -> p1.nume.compareTo(p2.nume))
                .map(Person::getId)
                .collect( toList() )
                .forEach(System.out::println);
    }
}
