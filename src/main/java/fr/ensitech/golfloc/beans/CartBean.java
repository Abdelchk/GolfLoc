package fr.ensitech.golfloc.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private List<Cart> carts;
	private Cart cart;
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
	public float getTotal() {
		List<Cart> carts = getCart();
        BigDecimal total = BigDecimal.ZERO;
        for (Cart cart : carts) {
            BigDecimal price = BigDecimal.valueOf(cart.getId().getItem().getPrice());
            BigDecimal quantity = BigDecimal.valueOf(cart.getQuantity());
            total = total.add(price.multiply(quantity));
        }
        return total.floatValue();
    }
	
	public String addToCart(int userId, int itemId) {
		cartMetier = new CartMetier();
		UserMetier userMetier = new UserMetier();
		ItemMetier itemMetier = new ItemMetier();
		
		try {
			
			if (userId != 0) {
				
				System.out.println("User ID : " + userId);
				
				User user = userMetier.getUserById(userId);
				Item item = itemMetier.getItemById(itemId);
				
				System.out.println(user.getNom() + " " + item.getName());
				
				CartId cartId = new CartId();
		        cartId.setUser(user);
		        cartId.setItem(item);
				
				Cart cart = new Cart();
				cart.setQuantity(1);
				cart.setId(cartId);
				
//				System.out.println(cart);
				
				cartMetier.addToCart(cart);
				
				return "";
			}
			else {
				return "connexion.xhtml";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String removeFromCart(CartId id) {
		try {
			
			Cart cart = cartMetier.getCartItem(id);
			
            cartMetier.removeFromCart(cart);
            return "cart.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
	public String clearCart() {
		 try {
			 
			 User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");

	            cartMetier.clearCart(user);
	            carts = new ArrayList<>();
	            return "cart.xhtml";
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return null;
	}
	
	public String updateQuantity(CartId id) {
        try {
            // Logic to update the quantity in the database
        	
        	//System.out.println(id);
        	Cart cart = cartMetier.getCartItem(id);
        	
        	System.out.println("Cart : " + cart);
        	
        	System.out.println("Quantité avant modification : " + cart.getQuantity());
        	
        	cart.setQuantity(quantity);
        	
			System.out.println("Quantité entré : " + quantity);
        	System.out.println("Quantité après modification : " + cart.getQuantity());
        	
            cartMetier.updateCart(cart);
            return "cart.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }
	
	public List<Cart> getCart() {
		cartMetier = new CartMetier();
		
		try {
			User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			return cartMetier.getCart(user.getId()).stream().filter(cart -> cart.getIsValide() == false)
                    .collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
