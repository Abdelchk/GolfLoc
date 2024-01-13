package fr.ensitech.golfloc.entity;

import java.util.Date;

public class User {
	
	private Integer id;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private Date dateNaissance;
	
	public User() {
	}

	public User(String nom, String prenom, String email, String password, Date dateNaissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.dateNaissance = dateNaissance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
}
