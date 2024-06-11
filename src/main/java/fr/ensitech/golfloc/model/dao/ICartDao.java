package fr.ensitech.golfloc.model.dao;

import fr.ensitech.golfloc.entity.Cart;
import fr.ensitech.golfloc.entity.Item;
import fr.ensitech.golfloc.entity.User;

public interface ICartDao {

	Integer addToCart(Cart cart) throws Exception;
	void updateItemQuantity(Item item, int quantity) throws Exception;
	void removeCartItem(int userId, int itemId) throws Exception;
	Cart clearCart(User user) throws Exception;
	Cart getCart(int id) throws Exception;
}
