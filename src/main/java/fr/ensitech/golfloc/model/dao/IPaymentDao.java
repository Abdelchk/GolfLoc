package fr.ensitech.golfloc.model.dao;

import fr.ensitech.golfloc.entity.Payment;
import fr.ensitech.golfloc.entity.User;

import java.util.List;

public interface IPaymentDao {
    void save(Payment payment);
    void update(Payment payment);
    void delete(Payment payment);
    Payment findById(int id);
    Payment findByUser(User user);
    boolean userHasCard(User user);
}

