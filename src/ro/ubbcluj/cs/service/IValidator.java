package ro.ubbcluj.cs.service;

public interface IValidator<T>
{
    String validate(T objectToValidate);
}
