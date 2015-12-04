package ro.ubbcluj.cs.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.ubbcluj.cs.data.model.Page;
import ro.ubbcluj.cs.data.model.Pageable;
import ro.ubbcluj.cs.model.Employee;
import ro.ubbcluj.cs.model.IPerson;
import ro.ubbcluj.cs.model.Student;
import ro.ubbcluj.cs.model.StudentHome;
import ro.ubbcluj.cs.repository.*;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class AppManager
{
    private StudentHomeRepository studentHomeRepository;
    private StudentHomeValidator studentHomeValidator;
    private EmployeeRepository employeeRepository;
    private PersonValidator employeeValidator;
    private StudentRepository studentRepository;
    private StudentValidator studentValidator;

    static Log log = LogFactory.getLog(AppManager.class.getSimpleName());
    private ExecutorService executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void saveAllToXml()
    {
        try
        {
            studentHomeRepository.saveAllToXml();
            studentRepository.saveAllToXml();
            employeeRepository.saveAllToXml();
        } catch (ServiceException e)
        {
            log.warn("saveAllToXml : ",e);
        }
    }

    public void addStudentHome(String nume, int numarPersoaneInCamera, int numarCamere, int regie)
    {
        StudentHome studentHome = new StudentHome(nume, numarPersoaneInCamera, numarCamere, regie);
        String errors = studentHomeValidator.validate(studentHome);
        if (errors != "")
            throw new ValidationException(errors);
        studentHomeRepository.save(studentHome);
    }

    public Collection<StudentHome> getAllStudentHomes() throws ServiceException
    {
        Future<Collection<StudentHome>> studentHomesFutures = executorService.submit(() ->
                studentHomeRepository.findAll());
        try
        {
            return studentHomesFutures.get();
        }
        catch (InterruptedException e)
        {
            log.warn("getAllStudentHomes - interrupted",e);
        }
        catch (ExecutionException e)
        {
            log.warn("getAllStudentHomes - execution failed",e);
        }
        return null;
    }

    public Page<StudentHome> getStudentHomePage(Pageable pageable) throws ServiceException
    {
        Collection<StudentHome> studentHomes = getAllStudentHomes();
        Page<StudentHome> page = new Page<>();

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

        List<StudentHome> pagedStudentHomes = studentHomes.stream()
                .sorted((n1, n2) -> n1.getName().compareTo(n2.getName()))
                .skip((pageNumber - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        page.setNoOfElements(studentHomes.size());
        page.setItems(pagedStudentHomes);
        return page;
    }

    public int countStudentHomes()
    {
        return studentHomeRepository.count();
    }

    public void addEmployee(String CNP, String nume, String prenume, String post)
    {
        Employee employee = new Employee(CNP,nume,prenume,post);
        String errors = employeeValidator.validate(employee);
        if (errors != "")
            throw new ValidationException(errors);
        employeeRepository.save(employee);
    }

    public Collection<Employee> getAllEmployees() throws ServiceException
    {
        Future<Collection<Employee>> employeeFutures = executorService.submit(() ->
                employeeRepository.findAll());
        try
        {
            return employeeFutures.get();
        }
        catch (InterruptedException e)
        {
            log.warn("getAllEmployees - interrupted",e);
        }
        catch (ExecutionException e)
        {
            log.warn("getAllEmployees - execution failed",e);
        }
        return null;
    }

    public Page<Employee> getEmployeePage(Pageable pageable) throws ServiceException
    {
        Collection<Employee> employees = getAllEmployees();
        Page<Employee> page = new Page<>();

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

        List<Employee> pagedStudentHomes = employees.stream()
                .sorted((n1, n2) -> n1.getName().compareTo(n2.getName()))
                .skip((pageNumber - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        page.setNoOfElements(employees.size());
        page.setItems(pagedStudentHomes);
        return page;
    }

    public int countEmployees()
    {
        return employeeRepository.count();
    }

    public void addStudent(String CNP, String nume, String prenume, double mediaAnuala)
    {
        Student student = new Student(CNP, nume, prenume, mediaAnuala);
        String errors = studentValidator.validate(student);
        if (errors != "")
            throw new ValidationException(errors);
        studentRepository.save(student);
    }

    public Collection<Student> getAllStudents() throws ServiceException
    {
        Future<Collection<Student>> studentFutures = executorService.submit(() ->
                studentRepository.findAll());
        try
        {
            return studentFutures.get();
        }
        catch (InterruptedException e)
        {
            log.warn("getAllStudents - interrupted",e);
        }
        catch (ExecutionException e)
        {
            log.warn("getAllStudents - execution failed",e);
        }
        return null;
    }

    public Page<Student> getStudentPage(Pageable pageable) throws ServiceException
    {
        Collection<Student> students = getAllStudents();
        Page<Student> page = new Page<>();

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

        List<Student> pagedStudentHomes = students.stream()
                .sorted((n1, n2) -> n1.getName().compareTo(n2.getName()))
                .skip((pageNumber - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        page.setNoOfElements(students.size());
        page.setItems(pagedStudentHomes);
        return page;
    }

    public int countStudents()
    {
        return studentRepository.count();
    }
}
