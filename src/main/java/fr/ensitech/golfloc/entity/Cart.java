package fr.ensitech.golfloc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "cart")
@XmlRootElement
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CartId id;

    @Column(name = "quantity", nullable = false)
    private int quantity;
    
    @Column(name = "is_valide", nullable = false, columnDefinition = "boolean default false", updatable = true)
    private boolean isValide = false;
    
    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = true)
    private Commande commande;

    public Cart() {}

    public Cart(CartId id, int quantity, boolean isValide) {
        this.id = id;
        this.quantity = quantity;
        this.isValide = isValide;
    }

    public CartId getId() {
        return id;
    }

    public void setId(CartId id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public boolean getIsValide() {
		return isValide;
	}
    
    public void setIsValide(boolean isValide) {
    	this.isValide = isValide;
    }
    
    public void setCommande(Commande commande) {
        this.commande = commande;
    }

	public Commande getCommande() {
		return commande;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", quantity=" + quantity + ", isValide=" + isValide + "]";
	}
}
