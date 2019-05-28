package com.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import com.beans.User;

public class AuthentificationBdUtilitaire {
	private DataSource dataSource;

	public AuthentificationBdUtilitaire(DataSource theDataSource) {
		dataSource = theDataSource;
	
	}

	public User redirectionUtilisateur(String login, String password) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		User user = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from personne where login=? and password=? ";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, login);
			myStmt.setString(2, password);
			myRs = myStmt.executeQuery();

			if (myRs.next()) {
				 user = new User();
				 user.setId(myRs.getString("idpersonne"));
				user.setLogin(myRs.getString("login"));
				user.setMotDePasse(myRs.getString("password"));
				user.setNom(myRs.getString("nom"));
				user.setPrenom(myRs.getString("prenom"));
				user.setRole(myRs.getString("role"));
				
				
			}

		} finally {
		
			close(myConn, myStmt, myRs);
		}
		return user;

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
