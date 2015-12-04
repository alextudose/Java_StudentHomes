package ro.ubbcluj.cs.repository;

import ro.ubbcluj.cs.data.convert.AbstractXmlEntityReader;
import ro.ubbcluj.cs.data.convert.AbstractXmlEntityWriter;
import ro.ubbcluj.cs.data.convert.XmlEntityListReader;
import ro.ubbcluj.cs.data.convert.XmlEntityListWriter;
import ro.ubbcluj.cs.model.Employee;
import ro.ubbcluj.cs.model.StudentHome;
import ro.ubbcluj.cs.service.ServiceException;
import ro.ubbcluj.cs.utils.Constants;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EmployeeRepository extends AbstractCrudRepository<Employee, Integer>
{
    public EmployeeRepository() throws ServiceException
    {
        entities = XmlEntityListReader.readList(Employee.class, "employees.xml", new XmlEmployeeReader());
        generatedId = entities.get(count() - 1).getId() + 1;
    }

    @Override
    protected void setId(Employee obj)
    {
        generatedId++;
        obj.setId(generatedId);
    }

    private class XmlEmployeeReader extends AbstractXmlEntityReader<Employee>
    {

        @Override
        protected Employee read(Map<String, String> properties)
        {
            Employee employee = new Employee();
            properties.keySet().forEach((propName) ->
            {
                String propValue = properties.get(propName);
                switch (propName)
                {
                    case "id":
                        employee.setId(Integer.parseInt(propValue));
                        break;
                    case "pid":
                        employee.setPID(propValue);
                        break;
                    case "name":
                        employee.setName(propValue);
                        break;
                    case "surname":
                        employee.setSurname(propValue);
                        break;
                    case "job":
                        employee.setJob(propValue);
                        break;
                    default:
                        log.warn("Unknown property " + propName);
                        break;
                }
            });
            return employee;
        }
    }

    private class XmlEmployeeWriter extends AbstractXmlEntityWriter<Employee>
    {
        @Override
        protected Map<String, String> getProperties(Employee employee)
        {
            Map<String, String> properties = new HashMap<String,String>();
            properties.put("pid",employee.getPID());
            properties.put("name",employee.getName());
            properties.put("surname",employee.getSurname());
            properties.put("job",employee.getJob());
            return properties;
        }
    }

    public void saveAllToXml() throws ServiceException
    {
        XmlEntityListWriter.saveAllToXml(entities, Employee.class, Constants.EMPLOYEE_RESOURCE_PATH, new XmlEmployeeWriter());
    }
}
