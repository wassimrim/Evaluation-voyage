package com.servers;

import java.io.IOException;

import java.util.List;

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
//import com.beans.Commentaire;
import com.beans.Voyage;
//import com.models.dao.CommentaireBdUtilitaire;
import com.models.dao.VoyageBdUtilitaire;

/**
 * Servlet implementation class VoyageController
 */
@WebServlet("/VoyageController")
public class VoyageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VoyageBdUtilitaire voyageBdUtilitaire;

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
			voyageBdUtilitaire = new VoyageBdUtilitaire(dataSource);
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

			HttpSession session = request.getSession();

			if (session.getAttribute("util") == null) {
				redirectToLogin(request, response);
			} else {
				User user = (User) session.getAttribute("util");

				if (user.getRole().equalsIgnoreCase("USER")) {
					accessErronee(request, response);
				}

			}

			String commande = request.getParameter("commande");

			if (commande == null) {
				commande = "LIST";
			}

			switch (commande) {
			case "LIST":
				listVoyages(request, response);
				break;
			case "AJOUT":
				ajoutVoyage(request, response);
				break;
			case "CHARGER":
				chargerVoyage(request, response);
				break;
			case "MISEAJOUR":
				miseajourVoyage(request, response);
				break;
			case "SUPPRIMER":
				supprimerVoyage(request, response);
				break;

			default:
				listVoyages(request, response);

			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	private void accessErronee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/security/accees-refusee.jsp");
		dispatcher.forward(request, response);
	}

	private void listVoyages(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Voyage> voyages = voyageBdUtilitaire.getVoyages();

		request.setAttribute("VOYAGE_LIST", voyages);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/voyage/list-voyages.jsp");
		dispatcher.forward(request, response);
	}

	private void miseajourVoyage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int idVoyage = Integer.parseInt(request.getParameter("idVoyage"));
		String labelle = request.getParameter("labelle");
		String pays = request.getParameter("pays");
		String description = request.getParameter("description");
		String image = request.getParameter("image");

		Voyage voyage = new Voyage();

		voyage.setIdVoyage(idVoyage);
		voyage.setLabelle(labelle);
		voyage.setPays(pays);
		voyage.setDescription(description);
		voyage.setImage(image);

		voyageBdUtilitaire.modificationVoyage(voyage);

		listVoyages(request, response);
	}

	private void chargerVoyage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idVoyage = request.getParameter("idVoyage");

		Voyage voyage = voyageBdUtilitaire.getVoyage(idVoyage);

		request.setAttribute("Voyage", voyage);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/voyage/mise-a-jour-voyage.jsp");
		dispatcher.forward(request, response);
	}

	private void ajoutVoyage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String labelle = request.getParameter("labelle");
		String pays = request.getParameter("pays");
		String description = request.getParameter("description");
		String image = request.getParameter("image");

		Voyage voyage = new Voyage();

		voyage.setLabelle(labelle);
		voyage.setPays(pays);
		voyage.setDescription(description);
		voyage.setImage(image);

		voyageBdUtilitaire.ajoutVoyage(voyage);

		listVoyages(request, response);

	}

	private void supprimerVoyage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idVoyage = request.getParameter("idVoyage");

		voyageBdUtilitaire.suppressionVoyage(idVoyage);

		listVoyages(request, response);
	}

	private void redirectToLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/security/login.jsp");
		dispatcher.forward(request, response);
	}

}
