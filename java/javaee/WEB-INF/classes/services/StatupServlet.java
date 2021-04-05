package services;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StatupServlet implements ServletContextListener {
	
	 @Override
	    public void contextInitialized(ServletContextEvent sce) {
	     
		 	try {
				Class.forName("persistance.MediathequeData");
				Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("******************************************** Mediatheque loaded ******************************************* ");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @Override
	    public void contextDestroyed(ServletContextEvent sce) {
	        System.out.println("On shutdown web app");
	    }


	
	
	
}
