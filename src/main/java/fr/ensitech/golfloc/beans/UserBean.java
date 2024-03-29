package fr.ensitech.golfloc.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.metier.UserMetier;
import fr.ensitech.golfloc.utils.ResetRequestDetails;

@ManagedBean(name = "userbean")
@SessionScoped
public class UserBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private String pwdConfirm;
	private Date dateNaissance;
	private String isActive;
	private String profile;
	private String phoneNumber;
	private UserMetier userMetier;
	private User connectedUser;
	
	public UserBean() {
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
	
	public String getPwdConfirm() {
		return pwdConfirm;
	}

	public void setPwdConfirm(String pwdConfirm) {
		this.pwdConfirm = pwdConfirm;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
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

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectUser) {
		this.connectedUser = connectUser;
	}
	
	
	// Méthodes de validations
	
	public void validatePasswordConfirmation(FacesContext context, UIComponent component, Object value) {
		
		pwdConfirm = (String) value;
		
	    // Rechercher les composants password et pwdConfirm dans le formulaire
	    UIInput inputPassword = (UIInput) component.findComponent("password");
	    

	    // Obtenir les valeurs actuelles des champs de mot de passe
	    String passwordValue = inputPassword.getLocalValue() == null ? "" : inputPassword.getLocalValue().toString();
	    

	    // Vérifier l'égalité des mots de passe
	    if (!passwordValue.equals(pwdConfirm)) {
	        FacesMessage msg = new FacesMessage("Les mots de passe ne correspondent pas.");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        context.addMessage(component.getClientId(), msg);
	        context.validationFailed();
	    } else {
	        // Stocker le mot de passe dans la session
	        ExternalContext externalContext = context.getExternalContext();
	        Map<String, Object> sessionMap = externalContext.getSessionMap();
	        sessionMap.put("resetPassword", passwordValue);
	    }
	}
	
	// Méthodes pour les boutons 
	
	public String creerUtilisateur() {
		userMetier = new UserMetier();
		
		try {
			User user = new User();
			
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setDateNaissance(dateNaissance);
			user.setEmail(email);
			user.setPassword(password);
			user.setPhoneNumber(phoneNumber);
			
			userMetier.creerUtilisateur(user);
			connectedUser = user;
			
			if (connectedUser != null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", connectedUser);
                return "achats.xhtml";
			} else {
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de l'inscription :", "Un des champs est vide ou incorrect."));
                return null;
            }
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "une erreur s'est produite lors de l'inscription"));
			return null;
		}
		
	}
	
	public String seConnecter() {
        // Instancier UserMetier
        userMetier = new UserMetier();

        try {
        	
            connectedUser = userMetier.seConnecter(email, password);

            if (connectedUser != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", connectedUser);
                return "achats.xhtml";
            } else {
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur de connexion :", "email ou mot de passe incorrect"));
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Loguer l'erreur
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "une erreur s'est produite lors de la connexion"));
            return null;
        }
    }
	
	public String seDeconnecter() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
	    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "connexion.xhtml";
	}
	
	public String demandeReinitialisation() {
		userMetier = new UserMetier();
		
		try {
			boolean emailUser = userMetier.demandeReinitialisation(email);
			
			if (emailUser) {
				
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès :", "le mail a bien été envoyé !"));
	            return "";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "l'adresse mail n'est pas associé à un compte existant"));
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Une erreur s'est produite dans la demande de réinitialisation : " + e.getMessage()));
			return null;
		}
	}
	
	public String reinitialiserMdp(String resetToken) {
		userMetier = new UserMetier();
		
		try {

	        System.out.println("Token : " + resetToken);
	        
	     // Valider le token et récupérer l'utilisateur associé
	        ResetRequestDetails userDetails = userMetier.validerToken(resetToken);
	        
	        if (userDetails != null) {
	        	// Récupérer le mot de passe depuis la session
	            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	            Map<String, Object> sessionMap = externalContext.getSessionMap();
	            String password = (String) sessionMap.get("resetPassword");

	            if (password != null) {
	                // Mettre à jour le mot de passe
	                boolean pwdUser = userMetier.reinitialiserMdp(password, userDetails.getUserId());

	                if (pwdUser) {
	                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès :", "Le mot de passe a bien été réinitialisé !"));
	                    // Nettoyer le mot de passe de la session après utilisation
	                    sessionMap.remove("resetPassword");
	                    return "";
	                } else {
	                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Votre nouveau mot de passe ne doit pas être identique à votre ancien !"));
	                }
	            }
	        } else {
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Token invalide. Veuillez réessayer."));
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "une erreur s'est produite "));
		}
		return null;
	}

}
