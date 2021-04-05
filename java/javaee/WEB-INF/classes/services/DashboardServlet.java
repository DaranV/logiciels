 package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2020.Mediatheque;
import mediatek2020.items.Document;
import mediatek2020.items.Utilisateur;
import persistance.Bibliothecaire;



@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException{
		
		

		HttpSession session = request.getSession(true);
		Utilisateur user = null;
		if((user = (Utilisateur) session.getAttribute("user")) == null) {
			//On redirige vers la partie login 
			response.sendRedirect("./login");
			return;
		}
		

		
//		Utilisateur user = new Bibliothecaire(1,"toto","toto","toto");
		
		List<Document> documents = Mediatheque.getInstance().tousLesDocuments();
		
		
		
		
		//On tri de sorte à avoir les documents non disponible à la fin de la liste
		Collections.sort(documents,new Comparator<Document>() {

			@Override
			public int compare(Document o1, Document o2) {
				
				return (Boolean) o1.data()[4] ? -1 : 1;
				
				
			}
		});
		
		request.setAttribute("documents",documents);
		request.setAttribute("user",user);
		
		
		if(user.isBibliothecaire()) {
			request.getRequestDispatcher("/bibliothecaire.jsp").forward(request,response);
		}else{
			List<Document> documentsEmprunter = new ArrayList<Document>();
			
			
			for (int i = documents.size()-1 ; i >= 0; i--) {
				Document document = documents.get(i);
				//On regarde si l'ID de l'emprunteur est le même que l'ID de l'utilisateur
				if((Integer)document.data()[6] == user.data()[2]) {
					documentsEmprunter.add(document);
					documents.remove(i);
				}
				
			}
			

			request.setAttribute("documentsEmprunter",documentsEmprunter);
			request.getRequestDispatcher("/abonne.jsp").forward(request,response);
		}
		
		
		
	}
	
	

	
	
}