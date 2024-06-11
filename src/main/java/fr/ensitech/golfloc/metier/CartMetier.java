package fr.ensitech.golfloc.metier;

import fr.ensitech.golfloc.entity.Cart;
import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.model.dao.CartDao;

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
		
		public String removeFromCart() {
			return null;
		}
		
		public String clearCart() {
			return null;
		}
		
		public Cart getCart(int id) {
			cartDao = new CartDao();
			
			try {
				return cartDao.getCart(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
}
