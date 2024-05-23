package fr.ensitech.golfloc.model.dao;

import fr.ensitech.golfloc.entity.Cart;

public interface ICartDao {

	Integer addToCart(int userId, int itemId, int quantity) throws Exception;
	void removeCartItem(int userId, int itemId) throws Exception;
	Cart clearCart(int userId) throws Exception;
	Cart getCart(int userId) throws Exception;
}
