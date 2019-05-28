package com.servers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.beans.Commentaire;
import com.beans.Personne;
import com.beans.User;
import com.beans.Voyage;
import com.models.dao.CommentaireBdUtilitaire;
import com.models.dao.PersonneBdUtilitaire;
import com.models.dao.VoyageBdUtilitaire;

/**
 * Servlet implementation class CommentaireBdUtilitaire
 */
@WebServlet("/CommentaireController")
public class CommentaireController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CommentaireBdUtilitaire commentaireBdUtilitaire;
	private VoyageBdUtilitaire voyageBdUtilitaire;
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
			
			voyageBdUtilitaire = new VoyageBdUtilitaire(dataSource);
			commentaireBdUtilitaire = new CommentaireBdUtilitaire(dataSource);
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

			
			

			
			HttpSession session = request.getSession(true);
			
	
			
			if(session.getAttribute("util")==null)
			{
			redirectToLogin(request, response);
			}
			String commande = request.getParameter("commande");
		
			if (commande == null) {
				commande = "LIST";
			}
			
			switch (commande) {
			case "LIST":
				consulterVoyages(request, response);
				break;
			case "CHARGERCOMMENTAIRE":
				chargerListCommentairesParVoyage(request, response);
				break;
			case "AJOUT":
				consulterVoyages(request, response);
				break;
			case "COMMENTAIRE":
				commenterVoyage(request, response);

			default:
			

			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	private void commenterVoyage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		HttpSession session = request.getSession();
		User  user = (User)session.getAttribute("util");
		
		 LocalDateTime datetime =   LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 String text = formatter.format(datetime);
		 LocalDateTime parsedDate = LocalDateTime.parse(text, formatter);
	
		String commentairee = request.getParameter("commentaire");

		Commentaire commentaire = new Commentaire();
		commentaire.setIdPersonne(Integer.parseInt(user.getId()));
		commentaire.setDescription(commentairee);
		commentaire.setIdVoyage(Integer.parseInt(request.getParameter("idVoyage")));
		commentaire.setDatetime(parsedDate);
		commentaireBdUtilitaire.ajoutCommentaire(commentaire);
		chargerListCommentairesParVoyage(request, response);
	}

	private void chargerListCommentairesParVoyage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		

		Voyage voyage = voyageBdUtilitaire.getVoyage(request.getParameter("idVoyage"));
		request.setAttribute("VOYAGE", voyage);
		List<Commentaire> commentaires = commentaireBdUtilitaire
				.getListCommentairesByIdVoyage(request.getParameter("idVoyage"));
		request.setAttribute("COMMENTAIRES_LIST", commentaires);
		List<Personne> personnes = personneBdUtilitaire.getPersonnes();
		request.setAttribute("PERSONNES_LIST", personnes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/client/list-commentaires-voyage.jsp");
		dispatcher.forward(request, response);

	}

	private void consulterVoyages(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		List<Voyage> voyages = voyageBdUtilitaire.getVoyages();
	
		request.setAttribute("VOYAGE_LIST", voyages);

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/client/consultation-voyages.jsp");
		dispatcher.forward(request, response);
	}
	
	private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/security/login.jsp");
		dispatcher.forward(request, response);
	}

}
