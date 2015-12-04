package ro.ubbcluj.cs.data.convert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.ubbcluj.cs.service.ServiceException;

import javax.xml.stream.XMLStreamReader;
import java.util.HashMap;
import java.util.Map;


public abstract class AbstractXmlEntityReader<E> implements XmlEntityReader<E>
{

    private Log log = LogFactory.getLog(getClass().getSimpleName());

    String PROPERTY = "property";
    String NAME = "name";
    String ID = "id";

    @Override
    public E read(XMLStreamReader reader) throws ServiceException
    {
        Map<String, String> properties = new HashMap<>();
        String entityElementName = reader.getLocalName();
        try
        {
            properties.put(ID, reader.getAttributeValue(null, ID));
            String currentElementName = null;
            String currentPropertyName = null;
            StringBuilder currentPropetryValue = new StringBuilder();
            boolean completed = false;
            do
            {
                int eventType = reader.getEventType();
                switch (eventType)
                {
                    case XMLStreamReader.START_ELEMENT:
                        currentElementName = reader.getLocalName();
                        if (currentElementName.equals(PROPERTY))
                        {
                            currentPropertyName = reader.getAttributeValue(null, NAME);
                            currentPropetryValue.setLength(0);
                        }
                        break;
                    case XMLStreamReader.END_ELEMENT:
                        currentElementName = reader.getLocalName();
                        if (currentElementName.equals(entityElementName))
                        {
                            completed = true;
                        } else if (currentElementName.equals(PROPERTY))
                        {
                            properties.put(currentPropertyName, currentPropetryValue.toString().trim());
                        }
                        break;
                    case XMLStreamReader.CHARACTERS:
                        currentPropetryValue.append(reader.getText());
                        break;
                }
                reader.next();
            }
            while (!completed && reader.hasNext());
        }
        catch (Exception e)
        {
            log.error(entityElementName + " invalid format", e);
            throw new ServiceException(e);
        }

        return read(properties);
    }

    protected abstract E read(Map<String, String> properties);
}

