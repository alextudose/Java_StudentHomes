package ro.ubbcluj.cs.ui;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ro.ubbcluj.cs.core.AppContext;

public class StudentHomesApp
{
    static Log log = LogFactory.getLog(StudentHomesApp.class.getSimpleName());

    public static void main(String [] args)
    {
        try
        {
            log.info("Creating app context");
            AppContext appContext = new AppContext("appContext.xml");
            log.info("App context created");
            UserInterface appUI = appContext.getBean(UserInterface.class);
            appUI.Run();
        }
        catch (Exception e)
        {
            System.out.println("The App has encountered an error. Sorry.");
            log.fatal("Unexpected error", e);
        }
    }
}
