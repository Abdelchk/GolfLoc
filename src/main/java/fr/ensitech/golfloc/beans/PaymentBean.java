package fr.ensitech.golfloc.beans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.ensitech.golfloc.entity.Commande;
import fr.ensitech.golfloc.entity.Payment;
import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.model.dao.CommandeDao;
import fr.ensitech.golfloc.model.dao.PaymentDao;

@ManagedBean(name = "paymentbean")
@SessionScoped
public class PaymentBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cardNumber;
    private String cardHolderName;
    private Date expiryDate;
    private String cvv;
    private boolean userHasCard;
    private PaymentDao paymentDao;

    // Constructor, getters, and setters

    public PaymentBean() {
    }

    public boolean isUserHasCard() {
        return userHasCard;
    }

    public void setUserHasCard(boolean userHasCard) {
        this.userHasCard = userHasCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String saveCard(User user) {
    	
    	try {
    	
    	paymentDao = new PaymentDao();
    	CommandeDao commandeDao = new CommandeDao();
    	
    	Payment payment = new Payment();
    	Commande commande = new Commande();
    	commandeDao.findByUser(user);
            payment.setUser(user);
            payment.setCardHolderName(cardHolderName);
            payment.setCardNumber(cardNumber);
            payment.setExpiryDate(expiryDate);
            payment.setCvv(cvv);
//            commande.setCarteDePaiement(payment);
//            commandeDao.update(commande);
            paymentDao.save(payment);
            
            return "";
            
            } catch (Exception e) {
				e.printStackTrace();
            }
		return null;
            
        
    }
    
	public String deleteCard(User user) {
    	try {
    		paymentDao = new PaymentDao();
    		Payment payment = paymentDao.findByUser(user);
    		paymentDao.delete(payment);
    		return "";
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		return null;
    	}
    
	public Payment findByUser(User user) {
		try {
			paymentDao = new PaymentDao();
			Payment payment = paymentDao.findByUser(user);
			
			return payment;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getMaskedCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "";
        }
        int length = cardNumber.length();
        return "**** **** **** " + cardNumber.substring(length - 4);
    }
}

