package ro.ubbcluj.cs.service;

import ro.ubbcluj.cs.model.StudentHome;

public class StudentHomeValidator implements IValidator<StudentHome>
{
    @Override
    public String validate(StudentHome studentHome)
    {
        String errors = "";
        if (studentHome.getRoomSize() < 0)
            errors = errors.concat(String.format("Numarul persoanelor in camera nu poate fi negativ! %s", System.lineSeparator()));

        if (studentHome.getNumberOfRooms() < 0)
            errors = errors.concat(String.format("Numarul camerelor de studentHome nu poate fi negativ! %s", System.lineSeparator()));

        if (studentHome.getTax() < 0)
            errors = errors.concat(String.format("Regia de studentHome nu poate fi negativa! %s", System.lineSeparator()));

        return errors;
    }
}
