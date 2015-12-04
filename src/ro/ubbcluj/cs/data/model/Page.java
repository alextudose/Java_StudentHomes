package ro.ubbcluj.cs.data.model;

import java.util.List;

public class Page<T>
{
    private List<T> items;
    private int pageNumber;
    private int pageSize;
    private int noOfElements;

    public List<T> getItems()
    {
        return items;
    }

    public Pageable next()
    {
        Pageable pageable = null;
        if (pageNumber * pageSize < noOfElements)
        {
            pageable = new Pageable();
            pageable.setPageNumber(pageNumber + 1);
            pageable.setPageSize(pageSize);
        }
        return pageable;
    }

    public void setItems(List<T> items)
    {
        this.items = items;
    }

    public void setPageNumber(int pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public void setNoOfElements(int noOfElements)
    {
        this.noOfElements = noOfElements;
    }
}
