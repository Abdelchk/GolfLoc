package fr.ensitech.golfloc.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("numeroValidator")
public class NumeroValidator implements Validator {

    private static final String REGEX_PATTERN = "^[0-9]{1,3}\\s?(b|B|bis|Bis)?$";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String input = (String) value;
        if (input != null && !input.isEmpty() && !input.matches(REGEX_PATTERN)) {
            FacesMessage msg = new FacesMessage("Le numéro de rue ne doit pas contenir de caractères spéciaux.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}

