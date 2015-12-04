package ro.ubbcluj.cs.service;

import ro.ubbcluj.cs.model.IPerson;
import ro.ubbcluj.cs.model.Student;

public class StudentValidator extends PersonValidator
{
    @Override
    public String validate(IPerson persoana)
    {
        String errors = super.validate(persoana);
        Student student = (Student)persoana;
        if ((student.getAnnualGrade()<1)||(student.getAnnualGrade()>10))
            errors = errors.concat(String.format("Media anuala a studentului %s %s este in intervalul [1,10]! %s",
                    persoana.getName(),
                    persoana.getSurname(),
                    System.lineSeparator()));
        return errors;
    }

}
