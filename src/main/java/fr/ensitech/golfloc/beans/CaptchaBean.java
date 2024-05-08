package fr.ensitech.golfloc.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="captchabean")
@RequestScoped
public class CaptchaBean {

    private String userInput;

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public void validerCaptcha() {
        // Votre logique de validation ici
        if ("valeur_attendue".equals(userInput)) {
            // Captcha valide, effectuer les actions nécessaires (soumettre le formulaire, etc.)
            System.out.println("Captcha validé avec succès !");
        } else {
            // Captcha invalide, afficher un message d'erreur
            System.out.println("Captcha incorrect. Veuillez réessayer.");
        }
    }
}

