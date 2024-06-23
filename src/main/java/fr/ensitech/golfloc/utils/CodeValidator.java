package fr.ensitech.golfloc.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("codeValidator")
public class CodeValidator implements Validator {

    private static final String REGEX_PATTERN = "^\\d{2}[0-9ABab]{3}$|^\\d{5}$";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String input = (String) value;
        if (input != null && !input.isEmpty() && !input.matches(REGEX_PATTERN)) {
            FacesMessage msg = new FacesMessage("Le code postal doit faire minimum 5 chiffres et ne doit pas contenir de caractères spéciaux.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}

