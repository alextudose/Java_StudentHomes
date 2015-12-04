package ro.ubbcluj.cs.ui;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.ubbcluj.cs.data.model.Page;
import ro.ubbcluj.cs.data.model.Pageable;
import ro.ubbcluj.cs.model.Student;
import ro.ubbcluj.cs.model.StudentHome;
import ro.ubbcluj.cs.repository.RepositoryException;
import ro.ubbcluj.cs.service.AppManager;
import ro.ubbcluj.cs.service.ServiceException;
import ro.ubbcluj.cs.service.ValidationException;
import ro.ubbcluj.cs.utils.Constants;

import java.util.Scanner;

public class UserInterface
{
    Scanner scanner = new Scanner(System.in);


    static Log log = LogFactory.getLog(StudentHomesApp.class.getSimpleName());
    private AppManager appManager;

    public void setAppManager(AppManager appManager)
    {
        this.appManager = appManager;
    }

    public void Run()
    {
        while (true)
        {
            System.out.println("0 - Iesire");
            System.out.println("1 - Adauga camin");
            System.out.println("2 - Afiseaza lista de camine");
            System.out.println("3 - Adauga student");
            System.out.println("4 - Afiseaza lista de studenti");
            System.out.println("5 - Adauga angajat");
            System.out.println("6 - Afiseaza lista de angajati");
            System.out.print("Comanda : ");
            String option = scanner.next();
            try
            {
                switch (option)
                {
                    case "0":
                        appManager.saveAllToXml();
                        log.info("----------- Saved all to XMLs, exiting application ---------------");
                        return;
                    case "1":
                        AddStudentHome();
                        break;
                    case "2":
                        ShowStudentHomes();
                        break;
                    case "3":
                        AddStudent();
                        break;
                    case "4":
                        ShowStudenti();
                        break;
                    case "5":
                        AddEmployee();
                        break;
                    case "6":
                        ShowEmployees();
                        break;
                    default:
                        log.info("Optiune invalida! Va rog reintroduceti comanda :");
                        break;
                }
            }
            catch (Exception e)
            {
                log.warn("main menu error : ", e);
            }
        }


    }

    private void ShowEmployees()
    {
        try
        {
            int numberOfEmployees = appManager.getAllEmployees().size();
            int i = 0;
            do
            {
                i++;
                Pageable pageable = new Pageable();
                pageable.setPageNumber(i);
                pageable.setPageSize(Constants.PAGE_SIZE);
                Page page = appManager.getEmployeePage(pageable);

                String pageTitleToString = String.format("----------------------  PAGE %s : ------------------------------",i);
                System.out.println(pageTitleToString);
                page.getItems().forEach(System.out::println);
            }
            while (i * Constants.PAGE_SIZE < numberOfEmployees);
        }
        catch (ServiceException e)
        {
            log.warn("showStudents - ServiceException ",e);
        }
    }

    private void AddEmployee()
    {
        try
        {
            log.info("add employee - started");

            String CNP="", nume="", prenume="", post="";
            System.out.print("CNP : ");
            CNP = scanner.next();
            System.out.print("Nume angajat : ");
            nume = scanner.next();
            System.out.print("Prenume angajat : ");
            prenume = scanner.next();
            System.out.print("Post : ");
            post = scanner.next();

            log.info("add employee - validating");
            appManager.addEmployee(CNP, nume, prenume, post);
        }
        catch (ValidationException e)
        {
            log.warn("add employee - invalid employee, please provide valid data", e);
            AddEmployee();
        }
        catch (RepositoryException e)
        {
            log.warn("add employee - duplicate employee, please provide valid data", e);
            AddEmployee();
        }
    }

    private void ShowStudenti()
    {
        try
        {
            int numberOfStudents = appManager.getAllStudents().size();
            int i = 0;
            do
            {
                i++;
                Pageable pageable = new Pageable();
                pageable.setPageNumber(i);
                pageable.setPageSize(Constants.PAGE_SIZE);
                Page page = appManager.getStudentPage(pageable);

                String pageToString = String.format("----------------------  PAGE %s : ------------------------------",i);
                System.out.println(pageToString);
                page.getItems().forEach(System.out::println);
            }
            while (i*Constants.PAGE_SIZE < numberOfStudents);
        }
        catch (ServiceException e)
        {
            log.warn("showStudents - ServiceException ",e);
        }
    }

    private void AddStudent()
    {
        try
        {
            log.info("add student - started");
            String CNP="", nume="", prenume="";
            double mediaAnuala = 0;

            System.out.print("CNP : ");
            CNP = scanner.next();
            System.out.print("Nume student : ");
            nume = scanner.next();
            System.out.print("Prenume student : ");
            prenume = scanner.next();
            System.out.print("Media anuala : ");
            mediaAnuala = Double.parseDouble(scanner.next());

            log.info("add student - validating");
            appManager.addStudent(CNP, nume, prenume, mediaAnuala);
        }
        catch (NumberFormatException e)
        {
            log.warn("add student - please introduce a real number", e);
            AddStudent();
        }
        catch (ValidationException e)
        {
            log.warn("add student - please introduce valid data", e);
            AddStudent();
        }
        catch (RepositoryException e)
        {
            log.warn("add student - duplicate student", e);
            AddStudent();
        }
    }

    private void AddStudentHome()
    {
        try
        {
            log.info("add student home - started");
            String nume="";
            int numarPersoaneInCamera=0, numarCamere=0, regie=0;

            System.out.print("Numar persoane in camera : ");
            numarPersoaneInCamera = scanner.nextInt();
            System.out.print("Numar camere : ");
            numarCamere = scanner.nextInt();
            System.out.print("Regie : ");
            regie = scanner.nextInt();
            System.out.print("Nume camin : ");
            nume = scanner.next();

            log.info("add student home - validating");
            appManager.addStudentHome(nume, numarPersoaneInCamera, numarCamere, regie);
        }
        catch (ValidationException e)
        {
            log.warn("add student home - please introduce valid data : ", e);
            AddStudentHome();
        }
        catch (NumberFormatException e)
        {
            log.warn("add student home - please introduce a real number", e);
            AddStudentHome();
        }
        catch (RepositoryException e)
        {
            log.warn("add student home - duplicate student", e);
            AddStudentHome();
        }

    }

    private void ShowStudentHomes()
    {
        try
        {
            int numberOfStudentHomes = appManager.getAllStudentHomes().size();
            int i = 0;
            do
            {
                i++;
                Pageable pageable = new Pageable();
                pageable.setPageNumber(i);
                pageable.setPageSize(Constants.PAGE_SIZE);
                Page page = appManager.getStudentHomePage(pageable);

                String pageToString = String.format("----------------------  PAGE %s : ------------------------------",i);
                System.out.println(pageToString);
                page.getItems().forEach(System.out::println);
            }
            while (i*Constants.PAGE_SIZE < numberOfStudentHomes);
        }
        catch (ServiceException e)
        {
            log.warn("showStudentHomes - ServiceException ",e);
        }

    }
}
