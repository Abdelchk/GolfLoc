package fr.ensitech.golfloc.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("pwdValidator")
public class PwdValidator implements Validator {

    private static final String REGEX_PATTERN = "^(?=.*[A-Za-zÀ-ÖØ-öø-ÿ])(?=.*\\d)(?=.*[@$!%*?&amp;#])[A-Za-zÀ-ÖØ-öø-ÿ\\d@$!%*?&amp;#]{12,}$";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String input = (String) value;
        if (input != null && !input.isEmpty() && !input.matches(REGEX_PATTERN)) {
            FacesMessage msg = new FacesMessage("Le mot de passe doit obligatoirement contenir 12 caractères (1 majuscule/minuscule et au moins un cractère spécial)");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}

