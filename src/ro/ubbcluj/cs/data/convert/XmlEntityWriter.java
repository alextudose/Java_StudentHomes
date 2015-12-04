package ro.ubbcluj.cs.data.convert;

import ro.ubbcluj.cs.service.ServiceException;

import javax.xml.stream.XMLStreamWriter;

public interface XmlEntityWriter<E>
{
    void write(E e, XMLStreamWriter writer) throws ServiceException;
}
