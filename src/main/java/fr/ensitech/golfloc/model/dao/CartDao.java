package fr.ensitech.golfloc.model.dao;

import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.ensitech.golfloc.entity.Cart;
import fr.ensitech.golfloc.entity.Item;
import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.model.connection.HibernateConnector;

public class CartDao implements ICartDao {

	@Override
	public Integer addToCart(Cart cart) {
		if (cart == null) {
			throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
		}
		
		Session session = null;
        Transaction tx = null;
        try {
            
            session = HibernateConnector.getSession();
            tx = session.beginTransaction();
            
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
	public Cart clearCart(User user) {
		return null;
	}

	@Override
	public Cart getCart(int id) {
		Session session = null;
        try {
            session = HibernateConnector.getSession();

            Query<Cart> query = session.createQuery("SELECT c, c.id.item.nom, c.id.item.prix FROM cart c WHERE c.id.user = :userId", Cart.class);
            query.setParameter("user_id", id);
            
			return query.uniqueResult();
			
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
	}

	@Override
	public void updateItemQuantity(Item item, int quantity) throws Exception {
			
	}

}
