package fr.ensitech.golfloc.model.dao;

import fr.ensitech.golfloc.entity.Commande;
import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.model.connection.HibernateConnector;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CommandeDao implements ICommandeDao {

    @Override
    public void save(Commande commande) {
        Transaction transaction = null;
        try (Session session = HibernateConnector.getSession()) {
            transaction = session.beginTransaction();
            session.save(commande);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Commande commande) {
        Transaction transaction = null;
        try (Session session = HibernateConnector.getSession()) {
            transaction = session.beginTransaction();
            session.update(commande);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Commande commande) {
        Transaction transaction = null;
        try (Session session = HibernateConnector.getSession()) {
            transaction = session.beginTransaction();
            session.update(commande);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Commande findById(int id) {
        try (Session session = HibernateConnector.getSession()) {
            return session.get(Commande.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Commande> findByUser(User user) {
        try (Session session = HibernateConnector.getSession()) {
            return session.createQuery("FROM commande WHERE user = :user", Commande.class)
                          .setParameter("user", user)
                          .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

