package ro.ubbcluj.cs.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.ubbcluj.cs.model.HasId;
import ro.ubbcluj.cs.service.ServiceException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractCrudRepository<T extends HasId<ID>, ID> implements IRepository<T, ID>
{
    protected Log log = LogFactory.getLog(getClass().getSimpleName());
    protected int generatedId;
    protected List<T> entities = new ArrayList<T>();

    @Override
    public int count()
    {
        int size = entities.size();
        log.info("size " + size);
        return entities.size();
    }

    @Override
    public void save(T objectToSave)
    {
        setId(objectToSave);
        T obj = findOne(objectToSave.getId());
        if (obj!=null)
            throw new RepositoryException("Duplicate entity to save!");
        entities.add(objectToSave);
        log.info("entity saved " + objectToSave);
    }

    @Override
    public T findOne(ID id)
    {
        if (id == null)
        {
            log.info("findOne - id is null");
            return null;
        }
        for(T e: entities)
        {
            if (id.equals(e.getId()))
            {
                log.info("findOne - returning " + e);
                return e;
            }
        }
        log.info("findOne - not found, id is " + id);
        return null;
    }

    @Override
    public Collection<T> findAll() throws ServiceException
    {
        log.info("----------- findAll ------------");
        return entities;
    }

    protected void setId(T obj) {}
}
