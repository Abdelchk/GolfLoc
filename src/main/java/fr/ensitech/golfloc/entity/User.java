package fr.ensitech.golfloc.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "user")
@XmlRootElement
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "lastname", length = 45, nullable = false)
	private String nom;
	
	@Column(name = "firstname", length = 45, nullable = false)
	private String prenom;
	
	@Column(length = 45, nullable = false, unique = true)
	private String email;
	
	@Lob
	@Column(length = 45, nullable = false)
	private String password;
	
	@Column(name = "birthdate", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	
	@Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
	private boolean isActive = true;
	
	@Column(name = "profile", length = 45, nullable = false, columnDefinition = "varchar(45) default 'client'")
	private String profile =  "client";
	
	@Column(name = "phone_number", length = 45, nullable = false)
	private String phoneNumber;
	
	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Adresse adresse;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Payment carteDePaiement;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PwdReset> pwdReset;
	
	@OneToMany(mappedBy = "id.user")
    private List<Cart> carts;
	
	@OneToMany(mappedBy = "user")
    private List<Comment> comments;
	
	public User() {
	}

	public User(Integer id, String nom, String prenom, String email, String password, Date dateNaissance, boolean isActive,
			String profile, String phoneNumber, Adresse adresse) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.dateNaissance = dateNaissance;
		this.isActive = isActive;
		this.profile = profile;
		this.phoneNumber = phoneNumber;
		this.adresse = adresse;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer idUser) {
		this.id = idUser;
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

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}
	
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Payment getCarteDePaiement() {
		return carteDePaiement;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password
				+ ", dateNaissance=" + dateNaissance + ", isActive=" + isActive + ", profile=" + profile
				+ ", phoneNumber=" + phoneNumber + ", adresse=" + adresse + "]";
	}

	
}
