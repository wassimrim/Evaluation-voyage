package com.beans;

import java.time.LocalDateTime;

public class Commentaire {

	private int idCommentaire;
	private String description;
	private int idPersonne;
	private int idVoyage;
	private LocalDateTime datetime;

	public int getIdCommentaire() {
		return idCommentaire;
	}

	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	public int getIdVoyage() {
		return idVoyage;
	}

	public void setIdVoyage(int idVoyage) {
		this.idVoyage = idVoyage;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	

}
