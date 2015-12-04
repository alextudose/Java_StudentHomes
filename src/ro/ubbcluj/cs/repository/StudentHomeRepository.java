package ro.ubbcluj.cs.repository;

import ro.ubbcluj.cs.data.convert.AbstractXmlEntityReader;
import ro.ubbcluj.cs.data.convert.AbstractXmlEntityWriter;
import ro.ubbcluj.cs.data.convert.XmlEntityListReader;
import ro.ubbcluj.cs.data.convert.XmlEntityListWriter;
import ro.ubbcluj.cs.model.StudentHome;
import ro.ubbcluj.cs.service.ServiceException;
import ro.ubbcluj.cs.utils.Constants;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentHomeRepository extends AbstractCrudRepository<StudentHome, Integer>
{
    public StudentHomeRepository() throws ServiceException
    {
        entities = XmlEntityListReader.readList(StudentHome.class, "studentHomes.xml", new XmlStudentHomeReader());
        generatedId = entities.get(count() - 1).getId() + 1;
    }

    @Override
    protected void setId(StudentHome obj)
    {
        generatedId++;
        obj.setId(generatedId);
    }

    private class XmlStudentHomeReader extends AbstractXmlEntityReader<StudentHome>
    {

        @Override
        protected StudentHome read(Map<String, String> properties)
        {
            StudentHome studentHome = new StudentHome();
            properties.keySet().forEach((propName) ->
            {
                String propValue = properties.get(propName);
                switch (propName)
                {
                    case "id":
                        studentHome.setId(Integer.parseInt(propValue));
                        break;
                    case "name":
                        studentHome.setName(propValue);
                        break;
                    case "roomSize":
                        studentHome.setRoomSize(Integer.parseInt(propValue));
                        break;
                    case "numberOfRooms":
                        studentHome.setNumberOfRooms(Integer.parseInt(propValue));
                        break;
                    case "tax":
                        studentHome.setTax(Double.parseDouble(propValue));
                        break;
                    default:
                        log.warn("Unknown property " + propName);
                        break;
                }
            });
            return studentHome;
        }
    }

    private class XmlStudentHomeWriter extends AbstractXmlEntityWriter<StudentHome>
    {

        @Override
        protected Map<String, String> getProperties(StudentHome studentHome)
        {
            Map<String, String> properties = new HashMap<String,String>();
            properties.put("name",studentHome.getName());
            properties.put("roomSize",String.valueOf(studentHome.getRoomSize()));
            properties.put("numberOfRooms",String.valueOf(studentHome.getNumberOfRooms()));
            properties.put("tax",String.valueOf(studentHome.getTax()));
            return properties;
        }
    }

    @Override
    public Collection<StudentHome> findAll() throws ServiceException
    {
        return super.findAll();
    }

    public void saveAllToXml() throws ServiceException
    {
        XmlEntityListWriter.saveAllToXml(entities, StudentHome.class, Constants.STUDENT_HOMES_RESOURCE_PATH, new XmlStudentHomeWriter());
    }
}
