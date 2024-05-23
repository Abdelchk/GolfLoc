package fr.ensitech.golfloc.metier;

import java.sql.Timestamp;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.entity.PwdReset;
import fr.ensitech.golfloc.model.dao.UserDao;
import fr.ensitech.golfloc.utils.ResetRequestDetails;
import fr.ensitech.golfloc.auth.GmailAuthentification;

public class UserMetier {
	private UserDao userDao;
	
	public Integer creerUtilisateur(User user) {
		
		try {
			userDao = new UserDao();
			
			// Ajouter une vérification de l'email
			// si un utilisateur existe déjà avec l'email alors ne pas insérer sinon insérer
			
			Integer createUser = userDao.addUser(user);
			
			if (createUser != null) {
				return createUser;
			}
			else {
				System.out.println("Une erreur est survenue lors de l'inscritpion de l'utilisateur.");
				return null;
			}
			
//			User userExiste = userDao.getUserByEmail(user.getEmail());
			
//			if (userExiste == null) {
//				Integer createUser = userDao.addUser(user);
//				
//				if (createUser != null) {
//					return createUser;
//				}
//				else {
//					System.out.println("L'adresse email est déjà utilisée !");
//					return null;
//				}
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Une erreur s'est produite dans UserMetier.CreerUtilisateur : " + e.getMessage());
		}
		return null;
	}
	
	public void updateUser(User user) {
		try {
			userDao = new UserDao();
			userDao.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Une erreur s'est produite dans UserMetier.UpdateUser : " + e.getMessage());
		}
	}

    public User seConnecter(String email, String password) {
        try {
            // Instancier UserDao
            userDao = new UserDao();

            // Appeler les méthodes de UserDao pour récupérer les utilisateurs par email et mot de passe
            User userValide = userDao.verifyUser(email, password);

            // Vérifier si les utilisateurs existent ou effectuer d'autres actions nécessaires
            if (userValide != null) {
                return userValide;
            } else {
                System.out.println("L'email ou le mot de passe est incorrect !");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Une erreur s'est produite dans UserMetier.seConnecter : " + e.getMessage());
            return null;
        }
    }
    
    public User getUserById(int id) {
		try {
			userDao = new UserDao();
			return userDao.getUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Une erreur s'est produite dans UserMetier.getUserById : " + e.getMessage());
			return null;
		}
	}
    
	public List<User> getUsers() {
		try {
			userDao = new UserDao();

			return userDao.getUsers();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Une erreur s'est produite dans UserMetier.getUtilisateurs : " + e.getMessage());
			return null;
		}
	}
    
    public boolean reinitialiserMdp(String newPassword, int id) {
    	if (newPassword.length() < 8) {
            System.out.println("Le mot de passe doit avoir au moins 12 caractères.");
        }
    	try {
			userDao = new UserDao();
			
			String currentPassword = userDao.getPasswordById(id);
			
			if (currentPassword != null && !BCrypt.checkpw(newPassword, currentPassword)) {
				userDao.updatePassword(newPassword, id);
	            return true;
			} else {
				System.out.println("Le nouveau mot de passe est identique à l'ancien.");
	            return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Une erreur s'est produite dans UserMetier.reinitialiserMdp : " + e.getMessage());
			return false;
		}
    }
    
    private Timestamp calculateExpirationTime() {
        // Calculer le temps d'expiration du token (par exemple, 24 heures à partir de maintenant)
        long expirationMillis = System.currentTimeMillis() + (24 * 60 * 60 * 1000);
        return new Timestamp(expirationMillis);
    }
    
 // Méthode pour vérifier si le token est expiré
    private boolean isTokenExpired(Timestamp expirationTime) {
        long currentMillis = System.currentTimeMillis();
        long expirationMillis = expirationTime.getTime();

        return currentMillis > expirationMillis;
    }
    
    public PwdReset validerToken(String resetToken) {
		try {
			userDao = new UserDao();
			
			PwdReset details = userDao.getResetRequestDetails(resetToken);
			
			if (details != null) {
				return details;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
            System.out.println("Une erreur s'est produite dans UserMetier.validerToken : " + e.getMessage());
            return null;
		}
	}
    
    public boolean demandeReinitialisation(String email) {
        try {
        	userDao = new UserDao();
        	
            User user = userDao.getUserByEmail(email);

            if (user != null) {
                // Utilisateur trouvé, générez le token
            	String resetToken = GmailAuthentification.generateResetToken();
                
                // Insérez une nouvelle ligne dans la table password_reset_requests
                userDao.insertResetRequest(user, resetToken, calculateExpirationTime());
                
                // Instanciation des détails de la demande de réinitialisation
                PwdReset details = new PwdReset();
                
//                details = userDao.getResetRequestDetails(resetToken);
//                
//                if (details != null && !isTokenExpired(details.getExpirationTime())) {
//					// Appeler la méthode sendResetEmail avec l'adresse e-mail et le jeton d'accès
//                	GmailAuthentification.sendResetEmail(email, resetToken);
//				}
                
                return true;
            } else {
                // Aucun utilisateur trouvé avec cet email
                System.out.println("L'email est incorrect ou n'est pas associé à un utilisateur");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Une erreur s'est produite dans UserMetier.demandeReinitialisation : " + e.getMessage());
            return false;
        }
    }
}
