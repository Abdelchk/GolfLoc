package fr.ensitech.golfloc.model.dao;

import java.util.List;

import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import fr.ensitech.golfloc.entity.Cart;
import fr.ensitech.golfloc.entity.CartId;
import fr.ensitech.golfloc.entity.Commande;
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
	public void removeCartItem(Cart cart) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateConnector.getSession();
			tx = session.beginTransaction();
			session.delete(cart);
			tx.commit();

		} catch (RollbackException e) {
			tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void clearCart(User user) throws Exception {
	    Session session = null;
	    Transaction tx = null;
	    try {
	        session = HibernateConnector.getSession();
	        tx = session.beginTransaction();

	        Query<Cart> query = session.createQuery("FROM cart WHERE id.user = :user", Cart.class);
	        query.setParameter("user", user);

	        List<Cart> cartItems = query.getResultList();
	        for (Cart item : cartItems) {
	            session.delete(item);
	        }

	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	}


	@Override
	public List<Cart> getCart(int id) {
		Session session = null;
        try {
            session = HibernateConnector.getSession();

            Query<Cart> query = session.createNativeQuery("SELECT item.name, item.price, cart.* FROM cart INNER JOIN item on item_id = item.id WHERE user_id = :user_id", Cart.class);
            query.setParameter("user_id", id);
            
			return query.list();
			
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
	}

	@Override
	public void updateItemQuantity(Cart cart) throws Exception {
	    if (cart == null) {
	        throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
	    }
	    
	    Session session = null;
	    Transaction tx = null;
	    try {
	        session = HibernateConnector.getSession();
	        tx = session.beginTransaction();
	        
	        Cart existingCart = (Cart) session.get(Cart.class, cart.getId());
	        if (existingCart != null) {
	            existingCart.setQuantity(cart.getQuantity());
	            session.update(existingCart);
	            tx.commit();
	        } else {
	            throw new IllegalArgumentException("Panier non trouvé pour l'utilisateur et l'item spécifiés !");
	        }

	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        throw new Exception("Erreur lors de la mise à jour de la quantité de l'article dans le panier.", e);
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	}

	@Override
	public Cart getCartItem(CartId id) throws Exception {
	    Session session = null;
	    try {
	        session = HibernateConnector.getSession();

	        // Utilisation de HQL pour récupérer le Cart basé sur la clé composite
	        String hql = "SELECT c FROM cart c JOIN FETCH c.id.item i WHERE c.id = :id";
	        Query<Cart> query = session.createQuery(hql, Cart.class);
	        query.setParameter("id", id);
	        
	        return query.uniqueResult();
	        
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	}

	@Override
    public List<Cart> getCartItemsByUser(int userId) throws Exception {
        Session session = null;
        try {
            session = HibernateConnector.getSession();
            return session.createQuery("FROM cart WHERE user_id = :userId", Cart.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void deleteCartItemsByUser(int userId) throws Exception {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateConnector.getSession();
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM cart WHERE user_id = :userId")
                    .setParameter("userId", userId)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new Exception("Erreur lors de la suppression des articles du panier", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

	@Override
	public void updateCartValide(User user) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateConnector.getSession();
			tx = session.beginTransaction();
			session.createQuery("UPDATE cart SET is_valide = true WHERE user_id = :user").setParameter("user", user)
					.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception("Erreur lors de la mise à jour de la validation du panier", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
	}

	@Override
	public void updateCartCommande(Commande commande) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateConnector.getSession();
			tx = session.beginTransaction();
			session.createQuery("UPDATE cart SET commande_id = :commande WHERE is_valide = 1")
					.setParameter("commande", commande).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception("Erreur lors de la mise à jour de la commande du panier", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		
	}

	@Override
	public List<Cart> getCartItemsByCommande(int commandeId) throws Exception {
		Session session = null;
		try {
			session = HibernateConnector.getSession();
			return session.createQuery("FROM cart WHERE commande_id = :commandeId", Cart.class)
					.setParameter("commandeId", commandeId).getResultList();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}



}
