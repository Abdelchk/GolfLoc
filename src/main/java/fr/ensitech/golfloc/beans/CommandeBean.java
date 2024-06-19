package fr.ensitech.golfloc.beans;

import fr.ensitech.golfloc.entity.Commande;
import fr.ensitech.golfloc.entity.OrderItem;
import fr.ensitech.golfloc.entity.Payment;
import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.metier.CartMetier;
import fr.ensitech.golfloc.model.dao.CartDao;
import fr.ensitech.golfloc.model.dao.CommandeDao;
import fr.ensitech.golfloc.model.dao.PaymentDao;
import fr.ensitech.golfloc.entity.Cart;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@ManagedBean(name = "commandebean")
@SessionScoped
public class CommandeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private CommandeDao commandeDao;
    private CartDao cartDao;
    private User user;
    private Date dateCommande;
    private Payment carteDePaiement;
    private float total;
    private Commande currentCommande;

	public CommandeBean() {
		
	}
    
    @PostConstruct
    public void init() {
        commandeDao = new CommandeDao();
        cartDao = new CartDao();
    }

    public Commande getCurrentCommande() {
        return currentCommande;
    }

    public void setCurrentCommande(Commande currentCommande) {
        this.currentCommande = currentCommande;
    }

    // Autres getters et setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Payment getCarteDePaiement() {
		return carteDePaiement;
	}

	public void setCarteDePaiement(Payment carteDePaiement) {
		this.carteDePaiement = carteDePaiement;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	public String saveCommande(User user, float total) {
        try {
            // Récupérer les articles du panier pour cet utilisateur
            List<Cart> cartItems = cartDao.getCartItemsByUser(user.getId());
            
            CartMetier cartMetier = new CartMetier();
            
            
            cartItems.forEach(cart -> {
            	cart.setIsValide(true);
            	cartMetier.updateCartValide(user);
            });
            
            System.out.println("user: " + user.getId());

            currentCommande = new Commande();
            
            // Ajouter les articles à la commande
            currentCommande.setUser(user);
            currentCommande.setTotal(total);
            
            currentCommande.setDateCommande(new Date());
            currentCommande.setCartItems(cartItems);

            // Enregistrer la commande
            commandeDao.save(currentCommande);

            // Supprimer les articles du panier
            //cartDao.deleteCartItemsByUser(user.getId());

            return "confirmation.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            
        }
		return null;
    }
    
    public String update() {
        try {
            commandeDao.update(currentCommande);
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            
        }
		return null;
    }

    public String delete(Commande commande) {
        try {
            commandeDao.delete(commande);
            return "orderList.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            
        }
		return null;
    }

    public String findById(int id) {
        try {
            commandeDao.findById(id);
            
            
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
	public String pay(int id) {
		try {
			
			User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			
			currentCommande = commandeDao.findById(id);
            
            CartMetier cartMetier = new CartMetier();
            List<Cart> carts = cartMetier.getCartItemsByUser(user.getId());
            
            carts.forEach(cart -> {
            	cart.setCommande(currentCommande);
            	cartMetier.updateCartCommande(currentCommande);
            });
            
            Payment payment = new Payment();
            PaymentDao paymentDao = new PaymentDao();	
            
            payment = paymentDao.findByUser(user);
            
            currentCommande.setCarteDePaiement(payment);
            
			currentCommande.setPaid(true);
			commandeDao.update(currentCommande);
			return "achats.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    
    public List<Commande> findByUser(User user) {
        try {
            
            return commandeDao.findByUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
