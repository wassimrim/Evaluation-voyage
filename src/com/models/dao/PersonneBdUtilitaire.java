package com.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.beans.Personne;

public class PersonneBdUtilitaire {

	private DataSource dataSource;

	public PersonneBdUtilitaire(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Personne> getPersonnes() throws Exception

	{
		List<Personne> personnes = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			
			myConn = dataSource.getConnection();
			
			String sql = "select * from personne order by idpersonne ASC";

			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			

			while (myRs.next()) {
				
				int idpersonne = myRs.getInt("idpersonne");
				String login = myRs.getString("login");
				String password = myRs.getString("password");
				String nom = myRs.getString("nom");
				String prenom = myRs.getString("prenom");
				String adresse = myRs.getString("adresse");
				String telephone = myRs.getString("telephone");
				String ville = myRs.getString("ville");
				String pays = myRs.getString("pays");
				String mail = myRs.getString("mail");
				String role = myRs.getString("role");
				String photoProfile = myRs.getString("photo_profile");

				
				Personne personne = new Personne();
				personne.setIdPersonne(idpersonne);
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

				
				personnes.add(personne);
			}

			return personnes;
		} finally {
			

			close(myConn, myStmt, myRs);

		}

	}

	public void ajoutPersonne(Personne personne) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			
			myConn = dataSource.getConnection();
			
			String sql = "insert into personne"
					+ "( login, password, nom, prenom, adresse, telephone ,ville, pays, mail, role, photo_profile)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?)";

			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, personne.getLogin());
			myStmt.setString(2, personne.getPassword());
			myStmt.setString(3, personne.getNom());
			myStmt.setString(4, personne.getPrenom());
			myStmt.setString(5, personne.getAdresse());
			myStmt.setString(6, personne.getTel());
			myStmt.setString(7, personne.getVille());
			myStmt.setString(8, personne.getPays());
			myStmt.setString(9, personne.getMail());
			myStmt.setString(10, personne.getRole());
			myStmt.setString(11, personne.getPhotoProfile());

			
			myStmt.execute();
			
		} finally {
			close(myConn, myStmt, null);
		}

	}

	public Personne getPersonne(String idpersonne) throws Exception {
		Personne personne = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int personneid;
		try {
			
			personneid = Integer.parseInt(idpersonne);

			
			myConn = dataSource.getConnection();
			
			String sql = "select * from personne where idpersonne=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, personneid);
			
			myRs = myStmt.executeQuery();
			

			if (myRs.next()) {
				String login = myRs.getString("login");
				String password = myRs.getString("password");
				String nom = myRs.getString("nom");
				String prenom = myRs.getString("prenom");
				String adresse = myRs.getString("adresse");
				String telephone = myRs.getString("telephone");
				String ville = myRs.getString("ville");
				String pays = myRs.getString("pays");
				String mail = myRs.getString("mail");
				String role = myRs.getString("role");
				String photoProfile = myRs.getString("photo_profile");

				
				personne = new Personne();
				personne.setLogin(login);
				personne.setPassword(password);
				personne.setIdPersonne(personneid);
				personne.setNom(nom);
				personne.setPrenom(prenom);
				personne.setAdresse(adresse);
				personne.setTel(telephone);
				personne.setVille(ville);
				personne.setPays(pays);
				personne.setMail(mail);
				personne.setRole(role);
				personne.setPhotoProfile(photoProfile);

			} else {
				throw new Exception("personne d'id : " + idpersonne + " est introuvable");
			}
			return personne;

		} finally {
			
			close(myConn, myStmt, myRs);
		}

	}

	public void modificationPersonne(Personne personne) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			
			myConn = dataSource.getConnection();
			
			String sql = "update personne "
					+ "set  login=?, password=?,  nom=?, prenom=?, adresse=?, telephone=?, ville=?, pays=?, mail=?, role=?, photo_profile=? "
					+ "where idpersonne=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, personne.getLogin());
			myStmt.setString(2, personne.getPassword());
			myStmt.setString(3, personne.getNom());
			myStmt.setString(4, personne.getPrenom());
			myStmt.setString(5, personne.getAdresse());
			myStmt.setString(6, personne.getTel());
			myStmt.setString(7, personne.getVille());
			myStmt.setString(8, personne.getPays());
			myStmt.setString(9, personne.getMail());
			myStmt.setString(10, personne.getRole());
			myStmt.setString(11, personne.getPhotoProfile());
			myStmt.setInt(12, personne.getIdPersonne());
			
			myStmt.execute();
		} finally {
			
			close(myConn, myStmt, null);
		}
	}

	public void suppressionPersonne(String idPersonne) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			
			int idpersonne = Integer.parseInt(idPersonne);

			
			myConn = dataSource.getConnection();

			
			String sql = "delete from personne where idpersonne =?";

			
			myStmt = myConn.prepareStatement(sql);

			
			myStmt.setInt(1, idpersonne);

			
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
