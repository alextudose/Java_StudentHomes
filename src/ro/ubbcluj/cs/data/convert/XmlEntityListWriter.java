package ro.ubbcluj.cs.data.convert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.ubbcluj.cs.service.ServiceException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.util.List;

public class XmlEntityListWriter
{
    private static Log log = LogFactory.getLog(XmlEntityListReader.class);

    public static <T> void saveAllToXml(List<T> entities, Class<T> employeeClass,
                                        String fileName, XmlEntityWriter<T> entityWriter) throws ServiceException
    {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = null;
        try
        {
            writer = outputFactory.createXMLStreamWriter(new FileWriter(fileName));
            writer.writeStartDocument();
            writer.writeStartElement(employeeClass.getSimpleName().toLowerCase() + "s");
            for(T entity : entities)
            {
                entityWriter.write(entity,writer);
            }

            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
        }
        catch (Exception e)
        {
            log.warn("Error : ",e);
            throw new ServiceException(e);
        }
    }
}
