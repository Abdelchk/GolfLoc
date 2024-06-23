package fr.ensitech.golfloc.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("priceValidator")
public class PriceValidator implements Validator {

    private static final String REGEX_PATTERN = "^[\\d]+[.,]?[\\d]*";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String input = (String) value;
        if (input != null && !input.isEmpty() && !input.matches(REGEX_PATTERN)) {
            FacesMessage msg = new FacesMessage("le prix ne doit contenir que des chiffres.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}

