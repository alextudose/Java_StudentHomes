package ro.ubbcluj.cs.service;

import ro.ubbcluj.cs.model.IPerson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonValidator implements IValidator<IPerson>
{
    @Override
    public String validate(IPerson persoana)
    {
        String errors = "";
        if (persoana.getPID().length() != 13)
            errors = errors.concat(String.format("CNP-ul persoanei %s %s nu este format din 13 cifre! %s",
                    persoana.getName(),
                    persoana.getSurname(),
                    System.lineSeparator()));

        char[] chars = persoana.getPID().toCharArray();
        List<Character> digits = new ArrayList<Character>();
        digits = Arrays.asList('0','1', '2', '3', '4', '5', '6', '7', '8', '9');
        for (char cifra : chars)
        {
            if (!digits.contains(cifra))
            {
                errors = errors.concat(String.format("CNP-ul persoanei %s %s nu poate contine %s! %s",
                        persoana.getName(),
                        persoana.getSurname(),
                        cifra,
                        System.lineSeparator()));
            }
        }

        return errors;
    }
}
