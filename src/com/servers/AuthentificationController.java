package com.servers;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.beans.User;
import com.models.dao.AuthentificationBdUtilitaire;;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/AuthentificationController")
public class AuthentificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AuthentificationBdUtilitaire authentificationBdUtilitaire;

	@Resource(name = "jdbc/evaluation_des_voyages")
	private DataSource dataSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		
		try {
			authentificationBdUtilitaire = new AuthentificationBdUtilitaire(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			

			String authentification = request.getParameter("authentification");

			if (authentification == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/security/login.jsp");
				dispatcher.forward(request, response);
			}

			switch (authentification) {
			case "authentifier":

				authentification(request, response);
				break;
			case "DISCONNECT":

				deconnecter(request, response);
				break;
			default:
				

			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	private void deconnecter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		session.setAttribute("util",null);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/security/login.jsp");
		dispatcher.forward(request, response);

	}

	private void authentification(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		HttpSession session = request.getSession(true);
		User user = authentificationBdUtilitaire.redirectionUtilisateur(login, password);

		if (user == null) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/security/authentification-errone.jsp");
			dispatcher.forward(request, response);
		} else if (user.getRole().equalsIgnoreCase("ADMIN")) {
			

			if (session.getAttribute("util") == null) {
				session.setAttribute("util", user);

			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/menu-admin.jsp");
			dispatcher.forward(request, response);

		} else if (user.getRole().equalsIgnoreCase("USER")) {

			if (session.getAttribute("util") == null) {
				session.setAttribute("util", user);
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("CommentaireController");
			dispatcher.forward(request, response);
		}
	}

}
