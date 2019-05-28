package com.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.beans.Voyage;

public class VoyageBdUtilitaire {

	private DataSource dataSource;

	public VoyageBdUtilitaire(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Voyage> getVoyages() throws Exception

	{
		List<Voyage> voyages = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			myConn = dataSource.getConnection();

			String sql = "select * from voyage order by idvoyage DESC";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {

				int idvoyage = myRs.getInt("idvoyage");
				String labelle = myRs.getString("labelle");
				String pays = myRs.getString("pays");
				String description = myRs.getString("description");
				String image = myRs.getString("image");

				Voyage voyage = new Voyage();
				voyage.setIdVoyage(idvoyage);
				voyage.setLabelle(labelle);
				voyage.setPays(pays);
				voyage.setDescription(description);
				voyage.setImage(image);

				voyages.add(voyage);
			}

			return voyages;
		} finally {

			close(myConn, myStmt, myRs);

		}

	}

	public void ajoutVoyage(Voyage voyage) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {

			myConn = dataSource.getConnection();

			String sql = "insert into voyage" + "(labelle, pays, description,image)" + "values(?,?,?,?)";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, voyage.getLabelle());
			myStmt.setString(2, voyage.getPays());
			myStmt.setString(3, voyage.getDescription());
			myStmt.setString(4, voyage.getImage());

			myStmt.execute();

		} finally {
			close(myConn, myStmt, null);
		}

	}

	public Voyage getVoyage(String idvoyage) throws Exception {
		Voyage voyage = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int voyageid;
		try {

			voyageid = Integer.parseInt(idvoyage);

			myConn = dataSource.getConnection();

			String sql = "select * from voyage where idvoyage=?";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1, voyageid);

			myRs = myStmt.executeQuery();

			if (myRs.next()) {
				String labelle = myRs.getString("labelle");
				String pays = myRs.getString("pays");
				String description = myRs.getString("description");
				String image = myRs.getString("image");
				
				voyage = new Voyage();
				voyage.setIdVoyage(voyageid);
				voyage.setLabelle(labelle);
				voyage.setPays(pays);
				voyage.setDescription(description);
				voyage.setImage(image);

			} else {
				throw new Exception("voyage d'id : " + idvoyage + " est introuvable");
			}
			return voyage;

		} finally {

			close(myConn, myStmt, myRs);
		}

	}

	public void modificationVoyage(Voyage voyage) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {

			myConn = dataSource.getConnection();

			String sql = "update voyage " + "set labelle=?, pays=?, description=?, image=? " + "where idvoyage=?";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, voyage.getLabelle());
			myStmt.setString(2, voyage.getPays());
			myStmt.setString(3, voyage.getDescription());
			myStmt.setString(4, voyage.getImage());
			myStmt.setInt(5, voyage.getIdVoyage());
			
			
			myStmt.execute();
		} finally {

			close(myConn, myStmt, null);
		}
	}

	public void suppressionVoyage(String voyageid) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {

			int idvoyage = Integer.parseInt(voyageid);

			myConn = dataSource.getConnection();

			String sql = "delete from voyage where idvoyage =?";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1, idvoyage);

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
