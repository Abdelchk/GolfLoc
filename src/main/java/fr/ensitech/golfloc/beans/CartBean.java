package fr.ensitech.golfloc.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.ensitech.golfloc.entity.Cart;
import fr.ensitech.golfloc.entity.CartId;
import fr.ensitech.golfloc.entity.Item;
import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.metier.CartMetier;
import fr.ensitech.golfloc.metier.ItemMetier;
import fr.ensitech.golfloc.metier.UserMetier;

@ManagedBean(name = "cartbean")
@SessionScoped
public class CartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;
	private Integer itemId;
	private int quantity;
	private CartMetier cartMetier;
	
	public CartBean() {
		
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	// Méthodes de Gestion
	
	public String addToCart(int userId, int itemId) {
		cartMetier = new CartMetier();
		UserMetier userMetier = new UserMetier();
		ItemMetier itemMetier = new ItemMetier();
		
		try {
			
			if (userId != 0) {
				
				User user = userMetier.getUserById(userId);
				Item item = itemMetier.getItemById(itemId);
				
//				System.out.println(user.getNom() + " " + item.getName());
				
				CartId cartId = new CartId();
		        cartId.setUser(user);
		        cartId.setItem(item);
				
				Cart cart = new Cart();
				cart.setQuantity(1);
				cart.setId(cartId);
				
//				System.out.println(cart);
				
				cartMetier.addToCart(cart);
				
				return "achats.xhtml";
			}
			else {
				return "connexion.xhtml";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String removeFromCart() {
		return null;
	}
	
	public String clearCart() {
		return null;
	}
	
	public Cart getCart() {
		cartMetier = new CartMetier();
		
		try {
			User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			System.out.println("Bonjour");
			return cartMetier.getCart(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
