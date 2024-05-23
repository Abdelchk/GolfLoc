package fr.ensitech.golfloc.model.dao;

import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.ensitech.golfloc.entity.Cart;
import fr.ensitech.golfloc.entity.Item;
import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.model.connection.HibernateConnector;

public class CartDao implements ICartDao {

	@Override
	public Integer addToCart(int userId, int itemId, int quantity) {
		if (userId == 0 && itemId == 0) {
			throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
		}
		
		Session session = null;
        Transaction tx = null;
        try {
            
            session = HibernateConnector.getSession();
            tx = session.beginTransaction();
            
            Cart cart = new Cart();
            User user = new User();
            Item item = new Item();
            
            session.save(cart);
            tx.commit();

        } catch (RollbackException e) {
            tx.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
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
