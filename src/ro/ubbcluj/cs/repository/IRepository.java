package ro.ubbcluj.cs.repository;

import ro.ubbcluj.cs.service.ServiceException;

public interface IRepository<T , ID>
{
    int count();
    void save(T objectToSave);
    T findOne(ID Id);
    Iterable<T> findAll() throws ServiceException;
}