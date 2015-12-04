package ro.ubbcluj.cs.data.convert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.ubbcluj.cs.model.HasId;
import ro.ubbcluj.cs.service.ServiceException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Map;

public abstract class AbstractXmlEntityWriter<T extends HasId<Integer>> implements XmlEntityWriter<T>
{
    private Log log = LogFactory.getLog(getClass().getSimpleName());

    @Override
    public void  write(T t, XMLStreamWriter writer) throws ServiceException
    {
        try
        {
            writer.writeDTD("\n");
            writer.writeDTD("\t");

            writer.writeStartElement(t.getClass().getSimpleName().toLowerCase());
            Map<String, String> properties = getProperties(t);
            String id = t.getId().toString();
            writer.writeAttribute("id",id);

            writer.writeDTD("\n");
            for(String property : properties.keySet())
            {
                writer.writeDTD("\t");
                writer.writeDTD("\t");

                writer.writeStartElement("property");
                writer.writeAttribute("name",property);
                writer.writeCharacters(properties.get(property));
                writer.writeEndElement();
                writer.writeDTD("\n");
            }
            writer.writeEndElement();
        }
        catch (XMLStreamException e)
        {
            log.warn(e);
            throw new ServiceException(e);
        }
    }

    protected abstract Map<String, String> getProperties(T t);
}
