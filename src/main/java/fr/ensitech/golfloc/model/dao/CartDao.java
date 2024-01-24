package fr.ensitech.golfloc.model.dao;

import fr.ensitech.golfloc.entity.Cart;

public class CartDao implements ICartDao {

	@Override
	public Integer addCartItem(int userId, int itemId, int quantity) {
		return null;
	}

	@Override
	public void removeCartItem(int userId, int itemId) {
		
	}

	@Override
	public Cart clearCart(int userId) {
		return null;
	}

	@Override
	public Cart getCart(int userId) {
		return null;
	}

}
