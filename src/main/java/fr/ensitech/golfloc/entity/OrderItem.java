package fr.ensitech.golfloc.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "order_item")
@XmlRootElement
public class OrderItem implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "cart_user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "cart_item_id", referencedColumnName = "item_id")
    })
    private Cart cart;

    
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}


	public OrderItem(int id, Commande commande, Cart cart) {
		this.id = id;
		this.commande = commande;
		this.cart = cart;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Commande getCommande() {
		return commande;
	}


	public void setCommande(Commande commande) {
		this.commande = commande;
	}


	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}

	
    
}

