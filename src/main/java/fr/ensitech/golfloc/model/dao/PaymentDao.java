package fr.ensitech.golfloc.model.dao;

import fr.ensitech.golfloc.entity.Payment;
import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.model.connection.HibernateConnector;
import fr.ensitech.golfloc.utils.DESUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PaymentDao implements IPaymentDao {
	@Override
    public void save(Payment payment) {
        Transaction transaction = null;
        try (Session session = HibernateConnector.getSession()) {
            transaction = session.beginTransaction();
            
            String encryptedCardNumber = DESUtil.encrypt(payment.getCardNumber());
            String encryptedCVV = DESUtil.encrypt(payment.getCvv());
            System.out.println("Card Number : " + encryptedCardNumber);
            System.out.println("CVV : " + encryptedCVV);
            payment.setCardNumber(encryptedCardNumber);
            payment.setCvv(encryptedCVV);
            
            session.save(payment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Payment payment) {
        Transaction transaction = null;
        try (Session session = HibernateConnector.getSession()) {
            transaction = session.beginTransaction();
            session.update(payment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Payment payment) {
        Transaction transaction = null;
        try (Session session = HibernateConnector.getSession()) {
            transaction = session.beginTransaction();
            session.delete(payment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Payment findById(int id) {
        try (Session session = HibernateConnector.getSession()) {
            return session.get(Payment.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Payment findByUser(User user) {
    	Session session = null;
        try {
            session = HibernateConnector.getSession();
            Query<Payment> query = session.createQuery("FROM Payment WHERE user = :user", Payment.class);
            query.setParameter("user", user);
            Payment payment = query.getSingleResult();
            
//            System.out.println("Carte trouvé : " + payment.getCardNumber());
            
//            if (payment != null) {
//                String decryptedCardNumber = DESUtil.decrypt(payment.getCardNumber());
//                payment.setCardNumber(decryptedCardNumber);
//            }
            
            return payment;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean userHasCard(User user) {
        try (Session session = HibernateConnector.getSession()) {
            Long count = session.createQuery("SELECT COUNT(p) FROM Payment p WHERE p.user = :user", Long.class)
                                .setParameter("user", user)
                                .uniqueResult();
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
