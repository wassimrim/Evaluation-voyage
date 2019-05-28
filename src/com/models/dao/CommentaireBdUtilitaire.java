package com.models.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.beans.Commentaire;

public class CommentaireBdUtilitaire {

	private DataSource dataSource;

	public CommentaireBdUtilitaire(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Commentaire> getCommentaires() throws Exception

	{
		List<Commentaire> commentaires = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			
			myConn = dataSource.getConnection();
			
			String sql = "select * from commentaire order by idcommentaire ASC";

			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			

			while (myRs.next()) {
				
				int idCommentaire = myRs.getInt("idcommentaire");
				String description = myRs.getString("description");
				int idPersonne = myRs.getInt("personne_idpersonne");
				int idVoyage = myRs.getInt("voyage_idvoyage");
				
				Commentaire commentaire = new Commentaire();
				commentaire.setIdCommentaire(idCommentaire);
				commentaire.setDescription(description);
				commentaire.setIdPersonne(idPersonne);
				commentaire.setIdVoyage(idVoyage);

				
				commentaires.add(commentaire);
			}

			return commentaires;
		} finally {
		

			close(myConn, myStmt, myRs);

		}

	}

	public void ajoutCommentaire(Commentaire commentaire) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
		
			myConn = dataSource.getConnection();
			
			String sql = "insert into commentaire" + "(description, personne_idpersonne, voyage_idvoyage,date_creation)"
					+ "values(?,?,?,?)";

			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, commentaire.getDescription());
			myStmt.setInt(2, commentaire.getIdPersonne());
			myStmt.setInt(3, commentaire.getIdVoyage());
			myStmt.setObject(4,commentaire.getDatetime());
			
			myStmt.execute();
		
		} finally {
			close(myConn, myStmt, null);
		}

	}

	public Commentaire getCommentaire(String idCommentaire) throws Exception {
		Commentaire commentaire = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int commentaireId;
		try {
			
			commentaireId = Integer.parseInt(idCommentaire);

			
			myConn = dataSource.getConnection();
			
			String sql = "select * from commentaire where idcommentaire=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, commentaireId);
			
			myRs = myStmt.executeQuery();
			

			if (myRs.next()) {
				String description = myRs.getString("Description");
				int personneIdPersonne = myRs.getInt("personne_idpersonne");
				int voyageIdVoyage = myRs.getInt("voyage_idvoyage");
				
				commentaire = new Commentaire();
				commentaire.setIdCommentaire(commentaireId);
				commentaire.setDescription(description);
				commentaire.setIdPersonne(personneIdPersonne);
				commentaire.setIdVoyage(voyageIdVoyage);

			} else {
				throw new Exception(" le commentaire d'id : " + idCommentaire + " est introuvable");
			}
			return commentaire;

		} finally {
			
			close(myConn, myStmt, myRs);
		}

	}

	public List<Commentaire> getListCommentairesByIdVoyage(String idVoyage) throws Exception {

		List<Commentaire> commentaires = new ArrayList<>();
		Commentaire commentaire = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int idvoyage;
		try {
			
			idvoyage = Integer.parseInt(idVoyage);
			
			myConn = dataSource.getConnection();
			
			
			String sql = "select * from commentaire where voyage_idvoyage=? order by date_creation ASC";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, idvoyage);
			
			myRs = myStmt.executeQuery();
			
			
			while (myRs.next()) {
				String description = myRs.getString("Description");
				
				int personneIdPersonne = myRs.getInt("personne_idpersonne");
				int voyageIdVoyage = myRs.getInt("voyage_idvoyage");
				int commentaireId = myRs.getInt("idcommentaire");
				
				commentaire = new Commentaire();
				commentaire.setIdCommentaire(commentaireId);
				commentaire.setDescription(description);
				commentaire.setIdPersonne(personneIdPersonne);
				commentaire.setIdVoyage(voyageIdVoyage);

				commentaires.add(commentaire);
			}

			return commentaires;

		} finally {
			

			close(myConn, myStmt, myRs);

		}

	}

	public void modificationCommentaire(Commentaire commentaire) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			
			myConn = dataSource.getConnection();
			
			String sql = "update commentaire " + "set description=?, personne_idpersonne=?, voyage_idvoyage=? "
					+ "where idcommentaire=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, commentaire.getDescription());
			myStmt.setInt(2, commentaire.getIdPersonne());
			myStmt.setInt(3, commentaire.getIdVoyage());
			myStmt.setInt(4, commentaire.getIdCommentaire());
			
			myStmt.execute();
		} finally {
			
			close(myConn, myStmt, null);
		}
	}

	public void suppressionCommentaire(String commentaireId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			
			int idCommentaire = Integer.parseInt(commentaireId);

			
			myConn = dataSource.getConnection();

			
			String sql = "delete from commentaire where idcommentaire =?";

			
			myStmt = myConn.prepareStatement(sql);

			
			myStmt.setInt(1, idCommentaire);

			
			myStmt.execute();

		} finally {

			
			close(myConn, myStmt, null);
		}

	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub

		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close(); 
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}
}
