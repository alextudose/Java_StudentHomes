package ro.ubbcluj.cs.data.convert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.ubbcluj.cs.core.TimingLogger;
import ro.ubbcluj.cs.service.ServiceException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlEntityListReader
{
    private static Log log = LogFactory.getLog(XmlEntityListReader.class);

    public static <E> List<E> readList(Class<E> clazz, String filename, XmlEntityReader<E> entityReader) throws ServiceException
    {
        TimingLogger loadingLogger = new TimingLogger(XmlEntityListReader.class, "loading " + filename);
        XMLInputFactory factory = XMLInputFactory.newFactory();
        List<E> entities = null;
        String entityTag = clazz.getSimpleName().toLowerCase();
        String rootTag = entityTag + "s";
        try
        {
            InputStream fileInputStream = ClassLoader.getSystemResourceAsStream(filename);
            if (fileInputStream == null)
            {
                String message = filename + " file not found";
                log.error(message);
                throw new ServiceException(message);
            }
            XMLStreamReader reader = factory.createXMLStreamReader(fileInputStream);
            while (reader.hasNext())
            {
                reader.next();
                int eventType = reader.getEventType();
                String elementName = null;
                switch (eventType) {
                    case XMLStreamReader.START_ELEMENT:
                        elementName = reader.getLocalName();
                        if (elementName.equals(rootTag))
                        {
                            entities = new ArrayList<>();
                        } else if (elementName.equals(entityTag))
                        {
                            entities.add(entityReader.read(reader));
                        }
                        break;
                }
            }
            reader.close();
            //log.info(entityTag + " list loaded");
        }
        catch (XMLStreamException e)
        {
            log.error(entityTag + " file - invalid format", e);
            throw new ServiceException(e);
        }
        finally
        {
            loadingLogger.dumpToLog();
        }
        return entities;
    }
}
