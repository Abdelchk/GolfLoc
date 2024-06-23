package fr.ensitech.golfloc.model.dao;

import java.util.List;

import fr.ensitech.golfloc.entity.Cart;
import fr.ensitech.golfloc.entity.CartId;
import fr.ensitech.golfloc.entity.Commande;
import fr.ensitech.golfloc.entity.Item;
import fr.ensitech.golfloc.entity.User;

public interface ICartDao {

	Integer addToCart(Cart cart) throws Exception;
	void updateItemQuantity(Cart cart) throws Exception;
	void updateCartValide(User user) throws Exception;
	void updateCartCommande(Commande commande) throws Exception;
	void removeCartItem(Cart cart) throws Exception;
	void clearCart(User user) throws Exception;
	List<Cart> getCart(int id) throws Exception;
	Cart getCartItem(CartId id) throws Exception;
	List<Cart> getCartItemsByUser(int userId) throws Exception;
	List<Cart> getCartItemsByCommande(int commandeId) throws Exception;
    void deleteCartItemsByUser(int userId) throws Exception;
}
