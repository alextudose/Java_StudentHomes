package ro.ubbcluj.cs.repository;

import ro.ubbcluj.cs.data.convert.AbstractXmlEntityReader;
import ro.ubbcluj.cs.data.convert.AbstractXmlEntityWriter;
import ro.ubbcluj.cs.data.convert.XmlEntityListReader;
import ro.ubbcluj.cs.data.convert.XmlEntityListWriter;
import ro.ubbcluj.cs.model.Student;
import ro.ubbcluj.cs.service.ServiceException;
import ro.ubbcluj.cs.utils.Constants;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentRepository extends AbstractCrudRepository<Student, Integer>
{
    public StudentRepository() throws ServiceException
    {
        entities = XmlEntityListReader.readList(Student.class, "students.xml", new XmlStudentReader());
        generatedId = entities.get(count() - 1).getId() + 1;
    }

    @Override
    protected void setId(Student obj)
    {
        generatedId++;
        obj.setId(generatedId);
    }

    private class XmlStudentReader extends AbstractXmlEntityReader<Student>
    {

        @Override
        protected Student read(Map<String, String> properties)
        {
            Student student = new Student();
            properties.keySet().forEach((propName) ->
            {
                String propValue = properties.get(propName);
                switch (propName)
                {
                    case "id":
                        student.setId(Integer.parseInt(propValue));
                        break;
                    case "pid":
                        student.setPID(propValue);
                        break;
                    case "name":
                        student.setName(propValue);
                        break;
                    case "surname":
                        student.setSurname(propValue);
                        break;
                    case "annualGrade":
                        student.setAnnualGrade(Double.parseDouble(propValue));
                        break;
                    default:
                        log.warn("Unknown property " + propName);
                        break;
                }
            });
            return student;
        }
    }

    private class XmlStudentWriter extends AbstractXmlEntityWriter<Student>
    {

        @Override
        protected Map<String, String> getProperties(Student student)
        {
            Map<String, String> properties = new HashMap<String,String>();
            properties.put("pid",student.getPID());
            properties.put("name",student.getName());
            properties.put("surname",student.getSurname());
            properties.put("annualGrade",student.getAnnualGrade().toString());
            return properties;
        }
    }

    public void saveAllToXml() throws ServiceException
    {
        XmlEntityListWriter.saveAllToXml(entities, Student.class, Constants.STUDENT_RESOURCE_PATH, new XmlStudentWriter());
    }
}
