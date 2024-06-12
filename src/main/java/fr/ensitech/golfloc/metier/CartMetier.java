package fr.ensitech.golfloc.metier;

import java.util.List;

import fr.ensitech.golfloc.entity.Cart;
import fr.ensitech.golfloc.entity.CartId;
import fr.ensitech.golfloc.entity.Item;
import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.model.dao.CartDao;
import fr.ensitech.golfloc.model.dao.ItemDao;

public class CartMetier {
	
	private CartDao cartDao;

	// Méthodes de Gestion
	
		public Integer addToCart(Cart cart) {
			cartDao = new CartDao();
			
			try {
				return cartDao.addToCart(cart);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public void removeFromCart(Cart cart) {
			try {
				cartDao = new CartDao();
				cartDao.removeCartItem(cart);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Une erreur est survenue dans CartMetier.removeFromCart : " + e.getMessage());
			}
		}
		
		public void clearCart(User user) {
			try {
				cartDao = new CartDao();
				cartDao.clearCart(user);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Une erreur est survenue dans CartMetier.clearCart : " + e.getMessage());
			}
		}
		
		public void updateCart(Cart cart) {
			cartDao = new CartDao();

			try {
				cartDao.updateItemQuantity(cart);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Une erreur est survenue dans CartMetier.updateCart : " + e.getMessage());
			}
		}
		
		public List<Cart> getCart(int id) {
			cartDao = new CartDao();
			
			try {
				return cartDao.getCart(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public Cart getCartItem(CartId id) {
			cartDao = new CartDao();

			try {
				return cartDao.getCartItem(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
}
