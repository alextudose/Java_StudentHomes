package ro.ubbcluj.cs.service;

public class ServiceException extends Exception
{
    public ServiceException(Exception e)
    {
        super(e);
    }

    public ServiceException(String message)
    {
        super(message);
    }
}
