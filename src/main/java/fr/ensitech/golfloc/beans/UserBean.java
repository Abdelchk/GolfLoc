package fr.ensitech.golfloc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import fr.ensitech.golfloc.entity.Adresse;
import fr.ensitech.golfloc.entity.PwdReset;
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
	private boolean isActive;
	private String profile;
	private String phoneNumber;
	private Adresse adresse;
	private UserMetier userMetier;
	private User connectedUser;
	@ManagedProperty(value="#{adresseBean}")
    private AdresseBean adresseBean;
	
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
	
	public Adresse getAdresseId() {
		return adresse;
	}
	
	public void setAdresseId(Adresse adresseId) {
		this.adresse = adresseId;
	}

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectUser) {
		this.connectedUser = connectUser;
	}
	
	public void setAdresseBean(AdresseBean adresseBean) {
        this.adresseBean = adresseBean;
    }
	
	// Méthodes qui gère l'edit du formulaire des utilisateurs
	
	 public void onRowEdit(RowEditEvent<User> event) {
	        FacesMessage msg = new FacesMessage("Utilisateur", event.getObject().getNom().concat(" ".concat(event.getObject().getPrenom().concat(" modifié"))));
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }

	    public void onRowCancel(RowEditEvent<User> event) {
	        FacesMessage msg = new FacesMessage("Modification annulée pour l'utilisateur", event.getObject().getNom().concat(" ".concat(event.getObject().getPrenom())));
	        FacesContext.getCurrentInstance().addMessage(null, msg);
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
			Adresse adresse = new Adresse();
			
		    AdresseBean adresseBean = (AdresseBean) FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{adressebean}", AdresseBean.class);
			
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setDateNaissance(dateNaissance);
			user.setEmail(email);
			user.setPassword(password);
			user.setPhoneNumber(phoneNumber);
			
			adresse.setUser(user);
			adresse.setNumero(adresseBean.getNumero());
			adresse.setRue(adresseBean.getRue());
			adresse.setVille(adresseBean.getVille());
			adresse.setCodePostal(adresseBean.getCodePostal());
			
			user.setAdresse(adresse);
			
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
			System.out.println("Une erreur s'est produite dans UserBean.CreerUtilisateur : " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "une erreur s'est produite lors de l'inscription"));
			return null;
		}
		
	}
	
	public String updateUser(User user) {
		userMetier = new UserMetier();
		
		try {
			System.out.println("ID: " + user.getId());
			System.out.println("Compte actif : " + user.getIsActive());
			System.out.println("Profil : " + user.getProfile());

			user = userMetier.getUserById(user.getId());
			
			user.setProfile(profile);
			user.setIsActive(isActive);
			
			System.out.println("Profil après modif : " + user.getProfile());
			System.out.println("Compte actif après modif : " + user.getIsActive());
			
			userMetier.updateUser(user);

			return "admin.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Une erreur s'est produite dans UserBean.UpdateUser : " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "une erreur s'est produite lors de la modification de l'utilisateur"));
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
	
	public List<User> getUsers() {
		userMetier = new UserMetier();
		try {
			List<User> users = userMetier.getUsers();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
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
	        PwdReset userDetails = userMetier.validerToken(resetToken);
	        
	        if (userDetails != null) {
	        	// Récupérer le mot de passe depuis la session
	            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	            Map<String, Object> sessionMap = externalContext.getSessionMap();
	            String password = (String) sessionMap.get("resetPassword");

	            if (password != null) {
	                // Mettre à jour le mot de passe
	                boolean pwdUser = userMetier.reinitialiserMdp(password, userDetails.getId());

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
