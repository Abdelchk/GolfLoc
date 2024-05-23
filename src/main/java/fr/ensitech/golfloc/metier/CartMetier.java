package fr.ensitech.golfloc.metier;

import fr.ensitech.golfloc.model.dao.CartDao;

public class CartMetier {
	
	private CartDao cartDao;

	// Méthodes de Gestion
	
		public String addToCart(int userId, int itemId) {
			cartDao = new CartDao();
			
			try {
				cartDao.addToCart(itemId, itemId, 1);
				return "achats.xhtml";
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
		
		public String getCart() {
			return null;
		}
}
