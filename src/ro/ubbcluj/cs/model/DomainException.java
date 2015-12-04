package ro.ubbcluj.cs.model;

public class DomainException extends RuntimeException
{
    public DomainException(String message)
    {
        super(message);
    }

    public DomainException(Exception e)
    {
        super(e);
    }
}
