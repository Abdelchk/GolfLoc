package fr.ensitech.golfloc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity(name = "commande")
public class Commande implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "date_commande", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateCommande;
    
    @ManyToOne
    @JoinColumn(name = "carte_de_paiement_id", nullable = true)
    private Payment carteDePaiement;
    
    @Column(name = "total", nullable = false)
    private float total;
    
    @Column(name = "is_paid", nullable = true, columnDefinition = "boolean default false")
    private boolean isPaid;
    
    @Column(name = "is_canceled", nullable = true, columnDefinition = "boolean default false")
    private boolean isCanceled;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cart> cartItems;
    
    public Commande() {}
    
	

    public Commande(int id, User user, Date dateCommande, Payment carteDePaiement, float total, boolean isPaid, boolean isCanceled) {
		this.id = id;
		this.user = user;
		this.dateCommande = dateCommande;
		this.carteDePaiement = carteDePaiement;
		this.total = total;
		this.isPaid = isPaid;
		this.isCanceled = isCanceled;
	}



	// Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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



	public boolean isPaid() {
		return isPaid;
	}



	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	public boolean isCanceled() {
		return isCanceled;
	}

	public void setCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

	public List<Cart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Cart> cartItems) {
        this.cartItems = cartItems;
    }
}
