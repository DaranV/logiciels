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
import mediatek2020.items.Document;
import mediatek2020.items.EmpruntException;
import mediatek2020.items.Utilisateur;
import persistance.Abonne;



@WebServlet("/emprunt")
public class EmpruntServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException{
			
		
		HttpSession session = request.getSession(true);
		
		Utilisateur user = null;
		if((user = (Utilisateur) session.getAttribute("user")) == null) {
			//Error
		}
		
		int docId = -1;
		try {
			docId = Integer.parseInt(request.getParameter("docId"));
		}catch(NumberFormatException e) {
			//Erreur
		}
		
		Document doc = Mediatheque.getInstance().getDocument(docId);
		PrintWriter out = response.getWriter();
		
		try {
			System.out.println("========================= Emprunter "+ docId + "  ======= ");
			Mediatheque.getInstance().emprunter(doc,user);
			
			response.setStatus(200);
			out.print("Clear");
		} catch (EmpruntException e) {
			e.printStackTrace();
			
			response.setStatus(200);
			out.print("Le document " + doc.data()[1] + " n'est plus disponible");
		}
			
		
		
	}
	

	
}