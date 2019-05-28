package com.servers;

//import java.io.File;
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

/*import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;*/

import com.beans.Personne;
import com.beans.User;
import com.models.dao.PersonneBdUtilitaire;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PersonneBdUtilitaire personneBdUtilitaire;

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
			personneBdUtilitaire = new PersonneBdUtilitaire(dataSource);
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

			String Commande = request.getParameter("commande");

			if (Commande == null) {
				Commande = "LIST";
			}

			switch (Commande) {

			case "LIST":
				listPersonnes(request, response);
				break;
			case "AJOUT":
				ajoutPersonne(request, response);
				break;
			case "CHARGER":
				chargerPersonne(request, response);
				break;
			case "MISEAJOUR":
				miseajourPersonne(request, response);
				break;
			case "SUPPRIMER":
				supprimerPersonne(request, response);
				break;
			default:
				listPersonnes(request, response);

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

	private void supprimerPersonne(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idPersonne = request.getParameter("idPersonne");

		personneBdUtilitaire.suppressionPersonne(idPersonne);

		listPersonnes(request, response);
	}

	private void miseajourPersonne(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("idPersonne"));
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		String telephone = request.getParameter("telephone");
		String ville = request.getParameter("ville");
		String pays = request.getParameter("pays");
		String mail = request.getParameter("mail");
		String role = request.getParameter("role");
		String photoProfile = request.getParameter("photo_profile");

		Personne personne = new Personne();

		personne.setIdPersonne(id);
		personne.setLogin(login);
		personne.setPassword(password);
		personne.setNom(nom);
		personne.setPrenom(prenom);
		personne.setAdresse(adresse);
		personne.setTel(telephone);
		personne.setVille(ville);
		personne.setPays(pays);
		personne.setMail(mail);
		personne.setRole(role);
		personne.setPhotoProfile(photoProfile);

		personneBdUtilitaire.modificationPersonne(personne);

		listPersonnes(request, response);
	}

	private void chargerPersonne(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idPersonne = request.getParameter("idPersonne");

		Personne personne = personneBdUtilitaire.getPersonne(idPersonne);

		request.setAttribute("Personne", personne);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/mise-a-jour-personne.jsp");
		dispatcher.forward(request, response);
	}

	private void ajoutPersonne(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");
		String telephone = request.getParameter("telephone");
		String ville = request.getParameter("ville");
		String pays = request.getParameter("pays");
		String mail = request.getParameter("mail");
		String role = request.getParameter("role");
		String photoProfile = request.getParameter("photo_profile");

		Personne personne = new Personne();

		personne.setLogin(login);
		personne.setPassword(password);
		personne.setNom(nom);
		personne.setPrenom(prenom);
		personne.setAdresse(adresse);
		personne.setTel(telephone);
		personne.setVille(ville);
		personne.setPays(pays);
		personne.setMail(mail);
		personne.setRole(role);
		personne.setPhotoProfile(photoProfile);

		personneBdUtilitaire.ajoutPersonne(personne);

		listPersonnes(request, response);

	}

	private void listPersonnes(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Personne> personnes = personneBdUtilitaire.getPersonnes();

		request.setAttribute("PERSONNE_LIST", personnes);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/list-personnes.jsp");
		dispatcher.forward(request, response);
	}

	private void redirectToLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/security/login.jsp");
		dispatcher.forward(request, response);
	}

}
