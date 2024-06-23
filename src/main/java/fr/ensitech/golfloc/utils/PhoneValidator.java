package fr.ensitech.golfloc.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("phoneValidator")
public class PhoneValidator implements Validator {

    private static final String REGEX_PATTERN = "^(0|\\+33|0033)[1-9][0-9]{8}";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String input = (String) value;
        if (input != null && !input.isEmpty() && !input.matches(REGEX_PATTERN)) {
            FacesMessage msg = new FacesMessage("Le numéro ne doit pas contenir de caractères spéciaux ou de lettres et doit faire au moins 10 caractères.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}

