package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.glass.ui.Application;

import mediatek2020.Mediatheque;
import mediatek2020.items.Utilisateur;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException{

		HttpSession session = request.getSession(true);	

		request.getRequestDispatcher("/login.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException{
		
		HttpSession session = request.getSession(true);	

		System.out.println("======================================== do post login =========================");

		String login = request.getParameter("log");
		String password = request.getParameter("mdp");
		
		Utilisateur user = Mediatheque.getInstance().getUser(login, password);
		

		if( user != null) {
			System.out.println("connexion réussi !");
			session.setAttribute("user",user);
			response.sendRedirect("./dashboard");
		} else {
			System.out.println("Erreur connexion");
			request.setAttribute("message", "Login ou mot de passe incorrect");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}


}
