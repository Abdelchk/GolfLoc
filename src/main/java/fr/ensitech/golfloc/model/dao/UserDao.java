package fr.ensitech.golfloc.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.model.connection.UserDataSource;
import fr.ensitech.golfloc.utils.Dates;
import fr.ensitech.golfloc.utils.ResetRequestDetails;

public final class UserDao implements IUserDao {

	public UserDao() {
	}

	@Override
	public final Integer addUser(User user) throws Exception {
		if (user == null) {
			throw new NullPointerException("Le user à créer ne doit pas être NULL !");
		}
		if (user.getNom() == null || user.getNom().trim().isEmpty() || user.getPrenom() == null
				|| user.getPrenom().trim().isEmpty() || user.getEmail() == null 
				|| user.getEmail().trim().isEmpty() || user.getPassword() == null 
				|| user.getPassword().trim().isEmpty() || user.getDateNaissance() == null
				|| Dates.convertDateToString(user.getDateNaissance()).trim().isEmpty()) 
		{
			throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
		}
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "INSERT INTO user(lastname, firstname, email, password, birthdate)" + " VALUES(?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getNom());
			ps.setString(2, user.getPrenom());
			ps.setString(3, user.getEmail());
			ps.setString(4, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()) );
			ps.setDate(5, Dates.convertDateUtilToDateSql(user.getDateNaissance()));

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
				return rs.getInt(1); // l'id de la ligne ajoutée dans la table user de la BDD
			}
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		return null;
	}

	@Override
	public User getUserById(int id) throws Exception {
		if (id <= 0) {
			throw new IllegalArgumentException("L'id doit être > 0 !");
		}
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "SELECT * FROM user WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(requete);
			ps.setInt(1, id);
			ps.execute();
			rs = ps.getResultSet();
			if (rs != null && rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setNom(rs.getString("lastname"));
				user.setPrenom(rs.getString("firstname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setDateNaissance(Dates.convertDateSqlToDateUtil(rs.getDate("birthdate")));
			}
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		return null;
	}

	@Override
	public void updateUser(User user) throws Exception {
		if (user == null) {
			throw new NullPointerException("Le user à créer ne doit pas être NULL !");
		}
		if (user.getNom() == null || user.getNom().trim().isEmpty() || user.getPrenom() == null
				|| user.getPrenom().trim().isEmpty()
		// ... tous les paramètres sauf l'id
		) {
			throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
		}
		Connection connection = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "UPDATE user SET nom = ?, prenom = ?, email = ?, password = ?,"
					+ " date_naissance = ? WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(requete);
			ps.setString(1, user.getNom());
			ps.setString(2, user.getPrenom());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setDate(5, Dates.convertDateUtilToDateSql(user.getDateNaissance()));
			ps.setInt(6, user.getId());

			ps.executeUpdate();
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public void removeUser(int id) throws Exception {
		if (id <= 0) {
			throw new IllegalArgumentException("L'id doit être > 0 !");
		}
		Connection connection = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "DELETE FROM user WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(requete);
			ps.setInt(1, id);
			ps.executeUpdate();
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public List<User> getUsers() throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "SELECT * FROM user";
			PreparedStatement ps = connection.prepareStatement(requete);
			ps.execute();
			rs = ps.getResultSet();
			if (rs != null) {
				List<User> users = new ArrayList<User>();
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setNom(rs.getString("nom"));
					user.setPrenom(rs.getString("prenom"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setDateNaissance(Dates.convertDateSqlToDateUtil(rs.getDate("date_naissance")));
					users.add(user);
				}
				return users;
			}
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		return null;
	}
	
	@Override
	public User getUserByEmail(String email) throws Exception {
		if (email == null || email.trim().isEmpty()) {
	        throw new IllegalArgumentException("L'email et le mot de passe doivent être renseignés !");
		}
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "SELECT * FROM user WHERE email = ?";
			PreparedStatement ps = connection.prepareStatement(requete);
			ps.setString(1, email);
			ps.execute();
			rs = ps.getResultSet();
			if (rs != null && rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setNom(rs.getString("lastname"));
				user.setPrenom(rs.getString("firstname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setDateNaissance(Dates.convertDateSqlToDateUtil(rs.getDate("birthdate")));
				
				System.out.println("Utilisateur trouvé : " + user.getEmail());
	            return user;  // Retourner l'objet User trouvé
			}else {
	            System.out.println("Aucun utilisateur trouvé pour l'email : " + email);
	            return null;  // Retourner null s'il n'y a pas d'utilisateur trouvé
			}
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
	}
	
	@Override
	public String getPasswordById(int userId) throws Exception {
		if (userId <= 0) {
			throw new IllegalArgumentException("L'id doit être > 0 !");
		}
	    Connection connection = null;
	    ResultSet rs = null;
	    try {
	        connection = UserDataSource.getConnection();
	        String requete = "SELECT password FROM user WHERE id = ?";
	        PreparedStatement ps = connection.prepareStatement(requete);
	        ps.setInt(1, userId);
	        ps.execute();
			rs = ps.getResultSet();
	        if (rs != null && rs.next()) {
	            return rs.getString("password");
	        }
	    } finally {
	        if (connection != null && !connection.isClosed()) {
	            connection.close();
	        }
	        if (rs != null && !rs.isClosed()) {
	            rs.close();
	        }
	    }
	    return null;
	}
	
	@Override
	public User verifyUser(String email, String password) throws Exception {
		if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
	        throw new IllegalArgumentException("L'email et le mot de passe doivent être renseignés !");
		}
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "SELECT * FROM user WHERE email = ?";
			PreparedStatement ps = connection.prepareStatement(requete);
			ps.setString(1, email.trim().toLowerCase());
			ps.execute();
			rs = ps.getResultSet();
			
			if (rs != null && rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setNom(rs.getString("lastname"));
				user.setPrenom(rs.getString("firstname"));
				user.setEmail(rs.getString("email"));
				
				user.setDateNaissance(Dates.convertDateSqlToDateUtil(rs.getDate("birthdate")));
				
				// Vérifier le mot de passe haché
	            String hashedPasswordFromDatabase = rs.getString("password");
				
				// Ajouter des journaux pour déboguer
	            System.out.println("\nUtilisateur trouvé : " + user.getEmail());
	            System.out.println("Mot de passe entré par l'utilisateur : " + password);
	            System.out.println("Mot de passe stocké dans la BDD : " + rs.getString("password"));

	         // Ajouter un journal pour voir la valeur de la comparaison
	            System.out.println("La comparaison de mot de passe donne : " + BCrypt.checkpw(password, hashedPasswordFromDatabase));
	            
				if (BCrypt.checkpw(password, hashedPasswordFromDatabase) || BCrypt.checkpw(password.toLowerCase(), hashedPasswordFromDatabase)) {
					user.setPassword(hashedPasswordFromDatabase);
					return user;
				} else {
		            // Si le mot de passe est incorrect, afficher un message de journal
		            System.out.println("Mot de passe incorrect pour l'utilisateur avec l'email : " + email);
		            return null;
		        }
			} else {
	            System.out.println("Aucun utilisateur trouvé pour l'email : " + email);
	            return null;  // Retourner null s'il n'y a pas d'utilisateur trouvé
			}
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		
	}
	
	@Override
	public void updatePassword(String password, int id) throws Exception {
		if (password == null || password.trim().isEmpty() || id <= 0) {
			throw new IllegalArgumentException("Le mot de passe ne doit pas être NULL et l'id doit être > à 0 !");
		}
		Connection connection = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "UPDATE user SET password = ? WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(requete);
			ps.setString(1, BCrypt.hashpw(password, BCrypt.gensalt()));
			ps.setInt(2, id);

			ps.executeUpdate();
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}
	
	// Méthode pour insérer une nouvelle demande de réinitialisation
	public void insertResetRequest(int userId, String resetToken, Timestamp expirationTime) throws Exception {
	    Connection connection = null;
	    try {
	        connection = UserDataSource.getConnection();
	        String requete = "INSERT INTO password_reset_requests (user_id, reset_token, expiration_time) VALUES (?, ?, ?)";
	        PreparedStatement ps = connection.prepareStatement(requete);
	        ps.setInt(1, userId);
	        ps.setString(2, resetToken);
	        ps.setTimestamp(3, expirationTime);

	        ps.executeUpdate();
	    } finally {
	        if (connection != null && !connection.isClosed()) {
	            connection.close();
	        }
	    }
	}

	// Méthode pour récupérer les détails de la demande de réinitialisation par token
	public ResetRequestDetails getResetRequestDetails(String resetToken) throws Exception {
	    Connection connection = null;
	    ResultSet rs = null;
	    try {
	        connection = UserDataSource.getConnection();
	        String requete = "SELECT * FROM password_reset_requests WHERE reset_token = ?";
	        PreparedStatement ps = connection.prepareStatement(requete);
	        ps.setString(1, resetToken);
	        ps.execute();
	        rs = ps.getResultSet();
	        if (rs != null && rs.next()) {
	            // Retournez les détails de la demande de réinitialisation
	            ResetRequestDetails details = new ResetRequestDetails();
	            details.setUserId(rs.getInt("user_id"));
	            details.setResetToken(rs.getString("reset_token"));
	            details.setExpirationTime(rs.getTimestamp("expiration_time"));
	            return details;
	        }
	    } finally {
	        if (connection != null && !connection.isClosed()) {
	            connection.close();
	        }
	        if (rs != null && !rs.isClosed()) {
	            rs.close();
	        }
	    }
	    return null;
	}

}
