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
import mediatek2020.items.RetourException;
import mediatek2020.items.Utilisateur;
import persistance.MediathequeDocument;

@WebServlet("/retour")
public class RetourDocumentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException{

		HttpSession session = request.getSession(true);


		Utilisateur user = (Utilisateur) session.getAttribute("user");



		//récupération de l'id

		int docId = -1;
		try {
			docId = Integer.parseInt(request.getParameter("docId"));
		}catch(NumberFormatException e) {
			//Erreur
		}

		Document doc = Mediatheque.getInstance().getDocument(docId);
		PrintWriter out = response.getWriter();
		
		
		if(doc == null) {
			response.setStatus(422);
			out.print("Document inconnu");
			return;
		}
		
		
		//Si le livre est disponible on renvoie une erreur 
		if((Boolean) doc.data()[4]){
			response.setStatus(422);
			out.print("Le document avec l'id " + docId + " est disponible");
			return;
		}



		System.out.println("========================= Retour "+ docId + "  ======= ");
		try {	
			Mediatheque.getInstance().rendre(doc, user);
			response.setStatus(200);
			out.print("Clear");
		} catch (RetourException e) {
			e.printStackTrace();
		}


		


	}

}

