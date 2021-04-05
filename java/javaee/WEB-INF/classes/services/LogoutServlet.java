package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2020.Mediatheque;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException{

		
	}
	
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException{

		
		HttpSession session = request.getSession(true);
		
		System.out.println("======================================== do post logout =========================");
		
		session.invalidate();
		
		System.out.println("======================================== do post logout fait =========================");
		
		response.sendRedirect("./login");
	}
	
	
}
