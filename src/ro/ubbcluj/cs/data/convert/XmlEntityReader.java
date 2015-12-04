package ro.ubbcluj.cs.data.convert;

import ro.ubbcluj.cs.service.ServiceException;

import javax.xml.stream.XMLStreamReader;

public interface XmlEntityReader<E>
{
    E read(XMLStreamReader reader) throws ServiceException;
}
