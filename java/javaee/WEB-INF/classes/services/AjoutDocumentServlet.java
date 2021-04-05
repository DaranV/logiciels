package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2020.Mediatheque;
import mediatek2020.items.EmpruntException;

@WebServlet("/ajout")
public class AjoutDocumentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException{

		HttpSession session = request.getSession(true);	
		
		String document = request.getParameter("document");
		String url = request.getParameter("url");
		String type = request.getParameter("type");
		
		System.out.println("------RECU----------- "+document+" -----------------");
		System.out.println("------RECU----------- "+url+" -----------------");
		System.out.println("------RECU----------- "+type+" -----------------");
		
		Mediatheque.getInstance().nouveauDocument(3, document, url, type);
		System.out.println("------------- good -------------");
		
		response.sendRedirect("./dashboard");
	}
	
	
}