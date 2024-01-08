package fr.ensitech.golfloc.auth;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.UUID;
//test
public class GmailAuthentification {
	
	public static String generateResetToken() {
	    
	    return UUID.randomUUID().toString();
	}
	
public static void sendResetEmail(String userEmail, String resetToken) throws MessagingException {
		
        String username = System.getenv("GMAIL_USERNAME");
	    String password = System.getenv("GMAIL_PASSWORD");
	    
	 // Construire le lien de réinitialisation avec le token
        String resetLink = "http://localhost:8080/GolfLoc/faces/pages/resetPwd.xhtml?token=" + resetToken;
		
        // Configurer les propriétés pour la session JavaMail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.checkserveridentity", "false");
        props.put("mail.smtp.ssl.trust", "*");

        // Créer une session JavaMail avec l'authentification
        Session session = Session.getInstance(props, new Authenticator() {
        	@Override
        	protected PasswordAuthentication getPasswordAuthentication() {

        	    if (username == null || password == null) {
        	        // Gérer le cas où les variables d'environnement ne sont pas définies
        	        throw new RuntimeException("Les variables d'environnement GMAIL_USERNAME et GMAIL_PASSWORD ne sont pas définies.");
        	    }
        	    return new PasswordAuthentication(username, password);
        	}

        });
        
        session.setDebug(true);
        ;
        // Créer un objet Message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
        message.setSubject("Réinitialisation de mot de passe");
        message.setText("Bonjour,\n\nCliquez sur le lien suivant pour réinitialiser votre mot de passe : " + resetLink);

        System.out.println("Envoi du mail...");
        
        // Envoyer le message
        Transport.send(message);
        
        System.out.println("Le mail a été envoyé avec succès !");
    }
}

